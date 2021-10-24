package com.nihilityer;

import com.nihilityer.service.AccordConfigCrawl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private AccordConfigCrawl accordConfigCrawl;

    @Test
    void contextLoads() throws Exception {
        accordConfigCrawl.init("http://www.shencou.com/read/0/313/18583.html");
        accordConfigCrawl.start(2);
        System.out.println("accordConfigCrawl.getBook() = " + accordConfigCrawl.getBook());
    }

}
