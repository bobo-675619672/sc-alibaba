package com.gfs.operatecenter.entity.table;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.table
 * @ClassName HomeModule
 * @description
 * @date created in 2020-05-13 10:25
 * @modified by
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cms_template_homemodule")
@ApiModel("首页模块")
public class HomeModule {
    @ApiModelProperty(value = "编号",example = "tscp 听说测评 tlcs 听力测试 tlxl 听力训练 zhlx 综合练习c 单词 jz 句子")
    private String id;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
    @ApiModelProperty(value = "模块类型 字典：MODULE_TYPE")
    private String moduleType;
    @ApiModelProperty(value = "模块名称")
    private String showName;
    @ApiModelProperty(value = "模块图片")
    private String showImg;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "排序")
    private Long sort;
    @ApiModelProperty(value = "备注")
    private String remark;
}
