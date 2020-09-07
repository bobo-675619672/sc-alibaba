package com.gfs.operatecenter.entity.vo.qiniu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.qiniu
 * @ClassName QiniuVoRequest
 * @description
 * @date created in 2020-06-05 14:15
 * @modified by
 */
@ApiModel("七牛文件管理入参")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QiniuVoRequest {
    @ApiModelProperty(value = "用户帐号或token", required = true, example = "admin")
    @NotNull
    private String key;

    @ApiModelProperty(value = "单个删除必传文件名", required = false, example = "")
    private String fileName;

    @ApiModelProperty(value = "删除未使用文件有使用的文件名", required = false, dataType="List", example = "http://xx.gfs100.cn/test.jpg")
    private List<String> fileNames;
}
