package com.example.mems.service;

import com.example.mems.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
public interface IUserService extends IService<User> {

    User login(User user);

    User register(User user);
}
