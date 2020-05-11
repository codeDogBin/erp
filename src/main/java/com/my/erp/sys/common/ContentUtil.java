package com.my.erp.sys.common;

public class ContentUtil {

    public static String arrayToString(String[] contents){
        if(contents==null){
            return null;
        }
        String content = String.join("-",contents);
        return content.replaceAll("null","0");
    }
    public static String[] stringToArray(String content){
        if(content==null){
            return null;
        }
       return content.split("-");
    }
}
