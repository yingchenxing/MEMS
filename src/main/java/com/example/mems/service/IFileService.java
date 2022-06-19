package com.example.mems.service;

import com.example.mems.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
public interface IFileService extends IService<File> {

    List<File> queryFileBySyn(Integer syn);
}
