<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
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
 <%@ include file="includes/navbar.jsp" %>  
 <form method="post" action="">
	<label>お名前：</label><input type="text" name="adminname" value="<%=loginAdmin.getAdminName() %>"><br>
	<label>フリガナ：</label><input type="text" name="kananame" value="<%=loginAdmin.getAdminKanaName() %>"><br>
	<label>メールアドレス：</label><input type="text" name="email" value="<%=loginAdmin.getEmail() %>"><br>
	<label>パスワード：</label><input type="text" name="password" value="<%=loginAdmin.getPassword() %>"><br>
	<button type="submit">更新</button>
 </form>
</body>
</html>