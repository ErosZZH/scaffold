<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>欢迎登录scaffold管理平台</title>
  </head>
  
  <body>
    <form id="loginForm" action="login" method="post">
		<label class="input-label" for="username">登录名</label>
		<input type="text" id="username" name="username">
		<br>
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password">
		<br>
		<input type="submit" value="登 录"/>
	</form>
  </body>
</html>
