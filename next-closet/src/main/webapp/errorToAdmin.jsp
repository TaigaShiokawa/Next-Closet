<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 管理者のセッション設定が必要 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラーページ</title>
<link rel="icon" href="image/favicon.png" id="favicon">
<link rel = "stylesheet" href = "css/error.css">
<link rel = "stylesheet" href = "css/admin-navbar.css">
</head>
<body>
<%@ include file="includes/admin-navbar.jsp" %>
<main>
    <div class="container">
	<img class="error_img" src="image/decoration/error.jpg" alt="error">
	<!-- 管理者用のナビゲーションバーが必要かも -->
		<% String errorMessageToAdmin = (String)session.getAttribute("errorMessageToAdmin"); %>
		<% String productError = (String)session.getAttribute("productError"); %>
		<% if(errorMessageToAdmin != null) { %>
		<p><%=errorMessageToAdmin %></p>
		<% session.removeAttribute("errorMessageToAdmin"); %>
		<% } else if(productError != null) { %>
		<p><%=productError %></p>
		<% session.removeAttribute("productError"); %>
		<% } %>
	</div>
</main>

<%@ include file="includes/admin-footer.jsp" %>
</body> 
</html>