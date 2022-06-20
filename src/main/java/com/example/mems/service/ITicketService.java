package com.example.mems.service;

import com.example.mems.entity.Ticket;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mems.entity.User;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
public interface ITicketService extends IService<Ticket> {
    Ticket buyticket(User user, Integer frontId, Integer toId, LocalDateTime time);


}
