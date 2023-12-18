<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% AddressBean loginUserAddress = (AddressBean)request.getSession().getAttribute("userAddress"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("LoginServlet"); %>
<% } %>  
<!DOCTYPE html>
<html>
		<head>
		<link rel="stylesheet" href="css/mypage.css">
		<link rel="stylesheet" href="css/navbar.css">
		<meta charset="UTF-8">
		<title>マイページ</title>
	</head>
<body>
 <%@ include file="includes/navbar.jsp" %>  
 <main>

		<div class="title">
			<h2 id="section_title">MyPage</h2>
		    <h3 class="page-title">マイページ</h3>
		    <% Integer passwordStrength = (Integer)request.getSession().getAttribute("passwordStrength"); %>
			<% if(passwordStrength != null) { %>
			<p>※変更後のパスワード強度は<%=passwordStrength %> レベルです</p>
			<% session.removeAttribute("passwordStrength"); %>
			<% } %>
		</div>
   
 
        </main>
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
