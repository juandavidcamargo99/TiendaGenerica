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
<title>Actualizar cliente</title>
</head>
<body>
	<jsp:include page="menuPrincipal.jsp" />
	<h2>Actualizar cliente</h2>
	<form method="POST" action="/actualizar-cliente">
		<label>Username</label><br>
		<input type="number" name="id" value="${cliente.id}" hidden>
		<input type="text" name="name" value="${cliente.name}"/><br>
		<label>Last name</label><br>
		<input type="text" name="lastname" value="${cliente.lastName}"/><br>
		<label>Cedula</label><br>
		<input type="text" value="${cliente.cardId}" readonly/><br>
		<p>${error}</p> 
		<input type="submit" name="Actualizar"/>
	</form>
</body>
</html>