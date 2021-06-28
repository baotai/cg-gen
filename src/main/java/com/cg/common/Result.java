package com.cg.common;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 200 表示成功
 * 500 表示错误，错误信息在msg中
 * 501 bean验证错误，不管有多少个错误都以  map形式返回
 * 502 拦截器拦截到用户token出错
 * 555 异常抛出错误信息
 * 556 用户QQ校验异常
 */
public class Result {

    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 相应数据
     */
    private Object data;
    /**
     * 相应消息
     */
    private String msg;
    /**
     * 相应业务状态
     */
    private Integer status;

    public Result() {
    }

    public Result(Object data, String msg, Integer status) {
        this.data = data;
        this.msg = msg;
        this.status = status;
    }

    public static Result ok() {
        return new Result(null, "OK", 200);
    }

    public static Result ok(Object data) {
        return new Result(data, "OK", 200);
    }

    public static Result errorMsg(String msg) {
        return new Result(null, msg, 500);
    }

    public static Result errorMap(Object data) {
        return new Result(data, "error", 501);
    }

    public static Result errorTokenMsg(String msg) {
        return new Result(null, msg, 502);
    }

    public static Result errorException(String msg) {
        return new Result(null, msg, 555);
    }

    public static Result errorUserQQ(String msg) {
        return new Result(null, msg, 556);
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getStatus() {
        return status;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
