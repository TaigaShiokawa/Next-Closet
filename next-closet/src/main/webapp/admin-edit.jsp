<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="junit.model.dao.*" %>
<% AdminBean admin = (AdminBean)request.getAttribute("admin");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="icon" href="image/favicon.png" id="favicon">
<link rel="stylesheet" href="css/admin-user-register.css">
<link rel="stylesheet" href="css/admin-navbar.css">
<title>管理者情報編集</title>
</head>
<body>
  <%@ include file="includes/admin-navbar.jsp" %>
<main>
 	<div class="container"
		 <form method="post" action="AdminEditServlet">
		 	<input type="hidden" name="adminId" value="<%=admin.getAdminId()%>">
			<label>お名前：</label><input type="text" name="adminname" value="<%=admin.getAdminName() %>"><br>
			<label>フリガナ：</label><input type="text" name="kananame" value="<%=admin.getAdminKanaName() %>"><br>
			<label>メールアドレス：</label><input type="email" name="email" value="<%=admin.getEmail() %>"><br>
			<button class="admin_submit" type="submit">更新</button>
		 </form>
		 
		 <form method="post" action="AdminPasswordEdit">
		  	<input type="hidden" name="adminId" value="<%=admin.getAdminId()%>">
		 	<label>パスワード：</label><input type="password" name="password"><br>
		 	<button class="admin_submit" type="submit">更新</button>
		 </form>
		 
		 <p class="back"><a href="AdminDetailServlet?adminId=<%= admin.getAdminId() %>">詳細へ戻る</a></p>
	</div>
 </main>
<%@ include file="includes/admin-footer.jsp" %>  
</body>
</html>