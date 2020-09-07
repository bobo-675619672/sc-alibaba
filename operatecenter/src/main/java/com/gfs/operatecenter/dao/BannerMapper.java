package com.gfs.operatecenter.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gfs.operatecenter.entity.table.Banner;

import java.util.HashMap;
import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.dao
 * @ClassName BannerMapper
 * @description
 * @date created in 2020-05-18 15:02
 * @modified by
 */
public interface BannerMapper extends BaseMapper<Banner> {
    /**
     * pc端查询轮播图
     *
     * @param params
     * @return
     */
    List<Banner> queryBannerForPc(HashMap<String, Object> params);
}
