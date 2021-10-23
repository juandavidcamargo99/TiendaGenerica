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
<title>Listar-productos</title>
</head>
<body>
	<jsp:include page="menuPrincipal.jsp" />
	<h2>Listar clientes</h2>
	<table id="tabla">
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Nombre</th>
				<th>Nit</th>
				<th>Precio de compra</th>
				<th>Iva</th>
				<th>Precio de venta</th>
			</tr>
		</thead>
		<tbody id="myTable">
			<c:forEach items="${productos}" var="producto">
				<tr>
					<td>${producto.code}</td>
					<td>${producto.name}</td>
					<td>${producto.nit}</td>
					<td>${producto.precioCompra}</td>
					<td>${producto.iva}</td>
					<td>${producto.precioVenta}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form method="post" action="/listar-productos">
		<label>Nombre del archivo:</label> <input type="file" name="file">
		<br> <input type="submit" name="submit" value="Cargar">
	</form>
</body>
</html>