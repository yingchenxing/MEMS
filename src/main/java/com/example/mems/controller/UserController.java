package com.example.mems.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mems.common.Constants;
import com.example.mems.common.Result;
import com.example.mems.entity.User;
import com.example.mems.service.IUserService;
import com.example.mems.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String account = user.getAccount();
        String password = user.getPassword();
        if (StrUtil.isBlank(account) || StrUtil.isBlank(password))
            return Result.error(Constants.CODE_400, "Parameter error!");
        User user1 = userService.login(user);
        return Result.success(user1);
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String account = user.getAccount();
        String password = user.getPassword();
        if (StrUtil.isBlank(account) || StrUtil.isBlank(password))
            return Result.error(Constants.CODE_400, "Parameter error!");
        return Result.success(userService.register(user));
    }

    //根据id新增或修改
    @PostMapping
    public Result save(@RequestBody User user) {
        return Result.success(userService.saveOrUpdate(user));
    }

    //根据id删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    //根据id批量删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeBatchByIds(ids));
    }

    //返回所有
    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    //通过id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    //分页查询mybatis-plus方式
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "-1") Integer health,
                               @RequestParam(defaultValue = "") String idcard,
                               @RequestParam(defaultValue = "-1") Integer role,
                               @RequestParam(defaultValue = "") String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!name.isEmpty())
            queryWrapper.like("name", name);
        if (!idcard.isEmpty())
            queryWrapper.like("idcard", idcard);
        if (!phone.isEmpty())
            queryWrapper.like("phone", phone);
        if (role != -1)
            queryWrapper.eq("role", role);
        if (health != -1)
            queryWrapper.eq("health", health);

        queryWrapper.orderByDesc("id");

//        //获取当前用户
//        User currentUser = TokenUtils.getCurrentUser();
//        System.out.println(currentUser.getName());

        return Result.success(userService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}

