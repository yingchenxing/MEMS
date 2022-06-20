package com.example.mems.controller;


import com.example.mems.service.IStationService;
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
@RequestMapping("/station")
public class StationController {
    @Resource
    private IStationService iStationService;


}

