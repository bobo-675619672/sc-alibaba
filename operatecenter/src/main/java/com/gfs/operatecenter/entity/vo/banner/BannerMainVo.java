package com.gfs.operatecenter.entity.vo.banner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.banner
 * @ClassName BannerMainVo
 * @description
 * @date created in 2020-05-18 10:29
 * @modified by
 */
@ApiModel("轮播图返回实体")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerMainVo {
    @ApiModelProperty(name = "轮播图集合", value = "轮播图集合", required = false, dataType = "boolean", example = "")
    private List<BannerVo> banners = new ArrayList<>();

    @ApiModelProperty(name = "轮播图商城H5弹框", value = "轮播图商城H5弹框", required = false, dataType = "boolean", example = "")
    private BannerVo bannerMalls;
}
