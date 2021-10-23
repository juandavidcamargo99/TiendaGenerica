<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	HttpSession sesion = request.getSession();
	if (sesion.getAttribute("login") == null || sesion.getAttribute("login").equals("0")){
			String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
		response.sendRedirect(baseURL + "/login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear Proveedor</title>
</head>
<body>
	<center>
	<h2>Crear Proveedor</h2>
	<hr size="8px" color="black" />
	<form method="POST" action="/crear-proveedor">
		<label>Username</label><br>
		<input type="text" name="name" value="ejemplo"/><br>
		<label>Last name</label><br>
		<input type="text" name="address" value="ejemplo"/><br>
		<label>NIT</label><br>
		<input type="text" name="cardId" value="123456"/><br>
		<p>${error}</p> 
		<input type="submit" name="enviar"/>
	</form>
	</center>
</body>
</html>