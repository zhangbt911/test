package com.dj.ssm.comtroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import com.dj.ssm.utils.PasswordSecurityUtil;
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


    @RequestMapping("findByName")
    public boolean findByName(User user){
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", user.getUserName());
            User userName = userService.getOne(queryWrapper);
            return null == userName ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("findByPhone")
    public boolean findByPhone(User user){
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", user.getPhone());
            User userName = userService.getOne(queryWrapper);
            return null == userName ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("findByEmail")
    public boolean findByEmail(User user){
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", user.getEmail());
            User userName = userService.getOne(queryWrapper);
            return null == userName ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping("add")
    private ResultModel add (User user){
        try {
            //密文密码
            String md5pwd = PasswordSecurityUtil.enCode32(user.getPassword());
            //盐
            String salt = PasswordSecurityUtil.generateSalt();

            user.setPassword(PasswordSecurityUtil.enCode32(md5pwd + salt));

            user.setSalt(salt);

            user.setCreatTime(new Date());
            return new ResultModel().success(userService.save(user));
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }


}
