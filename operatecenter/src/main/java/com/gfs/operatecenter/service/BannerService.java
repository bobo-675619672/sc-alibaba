package com.gfs.operatecenter.service;

import com.gfs.operatecenter.entity.vo.banner.BannerMainVo;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service
 * @ClassName BannerService
 * @description
 * @date created in 2020-05-18 10:41
 * @modified by
 */
public interface BannerService {
    /**
     *获取pc端首页轮播图
     * @param channelType
     * @return
     */
    BannerMainVo queryBannerMainForPc(String channelType);
}
