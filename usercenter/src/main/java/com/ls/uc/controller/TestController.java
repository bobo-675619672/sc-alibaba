package com.ls.uc.controller;

import com.dw.sc.common.bean.BaseController;
import com.dw.sc.common.bean.ResponseMsg;
import com.dw.sc.common.enums.ResultEnum;
import com.dw.sc.common.exception.BusiException;
import com.dw.starter.service.SmsService;
import com.ls.uc.entity.pojo.TestRequestVo;
import com.ls.uc.feign.client.ContentcenterFeignService;
import com.ls.uc.feign.client.OperatecenterFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api( "测试接口")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private ContentcenterFeignService contentcenterFeignService;
    @Autowired
    private OperatecenterFeignService operatecenterFeignService;


    @ApiOperation(value = "测试异常1")
    @GetMapping("/exception1")
    public ResponseMsg<String> exception1(@RequestParam(required = false) String flag) {
        if (null == flag) {
            throw new BusiException(ResultEnum.PARAM_ERROR, "参数");
        }
        return success("成功");
    }

    @ApiOperation(value = "测试异常2")
    @GetMapping("/exception2")
    public ResponseMsg<String> exception2(@RequestParam(required = false) String flag) {
        ResultEnum.PARAM_ERROR.assertNotNull(flag, "参数");
        return success("成功");
    }

    @ApiOperation(value = "测试校验")
    @PostMapping("/valid")
    public ResponseMsg<String> valid(@Valid @RequestBody TestRequestVo vo) {
        return success("成功");
    }

    @ApiOperation(value = "测试feign")
    @GetMapping("/feign/{center}")
    public ResponseMsg<String> feign(@PathVariable String center) {
        String result = "";
        if ("cc".equals(center)) {
            result = contentcenterFeignService.query().getData();
        } else if ("oc".equals(center)) {
            result = operatecenterFeignService.query().getData();
        }
        return success("成功:" + result);
    }

    @Resource(name = "smsService")
    private SmsService smsService;

    @ApiOperation(value = "测试starter")
    @GetMapping("/sms/{msg}")
    public ResponseMsg<String> sms(@PathVariable String msg) {
        smsService.sendMsg(msg);
        return success("成功!");
    }

}
