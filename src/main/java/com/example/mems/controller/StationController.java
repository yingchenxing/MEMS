package com.example.mems.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mems.common.Result;
import com.example.mems.entity.File;
import com.example.mems.entity.Station;
import com.example.mems.entity.User;
import com.example.mems.service.IStationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    private IStationService stationService;

    //分页查询mybatis-plus方式
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "0") Integer id) {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        if (!name.isEmpty())
            queryWrapper.like("name", name);
        if (id!=0)
            queryWrapper.like("id", id);
        queryWrapper.orderByDesc("id");
        return Result.success(stationService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    //根据id新增或修改
    @PostMapping
    public Result save(@RequestBody Station station) {
        return Result.success(stationService.saveOrUpdate(station));
    }


    //根据id删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(stationService.removeById(id));
    }

    //根据id批量删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(stationService.removeBatchByIds(ids));
    }

    //返回所有
    @GetMapping
    public Result findAll() {
        return Result.success(stationService.list());
    }

    //通过id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(stationService.getById(id));
    }
}

