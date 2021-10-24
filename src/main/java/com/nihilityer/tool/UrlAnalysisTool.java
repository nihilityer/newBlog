package com.nihilityer.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @PackageName com.nihilityer
 * @ClassName UrlAnalysisTool
 * @Description 通过url初始化，返回key后由配置类设置CssSelect
 * @Author nihilityer
 * @Date 2021/10/23 11:36
 */
public class UrlAnalysisTool {
    private final String url;
    private final String urlRegex;
    private final String key;
    private String book;
    private String titleCssSelector;
    private String contentCssSelector;
    private String contentRegex = null;
    private String bookCssSelect;
    UniversalNovelCrawler universalNovelCrawler;
    Logger logger;

    /**
     * 构造方法，只定义了这个，因为这个类运行必须使用url初始化urlRegex
     * @param url 需要爬取小说的第一章url
     */
    public UrlAnalysisTool(String url) {
        if (!(url.matches("^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$") || url.matches("^https://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$"))) {
            throw new RuntimeException("url错误!");
        }
        this.url = url;
        logger = LoggerFactory.getLogger(UrlAnalysisTool.class);

        String[] splitWithPoint = url.split("[/.]");
        if (splitWithPoint[splitWithPoint.length - 1].matches("[0-9]+")) {
            throw new RuntimeException("这不是小说的阅读界面");
        }
        StringBuilder stringBuffer = new StringBuilder();
        assert splitWithPoint.length > 3;
        for (int i = 0; i < splitWithPoint.length; i++) {
            if (i == splitWithPoint.length - 2 && !splitWithPoint[i+1].matches("[0-9]+")) {
                stringBuffer.append("[0-9]+.");
            } else if (i == 2 || i == 3){
                stringBuffer.append(splitWithPoint[i]);
                stringBuffer.append(".");
            } else if (i == splitWithPoint.length - 1){
                stringBuffer.append(splitWithPoint[i]);
            } else {
                stringBuffer.append(splitWithPoint[i]);
                stringBuffer.append("/");
            }
        }
        key = splitWithPoint[3];
        book = splitWithPoint[splitWithPoint.length - 3];
        urlRegex = stringBuffer.toString();
        logger.info("urlRegex = " + urlRegex);
    }

    /**
     *
     * @param depth 爬取深度
     * @throws Exception 爬虫框架start方法抛出的异常
     */
    public void start(int depth) throws Exception {
        universalNovelCrawler = new UniversalNovelCrawler(url, urlRegex, titleCssSelector, contentCssSelector, book, bookCssSelect);
        if (contentRegex != null) {
            universalNovelCrawler.setContentRegex(contentRegex);
        }
        universalNovelCrawler.start(depth);
    }

    public void setTitleCssSelector(String titleCssSelector) {
        this.titleCssSelector = titleCssSelector;
        logger.info("setTitleCssSelector: " + titleCssSelector);
    }

    public void setContentCssSelector(String contentCssSelector) {
        this.contentCssSelector = contentCssSelector;
        logger.info("setContentCssSelector: " + contentCssSelector);
    }

    public void setContentRegex(String contentRegex) {
        this.contentRegex = contentRegex;
        logger.info("setContentRegex: " + contentRegex);
    }

    public void setBook(String book) {
        this.book = book;
        logger.info("setBook: " + book);
    }

    public void setBookCssSelect(String bookCssSelect) {
        this.bookCssSelect = bookCssSelect;
    }

    /**
     *
     * @return 通过通用爬虫类获取爬取小说的名字
     */
    public String getBook() {
        return universalNovelCrawler.getBook();
    }

    public String getKey() {
        return key;
    }
}
