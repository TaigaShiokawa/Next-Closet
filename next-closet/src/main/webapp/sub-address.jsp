<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sub-address.css">
<link rel="stylesheet" href="css/navbar.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="container">
  <div class="split left-box">
<!-- add_addressテーブルに追加する --> 
<h2>新しい住所を追加</h2>
<% String postCodeError = (String)request.getSession().getAttribute("postCodeError"); %>
<% if(postCodeError != null) { %>
<p><%=postCodeError %></p>
<% session.removeAttribute("postCodeError"); %>
<% } %>
<form action="SubAddressServlet" method="post">
	<label>郵便番号：</label><input type="text" id="postcode" name="postcode" placeholder="例) 0000000" required><br> 

	<label>都道府県：</label><select id="prefectures" name="prefectures" required>
			    <option value="北海道" selected>北海道</option>
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

	<label>住所：</label><input type="text" name="address" placeholder="例) 〇〇市〇〇区〇丁目" required></input><br>

    <button type="submit">登録</button>
</form>
</div>

<div class="split right-box">
<!-- Listをfor文で回して追加した住所を表示 -->
<h2>追加した住所</h2>
<% String deleteAddressNotFound = (String)request.getSession().getAttribute("deleteAddressNotFound"); %>
<% List<AddressBean> addressList = (List<AddressBean>)request.getAttribute("addressList"); %>
<% if(deleteAddressNotFound != null) { %>
<p><%=deleteAddressNotFound %></p>
<% session.removeAttribute("deleteAddressNotFound"); %>
<% } %>
<% if(addressList == null) { %>
	<% response.sendRedirect("SubAddressServlet"); %>
	<% } else { %> 
	<% for(AddressBean addresses : addressList) { %>
		<form action="AddressDeleteServlet" method="post">
			<div>
		    	<input type="checkbox" name="addAddressId" value="<%=addresses.getAddAddressId()%>"> <!-- サブ住所のIDを値として返す -->
		    	<label><%=addresses.getPrefectures()%><%=addresses.getAddress()%></label>
		    </div>
	<% } %>
	<button type="submit">削除</button>
</form>
<% } %>
   </div>
</div>
   <div class="center"><a href="MypageServlet">マイページに戻る</a></div>
   
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