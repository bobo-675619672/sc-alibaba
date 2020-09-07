package com.gfs.operatecenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhangjie
 * @ProjectName myProject
 * @Package com.gfs.operatecenter.controller
 * @ClassName TestController
 * @description
 * @date created in 2020-09-07 11:27
 * @modified by
 */
@Api(tags = "测试请求")
@RestController
@RequestMapping("/operate")
public class TestController {

    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public String query(@RequestParam Long id) {

        return "your Id is : " + id;
    }
}
