<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList,model.bean.*,java.util.List" %>
<%@ page import= "java.util.List" %>
<%@ page import="model.ProductBean" %>
<%@ page import="model.SizeBean" %>
<%@ page import="model.CategoryBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集画面</title>
<link rel="icon" href="image/favicon.png" id="favicon">
<link rel="stylesheet" href="css/product-edit.css">
<link rel="stylesheet" href="css/admin-navbar.css">
<%
List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList");
List<CategoryBean> categoryList = (ArrayList <CategoryBean>)request.getAttribute("categoryList");
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

<%@ include file="includes/admin-navbar.jsp" %>
<main>

    <%
        if(!productList.isEmpty()) {
        ProductBean firstProduct = productList.get(0);
        String imagePath = firstProduct.getImage(); // 修正: '=' を正しい文字にする
        if (imagePath == null || imagePath.isEmpty()) {
            imagePath = "image/noimg.jpg"; // デフォルト画像
        }else{
        	imagePath = "image/" + imagePath;
        }
     
    %>
    <div class="container">
    <h2>商品情報編集画面</h2>
					<div class="form_container">
					      <!-- enctype="multipart/form-data"を追加 -->
					      <form action="AdminProductEditServlet" method="post" enctype="multipart/form-data">
					          <input type="hidden" name="productId" value="<%= firstProduct.getProductId() %>">
					          <label>商品名</label> <input type="text" name="productName" value="<%= firstProduct.getProductName() %>">
					          <label>商品説明</label>  <textarea name="description"> <%= firstProduct.getDescription() %></textarea>
					          
					         		<label for="p_category">カテゴリー</label>
									<select id="p_category" name="category">
									<% for ( CategoryBean columns : categoryList){ %>
										<% int categoryListId = columns.getCategoryId(); %>
										<% int productCategoryId = firstProduct.getCategory().getCategoryId(); %>
										
										 <% if( categoryListId == productCategoryId ){ %>
										<option value="<%= categoryListId %>" selected><%= columns.getCategoryName() %></option>
										 <% } else { %>
										<option value="<%= categoryListId %>"><%= columns.getCategoryName() %></option>
										<% } %>
									<% } %>
									</select><br>
									
									<label for="p_gender">性別</label>
									<select id="p_gender" name="gender">
									<% int gender = firstProduct.getGender();%>
									<% if(gender == 1){ %>
									<option value="1" selected>MAN</option>
									<option value="2">WOMAN</option>
									<% } else if(gender == 2 ){ %>
									<option value="1">MAN</option>
									<option value="2" selected>WOMAN</option>
									<% } %>
									</select><br>
		
						          <label>金額</label>  <input type="number" name="price" value="<%= firstProduct.getPrice() %>"><br>
						          <label>画像</label>  <input class="file" type="file" name="image" id="imageUpload" onchange="previewImage()"> <br>
					              <img id="imagePreview" src="<%= imagePath %>" alt="Image preview"/><br>
					             
					         <% 
					            int count = 0;
					            for (ProductBean product : productList) { 
					            	if (count<3) {
					            		for (SizeBean size : product.getSizes()) { 
					         %>
					             <label>サイズ <%= size.getSizeName() %></label>
					                <input type="number" name="stockQuantity_<%= product.getProductId() %>_<%= size.getSizeName() %>" value="<%= size.getStockQuantity() %>">
					             
					         <%
					                 }
					                 count++;
					             } else {
					                break;
					             }
					           
					         %>
					          
					          <input id="btn" type="submit" value="更新">
					      </form>
					      <p class="back"><a href="AdminProductDetailServlet?productId=<%= product.getProductId()%>">詳細へ戻る</a></p>
		
			  			  <% }}%>
			</div><!-- form_container -->
	 </div>
   </main>
<%@ include file="includes/admin-footer.jsp" %>  
</body>
</html>
