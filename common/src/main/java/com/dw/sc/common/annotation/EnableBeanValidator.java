package com.dw.sc.common.annotation;

import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(BeanValidatorPluginsConfiguration.class)
public @interface EnableBeanValidator {

}