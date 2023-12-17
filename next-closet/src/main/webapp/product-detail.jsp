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
					<div class="size_box">
						<input type="radio" name="sizeId" value="1" id="S" checked ><label for="S" class="radio_label">S</label>
						<input type="radio" name="sizeId" value="2" id="M" ><label for="M" class="radio_label">M</label>
						<input type="radio" name="sizeId" value="3" id="L" ><label for="L" class="radio_label">L</label>
					</div>
					
					
  
					<label class="quantity">数 量</label><span class="quantity_max">※10点まで購入可能</span><br>
					<label class="number-spinner-wrap">
						<span class="spinner spinner-down">-</span>
						<input class="number_input" type="number" name = "quantity" min="1" max="10"  step="1" value="1" required>
						<span class="spinner spinner-up">+</span>
					</label><br>
					<input class="submit" type="submit" value="ADD TO CART">
					<input class="submit" type="submit" value="BUY NOW" onclick="goOrder()">
					<!-- ログインしてなかったらログインに飛ばす -->
				</form>
				<% } else { %>
						<button id="goLogin"><a href="login.jsp">ログイン または新規登録して購入する</a></button>	
				<% } %>
				
				<a class="back" href="ProductListServlet">商品一覧へ戻る</a>
			</div>
		</div>
	</div>
		
	<%@ include file="includes/footer.jsp" %>
		
		<script>
			function goOrder(){
				document.getElementById('form').method = 'get';
				document.getElementById('form').action = 'OrderConfilmServlet';
			}

			const $wrap = document.querySelector('.number-spinner-wrap')
			const $input = $wrap.querySelector('input')
			
			$wrap.querySelector('.spinner-down').onclick = ()=>{
			  $input.stepDown()
			}
			$wrap.querySelector('.spinner-up').onclick = ()=>{
			  $input.stepUp()
			}
		</script>
	</body>
</html>