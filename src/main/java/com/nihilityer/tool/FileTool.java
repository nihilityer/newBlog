package com.nihilityer.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @PackageName com.nihilityer
 * @ClassName FileTool
 * @Description
 * @Author nihilityer
 * @Date 2021/10/23 13:40
 */
public class FileTool {
    public static void writeToText(String text, String fileName) throws IOException {
        Logger logger = LoggerFactory.getLogger(FileTool.class);
        // 生成的文件路径
        String path = "./text/" + fileName + ".txt";
        File file = new File(path);
        if (!file.exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
            if (mkdirs) {
                logger.info("创建了新目录");
            }
            boolean newFile = file.createNewFile();
            if (newFile) {
                logger.info("创建了新文件：" + fileName + ".txt");
            }
        }
        // write 解决中文乱码问题
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        logger.info("文件写入成功！");
        bw.flush();
        bw.close();
        fw.close();

    }
}
