<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.dto.*" %>
<%@ page import="model.dao.*" %>
<% UserDTO user = (UserDTO)request.getSession().getAttribute("user"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<jsp:include page="/includes/navbar.jsp" />
<% String userEmail = (String)request.getSession().getAttribute("userEmail"); %>
	<form action="Login" method="post">
		<label for="email">Eメール：</label>
		<input type="email" id="email" name="userEmail" value="<%= userEmail != null ? userEmail : "" %>" required><br>
		
		<label for="pass">パスワード：</label>
		<input type="password" id="pass" name="userPassword" required><br>
		
		<button type="submit">login</button>
	</form>
</body>
</html>