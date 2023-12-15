<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.* , model.SizeText" %>
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
<% OrderDAO orderDao = new OrderDAO(); %>

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

        
			                    			 
                  			 <% for( OrderBean list : orderList) {  %>
                  				 
                  					<ul>
                  						<li>
                  							  <p>注文番号</p>
                  							  <p><%= list.getOrderItemId() %></p>
                  						</li>
                  						<li>
                  							  <p>商品名</p>
                  							  <p><%= orderDao.getProductName(list.getProductId()) %></p>
                  						</li>                  						
                  						<li>
                  							  <p>数量</p>
                  							  <p><%= list.getQuanty() %></p>
                  						</li>
                  						<li>
                  							  <p>サイズ</p>
                  							  <p><%= st.sizeText(list.getSizeId()) %></p>
                  						</li>
                  						<li>
                  							  <p>購入者</p>
                  							  <p><%= orderDao.getUserName(list.getUserId()) %>/p>
                  						</li>
                  						<li>
                  							  <p>合計金額</p>
                  							  <p><%= list.getTotalAmount() %></p>
                  						</li>
                  						<li>
                  							  <p>発送先住所</p>
                  							  <p><%= list.getDeliveryAddress() %></p>
                  						</li>
                  						<li>
                  							  <p>注文日時</p>
                  							  <p><%= list.getOrderDate() %></p>
                  						</li>
                 		   			
                 		   			<% } %>
                 		   			
                 		   			<button><a href="AdminOrderHistoryListServlet">購入通知一覧に戻る</a></button>
			                  
			                  
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