<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<h2>Login Page</h2>

	<form method="POST" action="/login" modelAttribute="user">
		<label>User Name</label><br>
		<input type="text" name="username" value="adminicial"/><br>
		<label>Password</label><br>
		<input type="password" name="password" value="admin123456"/><br>
		<p>${error}</p> 
		<input type="submit" name="submit"/>
	</form>
</center>
</body>
</html>