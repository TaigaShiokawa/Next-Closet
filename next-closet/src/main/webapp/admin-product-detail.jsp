<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.ProductBean" %>

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
      for (ProductBean product : productList) {
    %>
        <p><img src="<%= product.getImage() %>" alt="Product Image"></p>
        <p>ID: <%= product.getProductId() %></p>
        <p>商品名: <%= product.getProductName() %></p>
        <p>説明: <%= product.getDescruption() %></p>
        <p>価格: <%= product.getPrice() %>円</p>
        <p>在庫数: <%= product.getStockQuantity() %></p>
        <p>登録日: <%= product.getRegistrationDate() %></p>
    <%
      }
    %>
</body>
</html>
