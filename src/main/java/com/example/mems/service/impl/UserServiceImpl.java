package com.example.mems.service.impl;

import com.example.mems.entity.User;
import com.example.mems.mapper.UserMapper;
import com.example.mems.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
