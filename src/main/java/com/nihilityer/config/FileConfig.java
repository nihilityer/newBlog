package com.nihilityer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @PackageName com.nihilityer.config
 * @ClassName FileConfig
 * @Description
 * @Author nihilityer
 * @Date 2021/10/20 14:37
 */
@ConfigurationProperties(prefix = "file")
public class FileConfig {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}