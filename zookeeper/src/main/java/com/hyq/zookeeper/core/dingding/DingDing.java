package com.hyq.zookeeper.core.dingding;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * @author dibulidohu
 * @classname DingDing
 * @date 2019/5/2111:40
 * @description
 */
@Slf4j
public class DingDing {

    /**
     * if u find this, u can send message to me;
     */
    private static final String WEB_HOOK = "https://oapi.dingtalk.com/robot/send?access_token=b5072525a4d682045c7d110530cf23764bfc94ad23b45b31b2a9cb3cbd7a3509";

    public boolean send(Object object) {
        try {
            String param;
            if (object instanceof String) {
                param = (String)object;
            } else {
                param = JSONObject.toJSONString(object);
            }
            byte[] bytes = HttpsUtil.doPost(WEB_HOOK, param);
            log.info("request :{}", JSONObject.toJSONString(object));
            JSONObject obj = JSONObject.parseObject(new String(bytes));
            log.info("response :{}", JSONObject.toJSONString(obj));
            if (null != obj && obj.containsKey("success") && obj.getBoolean("success")) {
                return true;
            }
        } catch (IOException e) {
            log.error("ding talk send error:{}", e);
        }
        return false;
    }

    @Data
    public class Message {
        private String msgtype;
        private TextBody text;
        private At at;
        private Link link;
        private MarkDown markDown;
        private ActionCard actionCard;
    }

    @Data
    public class ActionCard {
        private String title;
        private String text;
        private String hideAvatar;
        private String btnOrientation;
        private String singleTitle;
        private String singleURL;
    }
    @Data
    public class MarkDown {
        private String title;
        private String text;
    }
    @Data
    public class Link {
        private String text;
        private String title;
        private String picUrl;
        private String messageUrl;
    }
    @Data
    public class  At {
        private List<String> atMobiles;
        private Boolean isAtAll;
    }
    @Data
    public class TextBody {
        private String content;
    }
}
