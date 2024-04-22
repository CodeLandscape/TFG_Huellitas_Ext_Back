package com.preving.restapi.base.exceptions.errors;

import java.io.UnsupportedEncodingException;

public class RestApiErrorDetail {

    private String key;
    private String message;

    public RestApiErrorDetail() {
    }

    public RestApiErrorDetail (String message) {
        this(null, message);
    }

    public RestApiErrorDetail(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public RestApiErrorDetail setKey(String key) {
        this.key = key;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestApiErrorDetail setMessage(String message) {
        try {
            byte[] ptext = message.getBytes("ISO_8859_1");
            this.message = new String(ptext, "UTF-8");
        } catch (UnsupportedEncodingException e){
            this.message = message;
        }
        return this;
    }

    @Override
    public String toString() {
        return "RestApiErrorDetail{" +
                "key='" + key + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
