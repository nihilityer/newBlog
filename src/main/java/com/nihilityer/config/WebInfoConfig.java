package com.nihilityer.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @PackageName com.nihilityer.addWebCollector.config
 * @ClassName WebInfoConfig
 * @Description webinfo配置类
 * @Author nihilityer
 * @Date 2021/10/23 16:30
 */
@Configuration
@ConfigurationProperties(prefix = "webinfo")
public class WebInfoConfig {
    @Getter
    @Setter
    private Map<String, UrlInfo> map;

    @Getter
    @Setter
    @ToString
    public static class UrlInfo {
        private String url;
        private String titleCssSelect;
        private String contentCssSelect;
        private boolean needContentRegex;
        private String contentRegex;
        private String bookCssSelect;
    }
}
