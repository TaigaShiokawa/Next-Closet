<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>

<h1>買い物かご</h1>

   <!-- cartitemの全て情報をorderとorder_itemsに送る -->
   <form action="purchaseAll" method="post">
        <input type="submit" value="カート内のものを全て購入">
    </form>


   <c:forEach var="cartItem" items="${cartItems}">
    <div>
        <img src="${cartItem.product.image}" alt="Product Image">
        <div>
            <p>商品名: ${cartItem.product.productName}</p>
            <p>サイズ: ${cartItem.size.sizeName}</p>
            <form action="updateCartQuantity" method="post">
                <input type="hidden" name="cartItemId" value="${cartItem.cartItemId}">
                <input type="number" name="quantity" value="${cartItem.quantity}">
                <input type="submit" value="更新">
            </form>
            <p>合計金額: ${cartItem.product.price * cartItem.quantity}</p>
        </div>
        <form action="deleteCartItem" method="post">
            <input type="hidden" name="cartItemId" value="${cartItem.cartItemId}">
            <input type="submit" value="削除">
        </form>
        <form action="purchaseItem" method="post">
            <input type="hidden" name="productId" value="${cartItem.product.productId}">
            <input type="submit" value="購入">
        </form>
    </div>
</c:forEach>
   
</body>
</html>