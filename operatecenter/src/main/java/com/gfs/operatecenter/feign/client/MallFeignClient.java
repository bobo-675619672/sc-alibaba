package com.gfs.operatecenter.feign.client;

import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.entity.vo.feign.IsVipVo;
import com.gfs.operatecenter.feign.fallback.MallFeignClientFallbackFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mall", fallbackFactory = MallFeignClientFallbackFactory.class)
public interface MallFeignClient {



    @ApiOperation(value = "查询用户是否处于体验套餐中")
    @GetMapping("pc/order/userService/countExperienceByUserId")
    ResponseMsg<Integer> countExperienceByUserId(@RequestParam("userId") Long userId);

    @ApiOperation(value = "查询用户是否vip")
    @GetMapping("pc/order/userService/queryVipDetails")
    ResponseMsg<IsVipVo> queryVipDetails(@RequestParam("userId") Long userId);



}
