package com.nihilityer.controller;

import com.nihilityer.entity.CrawlNovelResponse;
import com.nihilityer.service.AccordConfigCrawl;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName CrawlNovelController
 * @Description 小说爬虫的controller，只实现了通过小说第一章页面进行爬取。
 * @Author nihilityer
 * @Date 2021/10/24 19:12
 */
@RestController
public class CrawlNovelController {
    private final AccordConfigCrawl accordConfigCrawl;
    private static final Logger logger = LoggerFactory.getLogger(CrawlNovelController.class);

    public CrawlNovelController(AccordConfigCrawl accordConfigCrawl) {
        this.accordConfigCrawl = accordConfigCrawl;
    }

    /**
     *
     * @param url 小说第一章页面的url
     * @param chapter 要爬取的章节数目
     * @return 返回json数据，success为1则至少爬取了一个章节，可以通过downloadUrl进行下载，bookName是用来给前端显示的，以后存入数据库也方便。
     * @throws Exception 爬虫框架的start方法抛出的异常
     */
    @PostMapping("/crawl/novel")
    public CrawlNovelResponse crawlNovel(@Param("url")String url, @Param("chapter")Integer chapter) throws Exception {
        CrawlNovelResponse crawlNovelResponse = new CrawlNovelResponse();

        accordConfigCrawl.init(url);
        accordConfigCrawl.start(chapter);

        String book = accordConfigCrawl.getBook();
        crawlNovelResponse.setBookName(book);

        if (book.matches("[0-9]+")) {
            crawlNovelResponse.setSuccess(0);
        } else {
            crawlNovelResponse.setSuccess(1);
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/getText/")
                .path(book + ".txt")
                .toUriString();
        crawlNovelResponse.setDownloadUrl(fileDownloadUri);
        return crawlNovelResponse;
    }

    /**
     *
     * @param fileName 需要下载的文件名，方便读取下载
     * @param request 下载需要的头部设置
     * @return 返回到浏览器以下载
     */
    @GetMapping("/getText/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = accordConfigCrawl.loadTextAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
