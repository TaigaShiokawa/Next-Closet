<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List" %>
<%@ page import="model.dao.*" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>next closet...</title>
	   <link rel="stylesheet" href="css/product-detail.css">
	   <link rel = "stylesheet" href = "css/navbar.css">
	   <% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
		 <%
	   		 List <ProductBean> productList = (ArrayList <ProductBean>)request.getAttribute("productList");
	     %>
	</head>
	<body>
	
　　 <%@ include file="includes/navbar.jsp" %> 
	<div class="img">
	<% for ( ProductBean columns : productList){ 
		 String img =  columns.getImage();
	  	 if( img == null ){
		 	 img = "https://placehold.jp/480x640.png";
	  	 }
	
	%>
		<img src="<%= img %>">
	</div>
		<div>
		
			<p><%= columns.getProductName() %><p>
			<p><%= columns.getDescruption() %></p>
			<p>&yen; <%= columns.getPrice() %><span>税込</span></p>
			<form id="form" name="form" action="AddToCartServlet" method="post">
				<input type="hidden" name="order" value="order" >
				<input type="hidden" name="productId" value="<%= columns.getProductId() %>" >
				<input type="radio" name="sizeId" value="1" checked ><label>S</label>
				<input type="radio" name="sizeId" value="2"  ><label>M</label>
				<input type="radio" name="sizeId" value="3" ><label>L</label>
				<label>数量</label><input type="number" name = "quantity"　required>
				<input type="submit" value="カートに入れる" >
				<input type="submit" value="いますぐ買う" onclick="goOrder()">
			</form>
		</div>
		<% } %>
		
		<script>
			function goOrder(){
				document.getElementById('form').method = 'get';
				document.getElementById('form').action = 'OrderConfilmServlet';
			}
		</script>
	</body>
</html>