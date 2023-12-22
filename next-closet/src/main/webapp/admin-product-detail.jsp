<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.ProductBean" %>
<%@ page import="model.bean.SizeBean" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者側商品詳細画面</title>
    <link rel="stylesheet" href="css/admin-product-detail.css">
    <link rel="stylesheet" href="css/admin-navbar.css">
    <%
      List<ProductBean> productList = (List<ProductBean>)request.getAttribute("productList");
      if (productList == null || productList.isEmpty()) {
          response.sendRedirect("AdminProductListServlet");
          return;
      }
      
      ProductBean firstProduct = productList.get(0);
      String img = firstProduct.getImage();
      if (img == null || img.isEmpty()) {
          img = "https://placehold.jp/480x640.png";
      } else {
          img = "image/" + img;
      }
    %>
</head>
<body>
		<%@ include file="includes/admin-navbar.jsp" %>
		<main>
			<div class="wrapper">
							<div class="product_contents">
								<div class="img">
									<p><img src="<%= img %>" alt="Product Image"></p>
								</div> <!-- img閉じタグ -->
								<div id="product-detail">
										<p><span class="list">商品ID:</span> <%= firstProduct.getProductId() %></p>
								        <p><span class="list">商品名:</span> <%= firstProduct.getProductName() %></p>
								        <p><span class="list">説明:</span> <%= firstProduct.getDescription() %></p>
								        <p><span class="list">価格: </span><%= firstProduct.getPrice() %>円</p>
								        <p><span class="list">登録日:</span> <%= firstProduct.getRegistrationDate() %></p>
								
								        <% for (SizeBean size : firstProduct.getSizes()) { %>
								            <p><span class="list">サイズ:</span><%= size.getSizeName() %>, 在庫数: <%= size.getStockQuantity() %></p>
								        <% } %>
								
								        <div class="btn_wrapper">
									        <button class="btn"><a href="AdminProductEditServlet?productId=<%= firstProduct.getProductId() %>">編集</a></button>
									        <% if (firstProduct.isStatus()) { %>
									            <form action="ProductStatusChangeServlet" method="get">
									                <input type="hidden" name="productId" value="<%= firstProduct.getProductId() %>">
									                <input class="btn" type="submit" value="削除">
									            </form>
									        <% } else { %>
									            <form action="ProductStatusChangeServlet" method="get">
									                <input type="hidden" name="productId" value="<%= firstProduct.getProductId() %>">
									                <input class="btn" type="submit" value="復元">
									            </form>
									        <% } %>
								        </div>
								</div>
							</div>
			</div>

        </main>
        <%@ include file="includes/footer.jsp" %>
    
</body>
</html>
