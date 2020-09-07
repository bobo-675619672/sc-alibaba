package com.gfs.operatecenter.aspect;

import com.alibaba.fastjson.JSON;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.common.bean.TokenBean;
import com.gfs.common.constant.TokenConstant;
import com.gfs.operatecenter.config.WhitePathConfig;
import com.gfs.operatecenter.entity.vo.dictdata.DictDataVoResponse;
import com.gfs.operatecenter.entity.vo.dictdata.InterfaceRecordVo;
import com.gfs.operatecenter.entity.vo.dictdata.PointsRecordVo;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import com.gfs.operatecenter.feign.client.UserFeignClient;
import com.gfs.operatecenter.service.DictDataService;
import com.gfs.operatecenter.service.RecordLogService;
import com.gfs.operatecenter.util.JsonUtils;
import com.gfs.operatecenter.util.RequestUtil;
import com.gfs.operatecenter.util.TokenUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 针对所有Controller类的切片
 * @author liaodewen
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class ControllerAspect {

    @Autowired
    private WhitePathConfig whitePathConfig;
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 记录起始时间，用于计算执行花费的时间
     */
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    public static final String REQ_KEY_REQDATA = "reqData";

    public static final String REQ_KEY_AIPNAME = "apiName";

    public static final String OPENID = "openid";

    @Autowired
    DictDataService dictDataService;

    @Autowired
    RecordLogService recordLogService;


    /**
     * 面向controller类的public函数的切片
     */
    @Pointcut("execution(public * com.gfs.operatecenter.controller..*(..))")
    public void controllerExecution() {
    }

    @Before("controllerExecution()")
    public void doBeforeControllerExecution(JoinPoint joinPoint) {
        log.debug("接口调用鉴权处理...");

        //日志处理
        this.startTime.set(System.currentTimeMillis());
        this.startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return;
        }
        RequestUtil.setContext(attributes.getRequest(),attributes.getResponse());
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        request.setAttribute(REQ_KEY_AIPNAME, joinPoint.getSignature().toShortString());
        // 请求数据
        String reqData = "";
        if (HttpMethod.GET.matches(request.getMethod())) { // GET
            if (StringUtils.isNotEmpty(request.getQueryString())) {// 有入参
                reqData = request.getQueryString();
            }
        } else if (HttpMethod.POST.matches(request.getMethod())) { // POST
            if (null != joinPoint.getArgs() && joinPoint.getArgs().length > 0) { // 有入参
                reqData = JSON.toJSONString(joinPoint.getArgs()[0]);
            } else { // 无参
                reqData = JSON.toJSONString(Maps.newHashMap());
            }
        }
        request.setAttribute(REQ_KEY_REQDATA, reqData);

        // 白名单判断
        String url = request.getRequestURI();
        log.debug("白名单判断 url:{}", url);
        if(isWhite(url)){
            log.debug("通过白名单,允许匿名访问!");
            return;
        } else {
            log.debug("不通过白名单,需要校验token!");
            // 校验token
            ResponseMsg<String> responseMsg = userFeignClient.checkToken();
            if ("success".equals(responseMsg.getData())) {
                log.debug("token通过!");
            } else {
                throw new GfsRuntimeException(responseMsg.getHead().getResultCode(), responseMsg.getHead().getResultMsg());
            }
        }
    }

    /**
     * 白名单判断逻辑
     * @param url
     * @return
     */
    private boolean isWhite(String url) {
        List<String> whiteList = whitePathConfig.getWhite();
        // 白名单不为空
        if (!whiteList.isEmpty()) {
            for (String rule : whiteList) {
                if ((rule.endsWith("**")) && (url.indexOf(rule.substring(0, rule.lastIndexOf("**"))) == 0)) {
                    return true;
                }
                if (rule.equals(url)) {
                    return true;
                }
            }
        }
        return false;
    }


    @AfterReturning(returning = "ret", pointcut = "controllerExecution()")
    public void doAfterReturningControllerExecution(Object ret) {
        logAPI(ret);
        startTime.remove();
        RequestUtil.removeHttpServletRequest();
    }

    //出入参，进入错误日志 ， 以及埋点表
    private void logAPI(Object ret) {
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        if (null == request) {
            return;
        }
        // 正常返回JSON
        String result = JsonUtils.beanToJson(ret);

        if (result.length() > 2000) {
            result = result.substring(0, 2000) + "...";
        }
        String target = request.getAttribute(REQ_KEY_AIPNAME).toString();

        String reqData = request.getAttribute(REQ_KEY_REQDATA).toString();
        String uri = request.getRequestURI();

        String token = request.getHeader(TokenConstant.TOKEN_NAME);
        String openId = request.getHeader(OPENID);
        // 结束时间
        long currentTimeMillis = System.currentTimeMillis();
        Date dateMillis = new Date(currentTimeMillis);
        // 开始时间
        Long startLong = startTime.get();
        Date date = new Date(startLong);
        // 总时长
        double costTime = (currentTimeMillis - startLong) / 1000.0;

        /**************************** 打印日志了**************************/
        log.info("Response Message: { Target: {}, Method: {}, Path: {}, token: {}, reqData: {}, openId: {}, Time: {}s,  return: {} }",
                target, request.getMethod(), uri, token, reqData, openId, costTime, result);

        //记录日志数据
        try {
            dealRecord(result, target, reqData, uri, token, dateMillis, date, costTime);
        } catch (Exception e) {
            log.info("记录操作日志失败:{}",e.getMessage());
        }
    }

    //记录日志数据
    private void dealRecord(String result, String target, String reqData, String uri, String token, Date dateMillis, Date date, double costTime) {

        TokenBean tokenBean = null;
        try {
            TokenUtil.getTokenBean(token);
        } catch (Exception e) {
           // 不作处理继续
        }
        //添加记录用户访问指定路径的卖点 (***如果不使用，需要把字典对应的路径值清空(在web操作)***)
        List<DictDataVoResponse> dataList = getDictDatas("POINTS_RECORD");
        dataList.forEach(dict -> {
            if (uri.equals(dict.getDictVal().trim())) {
                getPointsRecord(result, target, reqData, uri, dateMillis, date, costTime, tokenBean, dict.getDictName());
            }
        });

        //失败 直接添加日志
        String status = "Fail";
        if(result.contains("Success") && result.contains("00") && result.contains("成功")){
            status ="Success";
        }

        double setTime = 3l;
        // 成功则查询 字典配置的时间
        if("Success".equals(status)){
            DictDataVoResponse dictData = getDictData("second", "REQUEST_DURATION_RECORD");
            if(dictData != null){
                setTime = Double.parseDouble(dictData.getDictVal().trim());
            }
        }
        // 失败 、超时（字典配置的最大数）的记录日志表
        if ("Fail".equals(status) || setTime < costTime) {
            getInterfaceRecord(result, target, reqData, uri, dateMillis, date, costTime, tokenBean, status);
        }
    }

    private void getInterfaceRecord(String result, String target, String reqData, String uri, Date dateMillis, Date date, double costTime, TokenBean tokenBean, String remark) {
        InterfaceRecordVo pojo = new InterfaceRecordVo();
        pojo.setTarget(target);
        pojo.setMethod(RequestUtil.getHttpServletRequest().getMethod());
        pojo.setPath(uri);
        pojo.setTime(costTime);
        pojo.setReqdata(JsonUtils.beanToJson(reqData));
        pojo.setReturndata(result);
        pojo.setStartTime(date);
        pojo.setEndTime(dateMillis);
        pojo.setRemark(remark);
        if (tokenBean != null) {
            pojo.setUserId(tokenBean.getUserId());
            pojo.setTypeId(tokenBean.getTypeId());
            pojo.setPhone(tokenBean.getPhone());
        }
        recordLogService.addInterfaceRecordLog(pojo);
    }

    private void getPointsRecord(String result, String target, String reqData, String uri, Date dateMillis, Date date, double costTime, TokenBean tokenBean, String remark) {
        PointsRecordVo pojo = new PointsRecordVo();
        pojo.setTarget(target);
        pojo.setMethod(RequestUtil.getHttpServletRequest().getMethod());
        pojo.setPath(uri);
        pojo.setTime(costTime);
        pojo.setReqdata(JsonUtils.beanToJson(reqData));
        pojo.setReturndata(result);
        pojo.setStartTime(date);
        pojo.setEndTime(dateMillis);
        pojo.setRemark(remark);
        if (tokenBean != null) {
            pojo.setUserId(tokenBean.getUserId());
            pojo.setTypeId(tokenBean.getTypeId());
            pojo.setPhone(tokenBean.getPhone());
        }
        recordLogService.addPointsRecordLog(pojo);
    }

    //查询组， 字典项
    private List<DictDataVoResponse> getDictDatas(String groupId) {
        return dictDataService.queryByGroupId(groupId);
    }

    //查询字典项
    private DictDataVoResponse getDictData(String dictId,String groupId) {
        return dictDataService.queryDictData(groupId,dictId);
    }


}
