<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.ProductionBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者側商品詳細画面</title>
<%
  List<ProductBean> productList = (ArrayList<ProductBean>)request.getAttribute("productList");
  if (productList == null) {
	  response.sendRedirect("AdminProductListServlet");
	  return;
  }
%>
</head>
<body>
    <% 
       for (ProductBean product : productList) {
    %>
    <p><img src="<%= product.getImage() %>"</p>
    <p><%= product.getProductId() %></p>
    <p><%= product.getProductName() %></p>
    <p><%= product.getDescruption() %></p>
    <p><%= product.getPrice() %></p>
    <p><%= product.getStockQuantity() %></p>
    <p><%= product.getRegistrationDate() %></p>
    
    <% } %>

</body>
</html>