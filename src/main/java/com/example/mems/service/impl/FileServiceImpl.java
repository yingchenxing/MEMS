package com.example.mems.service.impl;

import com.example.mems.entity.File;
import com.example.mems.mapper.FileMapper;
import com.example.mems.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
