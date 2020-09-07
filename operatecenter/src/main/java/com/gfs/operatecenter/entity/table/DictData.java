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
 * @ClassName DictData
 * @description  字典
 * @date created in 2020-05-14 14:24
 * @modified by
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sysm_conf_dictitem")
@ApiModel("字典项")
public class DictData {

    @ApiModelProperty(value = "字典ID")
    private Integer id;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
    @ApiModelProperty(value = "字典编号值")
    private String dictId;
    @ApiModelProperty(value = "字典名称")
    private String dictName;
    @ApiModelProperty(value = "字典值")
    private String dictVal;
    @ApiModelProperty(value = "字典组编号")
    private String groupId;
    @ApiModelProperty(value = "排列顺序")
    private Integer seq;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "描述")
    private String remark;

}
