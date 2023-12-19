<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% AdminBean loginAdmin = (AdminBean)request.getSession().getAttribute("admin"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet...</title>
<%String password = (String) session.getAttribute("password");%>
</head>
<body>
<%-- <%@ include file="includes/admin-navbar.jsp" %> --%>
<form action="AdminEditServlet" method="post">
	<div>お名前：<%=loginAdmin.getAdminName()%></div>
	<div>フリガナ：<%=loginAdmin.getAdminKanaName()%></div>
	<div>メールアドレス：<%=loginAdmin.getEmail()%></div>
	<div>パスワード：<%=password%></div>
</form>
<button type="submit"><a href="AdminEditServlet">更新</a></button>
	<!--削除はjsでするからこっちはaタグを使わない？-->
<button type="submit">削除</button><br>
<a href="#">戻る</a>
</body>
</html>