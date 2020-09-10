package com.dw.sc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author liweicheng
 * @ProjectName gaofenshuo-parent
 * @Package com.gaofenshuo.common.enums
 * @ClassName ResponseHeadEnum
 * @description 响应返回信息字段
 * @date created in 2018-07-23 14:57
 * @modified by
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseHeadEnum {

    /**
     * 成功
     */
    SUCCEE("00", "Success", "成功"),

    /**
     * 失败
     */
    FAIL("01", "Fail", "失败");

    private String code;

    private String status;

    private String message;

}
