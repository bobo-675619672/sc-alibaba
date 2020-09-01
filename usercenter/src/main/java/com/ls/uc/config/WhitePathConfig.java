package com.ls.uc.config;


import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 白名单配置类
 * @author liaodewen
 */
@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties(prefix = "gfs.path")
@RefreshScope
public class WhitePathConfig {

    List<String> white = Lists.newArrayList();

}
