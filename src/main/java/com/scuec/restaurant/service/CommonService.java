package com.scuec.restaurant.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonService {

//    void download(String name,HttpServletResponse response);

//    String uploadImg(String uploadPath, int cacheSize, HttpServletRequest request);

    String upload(MultipartFile file);

    String getId();
}
