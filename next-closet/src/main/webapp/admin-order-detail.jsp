<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="model.bean.*" %>
<%@ page import="model.dao.* , model.SizeText" %>
<%@ page import="java.util.* , java.util.ArrayList, java.util.List" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %> 
<% List <OrderBean> orderList = ( ArrayList <OrderBean>)request.getAttribute("orderList"); %>
<% SizeText st = new SizeText(); %>
<% OrderDAO dao = new OrderDAO(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/order-history.css">
</head>
<body>
 <%@ include file="includes/navbar.jsp" %> 
 
<main>
<h3>購入履歴</h3>

<% for( OrderBean list : orderList) { %>


<div class="border">


  <div class="container">
  	<figure class="image"><img src="https://placehold.jp/150x150.png" alt=""></figure>
 		 <div class="right">
 		     <p class="product">注文番号<%= list.getOrderItemId() %></p>
 		     
 		     	<p class="product">商品名<%= dao.getProductName(list.getProductId()) %></p>
 		    
   			 <p class="purchasedate">注文日 <%= list.getOrderDate() %></p>
   			 <p class="purchasedate">配達先住所 <%= list.getDeliveryAddress() %></p>
   			 <p class="amount">合計金額<%= list.getTotalAmount() %></p>
    		 <p class="size">サイズ<%= st.sizeText(list.getSizeId()) %></p>
    		 <a href="ProductDetailServlet?productId=<%= list.getProductId() %>">もう一度購入</a>
    		
 			
 		</div>
</div>
</div>
<% } %>
</main>
</body>
</html>