package com.gfs.operatecenter.entity.vo.homemodule;

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
 * @ClassName HomePageVoRequest
 * @description
 * @date created in 2020-05-13 16:01
 * @modified by
 */
@ApiModel("首页模块入参")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomePageVoRequest {
    @ApiModelProperty(value = "教材ID")
    private Long bookId;
    @ApiModelProperty(value = "年级GRADE: 3 4 5 6 7 8 9")
    private String grade;
    @ApiModelProperty(value = "地区码")
    private String region;
}
