package com.ls.uc.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

/**
 * feign请求拦截器
 * @author liaodewen
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        log.info("所有feign请求都经过这里...");
        // 打印相关信息
        String method = template.method();
        String url = template.url();
        String variables = String.join(",", template.variables());

        log.info("method:{}, url:{}, variables:{}", method, url, variables);
    }

}
