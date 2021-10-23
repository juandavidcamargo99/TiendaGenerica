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
	<h2>Update user</h2>
	<form method="POST" action="/actualizar-usuario">
		<label>Username</label><br>
		<input type="number" name="id" value="${usuario.id}" hidden>
		<input type="text" name="name" value="${usuario.name}"/><br>
		<label>Last name</label><br>
		<input type="text" name="lastname" value="${usuario.lastName}"/><br>
		<label>Account name</label><br>
		<input type="text" name="accountName" value="${usuario.account}"/><br>
		<label>Password</label><br>
		<input type="text" name="password" value=""/><br>
		<p>${error}</p> 
		<input type="submit" name="Actualizar"/>
	</form>
</body>
</html>