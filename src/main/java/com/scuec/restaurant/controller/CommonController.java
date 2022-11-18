package com.scuec.restaurant.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/common")
@ApiOperation(value = "图片上传下载", notes = "图片上传下载")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;  //动态获取保存的地址

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file){ //此处参数名要与前端发送的一致

        //使用UUID重新生成文件名称，防止文件名称重复
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获得文件原始名称
        String fileName = file.getOriginalFilename();
        // 获得文件后缀名称
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // 生成最新的uuid文件名称
        String newFileName = uuid + "."+ suffixName;

        //创建一个目录文件
        File saveFile = new File(basePath);
        if(!saveFile.exists()){
            //目录不存在，创建该目录
            saveFile.mkdirs();
        }

        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath + newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    //文件下载
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));
            //输出流，通过输出流将文件写回浏览器，在浏览器中展示图片
            ServletOutputStream outputStream = response.getOutputStream();

            int len=0;
            byte[] bytes = new byte[1024];
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();//刷新
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
