<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 管理者のセッション設定が必要 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラーページ</title>
</head>
<body>
<!-- 管理者用のナビゲーションバーが必要かも -->
<% String errorMessageToAdmin = (String)session.getAttribute("errorMessageToAdmin"); %>
<% if(errorMessageToAdmin != null) { %>
<p><%=errorMessageToAdmin %></p>
<% } %>
</body> 
</html>