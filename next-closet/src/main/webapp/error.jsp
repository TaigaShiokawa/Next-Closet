<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラーページ</title>
<link rel = "stylesheet" href = "css/error.css">
<link rel = "stylesheet" href = "css/navbar.css">
</head>
<body>
	<%@ include file="includes/navbar.jsp" %>
	<div class="container">
	<img class="error_img" src="image/decoration/error.jpg" alt="error">
		<% String errorMessage = (String)session.getAttribute("errorMessage"); %>
		<% String userNotFound = (String)session.getAttribute("userNotFound"); %>
		<% String cartItemNotFound = (String)session.getAttribute("cartItemNotFound"); %>
		<% String productNotFound = (String)session.getAttribute("productNotFound"); %>
			<% if(errorMessage != null && !errorMessage.isEmpty()) { %>
			<p><%= errorMessage %></p>
			<% session.removeAttribute("errorMessage"); %>
			<% } else if(userNotFound != null) { %>
			<p><%=userNotFound %></p>
			<% session.removeAttribute("userNotFound"); %>
			<% } else if(cartItemNotFound != null) { %>
			<p><%=cartItemNotFound %></p>
			<% session.removeAttribute("cartItemNotFound"); %>
			<% } else if(productNotFound != null) { %>
			<p><%=productNotFound %></p>
			<% session.removeAttribute("productNotFound"); %>
			<% } %>
	
	</div>
	
			 <%@ include file="includes/footer.jsp" %> 
			 <script src="js/form.js"></script>
</body>
</html>