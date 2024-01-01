<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.bean.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser != null) { %>
<% response.sendRedirect("ProductListServlet"); %>
<% } %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title>
	 <link rel="icon" href="image/favicon.png" id="favicon">
	<link rel="stylesheet" href="css/login.css">
	<link rel = "stylesheet" href = "css/navbar.css">
	</head>
<body>
<%@ include file="includes/navbar.jsp" %>
	<main>
			<div class="container">
				<h2>ログイン</h2>
					<% String success = (String)request.getSession().getAttribute("success"); %>
					<% String loginError = (String)request.getSession().getAttribute("loginError"); %>
					<% String notFound = (String)request.getSession().getAttribute("notFound"); %>
					<% String email = (String)request.getSession().getAttribute("email"); %>
					<% Integer passwordStrength = (Integer)request.getSession().getAttribute("passwordStrength"); %>
					<% if(success != null) { %>
					<p><%=success %></p>
					<% session.removeAttribute("success"); %>
					<% } else if(loginError != null) { %>
					<p><%=loginError %></p>
					<% session.removeAttribute("loginError"); %>
					<% } else if(notFound != null) { %>
					<p><%=notFound %></p>
					<% session.removeAttribute("notFound"); %>
					<% } %>
					<div class="form_wrapper">
						<form action="LoginServlet" method="post">
							<input class="box" type="email" name="email" value="<%= email != null ? email : "" %>" required><br>
							<input class="box" type="password" name="password" required><br>
							<input type="hidden" name="userStatus" value="true">
							<button id="login_submit" type="submit">login</button>
						</form>
					</div>
					<p>アカウントをお持ちでない場合はこちらから</p>
					<a href="RegisterServlet">申し込み</a>
				</div>
		</main>
		<%@ include file="includes/footer.jsp" %>
		<script src="js/form.js"></script>
</body>
</html>