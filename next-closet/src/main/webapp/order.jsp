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
<title>Insert title here</title>
<link rel="stylesheet" href="css/order.css">
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="container">
<!-- ここに画像入れる -->

<h3><strong>ご注文ありがとうございました</strong></h3>
 
 
 <div class="link">
 	<ul>
 	<li class="product"><a href="商品一覧jsp">商品一覧</a></li>
  	<li class="history"><a href="購入履歴jsp">購入履歴</a></li>
  </ul>
 </div>
</div>
</body>
</html>