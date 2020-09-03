package com.dw.sc.gateway.config.white;

import com.dw.sc.common.constant.SecurityConstants;
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

    public List<String> getWhitePath(String serverName) {
        List<String> whitePaths = path.getOrDefault(serverName, Lists.newArrayList());
        // 添加基础白名单
        whitePaths.addAll(getBasePath());
        return whitePaths;
    }

    public List<String> getBasePath() {
        return Lists.newArrayList(
                SecurityConstants.DEFAULT_VALIDATE_HTML_JS_SUFFIX,
                SecurityConstants.DEFAULT_VALIDATE_HTML_FONTS_SUFFIX,
                SecurityConstants.DEFAULT_VALIDATE_HTML_IMAGES_SUFFIX,
                SecurityConstants.DEFAULT_VALIDATE_HTML_CSS_SUFFIX,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                SecurityConstants.DEFAULT_SWAGGER_V2_API_DOCS_URL,
                SecurityConstants.DEFAULT_SWAGGER_UI_URL,
                SecurityConstants.DEFAULT_SWAGGER_RESOURCES_URL,
                SecurityConstants.DEFAULT_SWAGGER_IMAGES_URL,
                SecurityConstants.DEFAULT_SWAGGER_WEBJARS_URL,
                SecurityConstants.DEFAULT_SWAGGER_CONFIGURATION_UI_URL,
                SecurityConstants.DEFAULT_SWAGGER_CONFIGURATION_SECURITY_URL
        );
    }

}
