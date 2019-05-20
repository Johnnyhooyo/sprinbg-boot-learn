package com.hyq.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dibulidohu
 * @classname CuratorLock
 * @date 2019/5/1912:14
 * @description
 */
@Slf4j
public class CuratorLock {
    @Autowired
    private CuratorFactoryBean curatorFactoryBean;
    private CuratorFramework client;
    private InterProcessLock lock;

    public CuratorLock() {
        try {
            client = curatorFactoryBean.getObject();
        } catch (Exception e) {
            log.error("CuratorLock initial error :{}", e);
        }
    }

    public boolean lock() {
        try {
            lock.acquire();
            return true;
        } catch (Exception e) {
            log.error("CuratorLock acquire error :{}", e);
        }
        return false;
    }

    public void unLock() {
        try {
            lock.release();
        } catch (Exception e) {
            log.error("CuratorLock release error :{}", e);
        }
    }
}
