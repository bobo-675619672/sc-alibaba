package com.gfs.operatecenter.entity.vo.banner;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.banner
 * @ClassName BannerJumpVo
 * @description
 * @date created in 2020-05-18 10:27
 * @modified by
 */
@ApiModel("轮播图跳转")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerJumpVo {
    @ApiModelProperty(name = " * ", value = " * ", required = true, dataType = "Long", example = "")
    private Long id;

    @Size(max = 100, min = 0, message = "bannerId 不能超过 100 最大字符数")
    @ApiModelProperty(name = " * 轮播图编号", value = " * 轮播图编号", required = true, dataType = "Long", example = "")
    private Long bannerId;

    @Size(max = 50, min = 0, message = "contentType 不能超过 50 最大字符数")
    @ApiModelProperty(name = " * 内容类型=CONTENT_TYPE", value = " * 内容类型=CONTENT_TYPE", required = true, dataType = "String", example = "")
    private String contentType;

    @ApiModelProperty(name = "内容编号", value = "内容编号", required = false, dataType = "Long", example = "")
    private Long contentId;

    @Size(max = 50, min = 0, message = "skipType 不能超过 50 最大字符数")
    @ApiModelProperty(name = " * 跳转方式", value = " * 跳转方式", required = true, dataType = "String", example = "")
    private String skipType;

    @ApiModelProperty(name = "教材编号", value = "教材编号", required = false, dataType = "Long", example = "")
    private Long bookId;

    @ApiModelProperty(name = "年级", value = "年级", required = false, dataType = "Integer", example = "")
    private Integer grade;

    @Size(max = 255, min = 0, message = "gradeName 不能超过 255 最大字符数")
    @ApiModelProperty(name = "年级名", value = "年级名", required = false, dataType = "String", example = "")
    private String gradeName;

    @Size(max = 2, min = 0, message = "upDown 不能超过 2 最大字符数")
    @ApiModelProperty(name = "上下册", value = "上下册", required = false, dataType = "String", example = "")
    private String upDown;

    @Size(max = 255, min = 0, message = "upDownName 不能超过 255 最大字符数")
    @ApiModelProperty(name = "上下册名", value = "上下册名", required = false, dataType = "String", example = "")
    private String upDownName;

    @ApiModelProperty(name = "单元编号", value = "单元编号", required = false, dataType = "Long", example = "")
    private Long unitId;

    @Size(max = 32, min = 0, message = "editor 不能超过 32 最大字符数")
    @ApiModelProperty(name = " * 编辑人", value = " * 编辑人", required = true, dataType = "String", example = "")
    private String editor;

    @Size(max = 255, min = 0, message = "remark 不能超过 255 最大字符数")
    @ApiModelProperty(name = "备注", value = "备注", required = false, dataType = "String", example = "")
    private String remark;

    @ApiModelProperty(name = "扩展编号", value = "扩展编号", required = false, dataType = "Object", example = "")
    private Long extendId;

    @Size(max = 20, min = 0, message = "modular 不能超过 20 最大字符数")
    @ApiModelProperty(name = "模块", value = "模块", required = false, dataType = "String", example = "")
    private String modular;

    @Size(max = 20, min = 0, message = "modularName 不能超过 20 最大字符数")
    @ApiModelProperty(name = "模块名", value = "模块名", required = false, dataType = "String", example = "")
    private String modularName;

    @Size(max = 100, min = 0, message = "contentName 不能超过 100 最大字符数")
    @ApiModelProperty(name = "内容名称", value = "内容名称", required = false, dataType = "String", example = "")
    private String contentName;

    @Size(max = 255, min = 0, message = "pageType 不能超过 255 最大字符数")
    @ApiModelProperty(name = "页面类型", value = "页面类型", required = false, dataType = "String", example = "")
    private String pageType;

    @ApiModelProperty(name = "公告ID", value = "公告ID", required = false, dataType = "Long", example = "")
    private Long noticeId;

    @ApiModelProperty(name = "趣味配音", value = "趣味配音", required = false, dataType = "JSONObject", example = "")
    private JSONObject extendObj;

    @ApiModelProperty(name = "篇章阅读", value = "篇章阅读", required = false, dataType = "String", example = "")
    private String url;

    @ApiModelProperty(name = "查询未完成记录", value = "查询未完成记录", required = false, dataType = "Long", example = "")
    private Long recordId;

    @ApiModelProperty(name = "跳转商品ID", value = "跳转商品ID", required = false, dataType = "String", example = "")
    private String goodsId;

    @ApiModelProperty(name = "定价ID", value = "定价ID", required = false, dataType = "Long", example = "")
    private Long priceId;

    @Size(max = 50, min = 0, message = "mallContentType 不能超过 50 最大字符数")
    @ApiModelProperty(name = " * 商城H5内容类型=MALL_CONTENT_TYPE", value = " * 商城H5内容类型=MALL_CONTENT_TYPE", required = true, dataType = "String", example = "")
    private String mallContentType;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
}
