<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.ProductBean" %>
<%@ page import="model.bean.SizeBean" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者側商品詳細画面</title>
</head>
<body>
    <%
      List<ProductBean> productList = (List<ProductBean>)request.getAttribute("productList");
      if (productList == null || productList.isEmpty()) {
          response.sendRedirect("AdminProductListServlet");
          return;
      }

      ProductBean firstProduct = productList.get(0);
    %>
        <p><img src="<%= firstProduct.getImage() %>" alt="Product Image"></p>
        <p>ID: <%= firstProduct.getProductId() %></p>
        <p>商品名: <%= firstProduct.getProductName() %></p>
        <p>説明: <%= firstProduct.getDescription() %></p>
        <p>価格: <%= firstProduct.getPrice() %>円</p>
        <p>登録日: <%= firstProduct.getRegistrationDate() %></p>

        <% for (SizeBean size : firstProduct.getSizes()) { %>
            <p>サイズ: <%= size.getSizeName() %>, 在庫数: <%= size.getStockQuantity() %></p>
        <% } %>

        <p><a href="AdminProductEditServlet?productId=<%= firstProduct.getProductId() %>">編集</a></p>
        <form action="ProductStatusChangeServlet" method="get">
            <input type="hidden" name="productId" value="<%= firstProduct.getProductId() %>" />
            <input type="submit" value="削除" />
        </form>
    
</body>
</html>
