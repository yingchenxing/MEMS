package com.example.mems.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mems.common.Constants;
import com.example.mems.entity.Ticket;
import com.example.mems.entity.User;
import com.example.mems.exception.ServiceException;
import com.example.mems.mapper.TicketMapper;
import com.example.mems.service.ITicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mems.service.IUserService;
import com.example.mems.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements ITicketService {
//    @Resource
//    private TicketMapper ticketMapper;
//    @Resource
//    private  IUserService iUserService;
//    @Resource
//    private ITicketService iTicketService;
//    private static final Log LOG = Log.get();
//    @Override
//    public Ticket buyticket(User user, Integer frontId, Integer toId, LocalDateTime time){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",user.getId());
//        User one;
//        Ticket two=null;
//        try {
//           one= iUserService.getOne(queryWrapper);
//        } catch (Exception e) {
//            LOG.error(e);
//            throw new ServiceException(Constants.CODE_500, "System error!");
//        }
//        if(one!=null){
//            Integer fee=calculateprice(frontId,toId);
//           if(one.getBalance()>=fee){
//               float left=one.getBalance()-fee;
//               one.setBalance(left);
//              iUserService.save(one);/*是不是持久化user的余额*/
//               two.setFromId(frontId);
//               two.setPrice(fee);
//               two.setToId(toId);
//               two.setUserId(one.getId());
//               iTicketService.save(two);
//            return two;
//           }else{
//               throw new ServiceException(Constants.CODE_400,"balance not enough!");
//           }
//        }else{
//            throw new ServiceException(Constants.CODE_500, "System error!");
//        }
//
//    }
//
//    public Integer calculateprice(Integer frontId,Integer toId){
//        return toId-frontId;/*没写*/
//    }
//
//
//



}
