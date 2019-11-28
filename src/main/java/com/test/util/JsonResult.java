package com.test.util;

import java.io.Serializable;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JsonResult implements Serializable {
    private static final long serialVersionUID = 1071681926787951549L;

    /**
     * 0异常，错误
     * 1无数据
     */
    private Integer code;
    private String operate;
    private String message;
    private Object data;


    //查询无数据返回
    public static JsonResult success(Integer code,String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(1);
        jsonResult.setOperate("success");
        jsonResult.setMessage(message);
        jsonResult.setData(null);
        return jsonResult;
    }

    public static JsonResult success(String message, Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        jsonResult.setOperate("success");
        jsonResult.setMessage(message);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult success() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        jsonResult.setOperate("success");
        return jsonResult;
    }

    public static JsonResult success(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        jsonResult.setOperate("success");
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult success(String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        jsonResult.setOperate("success");
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static JsonResult error() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setOperate("failed");
        return jsonResult;
    }

    public static JsonResult error(String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setOperate("failed");
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static JsonResult error(String message, Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setOperate("failed");
        jsonResult.setMessage(message);
        jsonResult.setData(data);
        return jsonResult;
    }

    public JsonResult(Throwable throwable) {
        this.operate = "failed";
        if (throwable instanceof NullPointerException) {
            this.code = 1001;
            this.message = "空指针：" + throwable;
        } else if (throwable instanceof ClassCastException) {
            this.code = 1002;
            this.message = "类型强制转换异常：" + throwable;
        } else if (throwable instanceof ConnectException) {
            this.code = 1003;
            this.message = "链接失败：" + throwable;
        } else if (throwable instanceof IllegalArgumentException) {
            this.code = 1004;
            this.message = "传递非法参数异常：" + throwable;
        } else if (throwable instanceof NumberFormatException) {
            this.code = 1005;
            this.message = "数字格式异常：" + throwable;
        } else if (throwable instanceof IndexOutOfBoundsException) {
            this.code = 1006;
            this.message = "下标越界异常：" + throwable;
        } else if (throwable instanceof SecurityException) {
            this.code = 1007;
            this.message = "安全异常：" + throwable;
        } else if (throwable instanceof SQLException) {
            this.code = 1008;
            this.message = "数据库异常：" + throwable;
        } else if (throwable instanceof ArithmeticException) {
            this.code = 1009;
            this.message = "算术运算异常：" + throwable;
        } else if (throwable instanceof RuntimeException) {
            this.code = 1010;
            this.message = "运行时异常：" + throwable;
        } else if (throwable instanceof Exception) {
            this.code = 9999;
            this.message = "未知异常" + throwable;
        }

    }

    public Integer getCode() {
        return this.code;
    }

    public String getOperate() {
        return this.operate;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    protected boolean canEqual(Object other) {
        return other instanceof JsonResult;
    }


    public String toString() {
        return "JsonResult(code=" + this.getCode() + ", operate=" + this.getOperate() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }

    public JsonResult() {
    }

    public JsonResult(Integer code, String operate, String message, Object data) {
        this.code = code;
        this.operate = operate;
        this.message = message;
        this.data = data;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>(3);
        result.put("message", this.message);
        result.put("code", this.code);
        result.put("Data", this.data);
        return result;

    }
}