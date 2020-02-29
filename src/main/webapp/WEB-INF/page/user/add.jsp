<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/2/23
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/res/js/jquery.validate.js"></script>
<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript">

    jQuery.validator.addMethod("phone",
        function(value, element) {
            var tel = /^1[3456789]\d{9}$/;
            return tel.test(value)
        }, "请正确填写您的手机号");

    jQuery.validator.addMethod("email",
        function(value, element) {
            var tel = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
            return tel.test(value)
        }, "请正确填写您的邮箱编号");


    $(function() {
        $("#fm").validate({

            rules:{
                userName:{
                    required:true,
                    minlength:2,
                    remote: {
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/user/findByNameAndPhoneAndEmail",
                        data:{
                            userName:function() {
                                return $("#userName").val();
                            },
                            dataType:"json",
                            dataFilter:function(data,type){
                                if (data == 'true'){
                                    return true;
                                }else {
                                    return false;
                                }
                            }

                        }
                    }
                },
                phone:{
                    required:true,
                    digits:true,
                    phone:true,
                    remote: {
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/user/findByNameAndPhoneAndEmail",
                        data:{
                            phone:function() {
                                return $("#phone").val();
                            },
                            dataType:"json",
                            dataFilter:function(data,type){
                                if (data == 'true'){
                                    return true;
                                }else {
                                    return false	;
                                }
                            }

                        }
                    }
                },
                email:{
                    required:true,
                    email:true,
                    remote: {
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/user/findByNameAndPhoneAndEmail",
                        data:{
                            email:function() {
                                return $("#email").val();
                            },
                            dataType:"json",
                            dataFilter:function(data,type){
                                if (data == 'true'){
                                    return true;
                                }else {
                                    return false	;
                                }
                            }

                        }
                    }
                },
                password:{
                    required:true,
                    rangelength:[3,10]
                },
                age:{
                    required:true
                }
            },
            messages:{
                userName:{
                    required:"用户名不能为空",
                    minlength:"用户名最短两位",
                    remote:"已注册"
                },
                phone:{
                    required:"手机号不能为空",
                    digits:"请输入正确的11位手机号",
                    phone:"手机号格式不对",
                    remote:"已注册"
                },
                email:{
                    required:"邮箱不能为空",
                    email:"邮箱格式不对",
                    remote:"已注册"
                },
                password:{
                    required:"请输入密码",
                    rangelength:"长度必须是{0}到{1}位之间"
                },
                age:{
                    required:"请输入年龄"
                }

            }


        })
    })


    $.validator.setDefaults({
        submitHandler: function() {
            $.post("<%=request.getContextPath() %>/user",
                $("#fm").serialize(),
                function(data){
                    if(data.code != 200){
                        layer.msg(data.msg, {icon: 5});
                        return;
                    }
                    layer.msg(data.msg, {
                        icon: 6,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        parent.window.location.href = "<%=request.getContextPath()%>/user/toLogin";
                    });

                })
        }
    });



</script>
<style>
    .error{
        color:red;
    }
</style>
<body>
<form id = "fm">
    <input type="hidden" name="_method" value="post"/>
    用户名<input type="text" name="userName" id = "userName"/><br/>
    密码<input type="text" name="password" id = "password"/><br/>
    手机号<input type="text" name="phone" id = "phone"/><br/>
    邮箱<input type="text" name="email" id = "email"/><br/>
    性别:<input type = "radio" name = "sex" value="1" checked/>男<input type = "radio" name = "sex" value="2"/>女<br/>
    年龄:<input type = "text" name = "age" id = "age"/><br/>
    角色:<input type = "radio" name = "level" value="1" checked/>用户<input type = "radio" name = "level" value="0"/>管理员<br/>
    <input type = "hidden" name = "isDel" value = "1"/>
    <input type="submit" value="新增"/>
</form>
</body>
</html>
