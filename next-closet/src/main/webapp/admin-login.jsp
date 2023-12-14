<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>

<% AdminBean loginAdmin = (AdminBean)request.getSession().getAttribute("Admin"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>next closet...</title>
</head>
<body>

<!-- 管理者用のナビゲーションバーが必要かも -->
   <%@ include file="includes/navbar.jsp" %>  
	<h2>管理者ログインおはよう</h2>
		<% String errorMessageToAdmin = (String)request.getSession().getAttribute("errorMessageToAdmin"); %>
		<% if(errorMessageToAdmin != null) { %>
		<p><%=errorMessageToAdmin %></p>
		<% session.removeAttribute("errorMessageToAdmin"); %>
		<% } %>
		<form action="AdminLoginServelt" method="post">
			<input type="text" name="email" placeholder="メールアドレス"><br>
			<input type="password" name="password" placeholder="パスワード"><br>
			<button type="submit">login</button>
		</form>


</body>
</html>