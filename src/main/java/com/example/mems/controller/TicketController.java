package com.example.mems.controller;


import com.example.mems.common.Result;
import com.example.mems.entity.Ticket;
import com.example.mems.entity.User;
import com.example.mems.service.ITicketService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Resource
    private ITicketService iTicketService;
    @PostMapping("/buyticket")
    /*有点疑惑是这里应该只用提交后三个数据,但是不知道怎么拿到user,mybatis-puls没学过*/
    public Result buyticket(@RequestBody User user, @RequestBody Integer frontId, @RequestBody Integer toId, @RequestBody LocalDateTime time){
        Ticket ticket1=iTicketService.buyticket(user,frontId,toId,time);

       return Result.success(ticket1);
    }

}

