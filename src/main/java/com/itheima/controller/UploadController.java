package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile image) throws IOException {
        // uuid
        String uuid = UUID.randomUUID().toString();
        log.info(name,age, image);
        String originalFilename = image.getOriginalFilename();

        int i = originalFilename.lastIndexOf(".");

        String extenam = originalFilename.substring(i);

        image.transferTo(new File("G:\\java\\tlias\\src\\main\\resources\\static\\images",uuid+extenam));
        return Result.success();
    }
}
