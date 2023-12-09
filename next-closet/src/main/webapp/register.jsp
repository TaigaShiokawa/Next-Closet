<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>新規会員登録</h3>
	<% String success = (String)request.getSession().getAttribute("success"); %>
	<% String failure = (String)request.getSession().getAttribute("failure"); %>
	<% if(success != null) { %>
	<p><%=success %></p>
	<% session.removeAttribute("success"); %>
	<% } else if(failure != null) { %>
	<p><%=failure %></p>
	<% session.getAttribute("failure"); %>
	<% } %>
		<form action="register" method="post">
			お名前：<input type="text" name="lastname" placeholder="山田" required>&nbsp;<input type="text" name="firstname" placeholder="太郎" required><br>
			フリガナ：<input type="text" name="lastkananame" placeholder="ヤマダ" required>&nbsp;<input type="text" name="firstkananame" placeholder="タロウ" required><br>
			郵便番号：<input type="text" name="postcode" placeholder="123-4567" required><br> 
			都道府県：
			<select name="prefectures" required>
			<option value="北海道">北海道</option>
			</select><br>
			住所：<textarea type="text" name="address" required></textarea><br>
			電話番号：<input type="text" name="telnumber" placeholder="080-1234-5678" required><br> 
			メールアドレス：<input type="email" name="email" placeholder="next.closet@example.com" required><br> 
			パスワード：<input type="password" name="password" placeholder="8文字以上" required><br> 
			<button type="submit">新規登録する</button>
		</form>
</body>
</html>