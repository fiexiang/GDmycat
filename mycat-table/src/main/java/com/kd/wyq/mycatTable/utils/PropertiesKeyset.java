package com.kd.wyq.mycatTable.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesKeyset {


    public Map<String,Object> getProperties(){//获取mycat属性文件内容，并封装为Map<String，Object>类型，String：属性名 Object：属性值

        Map<String,Object> map = new HashMap<String,Object>();

        InputStream inputStream = this.getClass().getResourceAsStream("/mycat.properties");

        Properties properties = new Properties();

        try {

            properties.load(inputStream);

            Set<Object> keySet = properties.keySet();

            for(Object obj : keySet){

                map.put(obj.toString(),properties.get(obj));

            }

        } catch (
                IOException e) {

            e.printStackTrace();

        }

        return map;

    }


}
