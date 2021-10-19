<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar usuarios</title>
</head>
<body>
	<h1>Lista de usuarios</h1>
	<h3>${msg}</h3>
	<table id="tabla">
		<thead>
			<tr>
				<th>Identidad</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody id="myTable">
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.name}</td>
					<td>${usuario.lastName}</td>
					<td><a href="/actualizar-usuario/${usuario.id}">Actualizar</a></td>
					<td><a href="/eliminar-usuario/${usuario.id}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form action="/logout" method="POST">
		<input type="submit" name="Logout" value="Logout"/>
	</form>
</body>
</html>
