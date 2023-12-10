<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% String loginUser = (String)request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>

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
		<form action="RegisterServlet" method="post">
			お名前：<input type="text" name="lastname" placeholder="例) 姓" required>&nbsp; &nbsp;<input type="text" name="firstname" placeholder="例) 名" required><br>
			フリガナ：<input type="text" name="lastkananame" placeholder="例) セイ" required>&nbsp; &nbsp;<input type="text" name="firstkananame" placeholder="例) メイ" required><br>
			郵便番号：<input type="text" name="postcode" placeholder="0000000" required><br> 
			都道府県：
			<select name="prefectures" required>
			    <option selected>選択してください</option>
			    <option value="北海道">北海道</option>
			    <option value="青森県">青森県</option>
			    <option value="岩手県">岩手県</option>
			    <option value="宮城県">宮城県</option>
			    <option value="秋田県">秋田県</option>
			    <option value="山形県">山形県</option>
			    <option value="福島県">福島県</option>
			    <option value="茨城県">茨城県</option>
			    <option value="栃木県">栃木県</option>
			    <option value="群馬県">群馬県</option>
			    <option value="埼玉県">埼玉県</option>
			    <option value="千葉県">千葉県</option>
			    <option value="東京都">東京都</option>
			    <option value="神奈川県">神奈川県</option>
			    <option value="新潟県">新潟県</option>
			    <option value="富山県">富山県</option>
			    <option value="石川県">石川県</option>
			    <option value="福井県">福井県</option>
			    <option value="山梨県">山梨県</option>
			    <option value="長野県">長野県</option>
			    <option value="岐阜県">岐阜県</option>
			    <option value="静岡県">静岡県</option>
			    <option value="愛知県">愛知県</option>
			    <option value="三重県">三重県</option>
			    <option value="滋賀県">滋賀県</option>
			    <option value="京都府">京都府</option>
			    <option value="大阪府">大阪府</option>
			    <option value="兵庫県">兵庫県</option>
			    <option value="奈良県">奈良県</option>
			    <option value="和歌山県">和歌山県</option>
			    <option value="鳥取県">鳥取県</option>
			    <option value="島根県">島根県</option>
			    <option value="岡山県">岡山県</option>
			    <option value="広島県">広島県</option>
			    <option value="山口県">山口県</option>
			    <option value="徳島県">徳島県</option>
			    <option value="香川県">香川県</option>
			    <option value="愛媛県">愛媛県</option>
			    <option value="高知県">高知県</option>
			    <option value="福岡県">福岡県</option>
			    <option value="佐賀県">佐賀県</option>
			    <option value="長崎県">長崎県</option>
			    <option value="熊本県">熊本県</option>
			    <option value="大分県">大分県</option>
			    <option value="宮崎県">宮崎県</option>
			    <option value="鹿児島県">鹿児島県</option>
			    <option value="沖縄県">沖縄県</option>
			</select><br>

			住所：<textarea type="text" name="address" placeholder="例) 〇〇市〇〇区〇丁目" required></textarea><br>
			電話番号：<input type="text" name="telnumber" placeholder="例) 00000000000" required><br> 
			メールアドレス：<input type="email" name="email" placeholder="例) 〇〇@〇〇.com" required><br> 
			パスワード：<input type="password" name="password" placeholder="8文字以上" required><br> 
			<button type="submit">新規登録する</button>
		</form>
</body>
</html>