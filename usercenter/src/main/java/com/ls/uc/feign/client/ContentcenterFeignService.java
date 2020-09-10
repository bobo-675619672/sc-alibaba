package com.ls.uc.feign.client;

import com.dw.sc.common.bean.ResponseMsg;
import com.ls.uc.feign.fallback.ContentcenterFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "contentcenter",
        fallbackFactory = ContentcenterFeignFallbackFactory.class
)
public interface ContentcenterFeignService {

    @GetMapping("/test/query")
    ResponseMsg<String> query();

}
