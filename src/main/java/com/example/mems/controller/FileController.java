package com.example.mems.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mems.common.Result;
import com.example.mems.entity.File;
import com.example.mems.service.IFileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
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
@RequestMapping("/file")
public class FileController {
    @Resource
    private IFileService fileService;

    @GetMapping("/syn/{syn}")
    public Result queryFileBySyn(@PathVariable Integer syn) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("syn", syn);
        List<File> filelist = fileService.list(queryWrapper);
        for (File file : filelist) {
            System.out.println(file);
        }
        return Result.success(filelist);
    }
    @PostMapping("uploadpicture")
    public Result uploadpicture( ) throws IOException {
        File file=fileService.uploadpicture();
        return Result.success(file);
    }



}

