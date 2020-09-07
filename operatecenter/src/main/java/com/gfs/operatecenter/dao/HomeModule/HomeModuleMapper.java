package com.gfs.operatecenter.dao.HomeModule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gfs.operatecenter.entity.table.HomeModule;
import com.gfs.operatecenter.entity.vo.homemodule.HomeModuleVo;
import com.gfs.operatecenter.entity.vo.homemodule.HomePageVoRequest;

import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.dao.HomeModule
 * @ClassName HomeModuleMapper
 * @description
 * @date created in 2020-05-13 16:09
 * @modified by
 */
public interface HomeModuleMapper extends BaseMapper<HomeModule> {
    /**
     * 查询pc端首页模块
     * @param request
     * @return
     */
    List<HomeModuleVo> queryPcModuleByRequest(HomePageVoRequest request);

    /**
     * 查询pc端左侧栏目首页模块
     * @param request
     * @return
     */
    List<HomeModuleVo> queryLeftPcModuleByRequest(HomePageVoRequest request);

    /**
     * 查询pc端趣味内容首页模块
     * @param request
     * @return
     */
    List<HomeModuleVo> queryFunPcModuleByRequest(HomePageVoRequest request);
}
