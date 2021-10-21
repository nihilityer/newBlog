package com.nihilityer.controller;

import com.nihilityer.entity.FileResponse;
import com.nihilityer.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName FileUploadController
 * @Description
 * @Author nihilityer
 * @Date 2021/10/20 10:21
 */
@RestController
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private final FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/postFile")
    @ResponseBody
    public FileResponse addFile(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        String fileName = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/getFile/")
                .path(fileName)
                .toUriString();

        logger.info("add " + fileName);

        return new FileResponse(1,fileName + "!" + file.getContentType() + "!" + file.getSize(), fileDownloadUri);
    }

    @PostMapping("/postFiles")
    public List<FileResponse> addFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::addFile)
                .collect(Collectors.toList());
    }

    @GetMapping("/getFile/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

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
