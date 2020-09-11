package com.ls.uc.feign.client;

import com.dw.sc.common.bean.ResponseMsg;
import com.ls.uc.feign.fallback.OperatecenterFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "operatecenter",
        fallbackFactory = OperatecenterFeignFallbackFactory.class
)
public interface OperatecenterFeignService {

    @GetMapping("/test/query")
    ResponseMsg<String> query();

}
