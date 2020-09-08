package com.dw.sc.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回数据对象")
public class ResponseMsg<T> {

    @ApiModelProperty(name = "返回信息头")
    private ResponseHead head;

    @ApiModelProperty(name = "返回信息数据")
    private T data;

}
