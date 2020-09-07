package com.gfs.operatecenter.entity.vo.dictdata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.dictdata
 * @ClassName DictDataVoResponse
 * @description
 * @date created in 2020-05-14 14:10
 * @modified by
 */
@ApiModel("字典出参")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DictDataVoResponse {
    @ApiModelProperty(value = "字典ID")
    private Integer id;

    @ApiModelProperty(value = "字典编号值")
    private String dictId;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典值")
    private String dictVal;

    @ApiModelProperty(value = "字典组编号")
    private String groupId;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "排列顺序")
    private Integer seq;

}
