<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="junit.model.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.StatusText" %>
 <%  StatusText st = new StatusText(); %>
 <% List<AdminBean> adminList = (ArrayList <AdminBean>)request.getAttribute("adminList");
 List <AdminBean> searchAdmins = (ArrayList<AdminBean>)request.getAttribute("searchAdmins");
 String  title = (String)request.getAttribute("title");
 
 boolean status = false;
 int targetAdminId = -1;
 int searchAdminId = -1;
 int i = 0;

 %>
<!-- 管理者のセッション設定が必要 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログインページ</title>
<link rel="stylesheet" href="css/admin-list.css">
<link rel="stylesheet" href="css/admin-navbar.css">
</head>

<body>
<%@ include file="includes/admin-navbar.jsp" %>
<main>
<div class="container"><i class="ri-arrow-right-up-fill"></i>
	<div class="sidebar">
									<ul id="category_list">
					                	<div class="accordion">
												<div class="option">
													<input type="checkbox" id="toggle1" class="toggle">
													<label class="category_title" for="toggle1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-tag" viewBox="0 0 16 16">
													  <path d="M6 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm-1 0a.5.5 0 1 0-1 0 .5.5 0 0 0 1 0z"/>
													  <path d="M2 1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 1 6.586V2a1 1 0 0 1 1-1zm0 5.586 7 7L13.586 9l-7-7H2v4.586z"/>
													</svg>　商品管理</label>
													<div class="category_content">
								                        <ul class="category">
								                            <li><a href='AdminProductListServlet'>商品一覧</a></li> <!-- アコーディオンの中身のリスト -->
								                            <li><a href='ProductAddServlet'>商品新規追加</a></li> <!-- アコーディオンの中身のリスト -->
								                            <li><a href='ProductCategoriesServlet'>カテゴリー追加 / 削除</a></li> <!-- アコーディオンの中身のリスト -->
								                        </ul>
													</div>
												</div>
												
												<div class="option">
													<input type="checkbox" id="toggle2" class="toggle">
													<label class="category_title" for="toggle2"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16"><path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/></svg>　ユーザー管理</label>
													<div class="category_content">
								                        <ul class="category">
								                            <li><a href='AdminUserListServlet'>ユーザー一覧</a></li> <!-- アコーディオンの中身のリスト -->
								                            <li><a href='AdminUserRegisterServlet'>ユーザー新規登録</a></li> <!-- アコーディオンの中身のリスト -->
								                        </ul>
													</div>
												</div>
												
												<div class="option">
													<input type="checkbox" id="toggle3" class="toggle">
													<label class="category_title" for="toggle3"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
													  <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
													  <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
													</svg>　管理者管理</label>
													<div class="category_content">
								                        <ul class="category">
								                            <li><a href='AdminListServlet'>管理者一覧</a></li> <!-- アコーディオンの中身のリスト -->
								                            <li><a href='AdminRegisterServlet'>管理者新規登録</a></li> <!-- アコーディオンの中身のリスト -->
								                        </ul>
													</div>
												</div>
												
												<div class="option">
													<input type="checkbox" id="toggle4" class="toggle">
													<label class="category_title" for="toggle4"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
													  <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
													  <path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z"/>
													</svg>　通知 / 購入履歴</label>
													<div class="category_content">
								                        <ul class="category">
								                            <li><a href='AdminOrderHistoryListServlet'>通知 / 購入履歴一覧</a></li> <!-- アコーディオンの中身のリスト -->
								                        </ul>
													</div>
												</div>
												
												<div class="option">
													<input type="checkbox" id="toggle5" class="toggle">
													<label class="category_title" for="toggle5"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clipboard-x" viewBox="0 0 16 16">
													  <path fill-rule="evenodd" d="M6.146 7.146a.5.5 0 0 1 .708 0L8 8.293l1.146-1.147a.5.5 0 1 1 .708.708L8.707 9l1.147 1.146a.5.5 0 0 1-.708.708L8 9.707l-1.146 1.147a.5.5 0 0 1-.708-.708L7.293 9 6.146 7.854a.5.5 0 0 1 0-.708z"/>
													  <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/>
													  <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/>
													</svg>　削除データ</label>
													<div class="category_content">
								                        <ul class="category">
								                            <li><a href='AdminProductListServlet?status=0'>削除済み商品一覧</a></li> <!-- アコーディオンの中身のリスト -->
								                            <li><a href='AdminUserListServlet?status=0'>退会済みユーザー一覧</a></li> <!-- アコーディオンの中身のリスト -->
								                            <li><a href='AdminListServlet?status=0'>削除済み管理者一覧</a></li> <!-- アコーディオンの中身のリスト -->
								                        </ul>
													</div>
												</div>
												
												
											
										</div> <!-- アコーディオンここまで -->	
					                </ul><!-- category_listここまで -->
	
	
	</div>
	<div class="list_container">
			<div class="top_wrapper">
				<p class="section_title"><%= title %></p>
				
				<button class="register"><a href='AdminRegisterServlet'>管理者新規登録</a></button>
			</div>
			<div class="table_wrapper">
				<div class="search_header">
						 <form action="AdminListServlet" method="get">
			                   <input type="text" name="searchName" class="list_search_box" placeholder="キーワードでを検索">
			                   <input class="list_search_btn" type="submit" value="検索">
			             </form>
				</div>
				<div class="table_list">
					<table>
						<thead>
							<tr>
							　<th>管理者ID</th>
						      <th>名前</th>
						      <th>フリガナ</th>
						      <th>メールアドレス</th>
						      <th>削除</th>
						    </tr>
						</thead>
						<tbody>
				                     
						<% if (searchAdmins != null ){
					                     		for (AdminBean columns : adminList) { 
					                     		   	for (AdminBean search : searchAdmins) {
					                     			   		targetAdminId = columns.getAdminId();
					                     			   		searchAdminId = search.getAdminId();
					                     		    		 if (targetAdminId == searchAdminId){ 
					                     		    		 		 status = search.isAdminStatus();
					                     		    		 			 if(status){ %>
																				<tr class="list_tr">
																		<td class="user_id"><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>"><%= columns.getAdminId() %></a></td>
																		<td><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>"><%= columns.getAdminName() %></a></td>
																		<td><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>"><%= columns.getAdminKanaName() %></a></td>
																		<td class="mail"><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>"><%= columns.getEmail() %></a></td>
																		<td id='modalOpen<%= i %>' class="trash"　><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
																		<path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
																		</svg></td>
																		
																		<div id="easyModal<%= i %>" class="modal">
																		    <div class="modal-content">
																		      <div class="modal-body">
																		         <p>この管理者を削除してもよろしいですか？</p>
																				        <div class="delete_btn">
																					        <label class="modalClose">キャンセル</label>
																					        <form action="AdminDeleteServlet" method="post">
																					           <input type="hidden" name="adminId" value="<%= columns.getAdminId() %>">
																					           <input class="delete" type="submit" value="削除する">
																					        </form>
																				        </div>
																		      </div>
																		    </div>
																	   </diV>
																		<% i += 1 ;  %>
																	</tr>
															 <%} } } } %>
					                     		 
					    	 
					                     	 <%  } else {//サーチ関係なし	%>
					  
									                        <% for (AdminBean columns : adminList) {
					                       						 status = columns.isAdminStatus();
					                       						 if(status){ %>
					                       						 
											            			  
											            			<tr class="list_tr">
																		<td class="user_id"><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>"><%= columns.getAdminId() %></a></td>
																		<td><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>"><%= columns.getAdminName() %></a></td>
																		<td><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>"><%= columns.getAdminKanaName() %></a></td>
																		<td class="mail"><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>"><%= columns.getEmail() %></a></td>
																		<td id='modalOpen<%= i %>' class="trash"　><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
																		<path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
																		</svg></td>
																		
																		<div id="easyModal<%= i %>" class="modal">
																		    <div class="modal-content">
																		      <div class="modal-body">
																		         <p>この管理者を削除してもよろしいですか？</p>
																				        <div class="delete_btn">
																					        <label class="modalClose">キャンセル</label>
																					        <form action="AdminDeleteServlet" method="post">
																					           <input type="hidden" name="adminId" value="<%= columns.getAdminId() %>">
																					           <input class="delete" type="submit" value="削除する">
																					        </form>
																				        </div>
																		      </div>
																		    </div>
																	   </diV>
																		<% i += 1 ;  %>
																	</tr>
		                  					                   <% } } %>				                    
					                                        <% }%>
															</tbody>
														</table>


						    <div id="count" data-repeat-count=<%= i %>></div>  
				</div>
			</div><!-- wrapper_container -->
		</div> <!-- list_container -->
	</div> <!-- container -->
</main>
<%@ include file="includes/footer.jsp" %>    

	<script>


	    var countrepeat = document.getElementById("count").getAttribute("data-repeat-count");
	    

		for( let j = 0 ; j < countrepeat ; j++ ){

			const buttonOpen = document.getElementById("modalOpen" + j);
			const modal = document.getElementById("easyModal" + j);
			const buttonClose = document.getElementsByClassName('modalClose')[0];

			console.log(buttonOpen);
		
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

			}} 

			

	</script>
	
</body>
</html>


<%-- 
ーーー

<div class="content wrapper">

						<div class="search">
				               <form action="AdminListServlet" method="get">
				                   <input type="text" name="searchName" class="search_box" placeholder="キーワードで商品名を検索">
				                   <input class="sarch_btn" type="submit" value="検索">
				               </form>
				               
				               <button><a href="AdminListServlet">一覧表示</a></button>
				        </div>
            
			  		<div class="tab">
			 				 <h1 class="page-title"><%= title %></h1>	
						    <ul class="tab__menu">
							      <li class="tab__menu-item is-active" data-tab="01">全管理者一覧表</li>
							      <li class="tab__menu-item" data-tab="02">管理者一覧表</li>
							      <li class="tab__menu-item" data-tab="03">削除済み管理者一覧表</li>
						    </ul> 
						    
						    <div class="tab__panel">
			                    <div class="tab__panel-box tab__panel-box001 is-show" data-panel="01"> <!-- 全表示 -->
			                    	
							                
			                     	<ul class="user-list">                     	
			                     								 <ul>			
				                     		   					   <li>ユーザーネーム</li>
				                     		   					   <li>メールアドレス</li>
				                     		   					   <li></li>
				                     		   					   <li></li>
				                     		   					 </ul>
			                     		
			                     		<% if (searchAdmins != null ){
				                     		for (AdminBean columns : adminList) { 
				                     		   	for (AdminBean sarch : searchAdmins) {
				                     			   		targetAdminId = columns.getAdminId();
				                     			 		  sarchAdminId = sarch.getAdminId();
				                     		    		    if (targetAdminId == sarchAdminId){ %>
			                     		     
				                     		   			
				                     		   					 <ul>
				                     		   					   <li><%= columns.getAdminName() %></li>
				                     		   					   <li><%= columns.getEmail() %></li>
				                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
				                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
				                     		   					 </ul>
							                    			 
						                    			 
						                    		  <% } } } %>
			                     		 
			    	 
			                     			 <%  } else {	%>
			  
							                         <% for (AdminBean columns : adminList){%>
								                    		    <ul>
				                     		   					   <li><%= columns.getAdminName() %></li>
				                     		   					   <li><%= columns.getEmail() %></li>
				                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
				                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
				                     		   					 </ul>
						                    			<% }%>
						                    
						                 	  <% }%>
						                  
						                  
						                  </ul>
				                    
			                    	 <button><a href="AdminRegisterServlet">管理者新規登録</a></button>
			                     </div>
			                     
			                     
			                     <div class="tab__panel-box tab__panel-box002 " data-panel="02"> <!-- 販売中 -->
			                    
			                     		<ul class="admin-list">
			                     		
					                      <% if (searchAdmins != null ){
					                     		for (AdminBean columns : adminList) { 
					                     		   	for (AdminBean sarch : searchAdmins) {
					                     			   		targetAdminId = columns.getAdminId();
					                     			 		sarchAdminId = sarch.getAdminId();
					                     		    		 if (targetAdminId == sarchAdminId){ 
					                     		    		 		 status = sarch.isAdminStatus();
					                     		    		 			 if(status){ %>
					                     		     
									                     		   				  <ul>
								                     		   					   <li><%= columns.getAdminName() %></li>
								                     		   					   <li><%= columns.getEmail() %></li>
								                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
								                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
								                     		   					 </ul>
												                    			 
								                    		  <%} } } } %>
					                     		 
					    	 
					                     	 <%  } else {//サーチ関係なし	%>
					  
									                        <% for (AdminBean columns : adminList) {
					                       						 status = columns.isAdminStatus();
					                       						 if(status){ %>
										            			   <ul>
				                     		   					   <li><%= columns.getAdminName() %></li>
				                     		   					   <li><%= columns.getEmail() %></li>
				                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
				                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
				                     		   					 </ul>
					                  					 <% } } %>
								                    
								                   <% }%>
			                   		  </ul>
			                    </div>
			                     
			                     
			                     
			                     <div class="tab__panel-box tab__panel-box003" data-panel="03"> <!-- 削除済み -->
			                     	
				                     <ul class="admin-list">
				                     <% if (searchAdmins != null ){
			                     		for (AdminBean columns : adminList) { 
			                     		   	for (AdminBean sarch : searchAdmins) {
			                     			   		targetAdminId = columns.getAdminId();
			                     			 		sarchAdminId = sarch.getAdminId();
			                     		    		 if (targetAdminId == sarchAdminId){ 
			                     		    		 		 status = columns.isAdminStatus();
			                     		    		 
			                     		    		 			 if(status != true){ %>
			                     		     
							                     		   				 <ul>
						                     		   					   <li><%= columns.getAdminName() %></li>
						                     		   					   <li><%= columns.getEmail() %></li>
						                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
						                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
						                     		   					 </ul>
								                    			 
						                    		  <%} } } } %>
			                     		 
			    	 
			                     	 <%  } else {//サーチ関係なし	%>
			  
							                        <% for (AdminBean columns : adminList) {
			                       								 status = columns.isAdminStatus();
			                       						
			                       						 if(status != true){ %>
								            					  <ul>
				                     		   					   <li><%= columns.getAdminName() %></li>
				                     		   					   <li><%= columns.getEmail() %></li>
				                     		   					   <li><%= st.adminStatusText(columns.isAdminStatus()) %></li>
				                     		   					   <li><a href="AdminDetailServlet?adminId=<%= columns.getAdminId() %>">詳細を見る</a></li>
				                     		   					 </ul>
			                  						 <% } } %>
						                    
						                   <% }%>
				                     
				                     </ul>
				                  </div>
				                  
				                  
				                  
  <script>

  'use strict'; /* 厳格にエラーをチェック */

  { /* ローカルスコープ */

  // DOM取得
  const tabMenus = document.querySelectorAll('.tab__menu-item');
  console.log(tabMenus);

  // イベント付加
  tabMenus.forEach((tabMenu) => {
    tabMenu.addEventListener('click', tabSwitch);
  })

  // イベントの処理
  function tabSwitch(e) {

    // クリックされた要素のデータ属性を取得
    const tabTargetData = e.currentTarget.dataset.tab;
    // クリックされた要素の親要素と、その子要素を取得
    const tabList = e.currentTarget.closest('.tab__menu');
    console.log(tabList);
    const tabItems = tabList.querySelectorAll('.tab__menu-item');
    console.log(tabItems);
    // クリックされた要素の親要素の兄弟要素の子要素を取得
    const tabPanelItems = tabList.
    nextElementSibling.querySelectorAll('.tab__panel-box');
    console.log(tabPanelItems);

    // クリックされたtabの同階層のmenuとpanelのクラスを削除
    tabItems.forEach((tabItem) => {
      tabItem.classList.remove('is-active');
    })
    tabPanelItems.forEach((tabPanelItem) => {
      tabPanelItem.classList.remove('is-show');
    })

    // クリックされたmenu要素にis-activeクラスを付加
    e.currentTarget.classList.add('is-active');
    // クリックしたmenuのデータ属性と等しい値を持つパネルにis-showクラスを付加
    tabPanelItems.forEach((tabPanelItem) => {
      if (tabPanelItem.dataset.panel ===  tabTargetData) {
        tabPanelItem.classList.add('is-show');
      }
    })

  }

  }

  </script> --%>
				                  
				            <!--   </div>tab閉じタグ
						</div> -->