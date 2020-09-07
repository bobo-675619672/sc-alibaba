package com.gfs.operatecenter.entity.vo.banner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.banner
 * @ClassName BannerVo
 * @description
 * @date created in 2020-05-18 10:21
 * @modified by
 */
@ApiModel("轮播图")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerVo {
    @ApiModelProperty(name = "编号", value = "编号", required = false, dataType = "String", example = "1")
    private Long id;

    @ApiModelProperty(name = "广告标题", value = "广告标题", required = true, dataType = "String", example = "请输入高分说")
    private String title;

    @ApiModelProperty(name = "广告投放渠道=BANNER_CHANNEL", value = "广告投放渠道=BANNER_CHANNEL", required = true, dataType = "String", example = "1")
    private String channelType;

    @ApiModelProperty(name = "排序", value = "排序", required = true, dataType = "Integer", example = "1")
    private Integer sort;

    @ApiModelProperty(name = "帮助提示", value = "帮助提示", required = true, dataType = "String", example = "help me!")
    private String hint;

    @ApiModelProperty(name = "封面图片", value = "封面图片", required = true, dataType = "String", example = "请输入高分说")
    private String imageName;

    @ApiModelProperty(name = "备注说明", value = "备注说明", required = false, dataType = "String", example = "请输入高分说")
    private String remark;

    //跳转类型修改：改为0=无、1=APP内部、2=外部链接 3=富文本详情 4=商城H5
    @ApiModelProperty(name = "广告跳转方式=JUMP_TYPE", value = "广告跳转方式=JUMP_TYPE", required = false, dataType = "Integer", example = "1")
    private Integer jump;

    @ApiModelProperty(name = "外部连接", value = "外部连接", required = false, dataType = "String", example = "www.gfs100.cn")
    private String link;

    @ApiModelProperty(name = "富文本详情", value = "富文本详情", required = false, dataType = "String", example = "<p>hello world</p>")
    private String htmlCode;

    @ApiModelProperty(name = "关联白名单", value = "关联白名单", required = false, dataType = "String", example = "31,26,28,16")
    private String relatedWl;

    @ApiModelProperty(name = "已选白名单", value = "已选白名单", required = false, dataType = "String", example = "")
    private List<Map<String, Object>> whitelistRelationVoList;

    @ApiModelProperty(name = "起始时间", value = "起始时间", required = false, dataType = "Date", example = "2018-11-23 15:52:12")
    private Date startTime;

    @ApiModelProperty(name = "结束时间", value = "结束时间", required = false, dataType = "Date", example = "2018-11-23 15:52:13")
    private Date finalTime;

    @ApiModelProperty(name = "二维码跳转增加内容参数", value = "二维码跳转增加内容参数", required = false, dataType = "BannerJumpVo", example = "")
    private BannerJumpVo bannerJumpVo;

}
