package com.gfs.operatecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gfs.operatecenter.entity.table.HomeModule;
import com.gfs.operatecenter.entity.vo.homemodule.FunPcHomePageVoResponse;
import com.gfs.operatecenter.entity.vo.homemodule.HomePageVoRequest;
import com.gfs.operatecenter.entity.vo.homemodule.HomePageVoResponse;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service
 * @ClassName HomeManageService
 * @description
 * @date created in 2020-05-13 14:46
 * @modified by
 */
public interface HomeManageService extends IService<HomeModule> {

    /**
     * 查询pc端首页模块
     * @param request
     * @return
     */
    HomePageVoResponse queryHomepageForPc(HomePageVoRequest request);

    /**
     * 查询pc端趣味内容首页模块
     * @param request
     * @return
     */
    FunPcHomePageVoResponse queryFunHomepageForPc(HomePageVoRequest request);

}
