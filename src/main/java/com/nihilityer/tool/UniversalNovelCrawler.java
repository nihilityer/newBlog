package com.nihilityer.tool;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @PackageName com.nihilityer
 * @ClassName UniversalCrawler
 * @Description 通用小说爬取类，调用webCollector框架实现的专爬小说的类
 * @Author nihilityer
 * @Date 2021/10/23 11:01
 */
public class UniversalNovelCrawler extends BreadthCrawler {

    private final String urlRegex;
    private final String titleCssSelector;
    private final String contentCssSelector;
    private final String bookCssSelect;
    private String book;
    Logger logger;

    private String contentRegex = null;

    public UniversalNovelCrawler(String seed, String urlRegex, String titleCssSelector, String contentCssSelector, String book, String bookCssSelect) {
        super("crawl", true);
        this.addSeed(seed);
        this.addRegex(urlRegex);
        this.urlRegex = urlRegex;
        this.titleCssSelector = titleCssSelector;
        this.contentCssSelector = contentCssSelector;
        this.bookCssSelect = bookCssSelect;
        this.book = book;
        logger = LoggerFactory.getLogger(UniversalNovelCrawler.class);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {

        if (page.matchUrl(urlRegex)) {
            String url = page.url();
            logger.info("Url: " + url);
            String title = page.select(titleCssSelector).text();
            String content = page.select(contentCssSelector).text();

            if (contentRegex != null) {
                content = content.replaceAll(contentRegex, "");
            }

            content = content.replaceAll("( )+","\n");

            if (book.matches("[0-9]+")) {
                book = page.select(bookCssSelect).text().replace(" ", "");
            }

            try {
                FileTool.writeToText(title + "\n" + content, book);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBook() {
        return book;
    }

    public void setContentRegex(String contentRegex) {
        this.contentRegex = contentRegex;
    }
}
