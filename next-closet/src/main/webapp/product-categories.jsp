<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List" %>
<%@ page import="junit.model.dao.*" %>

<!DOCTYPE html>
<%  List<CategoryBean> categoryList = (ArrayList <CategoryBean>)request.getAttribute("categoryList"); %>
<% 
String message = (String)request.getAttribute("message"); 
if(message == null){
	message = "";
}

%>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="image/favicon.png" id="favicon">
<title>カテゴリー編集</title>
<link rel="stylesheet" href="css/categories.css">
<link rel="stylesheet" href="css/admin-navbar.css">
</head>
	<body>
	<%@ include file="includes/admin-navbar.jsp" %>
	<main>
	<div class="container">
		<h2 class="section_title">カテゴリー追加 / 削除</h2>
		<p class="list_title">カテゴリー一覧</p>
		<p><%= message %></p>
		    <ul class="category_list">
		    	<% for ( CategoryBean columns : categoryList){ %>
	    				<ul>
		    				<li class="category_name"><%= columns.getCategoryName() %></li>
					    	<li>
					    	  <form action="ProductCategoriesServlet" method="post">
					    	  	<input type="hidden" name="delete" value='<%= columns.getCategoryId() %>'>
					    	  	<input class="spinner" type="submit" value="−">
					    	  </form>
					    	</li>
	    				</ul>
		      　<% } %>
		    </ul>
			<form action="ProductCategoriesServlet" method="post">
				<input class="add" type="text" name="AddCategoryName"> 
				<input type="hidden" name="add" value="add">
				<input class="add_btn" type="submit" value="カテゴリー追加">
			</form>
			
			<p class="back"><a href="AdminProductListServlet">商品一覧に戻る</a></p>
		</div>
		</main>
		<%@ include file="includes/footer.jsp" %>
	</body>
</html>