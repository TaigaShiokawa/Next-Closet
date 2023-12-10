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
			お名前：<input type="text" name="lastname" placeholder="例) 姓" required>&nbsp; &nbsp;<input type="text" name="firstname" placeholder="例) 名" required><br>
			フリガナ：<input type="text" name="lastkananame" placeholder="例) セイ" required>&nbsp; &nbsp;<input type="text" name="firstkananame" placeholder="例) メイ" required><br>
			郵便番号：<input type="text" name="postcode" placeholder="0000000" required><br> 
			都道府県：
			<select name="prefectures" required>
			<option selected>選択してください</option>
			<option value="北海道">北海道</option>
			</select><br>
			住所：<textarea type="text" name="address" placeholder="例) 〇〇市〇〇区〇丁目" required></textarea><br>
			電話番号：<input type="text" name="telnumber" placeholder="例) 00000000000" required><br> 
			メールアドレス：<input type="email" name="email" placeholder="例) 〇〇@〇〇.com" required><br> 
			パスワード：<input type="password" name="password" placeholder="8文字以上" required><br> 
			<button type="submit">新規登録する</button>
		</form>
</body>
</html>