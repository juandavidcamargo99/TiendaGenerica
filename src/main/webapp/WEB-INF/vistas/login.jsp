<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Login Page</h2>

	<form method="POST" action="/login">
		<label>User Name</label><br>
		<input type="text" name="username" value="user"/><br>
		<label>Password</label><br>
		<input type="password" name="password" value="password"/><br> 
		<input type="submit" name="submit"/>
	</form>
</body>
</html>