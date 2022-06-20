package com.example.mems.exception;

import com.example.mems.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Result handle(ServiceException se){
        return Result.error(se.getCode(),se.getMsg());
    }
}
