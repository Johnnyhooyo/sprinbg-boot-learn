package com.hyq.zookeeper.controller;

import com.hyq.zookeeper.core.ZooKeeperBase;
import com.hyq.zookeeper.curator.CuratorFactoryBean;
import com.hyq.zookeeper.curator.CuratorLock;
import com.hyq.zookeeper.service.ZooLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executor;

/**
 * @author dibulidohu
 * @classname LockController
 * @date 2019/5/1716:13
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/zookeeper/lock")
public class LockController {

    @Resource
    Executor executor;

    @Resource
    ZooKeeperBase zooKeeperBase;

    @GetMapping(value = "/test")
    public void lockTest(HttpServletRequest request) {
        for (int i = 0; i< 10; i++) {
            executor.execute(() -> {
                try {
                    ZooLock zooLock = new ZooLock(zooKeeperBase, "distribute");
                    if (zooLock.lock()){
                        Thread.sleep(1000);
                        zooLock.unLock();
                    }
                } catch (InterruptedException e) {
                    log.error("thread {} error!!!", Thread.currentThread());
                }
            });
        }
    }

//    @Resource
//    CuratorFactoryBean curatorFactoryBean;
//
//    @GetMapping(value = "/curator")
//    public void curator(HttpServletRequest request) {
//        log.info("curator start");
//        for (int i = 0; i< 10; i++) {
//            executor.execute(() -> {
//                try {
//                    CuratorLock lock  = new CuratorLock(curatorFactoryBean.getObject(), "curatorLock");
//                    if (lock.lock()){
//                        log.info("thread [{}]get lock", Thread.currentThread());
//                        Thread.sleep(1000);
//                        lock.unLock();
//                    }
//                } catch (Exception e) {
//                    log.error("thread {} error!!! {}", Thread.currentThread(), e);
//                }
//            });
//        }
//    }
}
