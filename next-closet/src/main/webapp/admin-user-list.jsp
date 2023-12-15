<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.StatusText" %>
 <%  StatusText st = new StatusText(); %>
 <% List<UserBean> userList = (ArrayList <UserBean>)request.getAttribute("userList");
 List <UserBean> searchUsers = (ArrayList<UserBean>)request.getAttribute("searchUsers");
 String  title = (String)request.getAttribute("title");
 
 boolean status = false;
 int targetUserId = -1;
 int sarchUserId = -1;

 %>
<!-- 管理者のセッション設定が必要 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログインページ</title>
<link rel="stylesheet" href="css/admin-user-list.css">
</head>

<body>
<div class="content wrapper">

						<div class="search">
				              <form action="AdminUserListServlet" method="get">
				                   <input type="text" name="searchName" class="search_box" placeholder="キーワードで商品名を検索">
				                   <input class="sarch_btn" type="submit" value="検索">
				               </form>
				        </div>
            
  		<div class="tab">
 				 <h1 class="page-title"><%= title %></h1>	
  
			    <ul class="tab__menu">
				      <li class="tab__menu-item is-active" data-tab="01">全ユーザー一覧表</li>
				      <li class="tab__menu-item" data-tab="02">ユーザー一覧表</li>
				      <li class="tab__menu-item" data-tab="03">削除済みユーザー一覧表</li>
			    </ul> 
			    
			    <div class="tab__panel">
                    <div class="tab__panel-box tab__panel-box001 is-show" data-panel="01"> <!-- 全表示 -->
                    	
				                
                     	<ul class="user-list">
                     	
                     								 <ul>			
	                     		   					   <li>ユーザーネーム</li>
	                     		   					   <li>メールアドレス</li>
	                     		   					   <li></li>
	                     		   					   <li></li>
	                     		   					 </ul>
                     		
                     		<% if (searchUsers != null ){
	                     		for (UserBean columns : userList) { 
	                     		   	for (UserBean sarch : searchUsers) {
	                     			   		targetUserId = columns.getUserId();
	                     			 		  sarchUserId = sarch.getUserId();
	                     		    		    if (targetUserId == sarchUserId){ %>
                     		     
	                     		   			
	                     		   					 <ul>
	                     		   					   <li><%= columns.getUserName() %></li>
	                     		   					   <li><%= columns.getEmail() %></li>
	                     		   					   <li><%= st.userStatusText(columns.isUserStatus()) %></li>ステータスメソッド作成
	                     		   					   <li><a href="AdminUserDetailServlet?userId=<%= columns.getUserId() %>">詳細を見る</a></li>
	                     		   					 </ul>
				                    			 </li>
			                    			 
			                    		  <% } } } %>
                     		 
    	 
                     			 <%  } else {	%>
  
				                         <% for (UserBean columns : userList){%>
					                    		    <ul>
	                     		   					   <li><%= columns.getUserName()  %></li>
	                     		   					   <li><%= columns.getEmail() %></li>
	                     		   					   <li><%= st.userStatusText(columns.isUserStatus()) %></li>ステータスメソッド作成
	                     		   					   <li><a href="AdminUserDetailServlet?userId=<%= columns.getUserId() %>">詳細を見る</a></li>
	                     		   					 </ul>
			                    			<% }%>
			                    
			                 	  <% }%>
			                  
			                  
			                  </ul>
	                    
                    	 <button><a href="AdminRegisterServlet">ユーザー新規登録</a></button>
                     </div>
                     
                     
                     <div class="tab__panel-box tab__panel-box002 " data-panel="02"> <!-- 販売中 -->
                    	 <div class="search">
				              <form action="AdminProductListServlet" method="get">
				                   <input type="text" name="searchName" class="search_box" placeholder="キーワードで商品名を検索">
				                   <input class="sarch_btn" type="submit" value="検索">
				               </form>
				        </div>
                     		<ul class="product-list">
                     		
		                      <% if (searchUsers != null ){
		                     		for (UserBean columns : userList) { 
		                     		   	for (UserBean sarch : searchUsers) {
		                     			   		targetUserId = columns.getUserId();
		                     			 		sarchUserId = sarch.getUserId();
		                     		    		 if (targetUserId == sarchUserId){ 
		                     		    		 		 status = sarch.isUserStatus();
		                     		    		 			 if(status){ %>
		                     		     
						                     		   				 <ul>
					                     		   					   <li><%= columns.getUserName()  %></li>
					                     		   					   <li><%= columns.getEmail() %></li>
					                     		   					   <li><%= st.userStatusText(columns.isUserStatus()) %></li>ステータスメソッド作成
					                     		   					   <li><a href="AdminUserDetailServlet?userId=<%= columns.getUserId() %>">詳細を見る</a></li>
					                     		   					 </ul>
					                    			 
					                    		  <%} } } } %>
		                     		 
		    	 
		                     	 <%  } else {//サーチ関係なし	%>
		  
						                        <% for (UserBean columns : userList) {
		                       						 status = columns.isUserStatus();
		                       						 if(status){ %>
							            			  <ul>
	                     		   					   <li><%= columns.getUserName() %></li>
	                     		   					   <li><%= columns.getEmail() %></li>
	                     		   					   <li><%= st.userStatusText(columns.isUserStatus()) %></li>
	                     		   					   <li><a href="AdminUserDetailServlet?userId=<%= columns.getUserId() %>">詳細を見る</a></li>
	                     		   					 </ul>
		                  					 <% } } %>
					                    
					                   <% }%>
                   		  </ul>
                    </div>
                     
                     
                     
                     <div class="tab__panel-box tab__panel-box003" data-panel="03"> <!-- 削除済み -->
                     	<div class="search">
				              <form action="AdminProductListServlet" method="get">
				                   <input type="text" name="searchName" class="search_box" placeholder="キーワードで商品名を検索">
				                   <input class="sarch_btn" type="submit" value="検索">
				               </form>
				        </div>
	                     <ul class="product-list">
	                     <% if (searchUsers != null ){
                     		for (UserBean columns : userList) { 
                     		   	for (UserBean sarch : searchUsers) {
                     			   		targetUserId = columns.getUserId();
                     			 		sarchUserId = sarch.getUserId();
                     		    		 if (targetUserId == sarchUserId){ 
                     		    		 		 status = columns.isUserStatus();
                     		    		 
                     		    		 			 if(status != true){ %>
                     		     
				                     		   				<ul>
			                     		   					   <li><%= columns.getUserName()  %></li>
			                     		   					   <li><%= columns.getEmail() %></li>
			                     		   					   <li><%= st.userStatusText(columns.isUserStatus()) %></li>ステータスメソッド作成
			                     		   					   <li><a href="AdminUserDetailServlet?userId=<%= columns.getUserId() %>">詳細を見る</a></li>
			                     		   					 </ul>
			                    			 
			                    		  <%} } } } %>
                     		 
    	 
                     	 <%  } else {//サーチ関係なし	%>
  
				                        <% for (UserBean columns : userList) {
                       								 status = columns.isUserStatus();
                       						
                       						 if(status != true){ %>
					            					 <ul>
	                     		   					   <li><%= columns.getUserName()  %></li>
	                     		   					   <li><%= columns.getEmail() %></li>
	                     		   					   <li><%= st.userStatusText(columns.isUserStatus()) %></li>ステータスメソッド作成
	                     		   					   <li><a href="AdminUserDetailServlet?userId=<%= columns.getUserId() %>">詳細を見る</a></li>
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