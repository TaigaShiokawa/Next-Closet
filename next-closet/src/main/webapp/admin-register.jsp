<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="junit.model.dao.*" %>
<% AdminBean loginAdmin = (AdminBean)request.getSession().getAttribute("admin"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規管理者登録</title>
<link rel="icon" href="image/favicon.png" id="favicon">
<link rel="stylesheet" href="css/admin-user-register.css">
<link rel="stylesheet" href="css/admin-navbar.css">
</head>
<body>
  <%@ include file="includes/admin-navbar.jsp" %>
  <main>
  <div class="container">
　<h2>新規管理者登録</h2>

 			<% String adminNameError = (String)request.getSession().getAttribute("adminNameError"); %>
			<% String userNameError = (String)request.getSession().getAttribute("userNameError"); //エラー出てたから追加した%> 
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
			<% } else if(userNameError != null){ %>
			<p><%=userNameError%></p>
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
					<button class="update_submit" type="submit">管理者を登録する</button>
					
					<p class="back"><a href="AdminListServlet">一覧に戻る</a></p>
</form>
</div>
  </main>
  <%@ include file="includes/admin-footer.jsp" %> 
</body>
</html>