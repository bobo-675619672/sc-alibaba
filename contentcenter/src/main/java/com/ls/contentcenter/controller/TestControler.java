package com.ls.contentcenter.controller;

import com.dw.sc.common.bean.BaseController;
import com.dw.sc.common.bean.ResponseMsg;
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
@RequestMapping("/test")
public class TestControler extends BaseController {

    @ApiOperation(value = "测试")
    @GetMapping("/query")
    public ResponseMsg<String> query() {
        return success("yes");
    }

}
