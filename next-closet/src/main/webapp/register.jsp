<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser != null) { %>
<% response.sendRedirect("ProductListServlet"); %>
<% } %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>next closet...</title>
	<link rel="stylesheet" href="css/register.css">
	<link rel = "stylesheet" href = "css/navbar.css">
	</head>
	<body>
	<%@ include file="includes/navbar.jsp" %>
		<main>
			<div class="container">
		
			<h2>新規会員登録</h2>
			<% String success = (String)request.getSession().getAttribute("success"); %>
			<% String failure = (String)request.getSession().getAttribute("failure"); %>
			<% String userNameError = (String)request.getSession().getAttribute("userNameError"); %>
			<% String kanaNameError = (String)request.getSession().getAttribute("kanaNameError"); %>
			<% String postCodeError = (String)request.getSession().getAttribute("postCodeError"); %>
			<% String addressError = (String)request.getSession().getAttribute("addressError"); %>
			<% String passError = (String)request.getSession().getAttribute("passError"); %>
			<% String telNumberError = (String)request.getSession().getAttribute("telNumberError"); %>
			<% String emailError = (String)request.getSession().getAttribute("emailError"); %>
			
			<% if(success != null) { %>
			<p><%=success %></p>
			<% session.removeAttribute("success"); %>
			<% } else if(failure != null) { %>
			<p><%=failure %></p>
			<% session.getAttribute("failure"); %>
			<% }  else if(userNameError != null){ %>
			<p><%=userNameError %></p>
			<% session.removeAttribute("userNameError"); %>
			<% } else if(kanaNameError != null) { %>
			<p><%=kanaNameError %></p>
			<% session.removeAttribute("kanaNameError"); %>
			<% } else if(postCodeError != null) { %>
			<p><%=postCodeError %></p>
			<% session.removeAttribute("postCodeError"); %>
			<% } else if(addressError != null) { %>
			<p><%=addressError %></p>
			<% session.removeAttribute("addressError"); %>
			<% } else if(passError != null) {%>
			<p><%=passError %></p>
			<% session.removeAttribute("passError"); %>
			<% } else if(telNumberError != null) { %>
			<p><%=telNumberError %></p>
			<% session.removeAttribute("telNumberError"); %>
			<% } else if(emailError != null) { %>
			<p><%=emailError %></p>
			<% session.removeAttribute("emailError"); %>
			<% } %>
			<% Integer passwordStrength = (Integer)request.getSession().getAttribute("passwordStrength"); %>
			<% if(passwordStrength != null) { %>
			<p>※パスワード強度は<%=passwordStrength %> レベルです</p>
			<% session.removeAttribute("passwordStrength"); %>
			<% } %>
				<form action="RegisterServlet" method="post">
					<div class="form_container">
					<label>お名前</label><input type="text" name="username" placeholder="例) テスト　太郎" required><br>
					<label>フリガナ</label><input type="text" name="kananame" placeholder="例) テスト　タロウ" required><br>

					<label>郵便番号</label><input type="text" name="postcode" placeholder="例) 0000000" required><br> 
					<label>都道府県</label>
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
		
					<label>住所</label><textarea type="text" name="address" placeholder="例) 〇〇市〇〇区〇丁目" required></textarea><br>
					<label>電話番号</label><input type="text" name="telnumber" placeholder="例) 00000000000" required><br> 
					<label>メールアドレス</label><input type="email" name="email" placeholder="例) 〇〇@〇〇.com" required><br> 
					<label>パスワード</label><input type="password" name="password" placeholder="8文字以上" required>
					<small><span id="password_count">0/100</span><small><br> 
					<div id="password_strength"></div><br>
					<button type="submit">新規登録する</button>
					</div>
				</form>
			</div>
		</main>
		<%@ include file="includes/footer.jsp" %>
		
		<script>
		    // パスワード入力フィールドの要素を取得
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