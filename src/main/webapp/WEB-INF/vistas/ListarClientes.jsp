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
<title>Listar clientes</title>
</head>
<body>
	<jsp:include page="menuPrincipal.jsp" />
	<h2>Listar clientes</h2>
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
			<c:forEach items="${clientes}" var="cliente">
				<tr>
					<td>${cliente.id}</td>
					<td>${cliente.name}</td>
					<td>${cliente.lastName}</td>
					<td><a href="/actualizar-cliente/${cliente.id}">Actualizar</a></td>
					<td><form action="/eliminar-cliente" method="POST"><button type="submit" name="id" value="${cliente.id}" >Eliminar</button></form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h2>Buscar por cedula</h2>
	<form action="/cliente-por-cedula" method="POST">
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
			<c:forEach items="${clientesPorCedula}" var="clientePorCedula">
				<tr>
					<td>${clientePorCedula.id}</td>
					<td>${clientePorCedula.name}</td>
					<td>${clientePorCedula.lastName}</td>
					<td>${clientePorCedula.cardId}</td>
					<td><a href="/actualizar-cliente/${clientePorCedula.id}">Actualizar</a></td>
					<td><form action="/eliminar-cliente" method="POST"><button type="submit" name="id" value="${clientePorCedula.id}" >Eliminar</button></form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="/logout" method="POST">
		<input type="submit" name="Logout" value="Logout"/>
	</form>
</body>
</html>
