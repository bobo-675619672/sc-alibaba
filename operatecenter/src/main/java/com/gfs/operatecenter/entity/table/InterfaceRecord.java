package com.gfs.operatecenter.entity.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liweicheng
 * @ProjectName gaofenshuo
 * @Package com.gaofenshuo.model.dto.admin.systemmanager.info
 * @ClassName InterfaceRecord
 * @description 请求接口记录
 * @tableName sysm_info_interface_record
 * @date created in 2019-06-27 11:30:34
 * @modified by
 */
@Getter
@Setter
@TableName("sysm_info_interface_record")
public class InterfaceRecord {

	/**
	 * ""
	 */
	private Long id;

	/**
	 * "目标接口"
	 */
	private String target;

	/**
	 * "请求类型"
	 */
	private String method;

	/**
	 * "路径"
	 */
	private String path;

	/**
	 * "请求时间"
	 */
	private double time;

	/**
	 * "请求数据"
	 */
	private String reqdata;

	/**
	 * "返回数据"
	 */
	private String returndata;

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
	 * "备注"
	 */
	private String remark;

	/**
	 * 开始获取接口信息时间
	 */
	private Date startTime;

	/**
	 * 结束获取接口信息时间
	 */
	private Date endTime;

}
