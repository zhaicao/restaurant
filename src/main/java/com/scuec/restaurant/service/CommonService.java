package com.scuec.restaurant.service;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

    /**
     * 上传文件
     * @param file 文件路径
     * @return 返回上传后的文件名（已插入数据库），失败抛全局异常
     */
    String upload(MultipartFile file);

    /**
     * 获取上传文件名
     * @return
     */
    String getId();
}
