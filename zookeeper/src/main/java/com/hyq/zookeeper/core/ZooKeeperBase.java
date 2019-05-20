package com.hyq.zookeeper.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.proto.WatcherEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author dibulidohu
 * @classname ZKClient
 * @date 2019/5/179:49
 * @description
 */
@Slf4j
@Component
public class ZooKeeperBase {

    private static final String CONNECT_STR = "148.70.215.26:2182,148.70.215.26:2183,148.70.215.26:2184";
    private static final int SESSION_TIMEOUT =  2000;
    private CountDownLatch count = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    public ZooKeeperBase() throws IOException {
        zooKeeper = new ZooKeeper(CONNECT_STR, SESSION_TIMEOUT, event -> {
            String path = event.getPath();
            Watcher.Event.KeeperState state = event.getState();
            Watcher.Event.EventType type = event.getType();
            WatcherEvent wrapper = event.getWrapper();
            log.info("path:{}, state:{}, type:{}, wrapper:{}", path, state, type, wrapper);
            if (Watcher.Event.KeeperState.SyncConnected.equals(state)) {
                if (Watcher.Event.EventType.None.equals(type)) {
                    count.countDown();
                    log.info("zookeeper建立连接成功！！！");
                }
            }
        });
    }

    public ZooKeeper getZooKeeper() throws InterruptedException {
        count.await();
        return zooKeeper;
    }

    public void colseClient() throws InterruptedException {
        if (null != zooKeeper) {
            zooKeeper.close();
            log.info("zookeeper关闭成功！！！");
        }
    }
}
