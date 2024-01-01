<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
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
<% OrderDAO orderDao = new OrderDAO(); %>

<!-- 管理者のセッション設定が必要 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者購入通知詳細</title>
 <link rel="icon" href="image/favicon.png" id="favicon">
<link rel="stylesheet" href="css/admin-order-history.css">
	<link rel="stylesheet" href="css/admin-navbar.css">
</head>

<body>
<%@ include file="includes/admin-navbar.jsp" %>
		<main>
		
		<div class="container">
				<div class="box">
        
					                    			 
		                  			 <% for( OrderBean list : orderList) {  %>
		                  			 
		                  			 <table>
		                  			 <tr>
								        <td><label for="name">注文番号</label></td>
								     	 <td><%= list.getOrderItemId() %></td> 
								     </tr>
								     
								     <tr>
								        <td><label for="name">商品名</label></td>
								     	 <td><%= orderDao.getProductName(list.getProductId()) %></td> 
								     </tr>
								     
								     <tr>
								        <td><label for="name">数量</label></td>
								     	 <td><%= list.getQuantity() %></td> 
								     </tr>
								     
								     <tr>
								        <td><label for="name">サイズ</label></td>
								     	 <td><%= st.sizeText(list.getSizeId()) %></td> 
								     </tr>
								     
								      <tr>
								        <td><label for="name">購入者</label></td>
								     	 <td><%= orderDao.getUserName(list.getUserId()) %></td> 
								     </tr>
								     
								      <tr>
								        <td><label for="name">合計金額</label></td>
								     	 <td><%= list.getTotalAmount() %></td> 
								     </tr>
								     
								      <tr>
								        <td><label for="name">発送先住所</label></td>
								     	 <td><%= list.getDeliveryAddress() %></td> 
								     </tr>
								     
								      <tr>
								        <td><label for="name">注文日時</label></td>
								     	 <td><%= list.getOrderDate() %></td> 
								     </tr>
		                  	</table>
		                  				
		                 		   			<% } %>
		                 		   			
		                 		   <p class="back"><a href="AdminOrderHistoryListServlet">購入通知一覧に戻る</a></p>
			                  
			           </div>       
				</div>
		</main>
    <%@ include file="includes/admin-footer.jsp" %>    
	<body>
	
  <script>


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