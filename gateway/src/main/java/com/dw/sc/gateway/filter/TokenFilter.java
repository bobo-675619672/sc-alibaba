package com.dw.sc.gateway.filter;

import com.dw.sc.common.constant.TokenConstant;
import com.dw.sc.common.util.TokenUtil;
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

    @Autowired
    private WhitePathConfig whitePathConfig;

    private static final String PATH_REGEX = "\\/(.[^\\/]*)(.*)";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 请求
        ServerHttpRequest request = exchange.getRequest();
        // 响应
        ServerHttpResponse response = exchange.getResponse();

        // 判断白名单
        if (isWhite(request.getURI().getPath())) {
            // 是白名单 通过
            return chain.filter(exchange);
        }
        // 请求头
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(TokenConstant.TOKEN_NAME);
        log.info("Token: {}", token);
        // 校验Token
        // 目前只校验解析正常
        TokenUtil.parseToken(token);
        // 通过
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    /**
     * 白名单判断逻辑
     * @param url
     * @return
     */
    private boolean isWhite(String url) {
        log.info("url:{}", url);
        // 路径为空
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        Pattern p = Pattern.compile(PATH_REGEX);
        Matcher m = p.matcher(url);
        if (m.find() && m.groupCount() == 2) {
            // 服务名
            String serverName = m.group(1);
            // 路径
            String path = m.group(2);
            log.info("server:{}, path:{}", serverName, path);
            List<String> whiteList = whitePathConfig.getWhitePath(serverName);
            log.info("whiteList:{}", String.join(",", whiteList));
            for (String rule : whiteList) {
                // 与白名单一致
                if (rule.equals(path)) {
                    return true;
                }
                // 通配符一致
                if ((rule.endsWith("**")) && (path.indexOf(rule.substring(0, rule.lastIndexOf("**"))) == 0)) {
                    return true;
                }
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
        String[] words = url.split("\\/", -1);
        for (String word : words) {
            // 返回第一个不为空
            if (StringUtils.isNotEmpty(word)) {
                return word;
            }
        }
        return "";
    }

    /**
     * 获取真是路径
     * 入参 /A/B/C 返回 A
     * @param url
     * @return
     */
    private String getPath(String url) {
        String[] words = url.split("\\/", -1);
        Boolean flag = false;
        List<String> paths = Lists.newArrayList();
        for (String word : words) {
            // 返回第一个不为空
            if (StringUtils.isNotEmpty(word)) {
                return word;
            }
        }
        return "/" + String.join("\\/", paths);
    }

//    public static void main(String[] args) {
//        Pattern p = Pattern.compile(PATH_REGEX);
//        Matcher m = p.matcher("/A/B/C/D/E");
//        if (m.find()) {
//            System.out.println(m.groupCount());
//            System.out.println(m.group(0));
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//            System.out.println(m.group(3));
//        }
//
//    }


}
