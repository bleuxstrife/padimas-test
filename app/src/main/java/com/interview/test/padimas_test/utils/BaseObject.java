package com.interview.test.padimas_test.utils;

public class BaseObject {
    private Object data;
    private int code;

    public BaseObject(Object data, int code){
        this.data = data;
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
