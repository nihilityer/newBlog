package com.nihilityer.entity;

import lombok.Data;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName CrawlNovelResponse
 * @Description success为1则至少爬取了一章，downloadUrl是方便前端直接读取下载的，bookName是给前端显示的，因为url回转义中文。
 * @Author nihilityer
 * @Date 2021/10/24 19:14
 */
@Data
public class CrawlNovelResponse {
    private Integer success;
    private String downloadUrl;
    private String bookName;
}
