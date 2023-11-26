package com.itheima.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("qiniu")
@Component
@Data
public class QiniuProperty {

    private String accessKey;
    private String bucketName;
    private String secretKey;

}
