package com.dw.sc.gateway.filter;

import com.dw.sc.gateway.config.white.WhitePathConfig;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    public static final String SERVER_REGEX = "\\/(.[^/]*)";

    @Autowired
    private WhitePathConfig whitePathConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 请求
        ServerHttpRequest request = exchange.getRequest();
        // 响应
        ServerHttpResponse response = exchange.getResponse();
        // 判断白名单
        if (isWhite(request.getURI().getPath())) {
            return chain.filter(exchange);

        }
        // 请求头
        HttpHeaders headers = request.getHeaders();
        headers.getFirst("token");


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    /**
     * 白名单判断逻辑
     * @param url
     * @return
     */
    private boolean isWhite(String url) {
        Map<String, List<String>> whiteList = whitePathConfig.getPath();
        // 路径 或 白名单 为空
        if (StringUtils.isEmpty(url) || whiteList.isEmpty()) {
            return false;
        }
        // 服务名
        String server = getServerName(url);
        // 对应服务名的白名单规则集合
        List<String> rules = whiteList.getOrDefault(server, Lists.newArrayList());
        for (String rule : rules) {
            // 拼接路径 拼接成 /服务名/规则
            rule = "/" + server + rule;
            // 与白名单一致
            if (rule.equals(url)) {
                return true;
            }
            // 通配符一致
            if ((rule.endsWith("**")) && (url.indexOf(rule.substring(0, rule.lastIndexOf("**"))) == 0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取服务名
     * 入参 /A/B/C 返回 A
     * @param url
     * @return
     */
    private String getServerName(String url) {
        Pattern p = Pattern.compile(SERVER_REGEX);
        Matcher m = p.matcher(url);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

}
