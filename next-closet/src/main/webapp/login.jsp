<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% String loginUser = (String)request.getSession().getAttribute("user"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
	<h3>ログイン</h3>
	<% String loginError = (String)request.getSession().getAttribute("loginError"); %>
	<% if(loginError != null) { %>
	<p><%=loginError %></p>
	<% session.removeAttribute("loginError"); %>
	<% } %>
		<form action="LoginServlet" method="post">
			Eメール：<input type="email" name="email" placeholder="next.closet@example.com" required><br>
			パスワード：<input type="password" name="password" placeholder="" required><br>
			<button type="submit">login</button>
		</form>
		<h4>アカウントをお持ちでない場合はこちらから</h4>
		<a href="register.jsp">申し込み</a>
</body>
</html>