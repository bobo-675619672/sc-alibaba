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
 * @ClassName HomeInfo
 * @description
 * @date created in 2020-05-13 10:38
 * @modified by
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cms_template_homeinfo")
@ApiModel("首页板块信息")
public class HomeInfo {
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
    @ApiModelProperty(value = "模块名称")
    private String homeName;
    @ApiModelProperty(value = "教材id")
    private Long bookId;
    @ApiModelProperty(value = "年级")
    private String homeGrade;
    @ApiModelProperty(value = "地区")
    private String region;
    @ApiModelProperty(value = "备注")
    private String remark;
}
