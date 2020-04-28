package com.my.erp.sys.common;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString@Setter
public class CacheBean {

    private String key;

    private  Object value;

    public String getKey() {
        return key;
    }

    public Object getValue() {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
