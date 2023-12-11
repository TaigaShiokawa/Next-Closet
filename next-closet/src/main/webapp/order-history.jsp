<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<h3>購入履歴</h3>

<% /* for文このあたりから？  */%>

<div class="border">

  <div class="flex">
  	<figure class="image"><img src="https://placehold.jp/200x200.png" alt=""></figure>
 		 <div class="right">
   			 <p class="product">商品名<!-- Javaで商品名を表示 --></p>
   			 <p class="purchasedate">購入日<!-- Javaで購入日を表示 --></p>
   			 <p class="amount">金額<!-- Javaで金額を表示 --></p>
    		 <p class="size">サイズ<!-- Javaでサイズを表示 --></p>
 		 </div>
 	<input type="submit" value="もう一度購入"><!-- 	javaで購入 -->
</div>
<% /* for文ここまで？ */ %>
</body>
</html>