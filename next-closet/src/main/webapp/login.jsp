<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>ログイン</h3>
	<% String loginError = (String)request.getSession().getAttribute("loginError"); %>
	<% if(loginError != null) { %>
	<p><%=loginError %></p>
	<% session.removeAttribute("loginError"); %>
	<% } %>
		<form action="login" method="post">
			Eメール：<input type="email" name="email" placeholder="next.closet@example.com" required><br>
			パスワード：<input type="password" name="password" placeholder="8文字以上" required><br>
			<button type="submit">login</button>
		</form>
		<h4>アカウントをお持ちでない場合はこちらから</h4>
		<a href="register.jsp">申し込み</a>
</body>
</html>