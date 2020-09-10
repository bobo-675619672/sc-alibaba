package com.ls.uc.feign.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MyRule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        log.info("这里自定义负载均衡规则!");
        ILoadBalancer lb = this.getLoadBalancer();
        List<Server> allList = lb.getAllServers();
        if (0 == allList.size()) {
            return null;
        } else {
            log.info("有 {} 个服务器选择!现在返回第1个", allList.size());
            return allList.get(0);
        }
    }
}
