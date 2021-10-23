<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
HttpSession sesion = request.getSession();
if (sesion.getAttribute("login") == null || sesion.getAttribute("login").equals("0")) {
	String url = request.getRequestURL().toString();
	String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
	response.sendRedirect(baseURL + "/login");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Proveedor</title>
</head>
<body>
	<center>
		<h2>Listar proveedor</h2>
		<hr size="8px" color="black" />
		<table id="tabla">
			<thead>
				<tr>
					<th>Identidad</th>
					<th>Nombre</th>
					<th>Direccion</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody id="myTable">
				<c:forEach items="${proveedores}" var="proveedor">
					<tr>
						<td>${proveedor.id}</td>
						<td>${proveedor.name}</td>
						<td>${proveedor.address}</td>
						<td><a href="/actualizar-proveedor/${proveedor.id}">Actualizar</a></td>
						<td><form action="/eliminar-proveedor" method="POST">
								<button type="submit" name="id" value="${proveedor.id}">Eliminar</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h2>Buscar por NIT</h2>
		<form action="/proveedor-por-NIT" method="POST">
			<lable>NIT</lable>
			<input name="cardId" type="text"> <input type="submit"
				value="Buscar">
		</form>
		<table>
			<thead>
				<tr>
					<th>Identidad</th>
					<th>Nombre</th>
					<th>Direccion</th>
					<th>NIT</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${proveedorPorNIT}" var="proveedorPorNIT">
					<tr>
						<td>${proveedorPorNIT.id}</td>
						<td>${proveedorPorNIT.name}</td>
						<td>${proveedorPorNIT.address}</td>
						<td>${proveedorPorNIT.cardId}</td>
						<td><a href="/actualizar-proveedor/${proveedorPorNIT.id}">Actualizar</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="/logout" method="POST">
			<input type="submit" name="Logout" value="Logout" />
		</form>
	</center>
</body>
</html>