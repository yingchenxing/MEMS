package com.example.mems.service;

import com.example.mems.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
public interface IFileService extends IService<File> {
//    File uploadpicture() throws IOException;
//
    int deleteById(Integer id);
}
