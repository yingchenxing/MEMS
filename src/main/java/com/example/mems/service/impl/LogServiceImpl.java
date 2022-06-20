package com.example.mems.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mems.common.Constants;
import com.example.mems.entity.Log;
import com.example.mems.entity.Ticket;
import com.example.mems.entity.User;
import com.example.mems.exception.ServiceException;
import com.example.mems.mapper.LogMapper;
import com.example.mems.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mems.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
//    @Resource
//    private ILogService iLogService;
//    @Resource
//    private IUserService iUserService;
//    private static final cn.hutool.log.Log LOG = cn.hutool.log.Log.get();
//    @Override
//    public Log createlog(User user, float temperature){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",user.getId());
//        User one;
//        Log two=null;
//        try {
//            one= iUserService.getOne(queryWrapper);
//        } catch (Exception e) {
//            LOG.error(e);
//            throw new ServiceException(Constants.CODE_500, "System error!");
//        }
//        if(one!=null){
//            two.setId(one.getId());
//         two.setTemperature(temperature);
//         iLogService.save(two);
//         return two;
//        }else{
//            throw new ServiceException(Constants.CODE_500, "System error!");
//        }
//    }

}
