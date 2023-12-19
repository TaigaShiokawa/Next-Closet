<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="java.util.* , java.util.ArrayList, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="css/mypage-edit.css"> -->
<!-- <link rel="stylesheet" href="css/navbar.css"> -->
<meta charset="UTF-8">
<title>会員情報編集</title>
</head>
<body>
<%-- <%@ include file="includes/navbar.jsp" %> --%>
<%
 List<UserBean> userLists = (List<UserBean>)request.getAttribute("userLists"); 
 for (UserBean user : userLists) {
 %>

<main>
<div class="container">
<h3>会員情報更新画面</h3>
<form action="AdminUserEditServlet" method="post">
			<input type="hidden" name="userId" value="<%= user.getUserId() %>">
			<label>お名前：</label><input type="text" name="username" value="<%=user.getUserName() %>"><br>
			<label>フリガナ：</label><input type="text" name="kananame" value="<%=user.getKanaName() %>"><br>
			<label>郵便番号：</label><input type="text" name="postcode" value="<%=user.getPostCode()%>"><br> 
			<label>都道府県：</label>
			<select name="prefectures">
			    <option value="<%=user.getPrefectures()%>"><%=user.getPrefectures()%></option>
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

			<label>住所：</label><textarea type="text" name="address"><%=user.getAddress() %></textarea><br>
			<label>電話番号：</label><input type="text" name="telnumber" value="<%=user.getTelNumber()%>"><br> 
			<label>メールアドレス：</label><input type="email" name="email" value="<%=user.getEmail()%>"><br> 
			<% 
 }
%>
			
			<button type="submit">会員情報を更新する</button>			
		</form>

		</div>
</main>

</body>
</html>