package com.gfs.operatecenter.feign.client;

import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.entity.vo.feign.StudentInfoResponseVo;
import com.gfs.operatecenter.feign.fallback.UserFeignClientFallbackFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "usercenter", fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {

    @ApiOperation(value = "学生信息", notes = "查询当前学生信息")
    @GetMapping("/pc/student/info")
    ResponseMsg<StudentInfoResponseVo> info();

    @GetMapping(value = "/pc/token/check")
    ResponseMsg<String> checkToken();

}
