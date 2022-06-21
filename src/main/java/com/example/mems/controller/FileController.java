package com.example.mems.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mems.common.Constants;
import com.example.mems.common.Result;
import com.example.mems.entity.File;
import com.example.mems.service.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 前端控制器
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

    @Value("${file.path}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;

    @Value("${server.port}")
    private String serverPort;

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

//    @PostMapping("uploadPicture")
//    public Result uploadPicture( ) throws IOException {
//        File file=fileService.uploadpicture();
//        return Result.success(file);
//    }

    //上传文件
    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        Long size = file.getSize();

        //生成唯一文件名
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + type;

        //生成文件夹
        java.io.File uploadFile = new java.io.File(fileUploadPath + fileUuid);
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdir();
        }


        String url;
        try {
            //文件传输
            file.transferTo(uploadFile);
        } catch (IOException e) {
            return Result.error(Constants.CODE_402, "Fail to upload");
        }

        //获取md5
        String md5 = SecureUtil.md5(uploadFile);
        File dbFiles = getFileByMd5(md5);
        //获取URL
        if (dbFiles != null) {
            url = dbFiles.getUrl();
            uploadFile.delete();
        } else {
//            url = "http://" + serverIp + ":"+serverPort+"/file/" + fileUuid;
            url = fileUuid;
        }

        // Check user
//        User user = TokenUtils.getCurrentUser();
//        if(user==null){
//            return Result.error(Constants.CODE_300,"Check right fail.");
//        }

        // Save to database
        File saveFile = new File();
        saveFile.setName(originalFilename);
        saveFile.setSize(size / 1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
//        saveFile.setUserId(user.getId());
        fileService.save(saveFile);
        return Result.success(url);
    }

    @GetMapping("/{fileUuid}")
    public Result download(@PathVariable String fileUuid, HttpServletResponse response) {
        java.io.File downloadFile = new java.io.File(fileUploadPath + fileUuid);
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("url", "%" + fileUuid);
        List<File> list = fileService.list(queryWrapper);
        if (list.size() == 0)
            return Result.error(Constants.CODE_402, "Invalid download");
        if (list.size() != 1) {
            new Exception("!!!more than one file match!!!:" + fileUuid).printStackTrace();
            return Result.error(Constants.CODE_402, "Service Badly Response");
        }

        String personFileName = list.get(0).getName();
        System.out.println("download:" + downloadFile.getName());
        try {
            ServletOutputStream os = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(personFileName, "UTF-8"));
            response.setContentType("application/octet-stream");
            os.write(FileUtil.readBytes(downloadFile));
            os.flush();
            os.close();
        } catch (IOException e) {
            return Result.error(Constants.CODE_402, "Fail to download");
        }
        return Result.success();
    }

    @GetMapping("/syn/md5/{md5}")
    public void verify(@PathVariable String md5, HttpServletResponse response) {
        UpdateWrapper<File> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("md5", md5).set("syn", 1);
        System.out.println("md5:" + md5 + "syn finish");
        fileService.update(updateWrapper);
    }

    private File getFileByMd5(String md5) {
        //查询md5是否存在
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<File> files = fileService.list(queryWrapper);
        return files.size() == 0 ? null : files.get(0);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        //这里可以加入删除物理文件

        //仅逻辑删除
        return Result.success(fileService.deleteById(id));
    }

    //分页查询mybatis-plus方式
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        if (!name.isEmpty())
            queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
//        queryWrapper.eq("is_delete",false);
        return Result.success(fileService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<File> files = fileService.list(queryWrapper);
        for (File file : files) {
            //删除
        }
        return Result.success();
    }


}

