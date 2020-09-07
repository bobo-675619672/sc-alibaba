package com.gfs.operatecenter.entity.table;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gfs.operatecenter.entity.vo.banner.BannerJumpVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.table
 * @ClassName BannerVo
 * @description
 * @date created in 2020-05-18 8:52
 * @modified by
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mk_act_banner")
@ApiModel("轮播图")
public class Banner {
    @ApiModelProperty( value = "编号",  dataType = "Long")
    private Long id;
    @ApiModelProperty( value = "广告标题",  dataType = "String")
    private String title;
    @ApiModelProperty( value = "广告投放渠道=BANNER_CHANNEL",  dataType = "String")
    private String channelType;
    @ApiModelProperty( value = "排序",  dataType = "Integer")
    private Integer sort;
    @ApiModelProperty( value = "帮助提示",  dataType = "String")
    private String hint;
    @ApiModelProperty( value = "封面图片",  dataType = "String")
    private String imageName;
    @ApiModelProperty( value = "备注说明",  dataType = "String")
    private String remark;
    @ApiModelProperty( value = "广告跳转方式=BANNER_JUMP",  dataType = "Integer")
    private Integer jump;
    @ApiModelProperty( value = "链接",  dataType = "String")
    private String link;
    @ApiModelProperty( value = "html代码",  dataType = "String")
    private String htmlCode;
    @ApiModelProperty( value = "开始时间",  dataType = "Date")
    private Date startTime;
    @ApiModelProperty( value = "结束时间",  dataType = "Date")
    private Date finalTime;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
    private BannerJumpVo bannerJump;

}
