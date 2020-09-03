package com.ls.contentcenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("内容中心")
@RestController
@RequestMapping("/contentcenter")
public class TestControler {
    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public String query() {
        return "yes";
    }
}
