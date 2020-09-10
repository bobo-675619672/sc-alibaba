package com.ls.uc.feign.fallback;

import com.ls.uc.feign.client.ContentcenterFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContentcenterFeignFallbackFactory implements FallbackFactory<ContentcenterFeignService> {

    @Override
    public ContentcenterFeignService create(Throwable throwable) {
        log.warn("内容中心连接出错...");
        return null;
    }

}
