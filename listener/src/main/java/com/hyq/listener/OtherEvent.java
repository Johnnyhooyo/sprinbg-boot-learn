package com.hyq.listener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @author dibulidohu
 * @classname OtherEvent
 * @date 2019/3/3014:16
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OtherEvent extends ApplicationEvent {
    private int code;
    private String desc;

    public OtherEvent(Object source, int code, String desc) {
        super(source);
        this.code = code;
        this.desc = desc;
    }

    public OtherEvent(Object source) {
        super(source);
    }

}
