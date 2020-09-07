package com.gfs.operatecenter.entity.vo.feign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.feign
 * @ClassName H5ArticleBookPageVo
 * @description
 * @date created in 2020-07-13 14:46
 * @modified by
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("篇章出参")
public class H5ArticleBookPageVo {
    @ApiModelProperty(value = "阅读编号",dataType = "Long",example = "test", required = false)
    private Long id;

    @ApiModelProperty(value = "名称", dataType = "String",example = "test", required = true)
    private String name;

    @ApiModelProperty(value = "封面图片",dataType = "String", example = "test", required = true)
    private String photo;

    @ApiModelProperty(value = "难度系数", dataType = "String",example = "test", required = true)
    private String level;

    @ApiModelProperty(value = "推荐年级", dataType = "String",example = "test", required = true)
    private String recommendgrade;

    @ApiModelProperty(value = "app显示的推荐", dataType = "String",example = "test")
    private String recommend;

    @ApiModelProperty(value = "营销标签",dataType = "String", example = "test", required = true)
    private String marketTag;

    @ApiModelProperty(value = "连载状态", dataType = "String",example = "test", required = true)
    private String updateStatus;

    @ApiModelProperty(value = "总章节数",dataType = "int", example = "0", required = false)
    private int articlechapterNum;

    @ApiModelProperty(value = "篇章章节编号",dataType = "Long", example = "0", required = false)
    private Long articlechapterId;

    @ApiModelProperty(value = "阅读人数",dataType = "int", example = "0", required = false)
    private int readCount;

    @ApiModelProperty(value = "app跳转H5篇章的url", dataType = "String")
    private String url;
}
