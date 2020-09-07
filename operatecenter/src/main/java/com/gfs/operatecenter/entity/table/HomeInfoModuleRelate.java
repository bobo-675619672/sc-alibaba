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
 * @ClassName HomeInfoModuleRelate
 * @description
 * @date created in 2020-05-13 11:05
 * @modified by
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cms_template_homeinfo_module_relate")
@ApiModel("app首页板块信息与模块关系表")
public class HomeInfoModuleRelate {
    @ApiModelProperty(value = "编号")
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "板块信息编号")
    private Long homeId;
    @ApiModelProperty(value = "模块编号")
    private String moduleId;
}
