package com.example.mems.controller;


import com.example.mems.entity.File;
import com.example.mems.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileMapper filemapper;
    @RequestMapping("/queryFileSyn0List")
    public List<File> queryFileSyn0List(){
        List<File> filelist=filemapper.querySyn0List();
        for(File file:filelist){
            System.out.println(file);
        }
        return filelist;
    }

    }




