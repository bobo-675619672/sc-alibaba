package com.dw.sc.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("返回信息头")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHead {

    @ApiModelProperty(name = "返回信息状态",example = "Success or Fail")
    private String respStatus;

    @ApiModelProperty(name = "返回信息错误码",example = "00",notes = "参考错误码")
    private String respCode;

    @ApiModelProperty(name = "返回信息错误信息",example = "错误信息")
    private String respDesc;

    @ApiModelProperty(name = "返回信息错误信息类型",example = "info:业务提示， error:系统错误提示")
    private String respTips;

    @ApiModelProperty(name = "返回错误码",example = "10001")
    private Integer resultCode;

    @ApiModelProperty(name = "返回错误信息",example = "异常")
    private String resultMsg;

}

