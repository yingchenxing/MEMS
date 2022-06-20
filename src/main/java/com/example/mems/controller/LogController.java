package com.example.mems.controller;


import com.example.mems.common.Result;
import com.example.mems.entity.Log;
import com.example.mems.entity.User;
import com.example.mems.service.ILogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private ILogService iLogService;
    @PostMapping("/createlog")
    public Result createlog(@RequestBody User user, @RequestBody Float temperature){
        Log log=iLogService.createlog(user,temperature);
        return Result.success(log);

    }

}

