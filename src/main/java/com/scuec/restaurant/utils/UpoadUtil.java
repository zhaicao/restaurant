package com.scuec.restaurant.utils;

public class UpoadUtil {
    /**
     * 判断文件大小
     * @param len 文件大小
     * @param size 限制的大小，格式"10MB"
     * @return
     */
    public static Boolean checkFileSize(Long len,String size){
        double fileSize = 0;
        // 提出数字并转类型
        double limitSize = Double.parseDouble(size.replaceAll("\\s*","").replaceAll("[^(0-9)]",""));
        // 提取单位
        String unit = size.replaceAll("\\s*","").replaceAll("[^(A-Za-z)]","");

        if("B".equals(unit.toUpperCase())){
            fileSize = (double) len;
        }else if("KB".equals(unit.toUpperCase())){
            fileSize = (double) len/1024;
        }else if("MB".equals(unit.toUpperCase())){
            fileSize = (double) len/1024/1024;
        }else if("GB".equals(unit.toUpperCase())){
            fileSize = (double) len/1024/1024/1024;
        }
        if(fileSize > limitSize) return false;
        return true;
    }
}

