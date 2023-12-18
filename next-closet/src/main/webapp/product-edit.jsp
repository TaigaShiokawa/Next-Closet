<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.List" %>
<%@ page import="model.bean.ProductBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集画面</title>
<%
  List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList");
%>
<script>
    function previewImage() {
        var oFReader = new FileReader();
        oFReader.readAsDataURL(document.getElementById("imageUpload").files[0]);

        oFReader.onload = functtoin (oFREvent) {
             document.getElementById("imagePreview").src = oFREvent.target.result;
        };
    }
</script>
</head>
<body>

    <%
      if(!productList.isEmpty()) {
    	  ProductBean firstProduct = productList.get(0);
    %>
      <form action="UpdateProductServlet" method="post" enctype="multipart/form-data">
          <input type="hidden" name="productId" value="<%= firstProduct.getProductId() %>">
          <p>商品名 <input type="text" name="productName" value="<%= firstProduct.getProductName() %>"></p>
          <p>商品説明 <textarea name="description"> <%= firstProduct.getDescription() %></textarea></p>
          <p>金額 <input type="number" name="price" value="<%= firstProduct.getPrice() %>"></p>
          <p>画像
              <img id="imagePreview" src="<%= firstProduct.getImage() %>" alt="Image preview"/>
              <input type="file" name="image" id="imageUpload" onchange="previewImage();">
          </p>
          
          <%
            for (ProductBean product : productList) {
          %>
          
             <p>サイズ <%= product.getSizeName() %>
                <input type="number" name="stockQuantity_<%= product.getSizeName() %>" value="<%= product.getStockQuantity() %>">
             </p>
          
          <% } %>
          
          <input type="submit" value="更新">
      </form>
    <% } %>
   
</body>
</html>