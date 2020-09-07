package com.gfs.operatecenter.entity.vo.homemodule;

import com.gfs.operatecenter.entity.vo.banner.BannerVo;
import com.gfs.operatecenter.entity.vo.feign.AppVideoVoResponse;
import com.gfs.operatecenter.entity.vo.feign.H5ArticleBookPageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.homemodule
 * @ClassName FunPcHomePageVoResponse
 * @description
 * @date created in 2020-07-13 13:57
 * @modified by
 */
@ApiModel("pc端趣味内容首页模块出参")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FunPcHomePageVoResponse {

    @ApiModelProperty(value = "轮播图")
    private List<BannerVo> bannerList;
    @ApiModelProperty(value = "pc端趣味内容首页模块")
    private List<HomeModuleVo> funPcModuleList;
    @ApiModelProperty(value = "推荐配音")
    private List<AppVideoVoResponse> dubbingList;
    @ApiModelProperty(value = "推荐篇章")
    public List<H5ArticleBookPageVo> articleList;

    }
