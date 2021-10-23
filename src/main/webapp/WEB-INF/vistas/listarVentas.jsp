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
	<h2>Ventas</h2>
	<form method="post" action="">
		<label>Cedula</label> 
		<input type="text">
		<button type="button">Consultar</button>
		<label>Consecutivo</label>
		<input type="text">
		<br><br>
		<label>Cliente</label>
		<input type="text"> 
	</form>

	<table>
		<thead>
			<tr>
				<th>Cod. Producto</th>
				<th></th>
				<th>Nombre Producto</th>
				<th>Cantidad</th>
				<th>Vlr. Total</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input></td>
				<td><button>Consultar</button></td>
				<td><input></td>
				<td><input></td>
				<td><input></td>
			</tr>
			<tr>
				<td><input></td>
				<td><button>Consultar</button></td>
				<td><input></td>
				<td><input></td>
				<td><input></td>
			</tr>
			<tr>
				<td><input></td>
				<td><button>Consultar</button></td>
				<td><input></td>
				<td><input></td>
				<td><input></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td>Total Venta</td>
				<td><input></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td>Total Iva</td>
				<td><input></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td>Total con Iva</td>
				<td><input></td>
			</tr>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><button>Confirmar</button></td>
			</tr>
		</tbody>
	</table>
</body>
</html>