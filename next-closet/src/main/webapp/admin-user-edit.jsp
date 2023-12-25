<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="java.util.* , java.util.ArrayList, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="image/favicon.png" id="favicon">
<link rel="stylesheet" href="css/admin-user-register.css">
<link rel="stylesheet" href="css/admin-navbar.css">
<meta charset="UTF-8">
<title>会員情報編集</title>
</head>
<body>
<%@ include file="includes/admin-navbar.jsp" %>
<%
 List<UserBean> userLists = (List<UserBean>)request.getSession().getAttribute("userLists"); 
 for (UserBean user : userLists) {
 %>

<main>
<div class="container">
<h2>会員情報更新画面</h2>
<% List<String> errorMessages = (List<String>)request.getSession().getAttribute("errorMessages"); %>
<% if(errorMessages != null && !errorMessages.isEmpty()) { %>
	<% for(String errorMessage : errorMessages) { %>
	<p><%=errorMessage %></p>
	<% } %>
	<% request.getSession().removeAttribute("errorMessages"); %>
<% } %>

<form action="AdminUserEditServlet" method="post">
			<input type="hidden" name="userId" value="<%= user.getUserId() %>">
			<label>お名前：</label><input type="text" name="username" 
				value="<%=request.getSession().getAttribute("userName") != null ? request.getSession().getAttribute("userName") : user.getUserName() %>"><br>
			<label class="caption">*姓と名のスペースは全角にしてください</label><br> 
			
			<label>フリガナ：</label><input type="text" name="kananame" 
				value="<%=request.getSession().getAttribute("kanaName") != null ? request.getSession().getAttribute("kanaName") : user.getKanaName() %>"><br>
			<label class="caption">*カタカナのみで入力してください</label><br>
			
			<label>郵便番号：</label><input type="text" id="postcode" name="postcode" 
				value="<%=request.getSession().getAttribute("postCode") != null ? request.getSession().getAttribute("postCode") : user.getPostCode() %>"><br> 
			
			<%
			    String selectedPrefecture = (String)request.getSession().getAttribute("prefectures");
			%>
			<label>都道府県：</label>
			<select id="prefectures" name="prefectures" required>
			    <option value="<%=user.getPrefectures()%>"><%=user.getPrefectures()%></option>
	    		<option value="北海道" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>北海道</option>
			    <option value="青森県" <%= "青森県".equals(selectedPrefecture) ? "selected" : "" %>>青森県</option>
			    <option value="岩手県" <%= "岩手県".equals(selectedPrefecture) ? "selected" : "" %>>岩手県</option>
			    <option value="宮城県" <%= "宮城県".equals(selectedPrefecture) ? "selected" : "" %>>宮城県</option>
			    <option value="秋田県" <%= "秋田県".equals(selectedPrefecture) ? "selected" : "" %>>秋田県</option>
			    <option value="山形県" <%= "山形県".equals(selectedPrefecture) ? "selected" : "" %>>山形県</option>
			    <option value="福島県" <%= "福島県".equals(selectedPrefecture) ? "selected" : "" %>>福島県</option>
			    <option value="茨城県" <%= "茨城県".equals(selectedPrefecture) ? "selected" : "" %>>茨城県</option>
			    <option value="栃木県" <%= "栃木県".equals(selectedPrefecture) ? "selected" : "" %>>栃木県</option>
			    <option value="群馬県" <%= "群馬県".equals(selectedPrefecture) ? "selected" : "" %>>群馬県</option>
			    <option value="埼玉県" <%= "埼玉県".equals(selectedPrefecture) ? "selected" : "" %>>埼玉県</option>
			    <option value="千葉県" <%= "千葉県".equals(selectedPrefecture) ? "selected" : "" %>>千葉県</option>
			    <option value="東京都" <%= "東京都".equals(selectedPrefecture) ? "selected" : "" %>>東京都</option>
			    <option value="神奈川県" <%= "神奈川県".equals(selectedPrefecture) ? "selected" : "" %>>神奈川県</option>
			    <option value="新潟県" <%= "新潟県".equals(selectedPrefecture) ? "selected" : "" %>>新潟県</option>
			    <option value="富山県" <%= "富山県".equals(selectedPrefecture) ? "selected" : "" %>>富山県</option>
			    <option value="石川県" <%= "石川県".equals(selectedPrefecture) ? "selected" : "" %>>石川県</option>
			    <option value="福井県" <%= "福井県".equals(selectedPrefecture) ? "selected" : "" %>>福井県</option>
			    <option value="山梨県" <%= "山梨県".equals(selectedPrefecture) ? "selected" : "" %>>山梨県</option>
			    <option value="長野県" <%= "長野県".equals(selectedPrefecture) ? "selected" : "" %>>長野県</option>
			    <option value="岐阜県" <%= "岐阜県".equals(selectedPrefecture) ? "selected" : "" %>>岐阜県</option>
			    <option value="静岡県" <%= "静岡県".equals(selectedPrefecture) ? "selected" : "" %>>静岡県</option>
			    <option value="愛知県" <%= "愛知県".equals(selectedPrefecture) ? "selected" : "" %>>愛知県</option>
			    <option value="三重県" <%= "三重県".equals(selectedPrefecture) ? "selected" : "" %>>三重県</option>
			    <option value="滋賀県" <%= "滋賀県".equals(selectedPrefecture) ? "selected" : "" %>>滋賀県</option>
			    <option value="京都府" <%= "京都府".equals(selectedPrefecture) ? "selected" : "" %>>京都府</option>
			    <option value="大阪府" <%= "大阪府".equals(selectedPrefecture) ? "selected" : "" %>>大阪府</option>
			    <option value="兵庫県" <%= "兵庫県".equals(selectedPrefecture) ? "selected" : "" %>>兵庫県</option>
			    <option value="奈良県" <%= "奈良県".equals(selectedPrefecture) ? "selected" : "" %>>奈良県</option>
			    <option value="和歌山県" <%= "和歌山県".equals(selectedPrefecture) ? "selected" : "" %>>和歌山県</option>
			    <option value="鳥取県" <%= "鳥取県".equals(selectedPrefecture) ? "selected" : "" %>>鳥取県</option>
			    <option value="島根県" <%= "島根県".equals(selectedPrefecture) ? "selected" : "" %>>島根県</option>
			    <option value="岡山県" <%= "岡山県".equals(selectedPrefecture) ? "selected" : "" %>>岡山県</option>
			    <option value="広島県" <%= "広島県".equals(selectedPrefecture) ? "selected" : "" %>>広島県</option>
			    <option value="山口県" <%= "山口県".equals(selectedPrefecture) ? "selected" : "" %>>山口県</option>
			    <option value="徳島県" <%= "徳島県".equals(selectedPrefecture) ? "selected" : "" %>>徳島県</option>
			    <option value="香川県" <%= "香川県".equals(selectedPrefecture) ? "selected" : "" %>>香川県</option>
			    <option value="愛媛県" <%= "愛媛県".equals(selectedPrefecture) ? "selected" : "" %>>愛媛県</option>
			    <option value="高知県" <%= "高知県".equals(selectedPrefecture) ? "selected" : "" %>>高知県</option>
			    <option value="福岡県" <%= "福岡県".equals(selectedPrefecture) ? "selected" : "" %>>福岡県</option>
			    <option value="佐賀県" <%= "佐賀県".equals(selectedPrefecture) ? "selected" : "" %>>佐賀県</option>
			    <option value="長崎県" <%= "長崎県".equals(selectedPrefecture) ? "selected" : "" %>>長崎県</option>
			    <option value="熊本県" <%= "熊本県".equals(selectedPrefecture) ? "selected" : "" %>>熊本県</option>
			    <option value="大分県" <%= "大分県".equals(selectedPrefecture) ? "selected" : "" %>>大分県</option>
			    <option value="宮崎県" <%= "宮崎県".equals(selectedPrefecture) ? "selected" : "" %>>宮崎県</option>
			    <option value="鹿児島県" <%= "鹿児島県".equals(selectedPrefecture) ? "selected" : "" %>>鹿児島県</option>
			    <option value="沖縄県" <%= "沖縄県".equals(selectedPrefecture) ? "selected" : "" %>>沖縄県</option>
			</select><br>

			<label>住所：</label><textarea type="text" name="address"><%=request.getSession().getAttribute("address") != null ? request.getSession().getAttribute("address") : user.getAddress() %></textarea><br>
			
			<label>電話番号：</label><input type="text" name="telnumber" 
				value="<%=request.getSession().getAttribute("telNumber") != null ? request.getSession().getAttribute("telNumber") : user.getTelNumber() %>"><br> 
			
			<label>メールアドレス：</label><input type="email" name="email" 
				value="<%=request.getSession().getAttribute("email") != null ? request.getSession().getAttribute("email") : user.getEmail() %>"><br> 
		
			
			<button class="update_submit" type="submit">会員情報を更新する</button>			
		</form>
		<p class="back"><a href="AdminUserDetailServlet?userId=<%= user.getUserId() %>">詳細に戻る</a></p>
			<% 
				 }
				%>
		</div>
</main>
<%@ include file="includes/admin-footer.jsp" %> 

	<script>
	//郵便番号で都道府県を検索
		document.getElementById('postcode').addEventListener('input', function() {
		    var postcode = this.value;
		    if (postcode.length === 7) { // 郵便番号が7桁の場合のみAPIを呼び出す
		        fetch('https://zipcloud.ibsnet.co.jp/api/search?zipcode=' + postcode)
		        .then(response => response.json())
		        .then(data => {
		            if (data && data.results) {
		                var prefecture = data.results[0].address1; // 都道府県を取得
		                document.getElementById('prefectures').value = prefecture;
		            }
		        });
		    }
		});
	</script>
</body>
</html>