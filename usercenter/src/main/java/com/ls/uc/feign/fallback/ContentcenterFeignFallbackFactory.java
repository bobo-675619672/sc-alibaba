package com.ls.uc.feign.fallback;

import com.dw.sc.common.enums.ResultEnum;
import com.dw.sc.common.exception.BusiException;
import com.ls.uc.feign.client.ContentcenterFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContentcenterFeignFallbackFactory implements FallbackFactory<ContentcenterFeignService> {

    @Override
    public ContentcenterFeignService create(Throwable throwable) {
        throw new BusiException(ResultEnum.PARAM_ERROR, throwable, "内容中心");
    }

}
