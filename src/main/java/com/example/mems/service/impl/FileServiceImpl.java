package com.example.mems.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mems.entity.File;
import com.example.mems.mapper.FileMapper;
import com.example.mems.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Override
    public List<File> queryFileBySyn(Integer syn) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("syn", syn);
        return this.list(queryWrapper);
    }
}
