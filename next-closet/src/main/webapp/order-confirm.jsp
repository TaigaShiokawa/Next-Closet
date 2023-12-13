<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List" %>
<%@ page import="model.dao.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("productListServlet"); %>
<% } %>
<% String order = (String)request.getSession().getAttribute("order"); %>
<% AddressBean address = (AddressBean)request.getAttribute("addres"); //メイン住所 %>
<% List <AddressBean> addAddresses = ( ArrayList<AddressBean> )request.getAttribute("addAddresses");//サブ住所 %>
<% UserBean user = ( UserBean )request.getSession().getAttribute("user");  //user関係の情報%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet ...</title>

</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div>
	<span>
		<strong>まだ注文が確定していません。この商品を購入しますか？</strong>
	</span>
</div>
<div class="border">
<form action="OrderConfilmServlet" method="post">

<% if( order.equals("allCartItems") ){
	
	List <CartBean> cartAllItemList = ( ArrayList <CartBean> )request.getAttribute("cartAllItemList"); %> 
		for(CartBean columns : cartAllItemList) { %>
		
		<div class="flex">
		 	<figure class="image"><img src="<%= columns.getImage() %>" alt="商品画像"></figure>
		  	<div class="right">
			    <p class="product">商品名：<%= columns.getProductName() %></p>
			    <p class="size">サイズ：<%= st.sizeText(columns.getSizeId() %>)</p>
			    <p class="quantity">数量：<%= columns.getQuantity() %></p>
			    <p class="price">金額：<%= columns.getPrice() %></p>
			    <% totalAmount += columns.petPrice(); %>
		  	</div>	
		</div>
		 <% }
		
	} else if(order.equals("order") ) { //商品詳細から今すぐ購入にした場合 %> 
	<% List <ProductBean> productList = (List <ProductBean>)request.getAttribute("productList"); %>
	<div class="flex">
	<%  for(ProductBean columns : productList ) { %>
	<% ProductBean productList = (ProductBean)request.getAttribute("productList"); %> 
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
 <% } else if(order.equals("allCartItems")){%>
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
<div class=delivery>
	<span>配送先</span><br>
	<input type="checkbox" name="address" value="default"><laber><%= Addresses.getPrefectures() + Addresses.getAddress() %></laber>	
</div>
<div class=changeDelivery>
	<span>配送先を変える</span>
	<a href="住所変更.jsp">マイページから住所追加</a>

	<% for( AddressBean add : addAddresses) { %> 	
		<input type="checkbox" name="address" value="another2"><laber><%= add.getPrefectures() + add.getAddress() %></laber>
	<% } %> 

</div>
<div class=total>
		<span>ご注文金額(税込)</span>
		<label>合計</label>
		<p><%= totalprice %></p></div>
<input type="submit" value="購入確定">
</form>
<a href="ProductListServlet">戻る</a>

</body>
</html>