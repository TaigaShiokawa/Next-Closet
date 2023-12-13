<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラーページ</title>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<% String errorMessage = (String)session.getAttribute("errorMessage"); %> <!-- 新規登録の際の例外処理 -->
<% String ERROR_MESSAGE_KEY = (String)session.getAttribute("ERROR_MESSAGE_KEY"); %> <!-- ログインの際の例外処理 -->
<% if(errorMessage != null && !errorMessage.isEmpty()) { %>
<p><%= errorMessage %></p>
<% session.removeAttribute("errorMessage"); %>
<% } else if(ERROR_MESSAGE_KEY != null) { %>
<p><%=ERROR_MESSAGE_KEY %></p>
<% session.removeAttribute("ERROR_MESSAGE_KEY"); %>
<% } %>

</body>
</html>