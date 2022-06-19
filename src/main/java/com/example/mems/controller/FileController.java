package com.example.mems.controller;


import com.example.mems.entity.File;
import com.example.mems.mapper.FileMapper;
import com.example.mems.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private IFileService fileService;

    @GetMapping("/syn/{syn}")
    public List<File> queryFileBySyn(@PathVariable Integer syn) {
        List<File> filelist = fileService.queryFileBySyn(syn);
        for (File file : filelist) {
            System.out.println(file);
        }
        return filelist;
    }

}




