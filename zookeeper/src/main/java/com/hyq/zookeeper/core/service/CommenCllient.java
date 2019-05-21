package com.hyq.zookeeper.core.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.utils.CloseableUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author dibulidohu
 * @classname CommenCllient
 * @date 2019/5/219:19
 * @description
 */
@Slf4j
public class CommenCllient {

    private static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";


    public static String post(ServiceEnum serviceEnum, Object object) throws IOException {
        ServiceInfo serviceInfo = ServiceConsumer.getServiceInfo(serviceEnum.getName());
        String serviceAddr = serviceInfo.getServiceAddr();
        return httpPost(object, serviceAddr);
    }

    public static String httpPost(Object object, String serviceAddr) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost("http://" + serviceAddr);
            String encodedParam;
            if (object instanceof  String) {
                encodedParam = (String)object;
            } else {
                encodedParam = JSONObject.toJSONString(object);
            }
            StringEntity uefEntity = new StringEntity(encodedParam, "UTF-8");
            uefEntity.setContentType(JSON_CONTENT_TYPE);
            uefEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    JSON_CONTENT_TYPE));
            httpPost.setEntity(uefEntity);
            log.info("executing request {}", httpPost.getURI());
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                response.close();
            }
        } finally {
            closeableHttpClient.close();
        }
        return null;
    }
}
