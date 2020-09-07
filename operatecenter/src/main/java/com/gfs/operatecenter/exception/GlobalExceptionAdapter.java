package com.gfs.operatecenter.exception;

import com.gfs.common.bean.ResponseHead;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.common.constant.CommonConstant;
import com.gfs.common.enums.HttpEnum;
import com.gfs.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liweicheng
 * @ProjectName common
 * @Package com.gfs.common.exception
 * @ClassName GlobalExceptionAdapter
 * @description
 * @date created in 2020-05-12 10:01
 * @modified by
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdapter {

    @Value("${spring.profiles.active}")
    String profile;

    @Value("${spring.application.name}")
    String appName;

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMsg exception(Exception e) {
        log.info("全局异常信息 ex={}", e.getMessage(), e);
        HttpServletRequest request = getHttpServletRequest();
        return ResponseMsg.builder().head(
                ResponseHead.builder()
                        .respStatus(HttpEnum.FAIL.getStatus())
                        .respCode(HttpEnum.FAIL.getCode())
                        .respDesc("环境：[" + profile + "], 微服务: [" + appName + "], 接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员")
                        .respTips(e.toString())
                        .build())
  //              .data(e.toString())
                .build();
    }

    /**
     * 方法接收到非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg illegalArgumentException(IllegalArgumentException e) {
        log.error("方法接收到非法参数异常 ex={}", e.getMessage(), e);
        HttpServletRequest request = getHttpServletRequest();
        return ResponseMsg.builder().head(
                ResponseHead.builder()
                        .respStatus(HttpEnum.FAIL.getStatus())
                        .respCode(HttpEnum.FAIL.getCode())
                        .respDesc("环境：[" + profile + "], 微服务: [" + appName + "], 接口 [" + request.getRequestURI() + "] 非法参数异常")
                        .build())
                .data(e.toString())
                .build();
    }

    /**
     * 处理文件上传大小过大异常
     */
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg multipartException(MultipartException e) {
        log.error("上传文件大小超过最大限制 最大文件大小限制为:10M ex={}", e.getMessage(), e);
        HttpServletRequest request = getHttpServletRequest();
        return ResponseMsg.builder().head(
                ResponseHead.builder()
                        .respStatus(HttpEnum.FAIL.getStatus())
                        .respCode(HttpEnum.FAIL.getCode())
                        .respDesc("环境：[" + profile + "], 微服务: [" + appName + "], 接口 [" + request.getRequestURI() + "] 上传大小过大异常")
                        .build())
                .data(e.toString())
                .build();
    }

    /**
     * 方法无效异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg businessException(MethodArgumentNotValidException e) {
        log.error(CommonConstant.GLOBAL_EXCEPTION, e.getMessage(), e);
        HttpServletRequest request = getHttpServletRequest();
        List<String> messages = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()
                );
        return ResponseMsg.builder().head(
                ResponseHead.builder()
                        .respStatus(HttpEnum.FAIL.getStatus())
                        .respCode(HttpEnum.FAIL.getCode())
                        .respDesc("环境：[" + profile + "], 微服务: [" + appName + "], 接口 [" + request.getRequestURI() + "] 方法无效异常")
                        .resultCode(ResultEnum.GLOBAL_EXCEPTION.getCode())
                        .resultMsg(String.format(ResultEnum.GLOBAL_EXCEPTION.getMsg(),e.getBindingResult().getAllErrors().get(0).getDefaultMessage()))
                        .build())
                .data(messages.toString())
                .build();
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(GfsRuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg businessException(GfsRuntimeException e) {
        log.error(CommonConstant.GLOBAL_EXCEPTION, e.getMessage(), e);
        HttpServletRequest request = getHttpServletRequest();
        return ResponseMsg.builder().head(
                ResponseHead.builder()
                        .respStatus(HttpEnum.FAIL.getStatus())
                        .respCode(HttpEnum.FAIL.getCode())
                        .respDesc("环境：[" + profile + "], 微服务: [" + appName + "], 接口 [" + request.getRequestURI() + "] 业务异常")
                        .resultCode(e.getCode())
                        .resultMsg(e.getMessage())
                        .build())
                .data(e.toString())
                .build();
    }

    /**
     * 方法参数异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMsg businessException(BindException e) {
        log.error(CommonConstant.GLOBAL_EXCEPTION, e.getMessage(), e);
        HttpServletRequest request = getHttpServletRequest();
        List<String> messages = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()
                );
        return ResponseMsg.builder().head(
                ResponseHead.builder()
                        .respStatus(HttpEnum.FAIL.getStatus())
                        .respCode(HttpEnum.FAIL.getCode())
                        .respDesc("环境：[" + profile + "], 微服务: [" + appName + "], 接口 [" + request.getRequestURI() + "] 方法参数异常")
                        .build())
                .data(messages.toString())
                .build();
    }

    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        return attributes.getRequest();
    }

}

