package com.scuec.restaurant.utils;

public class UpoadUtil {
        public static Boolean checkFileSize(Long len,Integer size,String unit){
            double fileSize = 0;
            if("B".equals(unit.toUpperCase())){
                fileSize = (double) len;
            }else if("KB".equals(unit.toUpperCase())){
                fileSize = (double) len/1024;
            }else if("MB".equals(unit.toUpperCase())){
                fileSize = (double) len/1024/1024;
            }else if("GB".equals(unit.toUpperCase())){
                fileSize = (double) len/1024/1024/1024;
            }
            if(fileSize>size)return false;
            return true;
        }
    }

