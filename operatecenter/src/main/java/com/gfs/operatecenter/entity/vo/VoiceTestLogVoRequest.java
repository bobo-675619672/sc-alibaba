package com.gfs.operatecenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hugaohui
 * @ProjectName gaofenshuo-parent
 * @Package com.gaofenshuo.model.vo.systemmanager.data
 * @ClassName VoiceTestLogVoRequest
 * @description语音评测日志入参类
 * @date created in 2019-02-25 14:49
 * @modified by
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("语音评测日志入参类")
public class VoiceTestLogVoRequest {
    @ApiModelProperty(value = "评测类型", dataType = "String", example = "chivox:驰声,unisound:云知声")
    private String testType;
    @ApiModelProperty(value = "成功标识", dataType = "Integer", example = "0 失败 1成功")
    private Integer flag;
}


