<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.bean.CartItemBean" %>
<% int i = 1 ; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet ...</title>
<link rel="stylesheet" href="css/cart.css">
<link rel="stylesheet" href="css/navbar.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Tangerine&display=swap" rel="stylesheet">	

</head>
<body>

<%@ include file="includes/navbar.jsp" %>
<main>


	<% List<CartItemBean> cartItems = (ArrayList<CartItemBean>)request.getAttribute("cartItems"); %>
	
	<% if(cartItems.isEmpty() ){  %>
	<div class="non_container">
	<div class="page-top">
		<div class="title">
			<h2>ShopingCart</h2>
			<h3>買い物かご</h3>
		</div>
		</div>
		
		<p>カート内に商品がありません。<p>
	
	</div>
	<footer class="non_footer">
	<p>
		<small>
			&copy; next closet ...
		</small>
	</p>
</footer>
	
	<% } else { %>
	<div class="container">
				<div class="left-container">
					<div class="page-top">
						<div class="title">
							<h2>ShopingCart</h2>
							<h3>買い物かご</h3>
						</div>
						
						
						<form action="OrderConfilmServlet" method="get">
				        	<input class="all_buy" type="submit" value="カート内のものを全て購入">
				    	</form>
					</div>
					<div class="list">
					
						<% 
					        
					    	if(cartItems == null) {
					    		request.getSession().setAttribute("productNotFound", cartItems);
					    		response.sendRedirect("product-detail.jsp");
					    		return;
					    	}
						
						
					        double totalPrice = 0;
					        for (CartItemBean item : cartItems) {
					            int itemPrice = item.getProduct().getPrice();
					            int quantity = item.getQuantity();
					            totalPrice += itemPrice * quantity;
					            String formattedItemTotal = String.format("%,d", itemPrice * quantity);
					        }
					        String formattedTotalPrice = String.format("%,d", (int)Math.round(totalPrice));
					    %>
					    <% 
					        for (CartItemBean item : cartItems) {
					        	int itemPrice = item.getProduct().getPrice();
					            int quantity = item.getQuantity();
					            String formattedItemTotal = String.format("%,d", itemPrice * quantity);
					            String img =  item.getProduct().getImage();
				                if ( img == null ){
				              	   img = "https://placehold.jp/480x640.png";
				                 } else {
				              	   img = "image/" + img;
				                 }
					    %>
					    <div class="product">
					        <img src="<%= img %>" alt="Product Image">
					        <div class="product_detail">
					            <p>商品名: <%= item.getProduct().getProductName() %></p>
					            <p>サイズ: <%= item.getSize().getSizeName() %></p>
					           
					            <form action="CartUpdateServlet" method="post">
					                <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">
					                <% String a = "number-spinner-wrap" + i ;%>
					                <label class="<%= a %> number-spinner-wrap">
										<span class="spinner spinner-down">-</span>
										<input class="number_input" type="number" name = "quantity" min="1" max="10"  step="1" value="<%= item.getQuantity() %>" required>
										<span class="spinner spinner-up">+</span>
										 <input class="update" type="submit" value="更新">
									</label><br>
					               
					            </form>     
					        </div>
					        <div class="form_box">
						        <form action="CartDeleteServlet" method="post">
						            <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">
						            <input class="delete" type="submit" value="この商品をカートから削除">
						        </form>
						        <form action="OrderConfilmServlet" method="get">
						            <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">
						            <input class="buy" type="submit" value="この商品のみ購入">
						        </form>
						         	<p>価格: <%= formattedItemTotal %>円</p>
						        
					        </div>
					    </div>
					    <% i += 1 ;  %>
					    
					    <% } %>  
					    <div id="count" data-repeat-count='<%= i %>'></div> 
				    
				    
					</div>
					
				</div>
				<div class="right-container">
					<div class="total_amount">
							         	<p>ご注文金額<span>（合計）</span><br>
							         	<span>税込</span>　 <%= formattedTotalPrice %>円</p>
					</div>
					
				</div>
			</div>
    </main>    
    <%@ include file="includes/footer.jsp" %>
	<% }  %>
		     

	

    
    <script>
					var countrepeat = document.getElementById("count").getAttribute("data-repeat-count");
					for( let j = 0 ; j <= countrepeat ; j++ ){

						const $wrap = document.querySelector('.number-spinner-wrap' + j)
						const $input = $wrap.querySelector('input')
						
						$wrap.querySelector('.spinner-down').onclick = ()=>{
						  $input.stepDown()
						}
						$wrap.querySelector('.spinner-up').onclick = ()=>{
						  $input.stepUp()
						}

						}
					

				
			
		</script>
</body>
</html>