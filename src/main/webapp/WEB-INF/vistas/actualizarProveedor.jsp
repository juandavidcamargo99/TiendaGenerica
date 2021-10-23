<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="baseURL" value="${pageContext.request.localName}"/>
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
<title>Update user</title>
</head>
<body>
	<center>
	<h2>Update user</h2>
	<hr size="8px" color="black" />
	<form method="POST" action="/actualizar-proveedor">
		<label>Username</label><br>
		<input type="text" name="id" value="${proveedor.id}" hidden/>
		<input type="text" name="name" value="${proveedor.name}"/><br>
		<label>address</label><br>
		<input type="text" name="address" value="${proveedor.address}"/><br>
		<label>NIT</label><br>
		<input type="text" name="cardId" value="${proveedor.cardId}"/><br>
		<p>${error}</p> 
		<input type="submit" name="Actualizar"/>
	</form>
	</center>
</body>
</html>