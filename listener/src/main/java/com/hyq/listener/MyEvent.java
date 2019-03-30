package com.hyq.listener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @author dibulidohu
 * @classname MyEvent
 * @date 2019/3/3014:08
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MyEvent extends ApplicationEvent {

    private int code;
    private String desc;

    public MyEvent(Object source, int code, String desc) {
        super(source);
        this.code = code;
        this.desc = desc;
    }

    public MyEvent(Object source) {
        super(source);
    }
}
