<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/2/29
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/md5-min.js"></script>
<script>
    function login(){

        var pwd = md5($(":password").val());

        $(":password").val(pwd);

        var index = layer.load(0,{shade:0.3});
        $.post("<%=request.getContextPath()%>/user/login",
            $("#fm").serialize(),
            function(data){
                layer.close(index);
                if (data.code != 200){
                    layer.msg(data.msg, {icon: 5}, {time: 2*1000});
                    return;
                }
                if (data.code == 200){
                    layer.msg(data.msg, {icon: 6}, {time: 2*1000});
                    window.location.href = "<%=request.getContextPath() %>/index/toIndex";
                }
            }
        )}

    //退出
    if(window.top.document.URL != document.URL) {
        window.top.location = document.URL
    }

    function toAdd(){
        layer.open({
            type: 2,
            title: '关联资源',
            shadeClose: true,
            shade: 0.8,
            area: ['480px', '70%'],
            resize:false,
            content: "<%=request.getContextPath() %>/user/toAdd"
        });
    }


</script>
<body>
    <form id = "fm">
        用户名：<input type="text" name = "userName" placeholder="用户名/手机号/邮箱"/><br />
        密  码：<input type="password" name = "password"/><br />
        <a href="#" onclick="toAdd()">还有没有账号?点我去注册</a><br/>
        <input type="button" value="登录" onclick="login()"/>
</form>
</body>
</html>
