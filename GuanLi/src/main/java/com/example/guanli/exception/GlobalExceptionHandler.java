package com.example.guanli.exception;

import com.example.guanli.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result HandleException(Exception e){
        log.error("出现异常了", e);
        return Result.error("程序出错了");
    }

    @ExceptionHandler
    public Result HandleDuplicateKeyException(DuplicateKeyException e){
        log.error("出现异常了", e);
        String message = e.getMessage();
        int i= message.indexOf("Duplicate entry");
        String errMsg= message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }

    //业务异常：把具体提示信息返回给前端
    @ExceptionHandler
    public Result HandleBusinessException(BusinessException e){
        log.warn("业务异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }

}
