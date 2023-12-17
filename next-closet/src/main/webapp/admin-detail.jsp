<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%-- <% AdminBean loginAdmin = (AdminBean)request.getSession().getAttribute("Admin"); %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet...</title>
</head>
<body>
<%-- <%@ include file="includes/admin-navbar.jsp" %> --%>
	<form action="AdminEditServlet" method="post">
	<div>お名前：<%-- <%=loginAdmin.getAdminName()%> --%></div>
	<div>フリガナ：<%-- <%=loginAdmin.getAdminName()%> --%></div>
	<div>メールアドレス：<%-- <%=loginAdmin.getAdminName()%> --%></div>
	<div>パスワード：<%-- <%=loginAdmin.getpassword()%> --%></div>

	<button type="submit">更新</button>
	<button type="submit">削除</button>
</form>
<a href="#">戻る</a>
</body>
</html>