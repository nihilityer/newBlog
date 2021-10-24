package com.nihilityer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @PackageName com.nihilityer.config
 * @ClassName FileConfig
 * @Description 文件存储路径配置
 * @Author nihilityer
 * @Date 2021/10/20 14:37
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileConfig {

    private String photoDir;
    private String textDir;

}