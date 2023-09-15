package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

/**
 * @author qiuxiaying
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //    并不规范的快速实现
    @Resource UserMapper userMapper;
     //postMapping新增，putMapping更新，getMapping查询
    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(search)) {
            wrapper.like(User::getNickName, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }

    @PutMapping
    public  Result<?> update(@RequestBody User user){
        userMapper.updateById(user);
        return  Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable long id){
        return Result.success(userMapper.selectById(id));
    }


    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id){
        userMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));

        // 判断密码是否正确
        if (res == null){
            return Result.error("-1","用户或密码错误");
        }
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if(res!=null){
            return Result.error("-1","用户存在");
        }
        // 判断密码是否正确
        if (user.getPassword()==null){
            return Result.error("-1","用户或密码错误");
        }
        userMapper.insert(user);
        return Result.success(res);
    }
}
