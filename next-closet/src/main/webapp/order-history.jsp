<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="model.bean.*" %>
<%@ page import="model.dao.*, model.SizeText" %>
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
	<title>next closet...</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Tangerine&display=swap" rel="stylesheet">	
	<link rel="stylesheet" href="css/order-history.css">
	<link rel="stylesheet" href="css/navbar.css">
</head>
<body>
<%@ include file="includes/navbar.jsp" %> 
<main>
	<div class="wrapper">
			<h2 id="section_title">Order History</h2>
		    <h3 class="page-title">購入履歴</h3>
			<% for( OrderBean list : orderList) {
				      img = dao.getProductImage(list.getProductId());
                      if ( img == null ){
                   	   img = "https://placehold.jp/480x640.png";
                      } else {
                   	   img = "image/" + img;
                      }
            %>
			<div class="border">
		   		<div class="container">
		  			 
		 		 	 <div class="right">
		 		 	 	<figure class="image"><img src='<%= img %>' alt=""></figure>
		 		 	 	 <div class="text_box">	
				 		     <p class="product">注文番号<%= list.getOrderItemId() %></p>
				 		     <p class="product">商品名<%= dao.getProductName(list.getProductId()) %></p>
				   			 <p class="purchasedate">注文日 <%= list.getOrderDate() %></p>
				   			 <p class="purchasedate">配達先住所 <%= list.getDeliveryAddress() %></p>
				   			 <p class="amount">合計金額<%= list.getTotalAmount() %></p>
				    		 <p class="size">サイズ<%= st.sizeText(list.getSizeId()) %></p>
			    		 </div>
		 		    </div>
		 		    <button><a href="ProductDetailServlet?productId=<%= list.getProductId() %>">もう一度購入</a></button>
			   </div>
		   </div>
		   <% } %>
	</div>
</main>
 <%@ include file="includes/footer.jsp" %>  
</body>
</html>