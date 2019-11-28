package com.test.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装json返回
 *
 * @author
 * @create 2019-11-17 13:19
 */
@Data
public class JsonData {
    private boolean ret;
    private String msg;
    private Object data;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.setData(object);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.setData(object);
        return jsonData;
    }

    public static JsonData success() {
        return new JsonData(true);
    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public static JsonData fail() {
        JsonData jsonData = new JsonData(false);
        return jsonData;
    }

    public static JsonData fail(String msg,Object data) {
        JsonData jsonData = new JsonData(false);
        jsonData.setData(data);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public static JsonData fail(Object data) {
        JsonData jsonData = new JsonData(false);
        jsonData.setData(data);
        return jsonData;
    }

    //返回数据封装成map
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("Data", data);
        return result;

    }
}
