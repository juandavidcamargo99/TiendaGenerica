<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	HttpSession sesion = request.getSession();
	if (sesion.getAttribute("login") == null || sesion.getAttribute("login").equals("0")){
		response.sendRedirect("login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar clientes</title>
</head>
<body>
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
					<td><a href="/eliminar-cliente/${cliente.id}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form action="/logout" method="POST">
		<input type="submit" name="Logout" value="Logout"/>
	</form>
</body>
</html>
