package com.hyq.zookeeper.core.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyq.zookeeper.core.ZooKeeperBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author dibulidohu
 * @classname ServiceConsumer
 * @date 2019/5/2016:36
 * @description
 */
@Slf4j
@Component
public class ServiceConsumer  {

    private static HashMap<String, List<ServiceInfo>> serviceTree = Maps.newHashMap();
    private static final String REGISTER_PATH = "/register";
    private ZooKeeper zooKeeper;
    private ServiceTree service = new ServiceTree();
    @Resource
    private ZooKeeperBase zooKeeperBase;

    @PostConstruct
    public void serviceSearch() {
        try {
            zooKeeper = zooKeeperBase.getZooKeeper();
        } catch (InterruptedException e) {
            log.error("ServiceConsumer init error, e:{}", e);
        }
        service.addService();
    }

    class ServiceTree implements Watcher {

        public void addService() {
            try {
                List<String> children = zooKeeper.getChildren(REGISTER_PATH, null);
                for (String child : children) {
                    String servicePath = REGISTER_PATH + "/" + child;
                    List<String> paths = zooKeeper.getChildren(servicePath, this);
                    for (String path : paths) {
                        byte[] data = zooKeeper.getData(servicePath + "/" + path, null, null);
                        addServiceToTree(servicePath, data);
                    }
                }
                for (String serviceName : serviceTree.keySet()) {
                    log.info("find service:{}", serviceName);
                }
            } catch (Exception e) {
                log.error("find service error: {}", e);
            }
        }

        private void addServiceToTree(String servicePath, byte[] data) {
            ServiceInfo serviceInfo = JSONObject.parseObject(data, ServiceInfo.class);
            List<ServiceInfo> serviceInfos = serviceTree.get(servicePath);
            if (CollectionUtils.isEmpty(serviceInfos)) {
                serviceInfos = Lists.newArrayList(serviceInfo);
            } else {
                serviceInfos.add(serviceInfo);
            }
            serviceTree.put(servicePath, serviceInfos);
        }

        @Override
        public void process(WatchedEvent event) {
            //return watch path ,not child path
            String path = event.getPath();
            log.info("event happened and serviceTree size{}", serviceTree.size());
            boolean watchAgain = false;
            //event is one-time trigger, so we need have a watch again(to avoid concurrency problems it's may have a distribute lock)
            try {
                List<String> paths = zooKeeper.getChildren(path, this);
                watchAgain = true;
                for (String s : serviceTree.keySet()) {
                    if (path.equals(s)) {
                        //child node changed
                        if (event.getType().equals(Event.EventType.NodeChildrenChanged)) {
                            for (String servicePath : paths) {
                                byte[] data = zooKeeper.getData(path + "/" + servicePath, null, null);
                                addServiceToTree(servicePath, data);
                            }
                            log.info("serviceTree changed, new size{}", paths.size());
                        }
                    }
                }
            } catch (Exception e) {
                log.error("event process error: {}", e);
                if (!watchAgain) {
                    try {
                        zooKeeper.getChildren(path, this);
                    } catch (KeeperException | InterruptedException e1) {
                        log.error("Incurable, start over again");
                    }
                }
            }
        }
    }
}
