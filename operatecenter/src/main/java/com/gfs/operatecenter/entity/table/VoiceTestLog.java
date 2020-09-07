package com.gfs.operatecenter.entity.table;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author hugaohui
 * @ProjectName gaofenshuo-parent
 * @Package com.gaofenshuo.model.dto.systemmanager.data
 * @ClassName VoiceTestLog
 * @description  语音评测日志
 * @date created in 2019-02-25 14:46
 * @modified by
 */
@Setter
@Getter
@TableName("sysm_data_voicetestlog")
public class VoiceTestLog {
    @ApiModelProperty(value = "编号")
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "评测类型")
    private String testType;
    @ApiModelProperty(value = "成功标识")
    private Integer flag;
}
