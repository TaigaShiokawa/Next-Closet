<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>next closet...</title>
		<link rel="stylesheet" href="css/order.css">
		<link rel = "stylesheet" href = "css/navbar.css">
	</head>
	<body>
		<%@ include file="includes/navbar.jsp" %>
		<main>
			<div class="container">
			<img src="image/decoration/step2.jpg">
			
			<h2><strong>ご注文ありがとうございました</strong></h2>
			 <div class="link">
			 	<ul>
				 	<li><a href="ProductListServlet">商品一覧</a></li>
				  	<li><a href="OrderHistoryServlet">購入履歴</a></li>
			 	</ul>
			 </div>
		</div>
		</main>
		<%@ include file="includes/footer.jsp" %>
	</body>
</html>