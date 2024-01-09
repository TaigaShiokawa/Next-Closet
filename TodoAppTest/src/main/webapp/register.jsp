<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="model.dto.*"%>
<%@ page import="model.dao.*"%>
<% UserDTO user = (UserDTO)request.getSession().getAttribute("user"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
	<form action="Register" method="post">
	<label for="name">名前：</label>
	<input type="text" id="name" name="userName" required><br>
	
	<label for="email">Eメール：</label>
	<input type="email" id="email" name="userEmail" required><br>
	
	<label for="pass">パスワード：</label>
	<input type="password" id="pass" name="userPassword" required><br>
	
	<button type="submit">登録</button>
	</form>
	
	<a href="Login">すでにアカウントをお持ちですか？</a>
</body>
</html>