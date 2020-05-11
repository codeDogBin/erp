package com.my.erp.sys.common;

public enum  StateUtil {
    OK("OK"),
    Fail("FAIL");
    private  String value;
    StateUtil(String value){this.value = value;}
    public  String getValue(){return value;}
}
