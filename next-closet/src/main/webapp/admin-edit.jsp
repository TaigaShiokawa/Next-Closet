<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<% AdminBean loginAdmin = (AdminBean)request.getSession().getAttribute("admin"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet...</title>
</head>
<body>
<%String password = (String) session.getAttribute("password");%>
<%-- <%@ include file="includes/navbar.jsp" %> --%>
 <form method="post" action="AdminEditServlet">
	<label>お名前：</label><input type="text" name="adminname" value="<%=loginAdmin.getAdminName() %>"><br>
	<label>フリガナ：</label><input type="text" name="kananame" value="<%=loginAdmin.getAdminKanaName() %>"><br>
	<label>メールアドレス：</label><input type="email" name="email" value="<%=loginAdmin.getEmail() %>"><br>
	<label>パスワード：</label><input type="password" name="password" value="<%=password%>"><br>
	<button type="submit">更新</button>
 </form>
</body>
</html>