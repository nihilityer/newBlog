package com.nihilityer.service;

import com.nihilityer.config.FileConfig;
import com.nihilityer.config.WebInfoConfig;
import com.nihilityer.exception.FileException;
import com.nihilityer.tool.UrlAnalysisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @PackageName com.nihilityer.addWebCollector.tools.crawl
 * @ClassName AccordConfigCrawl
 * @Description 通过读取配置以及分析url进行爬取
 * @Author nihilityer
 * @Date 2021/10/23 19:42
 */
@Service
public class AccordConfigCrawl {

    private final WebInfoConfig webInfoConfig;
    private final Path textLocation;

    /**
     *
     * @param webInfoConfig 注入webinfo信息来获取网站的配置
     * @param fileConfig 通过这个配置获取txt文件的存储路径
     */
    @Autowired
    public AccordConfigCrawl(WebInfoConfig webInfoConfig, FileConfig fileConfig) {
        this.webInfoConfig = webInfoConfig;
        this.textLocation = Paths.get(fileConfig.getTextDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.textLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    UrlAnalysisTool urlAnalysisTool;

    /**
     * 通过工具类来初始化爬取需要的信息和配置
     * 分析url获取网站配置的key，通过key取出webinfo存储的cssSelect，用来适配不同网站
     * @param url 需要爬取小说第一章的url
     */
    public void init(String url) {
        urlAnalysisTool = new UrlAnalysisTool(url);
        String key = urlAnalysisTool.getKey();
        if (webInfoConfig.getMap().get(key) != null) {
            WebInfoConfig.UrlInfo urlInfo = webInfoConfig.getMap().get(key);

            urlAnalysisTool.setTitleCssSelector(urlInfo.getTitleCssSelect());
            urlAnalysisTool.setContentCssSelector(urlInfo.getContentCssSelect());
            urlAnalysisTool.setBookCssSelect(urlInfo.getBookCssSelect());

            if (urlInfo.isNeedContentRegex()) {
                urlAnalysisTool.setContentRegex(urlInfo.getContentRegex());
            }
        } else {
            urlAnalysisTool.setTitleCssSelector("h1");
            urlAnalysisTool.setContentCssSelector("#content");
            urlAnalysisTool.setBookCssSelect("#main > div > div > div > a:nth-child(3)");
        }
    }

    /**
     *
     * @param depth 这个数字代表的是爬取的章节的期望数（如果传入的url没有问题）如果传入的url不是第一章而是其他章节，那么章节会出现错误
     * @throws Exception 使用的爬虫框架start方法抛出的异常
     */
    public void start(int depth) throws Exception {
        urlAnalysisTool.start(depth);
    }

    /**
     *
     * @return 返回爬取到书的名字，方便生成downloadUrl以及前端显示
     */
    public String getBook() {
        return urlAnalysisTool.getBook();
    }

    /**
     *
     * @param fileName 需要下载的文件名称
     * @return 返回文件
     */
    public Resource loadTextAsResource(String fileName) {
        try {
            Path filePath = this.textLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }
}
