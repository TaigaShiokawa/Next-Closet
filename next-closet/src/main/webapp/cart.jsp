<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.bean.CartItemBean" %>
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


    <% 
        List<CartItemBean> cartItems = (ArrayList<CartItemBean>) request.getAttribute("cartItems"); 
        for (CartItemBean item : cartItems) {
    %>
    <div>
        <img src="<%= item.getProduct().getImage() %>" alt="Product Image">
        <div>
            <p>商品名: <%= item.getProduct().getProductName() %></p>
            <p>サイズ: <%= item.getSize().getSizeName() %></p>
            <p>価格: <%= item.getProduct().getPrice() %></p>
            <form action="CartUpdateServlet" method="post">
                <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">
                <input type="number" name="quantity" value="<%= item.getQuantity() %>">
                <input type="submit" value="更新">
            </form>     
        </div>
        <form action="CartDeleteServlet" method="post">
            <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">
            <input type="submit" value="削除">
        </form>
        <form action="OrderConfilmServlet" method="get">
            <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">
            <input type="submit" value="購入">
        </form>
    </div>
    <% } %>   
    <p>合計金額: <%-- ${cartItem.product.price * cartItem.quantity} --%></p>
   
</body>
</html>