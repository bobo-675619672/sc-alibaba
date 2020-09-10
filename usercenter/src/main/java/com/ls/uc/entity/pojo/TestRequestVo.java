package com.ls.uc.entity.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class TestRequestVo {

    @NotNull(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "小于最小年龄")
    @Max(value = 100, message = "大于最大年龄")
    private int age;

}
