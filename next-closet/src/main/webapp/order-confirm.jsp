<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List , model.SizeText" %>
<%@ page import="junit.model.dao.*" %>
<%@ page import="model.bean.*" %>

<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("productListServlet"); %>
<% } %>

<% String order = (String)request.getAttribute("order");
String img = null;%>


<% String message = (String)request.getAttribute("message"); 
	if(message == null){
		message = "";
	}
	SizeText st = new SizeText();
   int totalAmount = 0 ;
   %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>next closet ...</title>
		<link rel="stylesheet" href="css/order-confirm.css">
		<link rel = "stylesheet" href = "css/navbar.css">
	</head>
	<body>
	<%@ include file="includes/navbar.jsp" %>
	<main>
	<div class="container">
	<div　class="order">
			<img class="step_image" src="image/decoration/step1.jpg">
			<h3>まだ注文が確定していません。この商品を購入しますか？</h3>
			<p><%= message %></p>
		</div>
		<div class="border">
		<form action="OrderConfilmServlet" method="post">
					<%  if(order.equals("order")) { //商品詳細から今すぐ購入にした場合 %> 
					<% List <ProductBean> productList = (List <ProductBean>)request.getAttribute("productList"); %>
						<div class="flex">
							<% for(ProductBean columns : productList ) {
							 		img = columns.getImage();
			                        if ( img == null ){
			                     	   img = "https://placehold.jp/480x640.png";
			                        } else {
			                     	   img = "image/" + img;
			                        }
							   int price = columns.getPrice();
							   int sizeId = (int)request.getAttribute("sizeId");
							   int quantity =  (int)request.getAttribute("quantity");
							   totalAmount += price * quantity; %>
							    <% String formattedItemPrice = String.format("%,d", columns.getPrice()); %>
				 			
						  		<div class="right">
						  		<div class="order_box">
						  		<figure class="image"><img src='<%= img %>' alt="商品画像"></figure>
							  		<div class="text">
									    <p class="product">商品名：<%= columns.getProductName() %></p>
									    <p class="size">サイズ：<%= st.sizeText(sizeId) %></p>
									    <p class="quantity">数量：<%= quantity %></p>
									    <p class="price">&yen; <%= formattedItemPrice %> 税込</p>
									     </div>
										 </div>
									    <input type="hidden" name="productId" value="<%= columns.getProductId() %>">
									    <input type="hidden" name="sizeId" value="<%= sizeId %>">
									     <input type="hidden" name="order" value="order">
									    <input type="hidden" name="quantity" value="<%= quantity %>">
									    <input type="hidden" name="totalAmount" value='<%= totalAmount %>'>　
						  		</div>	
								</div>
							 <% } %>
					  <% } else if(order.equals("singleCartItem")){ //カートのうち一つの商品のみ購入%>
								  <% CartItemBean cartItem = (CartItemBean)request.getAttribute("cartItem");%>
								  <% List <ProductBean> productList = (List <ProductBean>)request.getAttribute("productList"); %>
								  <% int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));%>
			
							<div class="flex">		
								<% for(ProductBean pl : productList ) { %>
								
								    <% img = pl.getImage();
			                        if ( img == null ){
			                     	   img = "https://placehold.jp/480x640.png";
			                        } else {
			                     	   img = "image/" + img;
			                        } %>
								
									<% int price = pl.getPrice(); %>
									<% int sizeId = cartItem.getSizeId(); %>
									<% int quantity = cartItem.getQuantity(); %>
									<% totalAmount += price * quantity; %>
									 <% String formattedItemPrice = String.format("%,d", pl.getPrice()); %>
								<div class="right">
								 <div class="order_box">
									<figure class="image"><img src="<%= img %>" alt="商品画像"></figure>
									 <div class="text">
										 <p class="product">商品名：<%= pl.getProductName() %></p>
										 <p class="size">サイズ：<%= st.sizeText(sizeId) %></p>
										 <p class="quantity">数量：<%= quantity %></p>
										 <p class="price">&yen; <%= formattedItemPrice %> 税込</p>
									 </div>
									 </div>
									 <input type="hidden" name="productId" value="<%= pl.getProductId() %>">
								     <input type="hidden" name="sizeId" value="<%= sizeId %>">	
								     <input type="hidden" name="order" value="singleCartItem">
								     <input type="hidden" name="quantity" value="<%= quantity %>">
								     <input type="hidden" name="totalAmount" value='<%= totalAmount %>'>
								     <input type="hidden" name="cartItemId" value='<%= cartItemId %>'>
								     <input type="hidden" name="productList" value='<%= productList %>'>
								 </div>
							<% } %>
					  <% } else if(order.equals("allCartItems")) {//もしカート全て購入だったら 
					        List <CartItemBean> cartAllItemList = (ArrayList<CartItemBean>) request.getAttribute("cartAllItemList");
							List <ProductBean> productList = null;
							int allCartProductId = -1;
							/* ProductDAO dao = new ProductDAO(); */
							int getProductId = 1;
					
					       		 for (CartItemBean item : cartAllItemList) {  
						       			 img = item.getProduct().getImage();
						                 if ( img == null ){
						              	   img = "https://placehold.jp/480x640.png";
						                 } else {
						              	   img = "image/" + img;
						                 } 
					      			     int price =  item.getProduct().getPrice();
							  		     int sizeId = item.getSizeId();
							  		     int quantity = item.getQuantity(); 
							  		     totalAmount = totalAmount + (price * quantity); %>
							  		     <% String formattedItemPrice = String.format("%,d", price); %>
		  		     	  		    
						      	  <div class="right">
						      	  	  <div class="order_box">
						      	  	  <figure class="image"><img src="<%= img %>" alt="商品画像"></figure>
				  						 <div class="text">
					  						 <p class="product">商品名： <%= item.getProduct().getProductName() %></p>
					  						 <p class="size">サイズ：<%= st.sizeText(sizeId) %></p>
					  						 <p class="quantity">数量：<%= quantity %></p>	
					  						 <p class="price">&yen; <%= formattedItemPrice %> 税込</p>
				  						 </div>
						      	  	  </div>
				  						 
							        <% } %>	        
					        <input type="hidden" name="order" value="allCartItems"> 
		  					<input type="hidden" name="totalAmount" value="<%= totalAmount %>">
     		 <% }  %> 
    		  </div>
				<div class=delivery>
				<% AddressBean address = (AddressBean)request.getAttribute("address");//メイン住所 %>
					<label class="bold">配送先</label><br>
					<input class="check" type="checkbox" name="address" checked value='<%= (address.getPrefectures() + address.getAddress()) %>'><label><%= (address.getPrefectures() + address.getAddress())%></label>	
				</div>
				</div>
			<div class=changeDelivery>
				<span class="bold">配送先を変える</span>
				<a href="MypageServlet">マイページから住所追加</a><br>
	
			<% List <AddressBean> addAddresses = ( ArrayList<AddressBean> )request.getAttribute("addAddresses");//サブ住所 %>
			<% for( AddressBean add : addAddresses) { %> 	
				<input class="check" type="checkbox" name="address" value="<%= add.getPrefectures() + add.getAddress() %>"><laber><%= add.getPrefectures() + add.getAddress() %> </laber><br>
		
			<% } %> 
			<div class="total">
			<span class="bold">ご注文金額</span>
			<% String formattedTolalAmount = String.format("%,d", totalAmount); %>
			<p>合計金額 &yen;<%= formattedTolalAmount %> 税込</p>
		</div>

		</div>
		
		<div class="submit_box"> 
			<input class="submit" type="submit" value="購入確定">
			</form>
			<br>
			<a class="back" href="ProductListServlet">商品一覧に戻る</a>
			
		</div>
		
</div>
	<%@ include file="includes/footer.jsp" %>
</main>
	<script>
			const checkGet = document.getElementsByName('address');
			  checkGet.forEach((check) => {
				    check.addEventListener('click', () => {
				      if(check.checked) {
				        checkGet.forEach((allChecks) => {
				          allChecks.checked = false;
				        });
				        check.checked = true;
				      }
				    });
				  });
	</script>
</body>
</html>