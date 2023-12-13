<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラーページ</title>
</head>
<body>
<% String errorMessageToAdmin = (String)session.getAttribute("errorMessageToAdmin"); %>
<% if(errorMessageToAdmin != null) { %>
<p><%=errorMessageToAdmin %></p>
<% } %>
</body> 
</html>