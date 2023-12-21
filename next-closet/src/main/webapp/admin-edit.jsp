<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<% AdminBean admin = (AdminBean)request.getAttribute("admin");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet...</title>
</head>
<body>
<%-- <%@ include file="includes/navbar.jsp" %> --%>
 <form method="post" action="AdminEditServlet">
 <input type="hidden" name="adminId" value="<%=admin.getAdminId()%>">
	<label>お名前：</label><input type="text" name="adminname" value="<%=admin.getAdminName() %>"><br>
	<label>フリガナ：</label><input type="text" name="kananame" value="<%=admin.getAdminKanaName() %>"><br>
	<label>メールアドレス：</label><input type="email" name="email" value="<%=admin.getEmail() %>"><br>
	<button type="submit">更新</button>
 </form>
 <form method="post" action="">
 	<label>パスワード：</label><input type="password" name="password"><br>
 	<button type="submit">更新</button>
 </form>
</body>
</html>