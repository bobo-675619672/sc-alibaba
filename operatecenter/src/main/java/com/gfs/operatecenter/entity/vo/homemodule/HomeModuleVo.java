package com.gfs.operatecenter.entity.vo.homemodule;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @ClassName HomeModuleVo
 * @description
 * @date created in 2020-05-13 15:45
 * @modified by
 */
@ApiModel("首页模块出参")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeModuleVo {
    @ApiModelProperty(value = "模块ID")
    private String id;
    @ApiModelProperty(value = "显示图标")
    private String showImg;
    @ApiModelProperty(value = "显示名称")
    private String showName;
    @ApiModelProperty(value = "模块类型 base module",hidden = true)
    @JsonIgnore
    private String moduleType;
}
