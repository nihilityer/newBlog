package com.nihilityer.service;

import com.nihilityer.config.WebInfoConfig;
import com.nihilityer.tool.UrlAnalysisTool;
import org.springframework.stereotype.Service;

/**
 * @PackageName com.nihilityer.addWebCollector.tools.crawl
 * @ClassName AccordConfigCrawl
 * @Description
 * @Author nihilityer
 * @Date 2021/10/23 19:42
 */
@Service
public class AccordConfigCrawl {
    private final WebInfoConfig webInfoConfig;

    public AccordConfigCrawl(WebInfoConfig webInfoConfig) {
        this.webInfoConfig = webInfoConfig;
    }

    UrlAnalysisTool urlAnalysisTool;

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
        }
    }

    public void start(int depth) throws Exception {
        urlAnalysisTool.start(depth);
    }

    public String getBook() {
        return urlAnalysisTool.getBook();
    }
}
