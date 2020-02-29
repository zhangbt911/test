package com.dj.ssm.comtroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author zhang_bt on 2020/2/29 16:48
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    private ResultModel login (){


        return new ResultModel().success();
    }


    @RequestMapping("findByNameAndPhoneAndEmail")
    public boolean findByNameAndPhoneAndEmail(String userName, String phone, String email){
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", userName);
            queryWrapper.eq("phone", phone);
            queryWrapper.eq("email", email);
            User user = userService.getOne(queryWrapper);
            return null == userName ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @PostMapping("add")
    private ResultModel add (User user){
        try {
            user.setCreatTime(new Date());
            return new ResultModel().success(userService.save(user));
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }


}
