package com.hyq.cloudexample;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.netflix.niws.loadbalancer.DefaultNIWSServerListFilter;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RibbonConfiguration
 * @Author dibulidohu
 * @Date 2019/8/8 15:35
 * @Description
 */
@Configuration
public class RibbonConfiguration {
    /**
     * com.netflix.client.config.IClientConfig：Ribbon的客户端配置，默认采用com.netflix.client.config.DefaultClientConfigImpl实现。
     */
    @Bean
    public IClientConfig clientConfig() {
        return new DefaultClientConfigImpl();
    }

    /**
     * com.netflix.loadbalancer.IRule：Ribbon的负载均衡策略，默认采用com.netflix.loadbalancer.ZoneAvoidanceRule实现，该策略能够在多区域环境下选出最佳区域的实例进行访问。
     */
    @Bean
    public IRule ribbonRule() {
        //return new RoundRobinRule();
        //return new RetryRule();
        //return new RandomRule();
        //return new AvailabilityFilteringRule();
        //return new BestAvailableRule();
        //return new ClientConfigEnabledRoundRobinRule();
        //return new WeightedResponseTimeRule();
        return new ZoneAvoidanceRule();
    }

    /**
     * com.netflix.loadbalancer.IPing：Ribbon的实例检查策略，默认采用com.netflix.loadbalancer.NoOpPing实现，该检查策略是一个特殊的实现，实际上它并不会检查实例是否可用，而是始终返回true，默认认为所有服务实例都是可用的。
     */
    @Bean
    public IPing ribbonPing() {
        return new DummyPing();
        //return new NoOpPing();
        //return new NIWSDiscoveryPing();
    }

    /**
     * com.netflix.loadbalancer.ServerList：服务实例清单的维护机制，默认采用com.netflix.loadbalancer.ConfigurationBasedServerList实现。
     */
    @Bean
    public ServerList ribbonServerList() {
        return new ConfigurationBasedServerList();
        //return new DiscoveryEnabledNIWSServerList();
    }

    /**
     * com.netflix.loadbalancer.ServerListFilter：服务实例清单过滤机制，默认采org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter，该策略能够优先过滤出与请求方处于同区域的服务实例。
     */
    @Bean
    public ServerListFilter ribbonServerListFilter() {
        return new ZonePreferenceServerListFilter();
        //return new ZoneAffinityServerListFilter();
        //return new DefaultNIWSServerListFilter();
    }

    /**
     * com.netflix.loadbalancer.ILoadBalancer：负载均衡器，默认采用com.netflix.loadbalancer.ZoneAwareLoadBalancer实现，它具备了区域感知的能力。
     */
    @Bean
    public ILoadBalancer ribbonLoadBalancer() {
        return new ZoneAwareLoadBalancer<>();
        //return new BaseLoadBalancer();
    }
}
