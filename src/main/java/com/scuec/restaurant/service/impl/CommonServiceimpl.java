package com.scuec.restaurant.service.impl;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.service.CommonService;
import com.scuec.restaurant.utils.UpoadUtil;
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
import java.util.UUID;

@Service
public class CommonServiceimpl implements CommonService {

//    @Value("${reggie.path}")
//    private String basePath;  //动态获取保存的地址
//
//    @Override
//    public String upload(MultipartFile file){ //此处参数名要与前端发送的一致
//
//        //使用UUID重新生成文件名称，防止文件名称重复
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        // 获得文件原始名称
//        String fileName = file.getOriginalFilename();
//        // 获得文件后缀名称
//        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//        // 生成最新的uuid文件名称
//        String newFileName = uuid + "."+ suffixName;
//
//        //创建一个目录文件
//        File saveFile = new File(basePath);
//        if(!saveFile.exists()){
//            //目录不存在，创建该目录
//            saveFile.mkdirs();
//        }
//
//        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
//        try {
//            //将临时文件转存到指定位置
//            file.transferTo(new File(basePath + newFileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return newFileName;
//    }
//
//    @Override
//    public void download(String name, HttpServletResponse response){
//        try {
//            //输入流，通过输入流读取文件内容
//            FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));
//            //输出流，通过输出流将文件写回浏览器，在浏览器中展示图片
//            ServletOutputStream outputStream = response.getOutputStream();
//
//            int len=0;
//            byte[] bytes = new byte[1024];
//            while ((len=fileInputStream.read(bytes))!=-1){
//                outputStream.write(bytes,0,len);
//                outputStream.flush();//刷新
//            }
//            //关闭资源
//            outputStream.close();
//            fileInputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



        public String upload(MultipartFile multipartFile) {
            // 判断为空
            if (multipartFile.isEmpty()) {
                throw new GlobalException(ResponseCode.ERROR, "文件不能为空");
            }

            // 判断大小
            boolean mb = UpoadUtil.checkFileSize(multipartFile.getSize(), 1, "mb");
            if (!mb) {
                throw new GlobalException(ResponseCode.ERROR, "上传文件大小超出范围");
            }

            // 获取文件名
            String filename = multipartFile.getOriginalFilename();
            System.out.println("文件名 = " + filename);
            // 获取文件后缀
            if (filename != null) {
                // 获取文件后缀
                String suffixName = filename.substring(filename.lastIndexOf("."));
                System.out.println("上传文件的后缀： " + suffixName);
                String checkStr = ".png,.jpg,.jpeg";
                boolean contains = checkStr.contains(suffixName);
                // 判断格式
                if (!contains) { ;
                    throw new GlobalException(ResponseCode.ERROR, "上传文件后缀错误");
                }

                // 文件上传之后的名字
                filename = UUID.randomUUID().toString().replace("-", "") + suffixName;
                // 文件上传后的路径（加上上传后的文件名字就是上传后的路径）
                // 将文件需要上传的路径加上日期，按照每个月创建一个文件夹

                String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                String filePath1 = null;
                try {
                    filePath1 = ResourceUtils.getURL("classpath:").getPath()+"/static/upload_file/"+ datePath;
                    // 文件夹，不存在就创建
                    File file = new File(filePath1);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    System.out.println(filePath1);
                    // 文件上传到指定路径
                    multipartFile.transferTo(new File(filePath1+"/"+filename));
                    System.out.println(ResourceUtils.getURL("classpath"));
                    System.out.println(filePath1+"/"+filename);
                    System.out.println("/upload_file/"+datePath+"/"+filename);
                    // 返回结果  回显
                    return  ("/upload_file/"+datePath+"/"+filename);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            throw new GlobalException(ResponseCode.ERROR, "上传文件失败");
        }

}
