<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	HttpSession sesion = request.getSession();
	if (sesion.getAttribute("login") == null || sesion.getAttribute("login").equals("0")){
		response.sendRedirect("login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Users</title>
</head>
<body>
	<h2>Create Users</h2>

	<form method="POST" action="/crear-usuario">
		<label>Username</label><br>
		<input type="text" name="name" value="ejemplo"/><br>
		<label>Last name</label><br>
		<input type="text" name="lastname" value="ejemplo"/><br>
		<label>Account name</label><br>
		<input type="text" name="accountName" value="ejemplo"/><br>
		<label>Password</label><br>
		<input type="text" name="password" value="ejemplo"/><br>
		<p>${error}</p> 
		<input type="submit" name="enviar"/>
	</form>
</body>
</html>