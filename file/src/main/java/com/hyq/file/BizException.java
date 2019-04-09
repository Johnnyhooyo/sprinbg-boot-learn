package com.hyq.file;


/**
 * @author dibulidohu
 * @classname BizException
 * @date 2019/3/2913:16
 * @description  异常常量
 */
public class BizException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BizException(String message, int code) {
        super(message);
        this.code = code;
    }
}
