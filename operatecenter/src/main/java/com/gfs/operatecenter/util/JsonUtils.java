package com.gfs.operatecenter.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfs.common.enums.ResultEnum;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.util
 * @ClassName JsonUtils
 * @description
 * @date created in 2020-06-05 14:10
 * @modified by
 */
@Slf4j
public class JsonUtils {
    /**
     * constant
     */
    private static final ObjectMapper JSON_UTILS = new ObjectMapper();

    protected JsonUtils() {
    }

    /**
     *
     * @param obj
     *            Bean
     * @return
     */
    public static String beanToJson(Object obj) {
        try {
            return JSON_UTILS.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("错误信息：", e);
            throw new GfsRuntimeException(ResultEnum.BEAN_TO_JSON);

        }
    }

    /**
     *
     * @param data
     *            String
     * @param beanType
     *            Object.Class
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String data, Class<T> beanType) {
        try {
            return JSON_UTILS.readValue(data, beanType);
        } catch (Exception e) {
            log.error("错误信息：", e);
            throw new GfsRuntimeException(ResultEnum.JSON_TO_BEAN);
        }
    }

    /**
     * 转换bean对象集合
     *
     * @param data
     *            String
     * @param beanType
     *            Object.Class
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String data, Class<T> beanType) {
        try {
            return JSON_UTILS.readValue(data,
                    JSON_UTILS.getTypeFactory().constructParametricType(List.class, beanType));
        } catch (Exception e) {
            log.error("错误信息：", e);
            throw new GfsRuntimeException(ResultEnum.JSON_TO_LIST);
        }
    }

    /**
     * 转换bean对象集合
     *
     * @param data
     *            String
     * @param beanType
     *            Object.Class
     * @param <T>
     * @return
     */
    public static <T> Set<T> jsonToSet(String data, Class<T> beanType) {
        try {
            return JSON_UTILS.readValue(data,
                    JSON_UTILS.getTypeFactory().constructParametricType(Set.class, beanType));
        } catch (Exception e) {
            log.error("错误信息：", e);
            throw new GfsRuntimeException(ResultEnum.JSON_TO_LIST);
        }
    }
}
