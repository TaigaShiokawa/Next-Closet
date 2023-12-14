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
<% String errorMessage = (String)session.getAttribute("errorMessage"); %>
<% String userNotFound = (String)session.getAttribute("userNotFound"); %>
<% String cartItemNotFound = (String)session.getAttribute("cartItemNotFound"); %>
<% if(errorMessage != null && !errorMessage.isEmpty()) { %>
<p><%= errorMessage %></p>
<% session.removeAttribute("errorMessage"); %>
<% } else if(userNotFound != null) { %>
<p><%=userNotFound %></p>
<% session.removeAttribute("userNotFound"); %>
<% } else if(cartItemNotFound != null) { %>
<p><%=cartItemNotFound %></p>
<% session.removeAttribute("cartItemNotFound"); %>
<% } %>
</body>
</html>