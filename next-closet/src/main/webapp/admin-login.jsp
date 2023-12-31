<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>


<% AdminBean loginAdmin = (AdminBean)request.getSession().getAttribute("Admin"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="icon" href="image/favicon.png" id="favicon">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="css/admin-login.css">
	<link rel = "stylesheet" href = "css/admin-navbar.css">
	
<title>管理者ログイン</title>
</head>
<body>

<!-- 管理者用のナビゲーションバーが必要かも -->

     <header>
	<div class="header_inner">
		<h1 id="site_title">next closet... 管理者画面</h1>
		<div class="header_right">
		</div>
	</div>
  </header> 
  <main>
   <div class="container"> 

	<h2>管理者ログイン</h2>
		<% String errorMessageToAdmin = (String)request.getSession().getAttribute("errorMessageToAdmin"); %>
		<% String loginError = (String)request.getSession().getAttribute("loginError"); %>
		<% if(errorMessageToAdmin != null) { %>
		<p><%=errorMessageToAdmin %></p>
		<% session.removeAttribute("errorMessageToAdmin"); %>
		<% } else if(loginError != null) { %>
		<p><%=loginError %></p>
		<% session.removeAttribute("loginError"); %>
		<% } %>
		<form class="form_wrapper" action="AdminLoginServlet" method="post">
			<input class="box" type="text" name="email" placeholder="メールアドレス"><br>
			<input class="box" type="password" name="password" placeholder="パスワード"><br>
			<button id="login_submit" type="submit">login</button>
		</form>
		</div>
	</main>

<%@ include file="includes/admin-footer.jsp" %>    
</body>
</html>