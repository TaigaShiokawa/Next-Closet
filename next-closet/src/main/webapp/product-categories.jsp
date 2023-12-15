<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList, model.bean.* , java.util.List" %>
<%@ page import="model.dao.*" %>

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
<title>管理者カテゴリー</title>
</head>
	<body>
	<p><%= message %></p>
	    <ul class="categoryList">
	    	<% for ( CategoryBean columns : categoryList){ %>
    				<ul>
	    				<li><%= columns.getCategoryName() %></li>
				    	<li>
				    	  <form action="ProductCategoriesServlet">
				    	  	<input type="hidden" name="delete" value="<%= columns.getCategoryName() %>">
				    	  	<input type="submit" value="削除">
				    	  </form>
				    	</li>
    				</ul>
	      　<% } %>
	    </ul>
		<form action="ProductCategoryServlet" method="post">
			<input type="text" name="AddCategoryName"> 
			<input type="hidden" name="add" value="add">
			<input type="submit" value="新規登録">
		</form>
		
		<button><a href="AdminProductListServlet">商品一覧に戻る</a></button>
	</body>
</html>