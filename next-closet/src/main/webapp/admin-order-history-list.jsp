<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*, model.SizeText" %>
<%@ page import="java.util.*" %>
<%@ page import="model.StatusText" %>
<%@ page import="model.DaysCalculation" %>
<%@ page import="java.util.* , java.util.ArrayList, java.util.List" %>
<% boolean status = false; %>
<%-- <% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %>  --%>
<% List <OrderBean> orderList = ( ArrayList <OrderBean>)request.getAttribute("orderList"); %>
<% SizeText st = new SizeText(); %>
<% OrderDAO dao = new OrderDAO(); %>
<% DaysCalculation dc = new DaysCalculation(); %>


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

            
  		<div class="tab">
 				 <h1 class="page-title">購入履歴・通知</h1>	
  
			    <ul class="tab__menu">
				      <li class="tab__menu-item is-active" data-tab="01">当日の購入通知</li>
				      <li class="tab__menu-item" data-tab="02">前日の購入通知</li>
				      <li class="tab__menu-item" data-tab="03">購入履歴一覧</li>
			    </ul> 
			    
			    <div class="tab__panel">
                    <div class="tab__panel-box tab__panel-box001 is-show" data-panel="01"> <!-- 全表示 -->
		                
                     	<ul class="user-list">
                     								 <ul>			
	                     		   					   <li>注文番号</li>
	                     		   					   <li>商品名</li>
	                     		   					   <li></li>
	                     		   					 </ul>
                     		
			                    			 
			                    			 <% for( OrderBean list : orderList) { 
			                    				 if( dc.getTodayOrder(list.getOrderDate())){ %>
			                    					 <ul>
	                     		   					   <li><%= list.getOrderItemId() %></li>
	                     		   					   <li><%= dao.getProductName(list.getProductId()) %></li>
	                     		   					   <li><a href="AdminOrderHistoryDetailServlet?orderItemId=<%=list.getOrderItemId() %>">詳細を見る</a></li>
	                     		   					 </ul>
	                     		   				<% } %>
	                     		   			<% } %>
			                  
			                  
			                  </ul>
	                    
                     </div>
                     
                     
                     <div class="tab__panel-box tab__panel-box002 " data-panel="02"> <!-- 販売中 -->
                    	 
                     		<ul class="order-list">
                     		
		                      
			                    			<% for( OrderBean list : orderList) { 
			                    				 if( dc.getYesterdayOrder(list.getOrderDate())){ %>
			                    					 <ul>
	                     		   					   <li><%= list.getOrderItemId() %></li>
	                     		   					   <li><%= dao.getProductName(list.getProductId()) %></li>
	                     		   					   <li><a href="AdminOrderHistoryDetailServlet?orderItemId=<%=list.getOrderItemId()%>">詳細を見る</a></li>
	                     		   					 </ul>
	                     		   				<% } %>
	                     		   			<% } %>
			                  
                   		  </ul>
                    </div>
                     
                     
                     
                     <div class="tab__panel-box tab__panel-box003" data-panel="03"> <!-- 削除済み -->
                     	

	                     <ul class="order-list">
	                    
			                    			 <% for( OrderBean list : orderList) { %>
			                    					 <ul>
	                     		   					   <li><%= list.getOrderItemId() %></li>
	                     		   					   <li><%= dao.getProductName(list.getProductId()) %></li>
	                     		   					   <li><a href="AdminOrderHistoryDetailServlet?orderItemId=<%= list.getOrderItemId() %>">詳細を見る</a></li>
	                     		   					 </ul>
	                     		   			<% } %>
	                     
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