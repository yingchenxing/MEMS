package com.example.mems.service;

import com.example.mems.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mems.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
public interface ILogService extends IService<Log> {
   Log createlog(User user, float temperature);

}
