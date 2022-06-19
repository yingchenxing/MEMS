package com.example.mems.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mems.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
public interface FileMapper extends BaseMapper<File> {
    List<File> queryFileList();
    List<File> querySyn0List();
    int addFile(File file);
    int updateFile(File file);
    int deleteFile(int id);

}
