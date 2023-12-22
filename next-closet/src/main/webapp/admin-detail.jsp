<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% AdminBean admin = (AdminBean)request.getAttribute("admin"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/admin-delete.css">
<meta charset="UTF-8">
<title>next closet...</title>
</head>
<body>
<%@ include file="includes/admin-navbar.jsp" %>
<form action="AdminEditServlet" method="post">
	<div>お名前：<%=admin.getAdminName()%></div>
	<div>フリガナ：<%=admin.getAdminKanaName()%></div>
	<div>メールアドレス：<%=admin.getEmail()%></div>
</form>
<button type="submit"><a href="AdminEditServlet?adminId=<%=admin.getAdminId()%>">更新</a></button>
	<!--削除はjsでするからこっちはaタグを使わない？-->
 <div class="delete_box">
        	<button id="modalOpen" class="delete_button">削除</button>
       				 </div>
						  <div id="easyModal" class="modal">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="check">最終確認画面</h1>
						        <span class="modalClose">×</span>
						      </div>
						      <div class="modal-body">
						        <p><strong>本当に削除しますか？</strong></p>
						        <form action="AdminDeleteServlet" method="post">
						        <input type="hidden" name="adminId" value="<%=admin.getAdminId()%>">
						        <input class="delete" type="submit" value="削除する">
						        </form>
						      </div>
						    </div>
						  </diV>
<a href="AdminListServlet">戻る</a>
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