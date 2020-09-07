package com.gfs.operatecenter.entity.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liweicheng
 * @ProjectName gaofenshuo-parent
 * @Package com.gaofenshuo.model.dto.systemmanager.info
 * @ClassName PointsRecord
 * @description 记录卖点接口数据
 * @tableName sysm_info_points_record
 * @date created in 2019-10-01 21:16:02
 * @modified by
 */
@Getter
@Setter
@TableName("sysm_info_points_record")
public class PointsRecord {


    /**
     * ""
     */
    private Long id;


    /**
     * "用户ID"
     */
    private Long userId;


    /**
     * "手机号码"
     */
    private String phone;


    /**
     * "类型id"
     */
    private Long typeId;


    /**
     * "请求类型"
     */
    private String method;


    /**
     * "请求时间（单位:秒）"
     */
    private Double time;


    /**
     * "目标接口"
     */
    private String target;


    /**
     * "路径"
     */
    private String path;


    /**
     * "请求数据"
     */
    private String reqdata;


    /**
     * "返回数据"
     */
    private String returndata;


    /**
     * "备注"
     */
    private String remark;


    /**
     * "开始时间"
     */
    private Date startTime;


    /**
     * "结束时间"
     */
    private Date endTime;


}
