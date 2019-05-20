package com.hyq.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author dibulidohu
 * @classname CuratorFactoryBean
 * @date 2019/5/1911:41
 * @description
 */
@Slf4j
//为了服务注册正常启动 测试curator时候打开
//@Component
public class CuratorFactoryBean implements FactoryBean<CuratorFramework>, InitializingBean, DisposableBean {

    private CuratorFramework client;
    private static final String CONNECT_STR = "148.70.215.26:2182,148.70.215.26:2183,148.70.215.26:2184";
    private RetryPolicy retryPolicy = new RetryNTimes(5, 2000);

    /***
     * 类初销毁时执行
     */
    @Override
    public void destroy() throws Exception {
        this.client.close();
        log.info("CuratorFramework {} closed", this.client);
    }

    @Override
    public CuratorFramework getObject() throws Exception {
        return this.client;
    }

    @Override
    public Class<?> getObjectType() {
        return client == null ? null : CuratorFramework.class;
    }

    /***
     * 类初始化后执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.client = CuratorFrameworkFactory.newClient(CONNECT_STR, retryPolicy);
        this.client.start();
        this.client.blockUntilConnected();
    }
}
