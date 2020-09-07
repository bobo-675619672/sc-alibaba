package com.gfs.operatecenter.entity.vo.dictdata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author liweicheng
 * @ProjectName gaofenshuo-parent
 * @Package com.gaofenshuo.model.vo.systemmanager.info
 * @ClassName PointsRecordVo
 * @description 记录卖点接口数据
 * @tableName sysm_info_points_record
 * @date created in 2019-10-01 21:16:02
 * @modified by
 */
@Getter
@Setter
@ApiModel(value = "记录卖点接口数据")
@AllArgsConstructor
@NoArgsConstructor
public class PointsRecordVo {


    @ApiModelProperty(name = " * ", value = " * ", required = true, dataType = "Long", example = "")
    private Long id;


    @ApiModelProperty(name = "用户ID", value = "用户ID", required = false, dataType = "Long", example = "")
    private Long userId;


    @Size(max = 50, min = 0, message = "phone 不能超过 50 最大字符数")
    @ApiModelProperty(name = "手机号码", value = "手机号码", required = false, dataType = "String", example = "")
    private String phone;


    @ApiModelProperty(name = "类型id", value = "类型id", required = false, dataType = "Long", example = "")
    private Long typeId;


    @Size(max = 64, min = 0, message = "method 不能超过 64 最大字符数")
    @ApiModelProperty(name = "请求类型", value = "请求类型", required = false, dataType = "String", example = "")
    private String method;


    @ApiModelProperty(name = "请求时间（单位:秒）", value = "请求时间（单位:秒）", required = false, dataType = "Double", example = "")
    private Double time;


    @Size(max = 128, min = 0, message = "target 不能超过 128 最大字符数")
    @ApiModelProperty(name = "目标接口", value = "目标接口", required = false, dataType = "String", example = "")
    private String target;


    @Size(max = 128, min = 0, message = "path 不能超过 128 最大字符数")
    @ApiModelProperty(name = "路径", value = "路径", required = false, dataType = "String", example = "")
    private String path;


    @ApiModelProperty(name = "请求数据", value = "请求数据", required = false, dataType = "String", example = "")
    private String reqdata;


    @ApiModelProperty(name = "返回数据", value = "返回数据", required = false, dataType = "String", example = "")
    private String returndata;


    @Size(max = 128, min = 0, message = "remark 不能超过 128 最大字符数")
    @ApiModelProperty(name = "备注", value = "备注", required = false, dataType = "String", example = "")
    private String remark;


    @ApiModelProperty(name = "开始时间", value = "开始时间", required = false, dataType = "Date", example = "")
    private Date startTime;


    @ApiModelProperty(name = "结束时间", value = "结束时间", required = false, dataType = "Date", example = "")
    private Date endTime;


}
