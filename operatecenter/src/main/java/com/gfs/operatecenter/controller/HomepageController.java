package com.gfs.operatecenter.controller;

import com.gfs.common.base.BaseController;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.entity.vo.homemodule.FunPcHomePageVoResponse;
import com.gfs.operatecenter.entity.vo.homemodule.HomePageVoRequest;
import com.gfs.operatecenter.entity.vo.homemodule.HomePageVoResponse;
import com.gfs.operatecenter.service.HomeManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "pc端首页模块")
@RestController
@RequestMapping("/pc/homepage")
public class HomepageController extends BaseController {


    @Autowired
    private HomeManageService homeManageService;

    @ApiOperation(value = "查询pc端首页模块")
    @PostMapping("/queryHomepageForPc")
    public ResponseMsg<HomePageVoResponse> queryHomepageForPc(@Validated @RequestBody HomePageVoRequest request) {
        return success(homeManageService.queryHomepageForPc(request));
    }

    @ApiOperation(value = "查询pc端趣味内容首页模块")
    @PostMapping("/queryFunHomepageForPc")
    public ResponseMsg<FunPcHomePageVoResponse> queryFunHomepageForPc(@Validated @RequestBody HomePageVoRequest request) {
        return success(homeManageService.queryFunHomepageForPc(request));
    }

}
