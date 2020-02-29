<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/2/29
  Time: 21:43
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

    function update(){
        var index = layer.load(0,{shade:0.3});
        $.post("<%=request.getContextPath()%>/user/update",
            $("#fm").serialize(),
            function(data){
                layer.close(index);
                if (data.code != 200){
                    layer.msg(data.msg, {icon: 5}, {time: 2*1000});
                    return;
                }
                if (data.code == 200){
                    layer.msg(data.msg, {icon: 6}, {time: 2*1000});
                    parent.window.location.href = "<%=request.getContextPath() %>/user/toShow";
                }
            }
        )}
</script>
<body>

    <form id="fm">
        <input type="hidden" name="id" value="${user.id}"/>
        用户名：<input type="text" name="userName" value="${user.userName}"/><br />
        手  机：<input type="text" name="phone" value="${user.phone}"/><br />
        邮  箱：<input type="text" name="email" value="${user.email}"/><br />
        年  龄：<input type="text" name="email" value="${user.age}"/><br />
        性别<input type="radio" name="sex" value="1" <c:if test="${user.sex == 1}">checked</c:if>>男
        <input type="radio" name="sex" value="2" <c:if test="${user.sex == 2}">checked</c:if>>女<br/>
        <input type="button" value="修改" onclick="update()"/><br />
    </form>


</body>
</html>
