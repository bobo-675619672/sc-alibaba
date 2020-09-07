package com.gfs.operatecenter.aspect;

import com.gfs.common.bean.ResponseHead;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.common.enums.HttpEnum;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 针对所有Controller类的切片
 * @author liaodewen
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class FeignAspect {

    /**
     * 面向controller类的public函数的切片
     */
    @Pointcut("execution(public * com.gfs.operatecenter.feign.client..*(..))")
    public void controllerExecution() {
    }

    @Before("controllerExecution()")
    public void doBefore(JoinPoint joinPoint) {
        log.debug("AOP拦截Feign请求...");
    }

    @AfterReturning(value = "controllerExecution()", returning = "keys")
    public void doAfterReturning(JoinPoint joinPoint, Object keys) {
        log.debug("AOP拦截Feign结果...");
        ResponseMsg result = (ResponseMsg) keys;
        ResponseHead head = result.getHead();
        if (!head.getRespCode().equals(HttpEnum.SUCCEE.getCode())) {
            throw new GfsRuntimeException(head.getResultCode(), head.getResultMsg());
        }
    }

}
