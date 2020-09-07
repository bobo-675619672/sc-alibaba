package com.gfs.operatecenter.entity.vo.feign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生信息出参类")
public class StudentInfoResponseVo {

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "头像地址")
    private String headUrl;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "积分")
    private Long score;

    @ApiModelProperty(value = "高分币")
    private Long coin;

    @ApiModelProperty(value = "积分等级")
    private Integer scoreLevel;

    @ApiModelProperty(value = "积分等级名")
    private String scoreName;

    @ApiModelProperty(value = "班级编号")
    private Long classesId;

    @ApiModelProperty(value = "班级名称")
    private String classesName;

    @ApiModelProperty(value = "上下册")
    private Integer upDown;

    @ApiModelProperty(value = "年级")
    private String gradeNum;

    @ApiModelProperty(value = "年级")
    private String grade;

    @ApiModelProperty(value = "学年")
    private String schoolYear;

    @ApiModelProperty(value = "学校类型")
    private Integer schoolType;

    @ApiModelProperty(value = "升学月份")
    private Integer upMonth;

    @ApiModelProperty(value = "升学日期")
    private Integer upDay;

    @ApiModelProperty(value = "学生编号")
    private Long studentId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "学校编号")
    private Long schoolId;

    @ApiModelProperty(value = "学校名称")
    private String schoolName;

    @ApiModelProperty(value = "地址编码")
    private String region;

    @ApiModelProperty(value = "地址")
    private String regionStr;

    @ApiModelProperty(value = "教师编号")
    private Long teacherId;

    @ApiModelProperty(value = "教师名称")
    private String teacherName;

    @ApiModelProperty(value = "redis是否存放教材信息")
    private Boolean isBookRedis;

    @ApiModelProperty(value = "教材编号")
    private Long bookId;

    @ApiModelProperty(value = "教材名称")
    private String bookName;

    @ApiModelProperty(value = "会员名称")
    private String vipName;

    @ApiModelProperty(value = "有效期", notes = "2019-01-01")
    private String validDate;

    @ApiModelProperty(value = "注册时间")
    private Date registerTime;

    @ApiModelProperty(value = "作业开始时间")
    private String taskTime;

    @ApiModelProperty(value = "是否可用错题推荐功能")
    private Boolean useTag;

    @ApiModelProperty(value = "是否有密码设置", dataType = "Boolean", example = "true已设置false未设置")
    private Boolean hasPassword;

}
