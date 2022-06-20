package com.example.mems;

import com.example.mems.entity.File;
import com.example.mems.entity.User;
import com.example.mems.service.IFileService;
import com.example.mems.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MemsApplicationTests {

    @Resource
    IUserService userService;

    @Resource
    IFileService fileService;



    @Test
    public void select() {
//        for(User user:userService.list()){
//            System.out.println(user);
//        }

//        for (File file : file) {
//            System.out.println(file);
//        }
    }

}
