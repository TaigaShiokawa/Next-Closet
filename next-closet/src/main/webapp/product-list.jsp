<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>next closet ...</title>
    <link rel="stylesheet" href="css/product-list.css">
	<link rel = "stylesheet" href = "css/navbar.css">
    <%
    List <ProductBean> productList = (ArrayList <ProductBean>)request.getAttribute("productList");
    List <CategoryBean> categoryList = (ArrayList <CategoryBean>)request.getAttribute("categoryList");
    String  title = (String)request.getAttribute("title");
    %>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
    <main>
        <div class = "container" >
            <div class="side_bar">
                <ul id="category_list">
                    <li class="gender list_top"><span>ALL</span>
                        <ul class="category">
                            <li><a href='ProductListServlet'>全ての商品</a></li>
                            <% for ( CategoryBean columns : categoryList){ %>
                             <li><a href='ProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=-1&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
                             <% } %>
                            
 
                        </ul>
                    </li>
                    <li class="gender"><span>MAN</span>
                        <ul class="category">
                             <% for ( CategoryBean columns : categoryList){ %>
                            <li><a href='ProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=0&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
                             <% } %>
                        </ul>
                    </li>
                    <li class="gender"><span>WOMAN</span>
                        <ul class="category">
 							<% for ( CategoryBean columns : categoryList){ %>
                            <li><a href='ProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=1&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
                            <% } %>
                        </ul>
                    </li>
                </ul>
            </div>
                <div class="content wrapper">
                    <h1 class="page-title"><%= title %></h1>
                     <ul class="product-list">
                    <% if ( productList != null ){
	                     for ( ProductBean columns : productList){ 
	                    	 	 String img =  columns.getImage();
	                    	  	 if( img == null ){
	                    		 	 img = "https://placehold.jp/480x640.png";
	                    	  	 }
                    %>  
                   		  <li>
	                         <a href="ProductDetailServlet?productId=<%= columns.getProductId() %>">
		                          <img src="<%= img %>">
		                          <p><%= columns.getProductName() %></p>
		                          <p>&yen; <%= columns.getPrice() %></p>
	                         </a>
                    	  </li>
		                 <% } 
		               } else { %>  
                  			  <p>当該商品がありません</p>
                  	 <% } %>
                    
                      <!-- ダミーの商品一覧
                      <li>
                      	<a href="#">
                        	<img src="../img/2.jpg" alt="">
                         	<p>プロダクトタイトルプロダクトタイトル</p>
                          	<p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                      <l
                      -->
                  </ul>
            </div>
        </div>
    </main>

</body>
</html>