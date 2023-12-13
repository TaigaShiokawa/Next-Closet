
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List , model.SizeText" %>
<%@ page import="model.dao.*" %>
<%@ page import="model.bean.*" %>

<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("productListServlet"); %>
<% } %>

<% String order = (String)request.getAttribute("order"); %>


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
		
		<div　class="order">
			<h3>まだ注文が確定していません。この商品を購入しますか？</h3>
			<p><%= message %></p>

		</div>
		<div class="border">
		<form action="OrderConfilmServlet" method="post">
		
		<%  if(order.equals("order")) { //商品詳細から今すぐ購入にした場合 %> 
			<% List <ProductBean> productList = (List <ProductBean>)request.getAttribute("productList"); %>

			<div class="flex">
				<% for(ProductBean columns : productList ) { %>
				<% int price = columns.getPrice(); %>
				<% int sizeId = (int)request.getAttribute("sizeId"); %>
				<% int quantity =  (int)request.getAttribute("quantity"); %>
				<%  totalAmount = price * quantity; %>
	 			<figure class="image"><img src="<%= columns.getImage() %>" alt="商品画像"></figure>
			  		<div class="right">
					    <p class="product">商品名：<%= columns.getProductName() %></p>
					    <p class="size">サイズ：<%= st.sizeText(sizeId) %></p>
					    <p class="quantity">数量：<%= quantity %></p>
					    <input type="hidden" name="productId" value="<%= columns.getProductId() %>">
					    <input type="hidden" name="sizeId" value="<%= sizeId %>">
					     <input type="hidden" name="order" value="order">
					    <input type="hidden" name="quantity" value="<%= quantity %>">
					    <input type="hidden" name="totalAmount" value='<%= totalAmount %>'>　
			  		</div>	
			</div>
			 <% } %>
		  <% } %>	

		
		
		
		
		
		
		
<%-- 
<% if( order.equals("allCartItems") ){ //　カート内全て
	List <CartItemBean> cartAllItemList = ( ArrayList <CartItemBean> )request.getAttribute("cartAllItemList"); %> 
		for(CartItemsBean columns : cartAllItemList) { %>


		<div class="flex">
		 	<figure class="image"><img src="<%= columns.getImage() %>" alt="商品画像"></figure>
		  	<div class="right">
			    <p class="product">商品名：<%= columns.getProductName() %></p>
			    <p class="size">サイズ：<%= st.sizeText(columns.getSizeId() %>)</p>
			    <p class="quantity">数量：<%= columns.getQuantity() %></p>
			    <p class="price">金額：<%= columns.getPrice() %></p>
			    <% totalAmount += columns.getPrice(); %>
		  	</div>	
		</div>
		 <% }
		

	} else 
 <% } else if(order.equals("allCartItems")){%>

	} else<%  
	 <% }} %>
 <% } else if(order.equals("cartItems")){%>

 <% ProductBean cartItem = (ProductBean)request.getAttribute("cartItem"); %> 
 <% List <ProductBean> productList = (List <ProductBean>)request.getAttribute("productList"); %>
 <%  for(ProductBean columns : productList ) { %>
 <figure class="image"><img src="<%= columns.getImage() %>" alt="商品画像"></figure>
  		<div class="right">
		    <p class="product">商品名：<%= columns.getProductName() %></p>
		    <p class="quantity">サイズ：<%= st.sizeText(columns.getSize() %></p>
		    <p class="quantity">数量：<%= columns.getQuantity() %></p>
		    <input type="hidden" name="productId" value="<%= columns.getProductId() %>">
		    <input type="hidden" name="sizeId" value="<%= columns.getSize() %>">
		    <input type="hidden" name="quantity" value="<%= columns.getQuantity() %>">
  		</div>	
	</div>
  <% } %>

 <% } %>
</div>

　--%>


<div class=delivery>
	<% AddressBean address = (AddressBean)request.getAttribute("address");//メイン住所 %>
	<span>配送先</span><br>

	
	<input type="radio" name="address" value="<%= (address.getPrefectures() + address.getAddress()) %>"　checked><label><%= (address.getPrefectures() + address.getAddress())%></label>	
	<% System.out.println(address.getPrefectures() + address.getAddress());%>
</div>

	
</div>
<div class=changeDelivery>
	<span>配送先を変える</span>
	<a href="MypageServlet">マイページから住所追加</a>
	
	<% List <AddressBean> addAddresses = ( ArrayList<AddressBean> )request.getAttribute("addAddresses");//サブ住所 %>
	<% for( AddressBean add : addAddresses) { %> 	
		<input type="radio" name="address" value="<%= add.getPrefectures() + add.getAddress() %>"><laber><%= add.getPrefectures() + add.getAddress() %> </laber>

	<% } %> 

</div>
<div class=total>
		<span>ご注文金額(税込)</span>
		<label>合計</label>

	<p><%= totalAmount %></p>
		</div>

 <input type="submit" value="購入確定">
</form>

<a href="ProductListServlet">戻る</a>

</main>

</body>
</html>