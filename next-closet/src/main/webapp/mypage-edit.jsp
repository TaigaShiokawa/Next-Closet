<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.bean.*" %>
<%@ page import="java.util.*" %>
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
<link rel="icon" href="image/favicon.png" id="favicon">
<meta charset="UTF-8">
<title>個人情報編集</title>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<main>
<div class="container">
<h3>ユーザー情報更新画面</h3>

<% List<String> errorMessages = (List<String>)request.getSession().getAttribute("errorMessage"); %>
<% if(errorMessages != null) { %>
	<% for(String errorMessage : errorMessages) { %>
    <p><%= errorMessage %></p>
	<% } %>
	<% request.getSession().removeAttribute("errorMessage"); %>
<% } %>

		<form action="UserEditServlet" method="post" class="center-form">
			<label class="label">お名前</label><input type="text" name="username" 
				value="<%=request.getSession().getAttribute("userName") != null ? request.getSession().getAttribute("userName") : loginUser.getUserName() %>"><br>
				<% if(request.getSession().getAttribute("mypageErrorMSG") != null) { %>
						<% HashMap<String, String> mypageErrorMSG = (HashMap<String, String>)request.getSession().getAttribute("mypageErrorMSG"); %>
						<% if(mypageErrorMSG.containsKey("userName")) { %>
							<p class="error_message"><%=mypageErrorMSG.get("userName") %></p>
						<% } %>
					<% } %>
			<label class="caption">*姓と名のスペースは全角にしてください</label><br>
			
			<label class="label">フリガナ</label><input type="text" name="kananame" 
				value="<%=request.getSession().getAttribute("kanaName") != null ? request.getSession().getAttribute("kanaName") : loginUser.getKanaName() %>"><br>
			<% if(request.getSession().getAttribute("mypageErrorMSG") != null) { %>
						<% HashMap<String, String> mypageErrorMSG = (HashMap<String, String>)request.getSession().getAttribute("mypageErrorMSG"); %>
						<% if(mypageErrorMSG.containsKey("kanaName")) { %>
							<p class="error_message"><%=mypageErrorMSG.get("kanaName") %></p>
						<% } %>
					<% } %>
			<label class="caption">*姓と名のスペースは全角にしてください</label><br> 
			<label class="caption">*カタカナのみで入力してください</label><br> 
			
			<label class="label">郵便番号</label><input type="text" id="postcode" name="postcode" 
				value="<%=request.getSession().getAttribute("postCode") != null ? request.getSession().getAttribute("postCode") : loginUserAddress.getPostCode() %>"><br>
				<% if(request.getSession().getAttribute("mypageErrorMSG") != null) { %>
						<% HashMap<String, String> mypageErrorMSG = (HashMap<String, String>)request.getSession().getAttribute("mypageErrorMSG"); %>
						<% if(mypageErrorMSG.containsKey("postCode")) { %>
							<p class="error_message"><%=mypageErrorMSG.get("postCode") %></p>
						<% } %>
					<% } %>
			
			<% String selectedPrefecture = (String)request.getSession().getAttribute("prefectures"); %>
			<label class="label">都道府県</label>
			<select id="prefectures" name="prefectures" required>
				<option value="<%=loginUserAddress.getPrefectures()%>"><%=loginUserAddress.getPrefectures()%>
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

			<label class="label">住所</label><textarea type="text" name="address"><%=request.getSession().getAttribute("address") != null ? request.getSession().getAttribute("address") : loginUserAddress.getAddress() %></textarea><br>
			<% if(request.getSession().getAttribute("mypageErrorMSG") != null) { %>
						<% HashMap<String, String> mypageErrorMSG = (HashMap<String, String>)request.getSession().getAttribute("mypageErrorMSG"); %>
						<% if(mypageErrorMSG.containsKey("address")) { %>
							<p class="error_message"><%=mypageErrorMSG.get("address") %></p>
						<% } %>
					<% } %>
			
			<label class="label">電話番号</label><input type="text" name="telnumber" 
				value="<%=request.getSession().getAttribute("telNumber") != null ? request.getSession().getAttribute("telNumber") : loginUser.getTelNumber() %>"><br> 
				<% if(request.getSession().getAttribute("mypageErrorMSG") != null) { %>
						<% HashMap<String, String> mypageErrorMSG = (HashMap<String, String>)request.getSession().getAttribute("mypageErrorMSG"); %>
						<% if(mypageErrorMSG.containsKey("telNumber")) { %>
							<p class="error_message"><%=mypageErrorMSG.get("telNumber") %></p>
						<% } %>
					<% } %>
				
			<label class="label">メールアドレス</label><input type="email" name="email" 
				value="<%=request.getSession().getAttribute("email") != null ? request.getSession().getAttribute("email") : loginUser.getEmail() %>"><br> 
					<% if(request.getSession().getAttribute("mypageErrorMSG") != null) { %>
						<% HashMap<String, String> mypageErrorMSG = (HashMap<String, String>)request.getSession().getAttribute("mypageErrorMSG"); %>
						<% if(mypageErrorMSG.containsKey("email")) { %>
							<p class="error_message"><%=mypageErrorMSG.get("email") %></p>
						<% } %>
					<% } %>
			
			<button type="submit">更新する</button>			
		</form>

		<%-- <% String passError = (String)request.getSession().getAttribute("passError"); %>
		<% if(passError != null) { %>
		<p><%=passError %></p>
		<% session.removeAttribute("passError"); %>
		<% } %> --%>
		<% if(request.getSession().getAttribute("mypagePassError") != null) { %>
						<% HashMap<String, String> mypagePassError = (HashMap<String, String>)request.getSession().getAttribute("mypagePassError"); %>
						<% if(mypagePassError.containsKey("password")) { %>
							<p class="error_message"><%=mypagePassError.get("password") %></p>
							
						<% } %>
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

		<div class="delete_box">
		        	<button id="modalOpen" class="delete_button">▶︎ 退会する</button>
		        </div>
								  <div id="easyModal" class="modal">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h1 class="check">最終確認画面</h1>
								        <span class="modalClose">×</span>
								      </div>
								      <div class="modal-body">
								        <p>一度退会するとアカウントの復元はできません<br>本当に退会しますか？</p>
								        <form action="WithdrawalServlet" method="post">
								           <input class="delete" type="submit" value="本当に退会する">
								        </form>
								      </div>
								    </div>
								  </diV>

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
    // パスワードレベルの色
    const btn1 = document.getElementById('pass1');
    const btn2 = document.getElementById('pass2');
    const btn3 = document.getElementById('pass3');
    const btn4 = document.getElementById('pass4');
    const btn5 = document.getElementById('pass5');

    if(strength == 1 ){
       	btn1.classList.remove("green" , "red" , "yellow" ,"gray");
       	btn2.classList.remove("green" , "red" , "yellow" ,"gray");
       	btn3.classList.remove("green" , "red" , "yellow" ,"gray");
       	btn4.classList.remove("green" , "red" , "yellow" ,"gray");
       	btn5.classList.remove("green" , "red" , "yellow" ,"gray");

    	btn1.classList.add("red");
       	btn2.classList.add("gray");
       	btn3.classList.add("gray");
       	btn4.classList.add("gray");
       	btn5.classList.add("gray");
       	 
   } else if(strength == 2 ){
	   
	   btn1.classList.remove("green" , "red" , "yellow" ,"gray");
      	btn2.classList.remove("green" , "red" , "yellow" ,"gray");
      	btn3.classList.remove("green" , "red" , "yellow" ,"gray");
      	btn4.classList.remove("green" , "red" , "yellow" ,"gray");
      	btn5.classList.remove("green" , "red" , "yellow" ,"gray");

   		btn1.classList.add("red");
      	btn2.classList.add("red");
      	btn3.classList.add("gray");
      	btn4.classList.add("gray");
      	btn5.classList.add("gray"); 
      	
	} else if(strength == 3 ){
		
		   btn1.classList.remove("green" , "red" , "yellow" ,"gray");
	      	btn2.classList.remove("green" , "red" , "yellow" ,"gray");
	      	btn3.classList.remove("green" , "red" , "yellow" ,"gray");
	      	btn4.classList.remove("green" , "red" , "yellow" ,"gray");
	      	btn5.classList.remove("green" , "red" , "yellow" ,"gray");

	   		btn1.classList.add("yellow");
	      	btn2.classList.add("yellow");
	      	btn3.classList.add("yellow");
	      	btn4.classList.add("gray");
	      	btn5.classList.add("gray"); 
	      	
		}else if(strength == 4 ){
			
			   btn1.classList.remove("green" , "red" , "yellow" ,"gray");
		      	btn2.classList.remove("green" , "red" , "yellow" ,"gray");
		      	btn3.classList.remove("green" , "red" , "yellow" ,"gray");
		      	btn4.classList.remove("green" , "red" , "yellow" ,"gray");
		      	btn5.classList.remove("green" , "red" , "yellow" ,"gray");

		   		btn1.classList.add("yellow");
		      	btn2.classList.add("yellow");
		      	btn3.classList.add("yellow");
		      	btn4.classList.add("yellow");
		      	btn5.classList.add("gray"); 
		      	
			}else if(strength == 5 ){
				
			   btn1.classList.remove("green" , "red" , "yellow" ,"gray");
		      	btn2.classList.remove("green" , "red" , "yellow" ,"gray");
		      	btn3.classList.remove("green" , "red" , "yellow" ,"gray");
		      	btn4.classList.remove("green" , "red" , "yellow" ,"gray");
		      	btn5.classList.remove("green" , "red" , "yellow" ,"gray");

		   		btn1.classList.add("green");
		      	btn2.classList.add("green");
		      	btn3.classList.add("green");
		      	btn4.classList.add("green");
		      	btn5.classList.add("green"); 
			}
	});

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