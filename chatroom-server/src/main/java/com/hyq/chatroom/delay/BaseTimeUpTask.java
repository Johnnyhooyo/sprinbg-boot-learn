package com.hyq.chatroom.delay;

import lombok.extern.slf4j.Slf4j;

/**
 * @author：huyuanqiang
 * @time: 2021-01-22 10:41
 * @description: 延时结束需要执行的任务
 **/
@Slf4j
public abstract class BaseTimeUpTask {

    public void execute() {
        log.info("{}延时结束执行任务", this.getClass().getName());
    }

}
