package com.scuec.restaurant.service.impl;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.service.CommonService;
import com.scuec.restaurant.utils.UpoadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
@Slf4j
public class CommonServiceimpl implements CommonService {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String fileLimitSize;
    @Value("${upload.file.suffix}")
    private String suffix;
    @Value("${upload.file.basic-path}")
    private String basicPath;

    public String upload(MultipartFile multipartFile) {
         //判断为空
        if (multipartFile.isEmpty()) {
            throw new GlobalException(ResponseCode.ERROR, "文件不能为空");
        }
        // 判断大小
        boolean mb = UpoadUtil.checkFileSize(multipartFile.getSize(), fileLimitSize);
        if (!mb) {
            throw new GlobalException(ResponseCode.ERROR, "上传文件大小超出范围");
        }
        // 获取文件名
        String filename = multipartFile.getOriginalFilename();
        // 获取文件后缀
        if (filename != null) {
            // 获取文件后缀
            String suffixName = filename.substring(filename.lastIndexOf("."));
            log.info("上传文件的后缀： " + suffixName);
            boolean contains = suffix.contains(suffixName);
            // 判断格式
            if (!contains) { ;
                throw new GlobalException(ResponseCode.ERROR, "上传文件后缀错误");
            }
            // 文件上传之后的名字
            filename = getId() + suffixName;
            // 文件上传后的路径（文件上传至target目录中即编译文件目录，可保证服务运行时可访问文件）
            String filePath = null;
            try {
                filePath = ResourceUtils.getURL("classpath:").getPath() + File.separator +
                        "static" + File.separator + "upload_file";
                // 文件夹，不存在就创建
                File file = new File(filePath);
                if(!file.exists()){
                    file.mkdirs();
                }
                // 文件上传到指定路径
                multipartFile.transferTo(new File(filePath + File.separator + filename));
                log.info(basicPath + File.separator + filename);
                // 返回结果  回显
                return  filename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new GlobalException(ResponseCode.ERROR, "上传文件失败");
    }

    @Override
    public String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

/*    public String upload(@RequestParam("file") MultipartFile file) {
        // 判断为空
        if (file.isEmpty()) {
            throw new GlobalException(ResponseCode.ERROR, "文件不能为空");
        }

        // 判断大小
        boolean mb = UpoadUtil.checkFileSize(file.getSize(), 5, "mb");
        if (!mb) {
            throw new GlobalException(ResponseCode.ERROR, "上传文件大小超出范围");
        }
        //获得文件的后缀
        String filename = UUID.randomUUID() + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        //获取文件存储路径
        File filepath = new File(Constant.ITEM_PICTURE_PATH + filename);

//        String url = String.valueOf(filepath);
        try {
            //存文件
            file.transferTo(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ("http://localhost:8090/temp-image/" + filename);
    }*/
}