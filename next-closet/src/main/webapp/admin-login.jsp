<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<%-- <% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %> --%>
<!-- 管理者のセッション設定が必要 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログインページ</title>
</head>
<body>
<!-- 管理者用のナビゲーションバーが必要かも -->
<h2>管理者ログイン</h2>
<form action="AdminLoginServelt" method="post">
<input type="text" name="email" placeholder="メールアドレス"><br>
<input type="password" name="password" placeholder="パスワード"><br>
<button type="submit">login</button>
</form>

</body>
</html>