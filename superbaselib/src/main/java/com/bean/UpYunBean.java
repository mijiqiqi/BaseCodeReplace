package com.bean;

import java.io.Serializable;

public class UpYunBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8035997426331940038L;
    private String url;
    private int code;
    private String message;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
