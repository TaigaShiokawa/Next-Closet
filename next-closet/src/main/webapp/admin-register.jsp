<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% AdminBean loginAdmin = (AdminBean)request.getSession().getAttribute("admin"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>next closet...</title>
</head>
<body>

 <%@ include file="includes/navbar.jsp" %>  

			<% String adminNameError = (String)request.getSession().getAttribute("adminNameError"); %>
			<% String kanaNameError = (String)request.getSession().getAttribute("kanaNameError"); %>
			<% String passError = (String)request.getSession().getAttribute("passError"); %>
			<% String emailError = (String)request.getSession().getAttribute("emailError"); %>
			<% String success = (String)request.getSession().getAttribute("success"); %>
			<% String failure = (String)request.getSession().getAttribute("failure"); %>
			
			<% if(success != null) { %>
			<p><%=success %></p>
			<% session.removeAttribute("success"); %>
			<% } else if(failure != null) { %>
			<p><%=failure %></p>
			<% session.getAttribute("failure"); %>
			<% }  else if(userNameError != null){ %>
			<p><%=userNameError %></p>
			<% session.removeAttribute("adminNameError"); %>
			<% } else if(kanaNameError != null) { %>
			<p><%=kanaNameError %></p>
			<% session.removeAttribute("kanaNameError"); %>
			<% } else if(passError != null) {%>
			<p><%=passError %></p>
			<% session.removeAttribute("passError"); %>
			<% } else if(emailError != null) { %>
			<p><%=emailError %></p>
			<% session.removeAttribute("emailError"); %>
			<% } %>
<form action="AdminRegisterServlet" method="post">

					<label>お名前：</label><input type="text" name="adminname" placeholder="例) テスト　太郎" required><br>
					<label>フリガナ：</label><input type="text" name="kananame" placeholder="例) テスト　タロウ" required><br>
					<label>メールアドレス：</label><input type="email" name="email" placeholder="例) 〇〇@〇〇.com" required><br> 
					<label>パスワード：</label><input type="password" name="password" placeholder="8文字以上" required><br> 
					<button type="submit">管理者を登録する</button>
</form>

</body>
</html>