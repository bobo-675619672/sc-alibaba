package com.ls.uc.feign.fallback;

import com.dw.sc.common.enums.ResultEnum;
import com.dw.sc.common.exception.BusiException;
import com.ls.uc.feign.client.OperatecenterFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OperatecenterFeignFallbackFactory implements FallbackFactory<OperatecenterFeignService> {

    @Override
    public OperatecenterFeignService create(Throwable throwable) {
        throw new BusiException(ResultEnum.PARAM_ERROR, throwable, "运营中心");
    }

}
