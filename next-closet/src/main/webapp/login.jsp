<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>next closet...</title>
	<link rel="stylesheet" href="css/login.css">
	<link rel = "stylesheet" href = "css/navbar.css">
	</head>
<body>
<%@ include file="includes/navbar.jsp" %>
	<main>
			<div class="container">
				<h2>ログイン</h2>
					<% String loginError = (String)request.getSession().getAttribute("loginError"); %>
					<% String notFound = (String)request.getSession().getAttribute("notFound"); %>
					<% if(loginError != null) { %>
					<p><%=loginError %></p>
					<% session.removeAttribute("loginError"); %>
					<% } else if(notFound != null) { %>
					<p><%=notFound %></p>
					<% session.removeAttribute("notFound"); %>
					<% } %>
					<div class="form_wrapper">
						<form action="LoginServlet" method="post">
							<input class="box" type="email" name="email" placeholder="メールアドレス" required><br>
							<input class="box" type="password" name="password" placeholder="password" required><br>
							<input type="hidden" name="userStatus" value="true">
							<button id="login_submit" type="submit">login</button>
						</form>
					</div>
					<p>アカウントをお持ちでない場合はこちらから</p>
					<a href="register.jsp">申し込み</a>
				</div>
		</main>
		<%@ include file="includes/footer.jsp" %>
</body>
</html>