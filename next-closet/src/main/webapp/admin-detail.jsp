<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% AdminBean admin = (AdminBean)request.getAttribute("admin"); %>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="css/admin-detail.css">
<link rel="stylesheet" href="css/admin-navbar.css"> 
<meta charset="UTF-8">
<title>next closet...</title>
</head>
<body>
<%@ include file="includes/admin-navbar.jsp" %>
<main>
	<div class="wrapper">
	
	<form action="AdminEditServlet" method="post">
	<table>
		<tr>
	        <td><label for="email">管理者ID</label></td>
	        <td><%=admin.getAdminId()%></td>
	    </tr>
	    
	    <tr>
	        <td><label for="name">お名前</label></td>
	      <td><%=admin.getAdminName()%></td> 
	    </tr>
	    <tr>
	        <td><label for="furigana">フリガナ</label></td>
	        <td><%=admin.getAdminKanaName()%></td>
	    </tr>
	 
	    <tr>
	        <td><label for="email">メールアドレス</label></td>
	        <td><%=admin.getEmail()%></td>
	    </tr>
	    
	     <tr>
	        <td><label for="email">登録日</label></td>
	        <td><%=admin.getRegistrationDate()%></td>
	    </tr>
	    
	    
	    
	</table>
		
	<div class="btn_wrapper">
	
	  <button class="btn"><a href="AdminEditServlet?adminId=<%=admin.getAdminId()%>">更新</a></button>
        <% if (admin.isAdminStatus()) { %>
              <label class="label_btn delete_button" id="modalOpen">削除</label> 
        <% } else { %>
        <button class="btn"><a href="AdminRestorationServlet?adminId=<%= admin.getAdminId()%>">復元</a></button>
		 <% } %>		      
		
	</div>
	</form>
	<p class="back"><a href="AdminListServlet">管理者一覧へ戻る</a><p>
	</div>
</main>

						  <div id="easyModal" class="modal">
						    <div class="modal-content">
						      <div class="modal-body">
						        <p><strong>本当に削除しますか？</strong></p>
						        <div class="delete_btn_wrapper">
						        <label class="modalClose">キャンセル</label>
							        <form action="AdminDeleteServlet" method="post">
								        <input type="hidden" name="adminId" value="<%=admin.getAdminId()%>">
								        <input class="delete_btn" type="submit" value="削除する">
							        </form>
							        </div>
						    </div>
						  </diV>
						  
						  
<%@ include file="includes/footer.jsp" %>
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