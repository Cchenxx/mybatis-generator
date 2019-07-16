package com.frank.mybatis.utils;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;

public class JsonUtil {

    public static final String dataFormat = "yyyy-MM-dd HH:mm:ss";

    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }

        return JSON.toJSONStringWithDateFormat(object, dataFormat);
    }

    public static String toJson(Object object, SerializerFeature... features) {
        if (object == null) {
            return null;
        }

        return JSON.toJSONStringWithDateFormat(object, dataFormat, features);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(Type type, String json) {
        return (T) JSON.parseObject(json, type);
    }

    public static <T> T fromJson(Class<T> clazz, String json) {
        return JSON.parseObject(json, clazz);
    }


}