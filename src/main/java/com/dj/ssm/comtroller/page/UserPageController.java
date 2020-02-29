package com.dj.ssm.comtroller.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhang_bt on 2020/2/29 16:48
 */
@Controller
@RequestMapping("/user/")
public class UserPageController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin(){
        return  "user/login";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return  "user/add";
    }


    @RequestMapping("toShow")
    public String toShow(){
        return  "user/show";
    }

    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Integer id, Model model){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userService.getOne(queryWrapper);
        model.addAttribute("user", user);
        return  "user/update";
    }




}
