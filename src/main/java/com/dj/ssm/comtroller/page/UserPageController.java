package com.dj.ssm.comtroller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhang_bt on 2020/2/29 16:48
 */
@Controller
@RequestMapping("/user/")
public class UserPageController {

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


}
