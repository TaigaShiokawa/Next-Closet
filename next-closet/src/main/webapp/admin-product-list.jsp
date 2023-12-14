<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="java.util.*" %>
 <% List<ProductBean> productList = (ArrayList <ProductBean>)request.getAttribute("productList");
 List<CategoryBean> categoryList = (ArrayList <CategoryBean>)request.getAttribute("categoryList");
 List<ProductBean> searchProducts = (ArrayList<ProductBean>)request.getAttribute("searchProducts");
 String  title = (String)request.getAttribute("title");
 String img = ""; 
 boolean status = true;
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
<div class="content wrapper">
		<div class="side_bar">
                <ul id="category_list">
                    <li class="gender list_top"><span>ALL</span>
                        <ul class="category">
                            <li><a href='ProductListServlet'>全ての商品</a></li>
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
	                       	 <% for (ProductBean columns : productList) {%>
			            	 <li>
		                         <a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">
			                         
			                          <p><%= columns.getProductName() %></p>
			                          <p>&yen; <%= columns.getPrice() %></p>
		                         </a>
	                    	 </li>
                  			 <%  } %>
                    	 </ul>
                     </div>
                     
                     
                     <div class="tab__panel-box tab__panel-box002 " data-panel="02"> <!-- 販売中 -->
                     		<ul class="product-list">
                       	 	<% for (ProductBean columns : productList) {
                       			 status = columns.getStatus();
                       			 if(status){ %>
		            			 <li>
	                       			  <a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">
				                          <p><%= columns.getProductName() %></p>
				                          <p>&yen; <%= columns.getPrice() %></p>
			                         </a>
                    			 </li>
                  			 <% } } %>
                   		  </ul>
                    </div>
                     
                     
                     
                     <div class="tab__panel-box tab__panel-box003" data-panel="03"> <!-- 削除済み -->
	                     <ul class="product-list">
	                    	  <%  for (ProductBean columns : productList) {
	                 		     status = columns.getStatus();
	                       		   if( status == false ){
			                       %>
					            	 <li>
				                         <a href="AdminProductDetailServlet?productId=<%= columns.getProductId() %>">
					                          <p><%= columns.getProductName() %></p>
					                          <p>&yen; <%= columns.getPrice() %></p>
				                         </a>
			                    	 </li>
	                  		 <%}} %>
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