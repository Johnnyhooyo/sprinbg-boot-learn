package com.hyq.zookeeper.core.service;

import com.hyq.zookeeper.core.ZooKeeperBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author dibulidohu
 * @classname ServiceRegistry
 * @date 2019/5/2014:01
 * @description register service in zookeeper registry
 */
@Slf4j
@Service
public class ServiceRegistry {

    private static final String REGISTER_PATH = "/register";
    private ZooKeeper zooKeeper;
    @Resource
    private ZooKeeperBase zooKeeperBase;

    public ServiceRegistry() {
    }

    @PostConstruct
    public void beforeInit() {
        try {
            zooKeeper = zooKeeperBase.getZooKeeper();
        } catch (InterruptedException e) {
            log.error("ServiceRegistry init error, e:{}", e);
        }
    }

    private void init(String serviceName) {
        try {
            Stat stat = zooKeeper.exists(REGISTER_PATH, null);
            if (null == stat) {
                zooKeeper.create(REGISTER_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            Stat stat1 = zooKeeper.exists(REGISTER_PATH + "/" + serviceName, null);
            if (null == stat1) {
                zooKeeper.create(REGISTER_PATH + "/" + serviceName, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("service register parent node init error :{}", e);
        }
    }

    public void registerService(String serviceName, byte[] serviceInfo) {
        try {
            init(serviceName);
            String serviceNode = REGISTER_PATH + "/" + serviceName + "/addr_";
            zooKeeper.create(serviceNode, serviceInfo, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException | InterruptedException e) {
            log.error("service register service node error :{}", e);
        }
    }
}
