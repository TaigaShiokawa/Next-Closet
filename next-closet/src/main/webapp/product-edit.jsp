<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.List" %>
<%@ page import="model.bean.ProductBean" %>
<%@ page import="model.bean.SizeBean" %>
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

        oFReader.onload = function (oFREvent) {
             document.getElementById("imagePreview").src = oFREvent.target.result;
        };
    }
</script>
</head>
<body>

    <%
        if(!productList.isEmpty()) {
        ProductBean firstProduct = productList.get(0);
        String imagePath = firstProduct.getImage(); // 修正: '=' を正しい文字にする
        if (imagePath == null || imagePath.isEmpty()) {
            imagePath = "https://placehold.jp/480x640.png"; // デフォルト画像
        }
    %>
      <!-- enctype="multipart/form-data"を追加 -->
      <form action="AdminProductEditServlet" method="post" enctype="multipart/form-data">
          <input type="hidden" name="productId" value="<%= firstProduct.getProductId() %>">
          <p>商品名 <input type="text" name="productName" value="<%= firstProduct.getProductName() %>"></p>
          <p>商品説明 <textarea name="description"> <%= firstProduct.getDescription() %></textarea></p>
          <p>金額 <input type="number" name="price" value="<%= firstProduct.getPrice() %>"></p>
          <p>画像
              <img id="imagePreview" src="<%= imagePath %>" alt="Image preview"/>
              <input type="file" name="image" id="imageUpload" onchange="previewImage();">
          </p>
          
         <% 
            int count = 0;
            for (ProductBean product : productList) { 
            	if (count<3) {
            		for (SizeBean size : product.getSizes()) { 
         %>
             <p>サイズ <%= size.getSizeName() %>
                <input type="number" name="stockQuantity_<%= product.getProductId() %>_<%= size.getSizeName() %>" value="<%= size.getStockQuantity() %>">
             </p>
         <%
                 }
                 count++;
             } else {
                break;
             }
           }
         %>
          
          <input type="submit" value="更新">
      </form>
    <% } %>
   
</body>
</html>
