<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<%@ page import="java.util.* , java.util.ArrayList, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="image/favicon.png" id="favicon">
<link rel="stylesheet" href="css/admin-user-detail.css">
<link rel="stylesheet" href="css/admin-navbar.css"> 
<meta charset="UTF-8">
<title>登録ユーザー情報編集</title>

</head>
<body>
<%@ include file="includes/admin-navbar.jsp" %>
<main>
 <%
 List<UserBean> userList = (List<UserBean>)request.getAttribute("userList"); 
 for (UserBean user : userList) {
 %>

 <div class="wrapper">
	<table>
	    <tr>
	        <td><label for="name">お名前</label></td>
	      <td><%=user.getUserName() %></td> 
	    </tr>
	    <tr>
	        <td><label for="furigana">フリガナ</label></td>
	        <td><%=user.getKanaName() %></td>
	    </tr>
	    <tr>
	        <td><label for="postalcode">郵便番号</label></td>
	        <td><%=user.getPostCode() %></td>
	    </tr>
	    <tr>
	        <td><label for="address">都道府県</label></td>
	        <td><%=user.getPrefectures() %></td>
	    </tr>
	    <tr>
	        <td><label for="city">住所</label></td>
	        <td><%=user.getAddress() %></td>
	    </tr>
	    <tr>
	        <td><label for="phone">電話番号</label></td>
	        <td><%=user.getTelNumber() %></td>
	    </tr>
	    <tr>
	        <td><label for="email">メールアドレス</label></td>
	        <td><%=user.getEmail() %></td>
	    </tr>
	</table>
	
	<div class="btn_wrapper">
	
	  <button class="btn"><a href="AdminUserUpdateServlet?userId=<%= user.getUserId() %>">更新</a></button>
        <% if (user.isUserStatus()) { %>
           <label class="label_btn delete_button" id="modalOpen">削除</label> 
        <% } else { %>
           <button class="btn"><a href="AdminUserRestorationServlet?userId=<%= user.getUserId() %>">復活</button></a>
		 <% } %>		        
	</div>
	 <div id="easyModal" class="modal">
						    <div class="modal-content">
						      <div class="modal-body">
						        <p><strong>本当に削除しますか？</strong></p>
						        <div class="delete_btn_wrapper">
						        	<label class="modalClose">キャンセル</label>
							        <form action="AdminUserWithdrawalServlet" method="get">
								        <input type="hidden" name="userId" value="<%= user.getUserId() %>">
								        <input class="delete_btn" type="submit" value="削除する">
							        </form>
							    </div>
						    </div>
						  </diV>
						   </diV>
						  
		 <% 
		 }
		%>
		
	
	<p class="back"><a href="AdminUserListServlet">一覧に戻る</a></p>
</div>

</main>
<%@ include file="includes/admin-footer.jsp" %> 

<script>
		const buttonOpen = document.getElementById('modalOpen');
		const modal = document.getElementById('easyModal');
		const buttonClose = document.getElementsByClassName('modalClose')[0];
	
		// ボタンがクリックされた時
		buttonOpen.addEventListener('click', modalOpen);
		function modalOpen() {
		  modal.style.display = 'block';
		}
	
		// バツ印がクリックされた時
		buttonClose.addEventListener('click', modalClose);
		function modalClose() {
		  modal.style.display = 'none';
		}
	
		// モーダルコンテンツ以外がクリックされた時
		addEventListener('click', outsideClose);
		function outsideClose(e) {
		  if (e.target == modal) {
		    modal.style.display = 'none';
		  }
		}
	</script>
</body>
</html>