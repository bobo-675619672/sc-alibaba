package com.gfs.operatecenter.entity.vo.homemodule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.homemodule
 * @ClassName HomePageExtendVo
 * @description
 * @date created in 2020-05-15 17:14
 * @modified by
 */
@ApiModel("额外信息")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomePageExtendVo {
    @ApiModelProperty(value = "座右铭")
    private String motto;
    @ApiModelProperty(value = "是否有未完成的作业(0：没有 1：有 -1：其他)")
    private Integer hasUnDoneTask;
    @ApiModelProperty(value = "是否展示作业图标(true 展示   false 不展示)) ")
    private Boolean showTaskImg;
    @ApiModelProperty(value = "购买状态(0:普通 1:体验劵 2:VIP 3:SVIP)")
    private Integer useState;
    @ApiModelProperty(value = "临时教材名称")
    private String tempBookName;
}
