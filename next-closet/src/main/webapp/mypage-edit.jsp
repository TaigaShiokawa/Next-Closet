<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% AddressBean loginUserAddress = (AddressBean)request.getSession().getAttribute("userAddress"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/mypage-edit.css">
<link rel="stylesheet" href="css/navbar.css">
<meta charset="UTF-8">
<title>個人情報編集</title>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<main>
<div class="container">
<h3>ユーザー情報更新画面</h3>
<% String userNameError = (String)request.getSession().getAttribute("userNameError"); %>
<% String kanaNameError = (String)request.getSession().getAttribute("kanaNameError"); %>
<% String postCodeError = (String)request.getSession().getAttribute("postCodeError"); %>
<% String addressError = (String)request.getSession().getAttribute("addressError"); %>
<% String telNumberError = (String)request.getSession().getAttribute("telNumberError"); %>
<% String emailError = (String)request.getSession().getAttribute("emailError"); %>
<% if(userNameError != null) { %>
<p><%=userNameError %></p>
<% session.removeAttribute("userNameError"); %>
<% } else if(kanaNameError != null) {%>
<p><%=kanaNameError %></p>
<% session.removeAttribute("kanaNameError"); %>
<% } else if(postCodeError != null) { %>
<p><%=postCodeError %></p>
<% session.removeAttribute("postCodeError"); %>
<% } else if(addressError != null) { %>
<p><%=addressError %></p>
<% session.removeAttribute("addressError"); %>
<% } else if(telNumberError != null) { %>
<p><%=telNumberError %></p>
<% session.removeAttribute("telNumberError"); %>
<% } else if(emailError != null) { %>
<p><%=emailError %></p>
<% session.removeAttribute("emailError"); %>
<% } %>
<form action="UserEditServlet" method="post" class="center-form">
			<label class="label">お名前</label><input type="text" name="username" value="<%=loginUser.getUserName() %>"><br>
			<label class="caption">*姓と名のスペースは全角にしてください</label><br> 
			<label class="label">フリガナ</label><input type="text" name="kananame" value="<%=loginUser.getKanaName() %>"><br>
			<label class="caption">*姓と名のスペースは全角にしてください</label><br> 
			<label class="caption">*カタカナのみで入力してください</label><br> 
			<label class="label">郵便番号</label><input type="text" name="postcode" value="<%=loginUserAddress.getPostCode()%>"><br> 
			<label class="label">都道府県</label>
			<select name="prefectures">
			    <option value="<%=loginUserAddress.getPrefectures()%>"><%=loginUserAddress.getPrefectures()%></option>
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

			<label class="label">住所</label><textarea type="text" name="address"><%=loginUserAddress.getAddress() %></textarea><br>
			<label class="label">電話番号</label><input type="text" name="telnumber" value="<%=loginUser.getTelNumber()%>"><br> 
			<label class="label">メールアドレス</label><input type="email" name="email" value="<%=loginUser.getEmail()%>"><br> 
			
			<button type="submit">更新する</button>			
		</form>

		<% String passError = (String)request.getSession().getAttribute("passError"); %>
		<% if(passError != null) { %>
		<p><%=passError %></p>
		<% session.removeAttribute("passError"); %>
		<% } %>
		<form action="PasswordUpdateServlet" method="post">
		<label for="pass" class="label">パスワードの変更</label>
		<input type="password" id="pass" name="password" placeholder="8文字以上">
		<label class="caption">*8文字以上で入力してください　　<div id="password_strength">パスワード強度: レベル 1 / 5 </div></label> 
		<div class="pass_count_box">
				<div id="pass1" class="pass1 pb red"></div>
				<div id="pass2" class="pass2 pb"></div>
				<div id="pass3" class="pass3 pb"></div>
				<div id="pass4" class="pass4 pb"></div>
				<div id="pass5" class="pass5 pb"></div>
			</div>
			<small><span id="password_count">0/100</span><small>
		<input type="hidden" name="userId" value="<%=loginUser.getUserId()%>">
		<button type="submit">変更する</button>
		</form>
		</div>
</main>

<script>
//パスワード入力フィールドの要素を取得
var passwordInput = document.querySelector('input[name="password"]');
var passwordCount = document.getElementById('password_count');

// パスワード入力フィールドの入力イベントにリスナーを追加
passwordInput.addEventListener('input', function() {
    var textLength = this.value.length;
    passwordCount.textContent = textLength + '/100'; 

    if(textLength > 100) {
        passwordCount.style.color = 'red';
    } else {
	    passwordCount.style.color = 'initial';
	}
});

function checkPasswordStrength(password) {
    var strength = 0;
    if (password.length >= 8) strength += 1; // 長さのチェック
    if (password.match(/[a-z]/)) strength += 1; // 小文字の存在
    if (password.match(/[A-Z]/)) strength += 1; // 大文字の存在
    if (password.match(/[0-9]/)) strength += 1; // 数字の存在
    if (password.match(/[^a-zA-Z0-9]/)) strength += 1; // 特殊文字の存在

    return strength;
}

// パスワード入力フィールドのイベントリスナー
passwordInput.addEventListener('input', function() {
    var textLength = this.value.length;
    passwordCount.textContent = textLength + '/100'; 

    var strength = checkPasswordStrength(this.value);
    var strengthDisplay = document.getElementById('password_strength');
    strengthDisplay.textContent = 'パスワード強度: レベル ' + strength + ' / 5';
    // 色もつける？
});
</script>
		
</body>
</html>