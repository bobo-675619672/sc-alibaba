package com.gfs.operatecenter.entity.vo.feign;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.feign
 * @ClassName IsVipVo
 * @description
 * @date created in 2020-05-19 10:31
 * @modified by
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("VIPvo")
public class IsVipVo {
    @ApiModelProperty(value = "是否vip代码", dataType = "Date")
    private String isVipCode;

    @ApiModelProperty(value = "是否vip中文", dataType = "Date")
    private String isVipInChinese;

    @ApiModelProperty(value = "失效时间", dataType = "Date")
    private Date endDate;

    @JsonIgnore
    private String goodsType;

}
