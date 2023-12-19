<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.StatusText" %>
 <%  StatusText st = new StatusText(); %>
 <% List<AdminBean> adminList = (ArrayList <AdminBean>)request.getAttribute("adminList");
 List <AdminBean> searchAdmins = (ArrayList<AdminBean>)request.getAttribute("searchAdmins");
 String  title = (String)request.getAttribute("title");
 
 boolean status = false;
 int targetAdminId = -1;
 int sarchAdminId = -1;

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
<%@ include file="includes/admin-navbar.jsp" %>
<div class="content wrapper">

						<div class="search">
				               <form action="AdminListServlet" method="get">
				                   <input type="text" name="searchName" class="search_box" placeholder="キーワードで商品名を検索">
				                   <input class="sarch_btn" type="submit" value="検索">
				               </form>
				               
				               <button><a href="AdminListServlet">一覧表示</a></button>
				        </div>
            
			  		<div class="tab">
			 				 <h1 class="page-title"><%= title %></h1>	
						    <ul class="tab__menu">
							      <li class="tab__menu-item is-active" data-tab="01">全管理者一覧表</li>
							      <li class="tab__menu-item" data-tab="02">管理者一覧表</li>
							      <li class="tab__menu-item" data-tab="03">削除済み管理者一覧表</li>
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
			                     		
			                     		<% if (searchAdmins != null ){
				                     		for (AdminBean columns : adminList) { 
				                     		   	for (AdminBean sarch : searchAdmins) {
				                     			   		targetAdminId = columns.getAdminId();
				                     			 		  sarchAdminId = sarch.getAdminId();
				                     		    		    if (targetAdminId == sarchAdminId){ %>
			                     		     
				                     		   			
				                     		   					 <ul>
				                     		   					   <li><%= columns.getAdminName() %></li>
				                     		   					   <li><%= columns.getEmail() %></li>
				                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
				                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
				                     		   					 </ul>
							                    			 
						                    			 
						                    		  <% } } } %>
			                     		 
			    	 
			                     			 <%  } else {	%>
			  
							                         <% for (AdminBean columns : adminList){%>
								                    		    <ul>
				                     		   					   <li><%= columns.getAdminName() %></li>
				                     		   					   <li><%= columns.getEmail() %></li>
				                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
				                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
				                     		   					 </ul>
						                    			<% }%>
						                    
						                 	  <% }%>
						                  
						                  
						                  </ul>
				                    
			                    	 <button><a href="AdminRegisterServlet">管理者新規登録</a></button>
			                     </div>
			                     
			                     
			                     <div class="tab__panel-box tab__panel-box002 " data-panel="02"> <!-- 販売中 -->
			                    
			                     		<ul class="admin-list">
			                     		
					                      <% if (searchAdmins != null ){
					                     		for (AdminBean columns : adminList) { 
					                     		   	for (AdminBean sarch : searchAdmins) {
					                     			   		targetAdminId = columns.getAdminId();
					                     			 		sarchAdminId = sarch.getAdminId();
					                     		    		 if (targetAdminId == sarchAdminId){ 
					                     		    		 		 status = sarch.isAdminStatus();
					                     		    		 			 if(status){ %>
					                     		     
									                     		   				  <ul>
								                     		   					   <li><%= columns.getAdminName() %></li>
								                     		   					   <li><%= columns.getEmail() %></li>
								                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
								                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
								                     		   					 </ul>
												                    			 
								                    		  <%} } } } %>
					                     		 
					    	 
					                     	 <%  } else {//サーチ関係なし	%>
					  
									                        <% for (AdminBean columns : adminList) {
					                       						 status = columns.isAdminStatus();
					                       						 if(status){ %>
										            			   <ul>
				                     		   					   <li><%= columns.getAdminName() %></li>
				                     		   					   <li><%= columns.getEmail() %></li>
				                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>ステータスメソッド作成
				                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
				                     		   					 </ul>
					                  					 <% } } %>
								                    
								                   <% }%>
			                   		  </ul>
			                    </div>
			                     
			                     
			                     
			                     <div class="tab__panel-box tab__panel-box003" data-panel="03"> <!-- 削除済み -->
			                     	
				                     <ul class="admin-list">
				                     <% if (searchAdmins != null ){
			                     		for (AdminBean columns : adminList) { 
			                     		   	for (AdminBean sarch : searchAdmins) {
			                     			   		targetAdminId = columns.getAdminId();
			                     			 		sarchAdminId = sarch.getAdminId();
			                     		    		 if (targetAdminId == sarchAdminId){ 
			                     		    		 		 status = columns.isAdminStatus();
			                     		    		 
			                     		    		 			 if(status != true){ %>
			                     		     
							                     		   				 <ul>
						                     		   					   <li><%= columns.getAdminName() %></li>
						                     		   					   <li><%= columns.getEmail() %></li>
						                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
						                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
						                     		   					 </ul>
								                    			 
						                    		  <%} } } } %>
			                     		 
			    	 
			                     	 <%  } else {//サーチ関係なし	%>
			  
							                        <% for (AdminBean columns : adminList) {
			                       								 status = columns.isAdminStatus();
			                       						
			                       						 if(status != true){ %>
								            					  <ul>
				                     		   					   <li><%= columns.getAdminName() %></li>
				                     		   					   <li><%= columns.getEmail() %></li>
				                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
				                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
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