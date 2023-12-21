<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% AdminBean admin = (AdminBean)request.getAttribute("admin"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet...</title>
</head>
<body>
<%@ include file="includes/admin-navbar.jsp" %>
<form action="AdminEditServlet" method="post">
	<div>お名前：<%=admin.getAdminName()%></div>
	<div>フリガナ：<%=admin.getAdminKanaName()%></div>
	<div>メールアドレス：<%=admin.getEmail()%></div>
</form>
<button type="submit"><a href="AdminEditServlet?adminId=<%=admin.getAdminId()%>">更新</a></button>
	<!--削除はjsでするからこっちはaタグを使わない？-->
<button type="submit">削除</button><br>
<a href="AdminListServlet">戻る</a>
</body>
</html>