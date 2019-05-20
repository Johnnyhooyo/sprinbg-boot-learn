package com.hyq.zookeeper.core.service;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.nio.charset.StandardCharsets;

/**
 * @author dibulidohu
 * @classname ServiceInfo
 * @date 2019/5/2017:20
 * @description
 */
@Data
public class ServiceInfo {
    private String serviceName;
    private String serviceDesc;
    private String serviceAddr;
    private String instanceId;

    public byte[] toByte() {
        return JSONObject.toJSONString(this).getBytes(StandardCharsets.UTF_8);
    }
}
