package com.scuec.restaurant;

import com.scuec.restaurant.service.CommonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@Slf4j
public class uploadTest {
    @Autowired
    private CommonService commonService;

    @Test
    @PostMapping("/upload")
    @ApiOperation(value = "上传", notes = "上传图片")
    public String testUpLoad(@RequestParam(value = "file") MultipartFile file) {
        String newFileName = commonService.upload(file);
        log.warn(String.valueOf(newFileName));
        return newFileName;
    }
}
