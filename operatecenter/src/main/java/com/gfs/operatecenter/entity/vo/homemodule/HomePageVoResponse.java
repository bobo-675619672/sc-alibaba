package com.gfs.operatecenter.entity.vo.homemodule;

import com.gfs.operatecenter.entity.vo.banner.BannerVo;
import com.gfs.operatecenter.entity.vo.feign.StudentInfoResponseVo;
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
 * @ClassName HomePageVoResponse
 * @description  首页模块出参
 * @date created in 2020-05-13 15:22
 * @modified by
 */
@ApiModel("pc端首页模块出参")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomePageVoResponse {
    @ApiModelProperty(value = "学生信息")
    private StudentInfoResponseVo studentInfo;
    @ApiModelProperty(value = "轮播图")
    private List<BannerVo> bannerList;
    @ApiModelProperty(value = "pc端首页模块")
    private List<HomeModuleVo> pcModuleList;
    @ApiModelProperty(value = "pc端首左侧栏目模块")
    private List<HomeModuleVo> leftPcModuleList;

//    @ApiModelProperty(value = "轮播图商城H5弹窗")
//    private BannerVo bannerMalls;
    @ApiModelProperty(value = "额外信息")
    private HomePageExtendVo extraInfo;
}
