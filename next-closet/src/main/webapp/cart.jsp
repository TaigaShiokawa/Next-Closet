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


    <% /* for文でカートの商品一覧を出す */ %>
    
        <!-- 変数名やアクション名は全て仮 -->
        <div>
            <img src="${cartItem.productImage}" alt="Product Image">
            <div>
                <p>商品名: ${cartItem.productName}</p>
                <p>サイズ: ${cartItem.size}</p>
                
                <form action="updateCartQuantity" method="post">
                    <input type="hidden" name="cartItemId" value="${cartItem.cartItemId}">
                    <input type="number" name="quantity" value="${cartItem.quantity}">
                    <input type="submit" value="更新">
                </form>
                <p>合計金額: ${cartItem.price * cartItem.quantity}</p>
            </div>
            
            <!-- 商品削除 -->
            <form action="deleteCartItem" method="post">
                <input type="hidden" name="cartItemId" value="${cartItem.cartItemId}">
                <input type="submit" value="削除">
            </form>
            
            <!-- cartitemの個別の情報をorderとorder_itemsに送る -->
            <form action="purchaseItem" method="post">
                <input type="hidden" name="productId" value="${cartItem.productId}">
                <input type="submit" value="購入">
            </form>
        </div>
    <% /* for文のおわり */ %>
   
</body>
</html>