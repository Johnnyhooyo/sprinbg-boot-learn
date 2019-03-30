package com.hyq.bind;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class PostInfo {

    private String title;
    private String content;

}
