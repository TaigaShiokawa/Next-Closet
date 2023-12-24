<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List" %>
<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>next closet ...</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Tangerine&display=swap" rel="stylesheet">	
    <link rel="stylesheet" href="css/product-list.css">
	<link rel = "stylesheet" href = "css/navbar.css">
    <%
	    List<ProductBean> productList = (ArrayList <ProductBean>)request.getAttribute("productList");
	    List<CategoryBean> categoryList = (ArrayList <CategoryBean>)request.getAttribute("categoryList");
	    List<ProductBean> searchProducts = (ArrayList<ProductBean>)request.getAttribute("searchProducts");
	    String  title = (String)request.getAttribute("title");
    %>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<% String productNotFound = (String)request.getSession().getAttribute("productNotFound"); %>
<% if(productNotFound != null) { %>
<p><%=productNotFound %></p>
<% session.removeAttribute("productNotFound"); %>
<% } %>
    <main> 
        <div class = "container product_list_main" >
            <div class="side_bar">
                <ul id="category_list">
                	<div class="accordion">
							<div class="option">
								<input type="checkbox" id="toggle1" class="toggle">
								<label class="category_title" for="toggle1">ALL</label>
								<div class="category_content">
			                        <ul class="category">
			                            <li><a href='ProductListServlet'>全ての商品</a></li>
			                            <% for ( CategoryBean columns : categoryList){ %>
			                             <li><a href='ProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=-1&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
			                             <% } %>
			                        </ul>
								</div>
							</div>
						<div class="option">
							<input type="checkbox" id="toggle2" class="toggle">
							<label class="category_title" for="toggle2">MEN</label>
							<div class="category_content">
	                      		<ul class="category">
	                            		<% for ( CategoryBean columns : categoryList){ %>
	                           				<li><a href='ProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=0&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
	                           			<% } %>
	                       		</ul>      			 
							</div>
						</div>
						<div class="option">
							<input type="checkbox" id="toggle3" class="toggle">
							<label class="category_title" for="toggle3">WOMEN</label>
							<div class="category_content">
	                      		<ul class="category">
								<% for ( CategoryBean columns : categoryList){ %>
	                         		<li><a href='ProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=1&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
	                         	<% } %>
	                     		</ul>
							</div>
						</div>
					</div>	
                </ul><!-- category_listここまで -->
            </div> <!-- sidebarここまで -->
                <div class="wrapper">
                	<h2 id="section_title">Product List</h2>
                    <h3 class="page-title"><%= title %></h3>
                     <ul class="product-list">
                          <% 
                          if (searchProducts != null && !searchProducts.isEmpty()) {
                          // 検索結果の表示
                          for (ProductBean columns : searchProducts) {
                               String img = columns.getImage();
                               if ( img == null ){
                            	   img = "https://placehold.jp/480x640.png";
                               } else {
                            	   img = "image/" + img;
                               }
                          %>  
                          <% String formattedItemPrice = String.format("%,d", columns.getPrice()); %>
                   		  <li>
	                         <a href="ProductDetailServlet?productId=<%= columns.getProductId() %>">
		                          <img src="<%= img %>">
		                          <p><%= columns.getProductName() %></p>
		                          <p>&yen; <%= formattedItemPrice %>　税込</p>
	                         </a>
                    	  </li>
		                 <% 
		                     } 
		                 } else if (productList != null && !productList.isEmpty()) {
		            	     //通常の商品リスト表示
		            	     for (ProductBean columns : productList) {
		                          String img = columns.getImage();
		                          if ( img == null ){
	                            	   img = "https://placehold.jp/480x640.png";
	                               } else {
	                            	   img = "image/" + img;
	                               }
		            	 %>
		            	 <li>
	                         <a href="ProductDetailServlet?productId=<%= columns.getProductId() %>">
	                         <% String formattedItemPrice = String.format("%,d", columns.getPrice()); %>
		                          <img src="<%= img %>">
		                          <p><%= columns.getProductName() %></p>
		                          <p>&yen; <%= formattedItemPrice %> 税込</p>
	                         </a>
                    	 </li>
                  		 <%  
		            	     }
		                 } else {
                  		 %>
                  		 <p>該当する商品がありません</p>
                  		 <% } %>
                  </ul>
            </div>
        </div>
    </main>
    
    <ul class="page_list">
    　<li><a href="ProductListServlet">1</a></li>
    　<li> <a href="PagenationServlet?value=2">2</a></li>
    　<li><a href="PagenationServlet?value=3">3</a></li>
    </ul>
    
   
    
<%@ include file="includes/footer.jsp" %>
<script src="js/form.js"></script>
</body>
</html>