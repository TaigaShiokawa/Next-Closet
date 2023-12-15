<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.StatusText" %>
 <% List<ProductBean> productList = (ArrayList <ProductBean>)request.getAttribute("productList");
 List<CategoryBean> categoryList = (ArrayList <CategoryBean>)request.getAttribute("categoryList");
 List<ProductBean> searchProducts = (ArrayList<ProductBean>)request.getAttribute("searchProducts");
 String  title = (String)request.getAttribute("title");
 String img = ""; 
 StatusText st = new StatusText();
/*  String genderStr = (String)request.getAttribute("gender");
 int gender = Integer.parseInt(genderStr);
 String categoryIdStr = (String)request.getAttribute("categoryId");
 int categoryId = Integer.parseInt(categoryIdStr); */
 boolean status = false;
 int targetProductId = -1;
 int sarchProductId = -1;
 /* 
 int targetGender = -1;
 int targetCategoryId = -1; */
 
 %>
<!-- 管理者のセッション設定が必要 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログインページ</title>
<link rel="stylesheet" href="css/admin-product-list.css">
</head>

<body>
<%@ include file="includes/admin-navbar.jsp" %>
<div class="content wrapper">
		<div class="side_bar">
                <ul id="category_list">
                    <li class="gender list_top"><span>ALL</span>
                        <ul class="category">
                            <li><a href='AdminProductListServlet'>全ての商品</a></li>
                            <% for ( CategoryBean columns : categoryList){ %>
                             <li><a href='AdminProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=-1&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
                             <% } %>
                        </ul>
                    </li>
                    
                    <li class="gender"><span>MAN</span>
                        <ul class="category">
                             <% for ( CategoryBean columns : categoryList){ %>
                            <li><a href='AdminProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=0&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
                             <% } %>
                        </ul>
                    </li>
                    
                    <li class="gender"><span>WOMAN</span>
                        <ul class="category">
 							<% for ( CategoryBean columns : categoryList){ %>
                            <li><a href='AdminProductListServlet?categoryId=<%= columns.getCategoryId() %>&gender=1&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
                            <% } %>
                        </ul>
                    </li>
                    
                </ul>
          </div><!--  side_barの閉じタグ -->
          
          <div class="search">
				              <form action="AdminProductListServlet" method="get">
				                   <input type="text" name="searchName" class="search_box" placeholder="キーワードで商品名を検索">
				                   <input class="sarch_btn" type="submit" value="検索">
				               </form>
				        </div>
            
  		<div class="tab">
 				 <h1 class="page-title"><%= title %></h1>	
  
			    <ul class="tab__menu">
				      <li class="tab__menu-item is-active" data-tab="01">商品一覧表</li>
				      <li class="tab__menu-item" data-tab="02">販売中</li>
				      <li class="tab__menu-item" data-tab="03">削除済み</li>
			    </ul> 
			    
			    <div class="tab__panel">
                    <div class="tab__panel-box tab__panel-box001 is-show" data-panel="01"> <!-- 全表示 -->
                    	
				                
                     	<ul class="product-list">
                     	 
                     	 <% if (searchProducts != null ){
                     		
                     		   	for (ProductBean columns : productList) { 
                     		   		for (ProductBean sarch : searchProducts) {
	                     			   		targetProductId = columns.getProductId();
	                     			 		sarchProductId = sarch.getProductId();
                     		    		 	if (targetProductId == sarchProductId){ %>
                     		     
	                     		   				<li>
	                     		   					 <ul>
	                     		   					   <li>商品番号:<%= columns.getProductId() %></li>
	                     		   					   <li>商品名:<%= columns.getProductName()  %></li>
	                     		   					   <li><%= st.productStatusText(columns.isStatus()) %></li>
	                     		   					   <li><a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">詳細を見る</a></li>
	                     		   					 </ul>
					                       			
								                         <p><%= columns.getProductName() %></p>
								                          <p>&yen; <%= columns.getPrice() %></p>
							                         </a>
				                    			 </li>
			                    			 
			                    		  <% } } } %>
                     		 
    	 
                     			 <%  } else {	%>
  
				                         <%   for (ProductBean columns : productList){%>
					                    		     <ul>
	                     		   					   <li>商品番号:<%= columns.getProductId() %></li>
	                     		   					   <li>商品名:<%= columns.getProductName()  %></li>
	                     		   					   <li><%= st.productStatusText(columns.isStatus()) %></li>
	                     		   					   <li><a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">詳細を見る</a></li>
	                     		   					 </ul>
			                    			<% }%>
			                    
			                 	  <% }%>
			                  
			                  
			                  </ul>
	  
                    	 </ul>
                    	 <button><a href="ProductAddServlet">商品追加</a></button>
                    	 <button><a href="ProductCategoriesSevlet">カテゴリ追加・削除</a></button>
                     </div>
                     
                     
                     <div class="tab__panel-box tab__panel-box002 " data-panel="02"> <!-- 販売中 -->
                    	
                     		<ul class="product-list">
                     		
		                      <% if (searchProducts != null ){
		                     		for (ProductBean columns : productList) { 
		                     		   	for (ProductBean sarch : searchProducts) {
		                     			   		targetProductId = columns.getProductId();
		                     			 		sarchProductId = sarch.getProductId();
		                     		    		 if (targetProductId == sarchProductId){ 
		                     		    		 		 status = sarch.isStatus();
		                     		    		 			 if(status){ %>
		                     		     
						                     		   				 <ul>
					                     		   					   <li>商品番号:<%= columns.getProductId() %></li>
					                     		   					   <li>商品名:<%= columns.getProductName()  %></li>
					                     		   					   <li><%= st.productStatusText(columns.isStatus()) %></li>
					                     		   					   <li><a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">詳細を見る</a></li>
					                     		   					 </ul>
					                    			 
					                    		  <%} } } } %>
		                     		 
		    	 
		                     	 <%  } else {//サーチ関係なし	%>
		  
						                        <% for (ProductBean columns : productList) {
		                       						 status = columns.isStatus();
		                       						 if(status){ %>
							            			  <ul>
	                     		   					   <li>商品番号:<%= columns.getProductId() %></li>
	                     		   					   <li>商品名:<%= columns.getProductName()  %></li>
	                     		   					   <li><%= st.productStatusText(columns.isStatus()) %></li>
	                     		   					   <li><a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">詳細を見る</a></li>
	                     		   					 </ul>
		                  					 <% } } %>
					                    
					                   <% }%>
                   		  </ul>
                    </div>
                     
                     
                     
                     <div class="tab__panel-box tab__panel-box003" data-panel="03"> <!-- 削除済み -->
                     	
	                     <ul class="product-list">
	                     <% if (searchProducts != null ){
                     		for (ProductBean columns : productList) { 
                     		   	for (ProductBean sarch : searchProducts) {
                     			   		targetProductId = columns.getProductId();
                     			 		sarchProductId = sarch.getProductId();
                     		    		 if (targetProductId == sarchProductId){ 
                     		    		 		 status = columns.isStatus();
                     		    		 
                     		    		 			 if(status != true){ %>
                     		     
				                     		   				 <ul>
			                     		   					   <li>商品番号:<%= columns.getProductId() %></li>
			                     		   					   <li>商品名:<%= columns.getProductName()  %></li>
			                     		   					   <li><%= st.productStatusText(columns.isStatus()) %></li>
			                     		   					   <li><a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">詳細を見る</a></li>
			                     		   					 </ul>
			                    			 
			                    		  <%} } } } %>
                     		 
    	 
                     	 <%  } else {//サーチ関係なし	%>
  
				                        <% for (ProductBean columns : productList) {
                       						 status = columns.isStatus();
                       						
                       						 if(status != true){ %>
					            					 <ul>
	                     		   					   <li>商品番号:<%= columns.getProductId() %></li>
	                     		   					   <li>商品名:<%= columns.getProductName()  %></li>
	                     		   					   <li><%= st.productStatusText(columns.isStatus()) %></li>
	                     		   					   <li><a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">詳細を見る</a></li>
	                     		   					 </ul>
                  					 <% } } %>
			                    
			                   <% }%>
	                     
	                     </ul>
	                  </div>
	                  
	              </div><!-- tab閉じタグ -->
			</div>
                    
	<body>
  <script>

  'use strict'; /* 厳格にエラーをチェック */

  { /* ローカルスコープ */

  // DOM取得
  const tabMenus = document.querySelectorAll('.tab__menu-item');
  console.log(tabMenus);

  // イベント付加
  tabMenus.forEach((tabMenu) => {
    tabMenu.addEventListener('click', tabSwitch);
  })

  // イベントの処理
  function tabSwitch(e) {

    // クリックされた要素のデータ属性を取得
    const tabTargetData = e.currentTarget.dataset.tab;
    // クリックされた要素の親要素と、その子要素を取得
    const tabList = e.currentTarget.closest('.tab__menu');
    console.log(tabList);
    const tabItems = tabList.querySelectorAll('.tab__menu-item');
    console.log(tabItems);
    // クリックされた要素の親要素の兄弟要素の子要素を取得
    const tabPanelItems = tabList.
    nextElementSibling.querySelectorAll('.tab__panel-box');
    console.log(tabPanelItems);

    // クリックされたtabの同階層のmenuとpanelのクラスを削除
    tabItems.forEach((tabItem) => {
      tabItem.classList.remove('is-active');
    })
    tabPanelItems.forEach((tabPanelItem) => {
      tabPanelItem.classList.remove('is-show');
    })

    // クリックされたmenu要素にis-activeクラスを付加
    e.currentTarget.classList.add('is-active');
    // クリックしたmenuのデータ属性と等しい値を持つパネルにis-showクラスを付加
    tabPanelItems.forEach((tabPanelItem) => {
      if (tabPanelItem.dataset.panel ===  tabTargetData) {
        tabPanelItem.classList.add('is-show');
      }
    })

  }

  }

  </script>

</body>

</body>
</html>