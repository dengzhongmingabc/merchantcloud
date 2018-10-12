package com.yidao.core.utils;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dzm
 * Date: 14-1-24
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
@JsonPropertyOrder({"stat","msg","data"})
public class MyJson implements Serializable {

    Integer status=1;
    String message ="ok";
    Object data;

    public MyJson() {
    }

    public MyJson(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public MyJson(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
