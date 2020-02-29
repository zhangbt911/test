package com.dj.ssm.comtroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import com.dj.ssm.utils.PasswordSecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author zhang_bt on 2020/2/29 16:48
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("login")
    private ResultModel login (User user){
        try{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            subject.login(token);

            return new ResultModel().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(e.getMessage());
        }
    }


    /**
     * 用户名去重
     * @param user
     * @return
     */
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

    /**
     * 手机号去重
     * @param user
     * @return
     */
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

    /**
     * 邮箱去重
     * @param user
     * @return
     */
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

    /**
     * 用户展示
     * @param user
     * @return
     */
    @PostMapping
    private ResultModel add(User user){
        try {
            //密文密码
            String md5pwd = PasswordSecurityUtil.enCode32(user.getPassword());
            //盐
            String salt = PasswordSecurityUtil.generateSalt();

            user.setPassword(PasswordSecurityUtil.enCode32(md5pwd + salt));

            user.setSalt(salt);

            user.setCreatTime(new Date());
            userService.save(user);
            return new ResultModel().success("成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }


    /**
     * 用户展示
     * @param user
     * @return
     */
    @RequestMapping("show")
    public ResultModel<Object> show(User user, HttpSession session){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //用户名/手机号/邮箱模糊查询
        if(!StringUtils.isEmpty(user.getUserName())){
            queryWrapper.like("user_name",user.getUserName()).or().like("phone",user.getUserName()).or().like("email",user.getUserName());
        }
        //单选按钮角色查询
        if(!StringUtils.isEmpty(user.getLevel())){
            queryWrapper.eq("level",user.getLevel());
        }
        //单选按钮性别查询
        if(!StringUtils.isEmpty(user.getSex())){
            queryWrapper.eq("sex",user.getSex());
        }
        User userLogin = (User) session.getAttribute("user");
        if (userLogin.getLevel() == 1) {
            queryWrapper.eq("level",1);
        }

        queryWrapper.eq("is_del",1);
        queryWrapper.orderByDesc("id");
        List<User> list = userService.list(queryWrapper);
        return new ResultModel<>().success(list);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResultModel<Object> update(User user){
        userService.updateById(user);
        return new ResultModel<>().success("成功");
    }

    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping("delById")
    public ResultModel<Object> delById(Integer id) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_del", 0);
        updateWrapper.eq("id", id);
        userService.update(updateWrapper);
        return new ResultModel<>().success("成功");
    }

}
