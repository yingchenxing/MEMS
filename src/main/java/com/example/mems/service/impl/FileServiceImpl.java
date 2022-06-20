package com.example.mems.service.impl;

import com.example.mems.entity.File;
import com.example.mems.mapper.FileMapper;
import com.example.mems.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
@Override
    public File uploadpicture() throws IOException {
    ServerSocket ss=null;
    Socket socket=null;
    InputStream in=null;
    OutputStream os=null;
    FileOutputStream fos=new FileOutputStream("./");
    try {
        ss=new ServerSocket(6060);
        socket = ss.accept();
        in = socket.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = in.read(b)) != -1) {
            fos.write(b,0,len);
        }
        os=socket.getOutputStream();
        os.write("成功接收".getBytes());

    }catch (IOException e){
        e.printStackTrace();
    }finally {
        if(os!=null){
            try {
                os.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(fos!=null){
            try {
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(in!=null){
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();}
        }
        if(socket!=null){
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();}
        }
        if(ss!=null){
            try{
                ss.close();
            }catch (IOException e){
                e.printStackTrace();}
        }
    }
return null;
}
}

