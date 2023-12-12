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
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div>
	<ul>
		<li class="list1">カートの商品</li>
		<li class="list2"><strong>ご注文内容確認</strong></li>
		<li class="list3">完了</li>
	</ul>
</div>
<div>
	<span>
		<strong>まだ注文が確定していません。この商品を購入しますか？</strong>
	</span>
</div>
<div class=border>
<%-- <% for(このへんからfor文？) { %> --%>
<div class="flex">
  <figure class="image"><img src="https://placehold.jp/150x150.png" alt=""></figure>
  <div class="right">
    <p class="product">商品名<!-- Javaで商品名を表示 --></p>
    <p class="quantity">数量<!-- Javaで数量を表示 --></p>
     <p class="quantity">数量<!-- Javaで数量を表示 --></p>
  </div>
</div>
<%-- <% } %> --%>
</p>
</div>
<div class=delivery>
	<span>配送先</span><br>
	<input type="checkbox" name="address" value="default"><!-- javaでデフォ住所表示 -->	
</div>
<div class=changeDelivery>
	<span>配送先を変える</span>
	<a href="住所変更.jsp">マイページから住所追加</a>
		<input type="checkbox" name="address" value="another1"><!-- javaでサブ住所１表示 -->	
		<input type="checkbox" name="address" value="another2"><!-- javaでサブ２住所表示 -->	
</div>
<div class=total>
		<span>ご注文金額(税込)</span>
		<label for="<!-- totalprice -->">合計</label>
		<!-- Javaで商品名を表示 -->
</div>
<button type="submit">購入確定</button>
<a href="一個前に戻る？.jsp">戻る</a>

</body>
</html>