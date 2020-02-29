package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author zhang_bt on 2020/2/29 16:45
 */
@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;


    private String userName;

    private String phone;

    private String email;

    private String password;

    private Integer sex;

    private Integer age;

    /**
     * 1正常    2删除
     */
    private Integer isDel;

    /**
     * 1用户   0管理员
     */
    private Integer level;

    private Date creatTime;

}
