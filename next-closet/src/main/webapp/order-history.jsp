<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="model.*" %>
<%@ page import="model.dao.bean.*, model.SizeText" %>
<%@ page import="java.util.* , java.util.ArrayList, java.util.List" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %> 
<% List <OrderBean> orderList = ( ArrayList <OrderBean>)request.getAttribute("orderList"); %>
<% SizeText st = new SizeText(); %>
<% OrderDAO dao = new OrderDAO(); 
String img = null;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>購入履歴</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Tangerine&display=swap" rel="stylesheet">	
	<link rel="stylesheet" href="css/order-history.css">
	<link rel="stylesheet" href="css/navbar.css">
	<link rel="icon" href="image/favicon.png" id="favicon">
</head>
<body>
<%@ include file="includes/navbar.jsp" %> 
<main>
	<div class="wrapper">
			<h2 id="section_title">Order History</h2>
		    <h3 class="page-title">購入履歴</h3>
			<% for( OrderBean list : orderList) {
				
				img = dao.getProductImage(list.getProductId());
				 if (img.isEmpty()){
              	   img = "image/noimg.jpg";
                } else {
             	    img = "image/" + img;
                }
            %>
             <% String TotalAmount = String.format("%,d", list.getTotalAmount()); %>
			<div class="border">
		   		<div class="container">
		  			 
		 		 	 <div class="right">
		 		 	 	<figure class="image"><img src='<%= img %>' alt=""></figure>
		 		 	 	 <div class="text_box">	
				 		     <p class="product">注文番号<%= list.getOrderItemId() %></p>
				 		     <p class="product">商品名<%= dao.getProductName(list.getProductId()) %></p>
				   			 <p class="purchasedate">注文日 <%= list.getOrderDate() %></p>
				   			 <p class="purchasedate">配達先住所 <%= list.getDeliveryAddress() %></p>
				   			 <p class="amount">合計 &yen; <%= TotalAmount %> 税込</p>
				    		 <p class="size">サイズ<%= st.sizeText(list.getSizeId()) %></p>
			    		 </div>
		 		    </div>
		 		    <button><a href="ProductDetailServlet?productId=<%= list.getProductId() %>">もう一度購入</a></button>
			   </div>
		   </div>
		   <% } %>
	</div>

 <% if(orderList.isEmpty()){ %>
 <div class="no_order_container">
 	 <p class="no_order">購入履歴はありません</p>
 </div>
<% } %>
</main>
<%@ include file="includes/footer.jsp" %>
</body>
</html>