package com.gfs.operatecenter.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.common.util.StringUtil;
import com.gfs.operatecenter.constant.OperationCenterConstant;
import com.gfs.operatecenter.dao.HomeModule.HomeModuleMapper;
import com.gfs.operatecenter.entity.table.HomeModule;
import com.gfs.operatecenter.entity.vo.banner.BannerMainVo;
import com.gfs.operatecenter.entity.vo.dictdata.DictDataVoResponse;
import com.gfs.operatecenter.entity.vo.feign.AppVideoVoResponse;
import com.gfs.operatecenter.entity.vo.feign.H5ArticleBookPageVo;
import com.gfs.operatecenter.entity.vo.feign.IsVipVo;
import com.gfs.operatecenter.entity.vo.feign.StudentInfoResponseVo;
import com.gfs.operatecenter.entity.vo.homemodule.*;
import com.gfs.operatecenter.feign.client.ContentFeignClient;
import com.gfs.operatecenter.feign.client.MallFeignClient;
import com.gfs.operatecenter.feign.client.UserFeignClient;
import com.gfs.operatecenter.feign.client.StudyFeignClient;
import com.gfs.operatecenter.service.BannerService;
import com.gfs.operatecenter.service.DictDataService;
import com.gfs.operatecenter.service.HomeManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service.impl
 * @ClassName HomeManageServiceImpl
 * @description  首页模块管理
 * @date created in 2020-05-13 14:47
 * @modified by
 */
@Slf4j
@Service
@Transactional
public class HomeManageServiceImpl  extends ServiceImpl<HomeModuleMapper, HomeModule> implements HomeManageService{
    @Autowired
    private HomeModuleMapper homeModuleMapper;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private DictDataService dictDataService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private StudyFeignClient studyFeignClient;
    @Autowired
    private MallFeignClient mallFeignClient;
    @Autowired
    private ContentFeignClient contentFeignClient;


    /**
     * 查询pc端首页模块
     * @param request
     * @return
     */
    @Override
    public HomePageVoResponse queryHomepageForPc(HomePageVoRequest request) {
        HomePageVoResponse result = new HomePageVoResponse();
//       TokenBean tokenBean = RequestUtil.getTokenBean();
        // 轮播图  channelType：8  pc端首页轮播图
        BannerMainVo banners = bannerService.queryBannerMainForPc("8");
        result.setBannerList(banners.getBanners());
//        if (isLogin) {
//            //判断是否存在手机号码的redis
//            if (!redisTemplate.hasKey(RedisKeyConstant.BANNER_POPUP_KEY + LocalRequestContextHolder.getTokenBean().getPhone())) {
//                // banner弹框
//                BannerVo bannerMalls = new BannerVo();
//                //7代表app弹窗广告
//                bannerMalls.setChannelType("7");
//                BannerMainVo bannerMain = BannerService.queryBannerMainForApp(bannerMalls);
//                result.setBannerMalls(bannerMain.getBannerMalls());
//
//                //有弹窗广告， 才设为缓存，一天弹一次。
//                if(bannerMain.getBannerMalls() != null) {
//                    //换算剩余小时（24-当前小时）
//                    Calendar cal = Calendar.getInstance();
//                    int h = cal.get(Calendar.HOUR_OF_DAY);
//                    //设置redis
//                    redisTemplate.opsForValue().set(RedisKeyConstant.BANNER_POPUP_KEY + LocalRequestContextHolder.getTokenBean().getPhone(), "1", 24 - h, TimeUnit.HOURS);
//                }
//            }
//        }
        // 处理渠道信息
        handleRequest(request);
        // pc端模块
        List<HomeModuleVo> pcModuleList = homeModuleMapper.queryPcModuleByRequest(request);
        // 匹配不到则取相应年级默认配置
        if (pcModuleList.size() == 0) {
            // 取小学初中默认配置
            handleGrade(request);
            pcModuleList = homeModuleMapper.queryPcModuleByRequest(request);
        }
        result.setPcModuleList(pcModuleList);
        //pc端左侧栏目模块
        List<HomeModuleVo> leftPcModuleList = homeModuleMapper.queryLeftPcModuleByRequest(request);
        // 匹配不到则取相应年级默认配置
        if (leftPcModuleList.size() == 0) {
            // 取小学初中默认配置
            handleGrade(request);
            leftPcModuleList = homeModuleMapper.queryLeftPcModuleByRequest(request);
        }
        result.setLeftPcModuleList(leftPcModuleList);

        // 学生信息
        StudentInfoResponseVo studentInfo = userFeignClient.info().getData();
        if (!StringUtils.isEmpty(studentInfo)) {
            result.setStudentInfo(studentInfo);
            // 额外信息 - 座右铭
            HomePageExtendVo extra = new HomePageExtendVo();
            extra.setMotto(getMotto(studentInfo));
            // 额外信息 - 是否有未完成的作业
            Integer hasUnDoneTask = hasUnDoneTask(studentInfo);
            extra.setHasUnDoneTask(hasUnDoneTask);
            //查询是否有完成的作业
            Boolean hasTaskRecord = hasTaskRecord(studentInfo);
            if (hasUnDoneTask > 0 || true == hasTaskRecord) {
                //有未完成的作业  或是 有已完成的作业  就显示图标
                extra.setShowTaskImg(true);
            } else {
                extra.setShowTaskImg(false);
            }
            // 额外信息 - SVIP、VIP、体验套餐、普通用户
            extra.setUseState(getUseState(studentInfo));
            //extra.setTempBookName(tempBookName);
            result.setExtraInfo(extra);
        }
        return result;
    }

    /**
     * 查询pc端趣味内容首页模块
     * @param request
     * @return
     */
    @Override
    public FunPcHomePageVoResponse queryFunHomepageForPc(HomePageVoRequest request) {
        FunPcHomePageVoResponse result = new FunPcHomePageVoResponse();
        // 轮播图  channelType：9  pc端趣味内容首页轮播图
        BannerMainVo banners = bannerService.queryBannerMainForPc("9");
        result.setBannerList(banners.getBanners());
        handleRequest(request);
        // pc端趣味内容首页模块
        List<HomeModuleVo> funPcModuleList = homeModuleMapper.queryFunPcModuleByRequest(request);
        // 匹配不到则取相应年级默认配置
        if (funPcModuleList.size() == 0) {
            // 取小学初中默认配置
            handleGrade(request);
            funPcModuleList = homeModuleMapper.queryFunPcModuleByRequest(request);
        }
        result.setFunPcModuleList(funPcModuleList);
        //获取推荐篇章
        List<H5ArticleBookPageVo> articleBookPageVosList = contentFeignClient.articleBookRelevantRecommend().getData();
        result.setArticleList(articleBookPageVosList);
        //获取推荐配音视频
        List<AppVideoVoResponse> dubbingList = contentFeignClient.dubbingRelevantRecommend().getData();
        result.setDubbingList(dubbingList);
        return result;
    }

    private void handleRequest(HomePageVoRequest request) {
        // 首页教材、年级、地区有为空的则优先使用token
        if (request.getBookId() == null || StringUtils.isEmpty(request.getGrade()) || StringUtils.isEmpty(request.getRegion())) {
                StudentInfoResponseVo info = userFeignClient.info().getData();
                request.setBookId(info.getBookId() == null ? 0 : info.getBookId());
                request.setGrade(info.getGradeNum() == null ? "0" : info.getGradeNum());
                request.setRegion(info.getRegion() == null ? "0" : handleRegion(info.getRegion()));
        }
    }


    // 处理小学|初中默认配置参数
    private void handleGrade(HomePageVoRequest request) {
        // grade为空字符串处理
        if ("".equals(request.getGrade().trim())) {
            request.setGrade("0");
        }
        request.setRegion("0");
        request.setBookId(0L);
        // 不是默认配置的
        if (!"0".equals(request.getGrade())) {
            int grade = Integer.valueOf(request.getGrade()).intValue();
            // 1-小学默认配置 2-初中默认配置   10-高中默认配置
            request.setGrade(grade <= 6 ? "1" : (grade <= 9 ? "2" :"10") );
        }
    }

    /**
     * 根据登录状态、学校类型取motto
     *
     * @param studentInfo
     * @return
     */
    private String getMotto(StudentInfoResponseVo studentInfo) {
        // 默认
        String hMotto = "高分说 · 周报说";
        String mMotto = "高分说 · 拿高分";
        // 字典值
        List<DictDataVoResponse> dictDataList= dictDataService.queryByGroupId(OperationCenterConstant.DICTGROUP_APP_HOME_MOTTO);
        if (CollectionUtils.isNotEmpty(dictDataList)) {
            hMotto = dictDataList.stream()
                    .filter(dictData -> OperationCenterConstant.DICT_WEEKLY_SPECK.equals(dictData.getDictId()))
                    .map(DictDataVoResponse::getDictVal).findFirst().orElse(hMotto);
            mMotto = dictDataList.stream()
                    .filter(dictData -> OperationCenterConstant.DICT_GFS.equals(dictData.getDictId()))
                    .map(DictDataVoResponse::getDictVal).findFirst().orElse(mMotto);
        }
        // 登录状态 - 未登录|学校类型不是高中
        return null == studentInfo || null == studentInfo.getSchoolType() || OperationCenterConstant.SCHOOLTYPE_SENIOR != studentInfo.getSchoolType() ? mMotto : hMotto;
    }



    private String handleRegion(String region) {
        if (!StringUtils.isEmpty(region) && region.length() == 6) {
            return region.substring(0, region.length() - 2) + "00";
        } else {
            return region;
        }
    }

    /**
     * 当前学生是否有未完成的作业
     *
     * @param studentInfo
     * @return
     */
    private Integer hasUnDoneTask(StudentInfoResponseVo studentInfo) {
        if (null == studentInfo) {
            return OperationCenterConstant.TASK_UNDEFIAL;
        }
        // 查询当前用户是否有未完成的作业
        Integer count =  studyFeignClient.queryNotDoneCount(studentInfo.getStudentId()).getData();
        return count != null && count > 0 ? OperationCenterConstant.TASK_UNDONE : OperationCenterConstant.TASK_DONE;
    }

    /**
     * 查询是否有 完成的作业
     * @param studentInfo
     * @return
     */
    private Boolean hasTaskRecord(StudentInfoResponseVo studentInfo) {
        Boolean res = false;
        if (null == studentInfo) {
            res = false;
        } else {
            //查询学生完成作业份数
            Integer taskCount = studyFeignClient.queryTaskCount(studentInfo.getStudentId()).getData();
            if (taskCount > 0) {
                res = true;
            }
        }
        return res;
    }


    /**
     * 套餐标识
     *
     * @param studentInfo
     * @return
     */
    private Integer getUseState(StudentInfoResponseVo studentInfo) {
        if (null == studentInfo || null == studentInfo.getUserId()) {
            return OperationCenterConstant.APP_HOME_USESTATE_NORMAL;
        }
//   根据isVip返回 购买状态(0:普通 1:体验劵 2:VIP 3:SVIP)
        String isVip = "";
        ResponseMsg<IsVipVo> responseMsg= mallFeignClient.queryVipDetails(studentInfo.getUserId());
        if (!StringUtil.isEmpty(responseMsg.getData().getIsVipCode())) {
            isVip=responseMsg.getData().getIsVipCode();
        }
        switch (isVip) {
            case OperationCenterConstant.SVIP:
                return OperationCenterConstant.APP_HOME_USESTATE_SVIP;
            case OperationCenterConstant.VIP:
                return OperationCenterConstant.APP_HOME_USESTATE_VIP;
            case OperationCenterConstant.NOT_VIP:
                // 当前用户是否有体验中的套餐
                boolean flag = existExperience(studentInfo);
                return flag ? OperationCenterConstant.APP_HOME_USESTATE_EXPERIENCE : OperationCenterConstant.APP_HOME_USESTATE_NORMAL;
            default:
                return OperationCenterConstant.APP_HOME_USESTATE_NORMAL;
        }
    }

    /**
     * 当前学生是否有体验中的套餐
     *
     * @param studentInfo
     * @return
     */
    private boolean existExperience(StudentInfoResponseVo studentInfo) {
        Integer count =  mallFeignClient.countExperienceByUserId(studentInfo.getUserId()).getData();
        return count != null && count.intValue() > 0;

    }

}
