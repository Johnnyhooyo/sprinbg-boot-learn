package com.hyq.zookeeper.service;

import com.google.common.collect.Lists;
import com.hyq.zookeeper.core.LockException;
import com.hyq.zookeeper.core.ZooKeeperBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author dibulidohu
 * @classname ZooLock
 * @date 2019/5/1711:20
 * @description
 */
@Slf4j
public class ZooLock {

    private static final String ROOT_NODE = "/locks";
    private ZooKeeper zooKeeper;
    private String CUR_LOCK;
    private String lockName;
    private String waitLock;
    private CountDownLatch count;
    protected boolean lockHold;
    private static final String lock_ = "_lock_";
    private ZooKeeperBase zooKeeperBase;
    private Watcher watcher = new Watcher();

    public ZooLock(ZooKeeperBase zooKeeperBase, String lockName) {
        this.zooKeeperBase = zooKeeperBase;
        this.lockName = lockName;
        count = new CountDownLatch(1);
    }

    public void init() {
        try {
            zooKeeper = zooKeeperBase.getZooKeeper();
            Stat stat = zooKeeper.exists(ROOT_NODE, false);
            if (null == stat) {
                zooKeeper.create(ROOT_NODE, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (InterruptedException | KeeperException e) {
            log.error("Zookeeper Lock init error,{}", e);
        }
    }

    public synchronized boolean lock() throws InterruptedException {
        try {
            init();
            log.info("1111111");
            if (lockName.contains(lock_)) {
                throw new LockException("0001", "illegal lock name");
            }
            if (lockHold) {
                throw new LockException("0002", "do not try get same lock again");
            } else {
                CUR_LOCK = zooKeeper.create(ROOT_NODE + "/" + lockName + lock_, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
                log.info("node {} has created in date:{}", CUR_LOCK, System.currentTimeMillis());
                watcher.checkLock();
                count.await();
                log.info("thread {} wait lock !!", Thread.currentThread());
                if (lockHold) {
                    log.info("thread {} get lock !!", Thread.currentThread());
                    return true;
                }
            }
        } catch (KeeperException e) {
            log.error("thread:{} try lock error:{}", Thread.currentThread(), e);
        } catch (Exception e) {
            if (e instanceof LockException) {
                throw e;
            }
            log.error("thread:{} try lock error:{}", Thread.currentThread(), e);
        }
        return false;
    }

    public synchronized void unLock() {
        if (CUR_LOCK == null) {
            throw new LockException("0003", "you r not the lock ownner");
        }
        try {
            Stat stat = zooKeeper.exists(CUR_LOCK, false);
            if (stat != null) {
                zooKeeper.delete(CUR_LOCK, stat.getVersion());
                log.info("current lock {} is deleted", CUR_LOCK);
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("thread {} delete node error {}", Thread.currentThread(), e);
        }
    }

    class Watcher implements org.apache.zookeeper.Watcher {
        private synchronized void checkLock() {
            try {
                List<String> children = zooKeeper.getChildren(ROOT_NODE, false);
                //find all child node below root node
                List<String> curLockList = Lists.newArrayList();
                for (String child : children) {
                    String node = child.split(lock_)[0];
                    if (node.equals(lockName)) {
                        curLockList.add(child);
                    }
                }
                Collections.sort(curLockList);
                log.info("current lock {}, and first lock {}", CUR_LOCK, curLockList.get(0));
                if (CUR_LOCK.equals(ROOT_NODE + "/" + curLockList.get(0))) {
                    lockHold = true;
                    count.countDown();
                } else {
                    int index = Collections.binarySearch(curLockList, CUR_LOCK.substring(CUR_LOCK.lastIndexOf("/") + 1));
                    waitLock = curLockList.get(index - 1);
                    Stat stat = zooKeeper.exists(ROOT_NODE + "/" + waitLock, this);
                    //log.info("stat {}", stat);
                    if (null == stat) {
                        log.info("lock {} check now", CUR_LOCK);
                        checkLock();
                    } else {
                        log.info("current lock {} wait for {}, wait lock stat {}", CUR_LOCK, waitLock, stat);
                    }
                }
            } catch (KeeperException | InterruptedException e) {
                log.error("thread {} check lock condition error {}--------------------", Thread.currentThread(), e);
            }
        }

        @Override
        public void process(WatchedEvent event) {
            if (!event.getPath().equals(String.format("%s/%s", ROOT_NODE, waitLock))) {
                log.info("event from node:{}", event.getPath());
                log.info("Ignoring call for node: {}", waitLock);
                return;
            }
            if (event.getType().equals(Event.EventType.NodeDeleted)) {
                log.info("node {} has been deleted, it is {} turn", event.getPath(), CUR_LOCK);
                checkLock();
                log.info("check after {}", Thread.currentThread());
            } else {
                log.info("thread {} ignore event type {}", Thread.currentThread(), event.getType());
            }
        }
    }
}
