<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/2/29
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/layer/layer.js"></script>
<script type="text/javascript">
    $(function() {
        search();
    })

    function search(){
        var index = layer.load(0,{shade:0.3});
        $.post("<%=request.getContextPath()%>/user/show",
            $("#fm").serialize(),
            function(data){
                layer.close(index);
                var html = "";
                for (var i = 0; i < data.data.length; i++) {
                    var userList = data.data[i];
                    html += "<tr>";
                    html += "<td><input type='checkbox' name='id' value='"+userList.id+"'/>"+userList.id+"</td>";
                    html += "<td>"+userList.userName+"</td>";
                    html += "<td>"+userList.phone+"</td>";
                    html += "<td>"+userList.email+"</td>";
                    html += "<td>"+userList.age+"</td>";
                    if (userList.sex == 1){
                        html += "<td>男</td>";
                    }else{
                        html += "<td>女</td>";
                    }
                    if (userList.level == 0){
                        html += "<td>管理员</td>";
                    }else{
                        html += "<td>用户</td>";
                    }
                    html += "<td>"+userList.creatTime+"</td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )}


</script>
<body>

<form id = "fm">

    <input type="text" name="userName" placeholder="用户名/手机号/邮箱"/><br />
    <c:if test="${user.level == 0}">
        角色：
        <input type="radio" name="level" value="1"/>用户
        <input type="radio" name="level" value="0"/>管理员<br />
    </c:if>
    性别：  <input type="radio" name="sex" value="1"/>男
    <input type="radio" name="sex" value="2"/>女<br />
    <input type="button" value="搜索" onclick="search()"/><br />
    <input type="button" value="修改" onclick="toUpdate()"/>
    <input type="button" value="删除" onclick="delById()"/>

    <table border="1px">
        <tr>
            <th>用户ID</th>
            <th>用户名</th>
            <th>手机号</th>
            <th>邮箱</th>
            <th>年龄</th>
            <th>性别</th>
            <th>角色</th>
            <th>注册时间</th>
        </tr>
        <tbody id = "tb"></tbody>
    </table>
</form>


</body>
</html>
