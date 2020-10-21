package com.lkl.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 刘奎亮
 * @date 2020/10/20
 */
@Component
@ConfigurationProperties(prefix = "url")
public class MyProps {
    public String url;
    public String getUrl() {
        return url;
    }
    public MyProps setUrl(String url) {
        this.url = url;
        return this;
    }
}
