package com.hyq.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @author dibulidohu
 * @classname CuratorLock
 * @date 2019/5/1912:14
 * @description
 */
@Slf4j
public class CuratorLock {
    private InterProcessLock lock;
    private static final String ROOT_PATH = "/curator/";
    public CuratorLock(CuratorFramework client, String lockName) {
        lock = new InterProcessMutex(client, ROOT_PATH + lockName);
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
