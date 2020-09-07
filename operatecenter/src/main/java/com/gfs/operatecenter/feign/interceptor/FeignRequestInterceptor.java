package com.gfs.operatecenter.feign.interceptor;

import com.gfs.common.constant.TokenConstant;
import com.gfs.common.util.StringUtil;
import com.gfs.operatecenter.util.RequestUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * feign请求拦截器
 * @author liaodewen
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        log.info("所有feign请求都经过这里...");
        HttpServletRequest httpServletRequest = RequestUtil.getHttpServletRequest();
        if (httpServletRequest != null) {
            String token = httpServletRequest.getHeader(TokenConstant.TOKEN_NAME);
            // 原来请求带token
            if (!StringUtil.isEmpty(token)) {
                template.header(TokenConstant.TOKEN_NAME, token);
                log.info("请求添加Token:{}", token);
            }
        }
    }

}
