<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/index/exit">退出</a>
	<h1 align="center">欢迎${user.userName}登录北京点金教育,万元高薪,尽在点金</h1>
	<div id="datetime" align="right" style="color:black">
	  <script>
 			setInterval("document.getElementById('datetime').innerHTML=new Date().toLocaleString();", 1000);
 	  </script>
	</div>
	
	
</body>