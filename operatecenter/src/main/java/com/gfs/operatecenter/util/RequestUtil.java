package com.gfs.operatecenter.util;

import com.gfs.common.bean.TokenBean;
import com.gfs.common.constant.TokenConstant;
import com.gfs.common.enums.ResultEnum;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求工具类
 * @author liaodewen
 */
@Slf4j
@NoArgsConstructor
public class RequestUtil {

    /**
     * 记录HttpServletRequest，用于记录请求信息
     */
    private final static ThreadLocal<RequestContext> CONTEXTS = new ThreadLocal<>();

    /**
     * 获取当前请求
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        try {
            return CONTEXTS.get().getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前响应
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        try {
            return CONTEXTS.get().getResponse();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 清空请求/响应内容
     * @return
     */
    public static void removeHttpServletRequest(){
        try {
            CONTEXTS.remove();
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }

    public static TokenBean getTokenBean() {
        return TokenUtil.getTokenBean(getToken());
    }

    public static TokenBean getTokenBean(String userType) {
        TokenBean tokenBean = TokenUtil.getTokenBean(getToken());
        if (!tokenBean.getUserType().equals(userType)) {
            throw new GfsRuntimeException(ResultEnum.UC_TOKEN_USERTYPE_ERROR);
        }
        return tokenBean;
    }

    public static String getToken() {
        String token = getHttpServletRequest().getHeader(TokenConstant.TOKEN_NAME);
        if (token == null) {
            throw new GfsRuntimeException(ResultEnum.TOKEN_EMPTY);
        }
        return token;
    }

    public static void setContext(HttpServletRequest request, HttpServletResponse response) {
        RequestContext rc = new RequestContext();
        rc.request = request;
        rc.response = response;

        CONTEXTS.set(rc);
    }

}
