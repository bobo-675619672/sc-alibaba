package com.gfs.operatecenter.constant;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.constant
 * @ClassName OperationCenterConstant
 * @description  运营中心常量
 * @date created in 2020-05-13 10:08
 * @modified by
 */
public class OperationCenterConstant {

    /**
     * APP首页座右铭
     */
    public static final String DICTGROUP_APP_HOME_MOTTO = "APP_HOME_MOTTO";
    public static final String DICT_WEEKLY_SPECK = "WEEKLY_SPECK";
    public static final String DICT_GFS = "GFS";

    public static final int SCHOOLTYPE_PRIMARY = 1; // 小学
    public static final int SCHOOLTYPE_JUNIOR_MIDDLE = 2; // 初中
    public static final int SCHOOLTYPE_SENIOR = 3; // 高中

    /**
     * 是否有未完成的作业
     */
    public static final int TASK_UNDONE = 1; // 未完成作业
    public static final Integer TASK_DONE = 0;  // 完成作业
    public static final Integer TASK_UNDEFIAL = -1; // 其他未知状态

    /**
     * APP首页套餐标识
     */
    public static final Integer APP_HOME_USESTATE_SVIP = 3;
    public static final Integer APP_HOME_USESTATE_VIP = 2;
    public static final Integer APP_HOME_USESTATE_EXPERIENCE = 1;
    public static final Integer APP_HOME_USESTATE_NORMAL = 0;

    /**
     * vip类型
     */
    public static final String SVIP = "svip";
    public static final String VIP = "vip";
    public static final String NOT_VIP = "notVip";


}
