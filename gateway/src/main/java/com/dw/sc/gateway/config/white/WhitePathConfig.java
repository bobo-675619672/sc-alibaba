package com.dw.sc.gateway.config.white;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties(prefix = "white")
@RefreshScope
public class WhitePathConfig {

    Map<String, List<String>> path = Maps.newHashMap();

}
