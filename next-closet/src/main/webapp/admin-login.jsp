<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h2>管理者ログイン</h2>
<form action="AdminLoginServelt" method="post">
<input type="text" name="email" placeholder="メールアドレス"><br>
<input type="password" name="password" placeholder="パスワード"><br>
<button type="submit">login</button>
</form>

</body>
</html>