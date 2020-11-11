package com.gdut.jiyi.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 阿里云短信验证码配置信息
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "ali.sms")
public class SendSmsConfig {
    private String accessKeyId;
    private String accessSecret;
    private String domain;
    private String version;
    private String action;
    private String  regionId;
    private String signName;
    private String  templateCode;
}
