package com.gfs.operatecenter.entity.vo.qiniu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.entity.vo.qiniu
 * @ClassName QiniuVoResponse
 * @description
 * @date created in 2020-06-05 14:16
 * @modified by
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("七牛参数配置")
public class QiniuVoResponse {
    @ApiModelProperty(value = "访问七牛服务器token值", required = false)
    private String token;
    @ApiModelProperty(value = "访问七牛资源域名", required = false)
    private String domain;
    @ApiModelProperty(value = "七牛上传文件http路径", required = false)
    private String uploadHttp;
    @ApiModelProperty(value = "七牛上传文件https路径", required = false)
    private String uploadHttps;
}
