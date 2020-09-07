package com.gfs.operatecenter.entity.vo.feign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.feign
 * @ClassName AppVideoVoResponse
 * @description
 * @date created in 2020-07-13 14:04
 * @modified by
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("配音视频出参")
public class AppVideoVoResponse {
    @ApiModelProperty(value = "视频id",dataType = "Long")
    private Long id;
    @ApiModelProperty(value = "标题",dataType = "String")
    private String title;
    @ApiModelProperty(value = "视频备注",dataType = "String")
    private String remark;
    @ApiModelProperty(value = "视频种类",dataType = "Integer")
    private Integer type;
    @ApiModelProperty(value = "封面url",dataType = "String")
    private String coverUrl;
    @ApiModelProperty(value = "原视频url",dataType = "String")
    private String originalUrl;
    @ApiModelProperty(value = "无人声视频",dataType = "String")
    private String videoUrl;
    @ApiModelProperty(value = "无音轨视频url",dataType = "String")
    private String silentUrl;
    @ApiModelProperty(value = "无人声音频url",dataType = "String")
    private String	audioUrl;
    @ApiModelProperty(value = "完成配音总人数",dataType = "Integer")
    private Integer finishCount;
    @ApiModelProperty(value = "营销标签",dataType = "String")
    private String marketTag;
    @ApiModelProperty(value = "视频状态",dataType = "Integer")
    private Integer status;
}
