<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List" %>
<%@ page import="model.dao.*" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>next closet...</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400&display=swap" rel="stylesheet">
	   <link rel="stylesheet" href="css/product-detail.css">
	   <link rel = "stylesheet" href = "css/navbar.css">
	   <% UserBean loginUser = (UserBean)request.getSession().getAttribute("user");
	   	  List <ProductBean> productList = (ArrayList <ProductBean>)request.getAttribute("productList");
		  if(productList == null) {
				request.getSession().setAttribute("productNotFound", productList);
				response.sendRedirect("ProductDetailServlet");
				return;
		  }
		  int product_id = -1;
	    %>
	</head>
	<body>
	<%@ include file="includes/navbar.jsp" %> 
	<div class="wrapper">
		<div class="product_contents">
			<div class="img">
				<% for ( ProductBean columns : productList){ 
				  	String img = columns.getImage();
			        if ( img == null ){
			     	   img = "https://placehold.jp/480x640.png";
			        } else {
			     	   img = "image/" + img;
			        }
			        
			        product_id = columns.getProductId();
				%>
				<img src="<%= img %>">
			</div> <!-- img閉じタグ -->
			<div id="product-detail">
				<p id="product_name" class="text"><%= columns.getProductName() %><p>
				<p class="text">&yen; <%= columns.getPrice() %><span class="tax_in">税込</span></p>
				<p id="product_descruption" class="text"><%= columns.getDescription() %></p>
				
				<% } %>
				
				<% if( loginUser != null) { %>
				<form id="form" name="form" action="AddToCartServlet" method="post">
					<input type="hidden" name="order" value="order" >
					<input type="hidden" name="productId" value="<%= product_id %>" >
					<input type="radio" name="sizeId" value="1" id="S" checked ><label for="S" class="radio_label">S</label>
					<input type="radio" name="sizeId" value="2" id="M" ><label for="M" class="radio_label">M</label>
					<input type="radio" name="sizeId" value="3" id="L" ><label for="L" class="radio_label">L</label><br><br>
					
					<span>数量</span><input type="number" name = "quantity"　required>
					<input class="submit" type="submit" value="カートに入れる" >
					<input class="submit" type="submit" value="いますぐ買う" onclick="goOrder()">
					<!-- ログインしてなかったらログインに飛ばす -->
				</form>
				<% } else { %>
						<button id="goLogin"><a href="login.jsp">ログイン または新規登録して購入する</a></button>	
				<% } %>
			</div>
		</div>
	</div>
		
	<%@ include file="includes/footer.jsp" %>
		
		<script>
			function goOrder(){
				document.getElementById('form').method = 'get';
				document.getElementById('form').action = 'OrderConfilmServlet';
			}
		</script>
	</body>
</html>