package com.hyq.chatroom.delay;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author：huyuanqiang
 * @time: 2021-01-22 10:50
 * @description: 时间轮的节点
 **/
@Slf4j
public class Wheel {

    public Wheel next;
    public Wheel before;

    private int remainRound;

    private int position;

    private BaseTimeUpTask timeUpTask;

    public Wheel(int remainRound, int position, BaseTimeUpTask timeUpTask) {
        this.remainRound = remainRound;
        this.position = position;
        this.timeUpTask = timeUpTask;
    }

    public void execute(Date startTime) {
        remainRound--;
        if (remainRound == 0) {
            timeUpTask.execute();
            before.next = next;
        }
        if (Objects.nonNull(next)) {
            next.execute(startTime);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            log.info("当前时间点任务执行成功,开始时间：{}。结束时间:{}", simpleDateFormat.format(startTime), simpleDateFormat.format(new Date()));
        }
    }


}
