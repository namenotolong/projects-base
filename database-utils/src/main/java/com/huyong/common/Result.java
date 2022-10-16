package com.huyong.common;

import lombok.Data;

/**
 * @author huyong
 * @description
 **/

@Data
public class Result<T> {

    private  T data;

    private String message;

    private Integer code;

    private boolean success;

    public T getData() {
        return data;
    }

    public static Result error(String msg) {
        final Result<Object> result = new Result<>();
        result.setSuccess(false);
        result.setMessage(msg);
        return result;
    }

    public static Result error(String msg, int code) {
        final Result<Object> result = new Result<>();
        result.setSuccess(false);
        result.setMessage(msg);
        result.setCode(code);
        return result;
    }

    public static Result success(Object data) {
        final Result<Object> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

}


