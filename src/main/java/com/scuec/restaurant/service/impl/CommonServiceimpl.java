package com.scuec.restaurant.service.impl;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.service.CommonService;
import com.scuec.restaurant.utils.UpoadUtil;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CommonServiceimpl implements CommonService {

    public class Constant {
        public static final String ITEM_PICTURE_PATH = "D:\\java_workspace\\restaurant\\Tools\\";
    }


//        public String upload(MultipartFile multipartFile) {
//            // 判断为空
//            if (multipartFile.isEmpty()) {
//                throw new GlobalException(ResponseCode.ERROR, "文件不能为空");
//            }
//
//            // 判断大小
//            boolean mb = UpoadUtil.checkFileSize(multipartFile.getSize(), 1, "mb");
//            if (!mb) {
//                throw new GlobalException(ResponseCode.ERROR, "上传文件大小超出范围");
//            }
//
//            // 获取文件名
//            String filename = multipartFile.getOriginalFilename();
//            System.out.println("文件名 = " + filename);
//            // 获取文件后缀
//            if (filename != null) {
//                // 获取文件后缀
//                String suffixName = filename.substring(filename.lastIndexOf("."));
//                System.out.println("上传文件的后缀： " + suffixName);
//                String checkStr = ".png,.jpg,.jpeg";
//                boolean contains = checkStr.contains(suffixName);
//                // 判断格式
//                if (!contains) { ;
//                    throw new GlobalException(ResponseCode.ERROR, "上传文件后缀错误");
//                }
//
//                // 文件上传之后的名字
//                filename = UUID.randomUUID().toString().replace("-", "") + suffixName;
//                // 文件上传后的路径（加上上传后的文件名字就是上传后的路径）
//                // 将文件需要上传的路径加上日期，按照每个月创建一个文件夹
//
//                String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//
//                String filePath1 = null;
//                try {
//                    filePath1 = ResourceUtils.getURL("classpath:").getPath()+"/static/upload_file/"+ datePath;
//                    // 文件夹，不存在就创建
//                    File file = new File(filePath1);
//                    if(!file.exists()){
//                        file.mkdirs();
//                    }
//                    System.out.println(filePath1);
//                    // 文件上传到指定路径
//                    multipartFile.transferTo(new File(filePath1+"/"+filename));
//                    System.out.println(ResourceUtils.getURL("classpath"));
//                    System.out.println(filePath1+"/"+filename);
//                    System.out.println("/upload_file/"+datePath+"/"+filename);
//                    // 返回结果  回显
//                    return  ("/upload_file/"+datePath+"/"+filename);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            throw new GlobalException(ResponseCode.ERROR, "上传文件失败");
//        }




        public String getId(){

            return UUID.randomUUID().toString().replaceAll("-","");
        }

    public String upload(@RequestParam("file") MultipartFile file) {
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
    }


}
