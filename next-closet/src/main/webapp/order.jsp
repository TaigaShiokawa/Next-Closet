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
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div>
	<ul>
		<li class="list1">カートの商品</li>
		<li class="list2">ご注文内容確認</li>
		<li class="list3"><strong>完了</strong></li>
	</ul>
</div>

<div>
	<h3><strong>ご注文ありがとうございました</strong></h3>
 </div>
 
 <div>
 	<p><a href="商品一覧jsp">商品一覧</a></p>
  	<p><a href="購入履歴jsp">購入履歴</a></p>
 </div>

</body>
</html>