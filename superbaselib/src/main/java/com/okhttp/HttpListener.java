package com.okhttp;

/**
 * Created by 蔡永汪 on 2017/8/17.
 */

public interface HttpListener<T> {
    void data(T data);
}
