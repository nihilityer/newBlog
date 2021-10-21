package com.nihilityer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName FileResponse
 * @Description
 * @Author nihilityer
 * @Date 2021/10/20 14:44
 */
@Data
@AllArgsConstructor
public class FileResponse {
    private Integer success;
    private String message;
    private String url;
}