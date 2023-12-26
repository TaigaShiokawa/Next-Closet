<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="java.util.*" %>
<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser != null) { %>
<% response.sendRedirect("ProductListServlet"); %>
<% } %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link rel="icon" href="image/favicon.png" id="favicon">
	<title>新規会員登録</title>
	<link rel="stylesheet" href="css/register.css">
	<link rel = "stylesheet" href = "css/navbar.css">
	</head>
	<body>
	<%@ include file="includes/navbar.jsp" %>
		<main>
			<div class="container">
		
			<h2>新規会員登録</h2>
			<% String failure = (String)request.getSession().getAttribute("failure"); %>
			
			<% if(failure != null) { %>
			<p><%=failure %></p>
			<% session.getAttribute("failure"); %>
			<% } %>
			
			<%-- <%
    		// セッションからエラーメッセージのリストを取得
		    List<String> errorMessages = (List<String>) request.getSession().getAttribute("errorMessages");
		
		    // エラーメッセージを表示
		    if(errorMessages != null && !errorMessages.isEmpty()) {
			%>
			        <div class="error-messages">
			            <% for(String errorMessage : errorMessages) { %>
			                <p class="error_message"><%= errorMessage %></p>
			            <% } %>
			        </div>
			<%
			        request.getSession().removeAttribute("errorMessages");
			    }
			%> --%>
			<form action="RegisterServlet" method="post">
					<div class="form_container">
					
					<label>お名前</label>
					<input type="text" name="username" placeholder="例) テスト　太郎" 
					    value="<%= request.getSession().getAttribute("userName") != null ? request.getSession().getAttribute("userName") : "" %>" required><br>
					<% if(request.getSession().getAttribute("errorMessages") != null) { %>
						<% HashMap<String, String> errorMessage = (HashMap<String, String>)request.getSession().getAttribute("errorMessages"); %>
						<% if(errorMessage.containsKey("userName")) { %>
							<p class="error_message"><%=errorMessage.get("userName") %></p>
						<% } %>
					<% } %>
					<label class="caption">*姓と名のスペースは全角にしてください</label><br> 
					
					<label>フリガナ</label><input type="text" name="kananame" placeholder="例) テスト　タロウ" 
						value="<%= request.getSession().getAttribute("kanaName") != null ? request.getSession().getAttribute("kanaName") : "" %>" required><br>
					<% if(request.getSession().getAttribute("errorMessages") != null) { %>
						<% HashMap<String, String> errorMessage = (HashMap<String, String>)request.getSession().getAttribute("errorMessages"); %>
						<% if(errorMessage.containsKey("kanaName")) { %>
							<p class="error_message"><%=errorMessage.get("kanaName") %></p>
						<% } %>
					<% } %>
					<label class="caption">*カタカナのみで入力してください</label><br> 
					
					<label>郵便番号</label><input type="text" id="postcode" name="postcode" placeholder="例) 0000000"
						value="<%= request.getSession().getAttribute("postCode") != null ? request.getSession().getAttribute("postCode") : "" %>" required><br> 
					<% if(request.getSession().getAttribute("errorMessages") != null) { %>
						<% HashMap<String, String> errorMessage = (HashMap<String, String>)request.getSession().getAttribute("errorMessages"); %>
						<% if(errorMessage.containsKey("postCode")) { %>
							<p class="error_message"><%=errorMessage.get("postCode") %></p>
						<% } %>
					<% } %>
					<% String selectedPrefecture = (String)request.getSession().getAttribute("prefectures"); %>
					<label>都道府県</label>
					<select id="prefectures" name="prefectures" required>
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
					
					<label>住所</label><textarea type="text" name="address" placeholder="例) 〇〇市〇〇区〇丁目" required><%= request.getSession().getAttribute("address") != null ? request.getSession().getAttribute("address") : "" %></textarea><br>
					<% if(request.getSession().getAttribute("errorMessages") != null) { %>
						<% HashMap<String, String> errorMessage = (HashMap<String, String>)request.getSession().getAttribute("errorMessages"); %>
						<% if(errorMessage.containsKey("address")) { %>
							<p class="error_message"><%=errorMessage.get("address") %></p>
						<% } %>
					<% } %>
					
					<label>電話番号</label><input type="text" name="telnumber" placeholder="例) 00000000000" 
						value="<%= request.getSession().getAttribute("telNumber") != null ? request.getSession().getAttribute("telNumber") : "" %>" required><br> 
						<% if(request.getSession().getAttribute("errorMessages") != null) { %>
						<% HashMap<String, String> errorMessage = (HashMap<String, String>)request.getSession().getAttribute("errorMessages"); %>
						<% if(errorMessage.containsKey("telNumber")) { %>
							<p class="error_message"><%=errorMessage.get("telNumber") %></p>
						<% } %>
					<% } %>
					
					<label>メールアドレス</label><input type="email" name="email" placeholder="例) 〇〇@〇〇.com" 
						value="<%= request.getSession().getAttribute("email") != null ? request.getSession().getAttribute("email") : "" %>" required><br> 
						<% if(request.getSession().getAttribute("errorMessages") != null) { %>
						<% HashMap<String, String> errorMessage = (HashMap<String, String>)request.getSession().getAttribute("errorMessages"); %>
						<% if(errorMessage.containsKey("email")) { %>
							<p class="error_message"><%=errorMessage.get("email") %></p>
						<% } %>
					<% } %>
					
					<label>パスワード</label><input id="passwordField" type="password" name="password" placeholder="8文字以上" required>
					<% if(request.getSession().getAttribute("errorMessages") != null) { %>
						<% HashMap<String, String> errorMessage = (HashMap<String, String>)request.getSession().getAttribute("errorMessages"); %>
						<% if(errorMessage.containsKey("password")) { %>
							<p class="error_message"><%=errorMessage.get("password") %></p>
						<% } %>
					<% } %>
					
					<label class="caption pass_open" id="togglePassword">パスワードを表示</label>
					<label class="caption">*8文字以上で入力してください　　<div id="password_strength">パスワード強度: レベル 1 / 5 </div></label> 
					<div class="pass_count_box">
							<div id="pass1" class="pass1 pb red"></div>
							<div id="pass2" class="pass2 pb"></div>
							<div id="pass3" class="pass3 pb"></div>
							<div id="pass4" class="pass4 pb"></div>
							<div id="pass5" class="pass5 pb"></div>
						</div>
						<small><span id="password_count">0/100</span><small>
						
					
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

		    document.getElementById('togglePassword').addEventListener('click', function () {
		        const passwordField = document.getElementById('passwordField');
		        const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
		        passwordField.setAttribute('type', type);

		        // ボタンのラベルを切り替える
		        this.textContent = type === 'password' ? 'パスワードを表示' : 'パスワードを隠す';
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
		<script src="js/form.js"></script>
	</body>
</html>