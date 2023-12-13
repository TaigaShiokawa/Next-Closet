<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/order-confirm.css">
</head>
<body>
 <%@ include file="includes/navbar.jsp" %>
<main>
<!-- 以下コメントアウトは画像で対応予定
	<ul>
		<li class="list1">カートの商品</li>
		<li class="list2"><strong>ご注文内容確認</strong></li>
		<li class="list3">完了</li>
	</ul> -->
	<div　class="order">
			<h3>まだ注文が確定していません。この商品を購入しますか？</h3>
	</div>
<%-- <% for(このへんからfor文？) { %> --%>
	<div class=border>
		<div class="container">
  			<figure class="image"><img src="https://placehold.jp/150x150.png" alt=""></figure>
  				<div class="right">
   				 	<p class="product">商品名<!-- Javaで商品名を表示 --></p>
   					<p class="quantity">数量<!-- Javaで数量を表示 --></p>
    		 		<p class="amount">¥<!-- Javaで金額を表示 --></p>
 	 			</div>
		</div>
	</div>
<%-- <% } %> --%>
	<div class=delivery>
		<span>配送先</span><br>
		<input type="checkbox" name="address" value="default"><!-- javaでデフォ住所表示 -->	
	</div>
	<div class=changeDelivery>
		<span>配送先を変える</span>
			<a href="住所変更.jsp" class="add">マイページから住所追加</a>
			<p class="sub"><input type="checkbox" name="address" value="another1"><!-- javaでサブ住所１表示 --></p>	
			<p class="sub"><input type="checkbox" name="address" value="another2"><!-- javaでサブ２住所表示 --></p>	
	</div>
	<div class=total>
		<span>ご注文金額(税込)</span>
		<label for="totalprice">合計   ¥</label>
		<!-- Javaで商品名を表示 -->
	</div>
		<p class="button"><input class="radius" type="submit" value="購入確定"><!-- 	javaで購入 --></p>
		<p class="back"><a href="一個前に戻る？.jsp">戻る</a></p>
</main>
</body>
</html>