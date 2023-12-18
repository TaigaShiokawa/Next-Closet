<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% AddressBean loginUserAddress = (AddressBean)request.getSession().getAttribute("userAddress"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("LoginServlet"); %>
<% } %>  
<!DOCTYPE html>
<html>
		<head>
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Tangerine&display=swap" rel="stylesheet">	
		<link rel="stylesheet" href="css/mypage.css">
		<link rel="stylesheet" href="css/navbar.css">
		<meta charset="UTF-8">
		<title>マイページ</title>
	</head>
<body>
 <%@ include file="includes/navbar.jsp" %>  
 <main>

		<div class="title">
			<h2 id="section_title">MyPage</h2>
		    <h3 class="page-title">マイページ</h3>
		    <% Integer passwordStrength = (Integer)request.getSession().getAttribute("passwordStrength"); %>
			<% if(passwordStrength != null) { %>
			<p>※変更後のパスワード強度は<%=passwordStrength %> レベルです</p>
			<% session.removeAttribute("passwordStrength"); %>
			<% } %>
		</div>
   
 <div class="container">
        <table>
		    <tr>
		        <td><label for="name">お名前</label></td>
		      <td><%=loginUser.getUserName() %></td> 
		    </tr>
		    <tr>
		        <td><label for="furigana">フリガナ</label></td>
		        <td><%=loginUser.getKanaName() %></td>
		    </tr>
		    <tr>
		        <td><label for="postalcode">郵便番号</label></td>
		        <td>&#12306; <%=loginUserAddress.getPostCode() %></td>
		    </tr>
		    <tr>
		        <td><label for="address">都道府県</label></td>
		        <td><%=loginUserAddress.getPrefectures() %></td>
		    </tr>
		    <tr>
		        <td><label for="city">住所</label></td>
		        <td><%=loginUserAddress.getAddress() %></td>
		        <td><a href="SubAddressServlet">その他の住所を見る</a></td> <!-- サブ住所の追加 SubAddressServletへ -->
		    </tr>
		    <tr>
		        <td><label for="phone">電話番号</label></td>
		        <td><%=loginUser.getTelNumber() %></td>
		    </tr>
		    <tr>
		        <td><label for="email">メールアドレス</label></td>
		        <td><%=loginUser.getEmail() %></td>
		    </tr>
	</table>


            <div class="button_nav">
					<button type="submit"><a href="mypage-edit.jsp">変更</a></button>
		            <button type="submit"><a href="OrderHistoryServlet">購入履歴</a></button>
				</div>
        
        <p><a href="ProductListServlet" class="back">商品一覧へ戻る</a></p>

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
						        <p>一度退会するとアカウントの復元はできません<br>本当に削除しますか？</p>
						        <form action="WithdrawalServlet" method="post">
						           <input class="delete" type="submit" value="本当に退会する">
						        </form>
						      </div>
						    </div>
						  </diV>
        	
  
        </div>
        </main>
		<%@ include file="includes/footer.jsp" %>  
<script>
		const buttonOpen = document.getElementById('modalOpen');
		const modal = document.getElementById('easyModal');
		const buttonClose = document.getElementsByClassName('modalClose')[0];
	
		// ボタンがクリックされた時
		buttonOpen.addEventListener('click', modalOpen);
		function modalOpen() {
		  modal.style.display = 'block';
		}
	
		// バツ印がクリックされた時
		buttonClose.addEventListener('click', modalClose);
		function modalClose() {
		  modal.style.display = 'none';
		}
	
		// モーダルコンテンツ以外がクリックされた時
		addEventListener('click', outsideClose);
		function outsideClose(e) {
		  if (e.target == modal) {
		    modal.style.display = 'none';
		  }
		}
	</script>
</body>
</html>
