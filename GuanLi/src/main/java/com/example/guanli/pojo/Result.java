package com.example.guanli.pojo;

import lombok.Data;

@Data
public class Result {
    private Integer code;//状态码
    private String msg;//提示信息
    private Object data;//数据

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(1);
        result.setMsg("success");
        result.setData(data);
        return result;
    }
    public static Result success() {
        Result result = new Result();
        result.setCode(1);
        result.setMsg("success");
        result.setData(null);
        return result;
    }
    public static Result error( String message) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(message);
        result.setData(null);
        return result;
    }
}
