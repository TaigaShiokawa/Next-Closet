<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductBean" %>
<%@ page import="model.SizeBean" %>
<%@ page import="model.StatusText" %>
<% StatusText text = new StatusText(); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者商品詳細画面</title>
     <link rel="icon" href="image/favicon.png" id="favicon">
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
      if (img.isEmpty()){
   	   img = "image/noimg.jpg";
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
										<p><span class="list">商品ID</span> <%= firstProduct.getProductId() %></p>
								        <p><span class="list">商品名</span> <%= firstProduct.getProductName() %></p>
								        <p><span class="list">性別カテゴリー</span> <%= text.genderText(firstProduct.getGender()) %></p>
								        <p><span class="list">商品カテゴリー</span> <%= firstProduct.getCategory().getCategoryName() %></p>
								        <p><span class="list">説明</span> <%= firstProduct.getDescription() %></p>
								        <p><span class="list">価格 </span>&yen;<%= String.format("%,d", firstProduct.getPrice())%> 税込</p>
								        <p><span class="list">登録日</span> <%= firstProduct.getRegistrationDate() %></p>
								
								        <% for (SizeBean size : firstProduct.getSizes()) { %>
								            <p><span class="list"></span><%= size.getSizeName() %>サイズ : 在庫数『<%= size.getStockQuantity() %>』</p>
								        <% } %>
								
								        <div class="btn_wrapper">
									        <button class="btn"><a href="AdminProductEditServlet?productId=<%= firstProduct.getProductId() %>">編集</a></button>
									        <% if (firstProduct.isStatus()) { %>
									                   <label class="btn_input" id="modalOpen">削除</label> 
									           
									        <% } else { %>
									            <form action="ProductStatusChangeServlet" method="get">
									                <input type="hidden" name="productId" value="<%= firstProduct.getProductId() %>">
									                <input class="btn_input" type="submit" value="復元">
									            </form>
									        <% } %>
								        </div>
								        
								        <p class="back"><a href="AdminProductListServlet">商品一覧へ戻る</a></p>
								</div>
							</div>
			</div>
			
			 <div id="easyModal" class="modal">
						    <div class="modal-content">
						      <div class="modal-body">
						        <p><strong>本当に削除しますか？</strong></p>
						        <div class="delete_btn_wrapper">
						        	<label class="modalClose">キャンセル</label>
							        <form action="ProductStatusChangeServlet" method="get">
						                <input type="hidden" name="productId" value="<%= firstProduct.getProductId() %>">
						                <input class="delete_btn" type="submit" value="削除">
									  </form>
							    </div>
						    </div>
						  </diV>
						   </diV>

        </main>
       <%@ include file="includes/admin-footer.jsp" %> 
        
<script>
		const buttonOpen = document.getElementById('modalOpen');
		const modal = document.getElementById('easyModal');
		const buttonClose = document.getElementsByClassName('modalClose')[0];
	
		// ボタンがクリックされた時
		buttonOpen.addEventListener('click', modalOpen);
		function modalOpen() {
		  modal.style.display = 'block';
		}
	
		// バツ印がクリックされた時
		buttonClose.addEventListener('click', modalClose);
		function modalClose() {
		  modal.style.display = 'none';
		}
	
		// モーダルコンテンツ以外がクリックされた時
		addEventListener('click', outsideClose);
		function outsideClose(e) {
		  if (e.target == modal) {
		    modal.style.display = 'none';
		  }
		}
	</script>
    
</body>
</html>
