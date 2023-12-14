<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% AdminBean loginAdmin = (AdminBean)request.getSession().getAttribute("admin"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet...</title>
</head>
<body>
<form action="AdminRegisterServlet" method="post">

					<label>お名前</label><input type="text" name="adminname" placeholder="例) テスト　太郎" required><br>
					<label>フリガナ</label><input type="text" name="kananame" placeholder="例) テスト　タロウ" required><br>
					<label>メールアドレス</label><input type="email" name="email" placeholder="例) 〇〇@〇〇.com" required><br> 
					<label>パスワード</label><input type="password" name="password" placeholder="8文字以上" required><br> 
					<button type="submit">管理者を登録する</button>
</form>

</body>
</html>