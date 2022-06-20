package com.example.mems.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mems.common.Constants;
import com.example.mems.entity.User;
import com.example.mems.exception.ServiceException;
import com.example.mems.mapper.UserMapper;
import com.example.mems.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mems.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private UserMapper userMapper;

    private static final Log LOG = Log.get();

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount());
        queryWrapper.eq("password", user.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "System error!");
        }
        if (one != null) {
            BeanUtil.copyProperties(one, user, true);
            String token = TokenUtils.getToken(one.getId().toString(), one.getPassword());
            user.setToken(token);

            return user;
        } else {
            throw new ServiceException(Constants.CODE_600, "Incorrect username or password.");
        }
    }

    @Override
    public User register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount());
        List list = userMapper.selectList(queryWrapper);

        if (list.size() == 0) {
            this.save(user);
            return user;
        } else {
            throw new ServiceException(Constants.CODE_500, "Username has been used!");
        }

    }
}
