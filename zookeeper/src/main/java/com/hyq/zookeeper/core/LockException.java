package com.hyq.zookeeper.core;

/**
 * @author dibulidohu
 * @classname LockException
 * @date 2019/5/1716:39
 * @description
 */
public class LockException extends RuntimeException {
    private String code;

    public LockException(String message, String code) {
        super(message);
        this.code = code;
    }
}
