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
	<jsp:include page="menuPrincipal.jsp" />
	<h1>Usuarios</h1>
	<h2>Lista de usuarios</h2>
	<h3>${msg}</h3>
	<table id="tabla">
		<thead>
			<tr>
				<th>Identidad</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Cedula</th>
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
					<td>${usuario.cardId}</td>
					<td><a href="/actualizar-usuario/${usuario.id}">Actualizar</a></td>
					<td><form action="/eliminar-usuario" method="POST"><button type="submit" name="id" value="${usuario.id}" >Eliminar</button></form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h2>Buscar por cedula</h2>
	<form action="/usuario-por-cedula" method="POST">
		<lable>Cedula</lable>
		<input name="cardId" type="text">
		<input type="submit" value="Buscar">
	</form>
	<table>
		<thead>
			<tr>
				<th>Identidad</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Cedula</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuariosPorCedula}" var="usuarioPorCedula">
				<tr>
					<td>${usuarioPorCedula.id}</td>
					<td>${usuarioPorCedula.name}</td>
					<td>${usuarioPorCedula.lastName}</td>
					<td>${usuarioPorCedula.cardId}</td>
					<td><a href="/actualizar-usuario/${usuarioPorCedula.id}">Actualizar</a></td>
					<td><form action="/eliminar-usuario" method="POST"><button type="submit" name="id" value="${usuarioPorCedula.id}" >Eliminar</button></form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form action="/logout" method="POST">
		<input type="submit" name="Logout" value="Logout"/>
	</form>
</body>
</html>
