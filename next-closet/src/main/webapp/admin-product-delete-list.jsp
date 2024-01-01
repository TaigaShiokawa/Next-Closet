<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="junit.model.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.StatusText" %>
 <% List<ProductBean> productList = (ArrayList <ProductBean>)request.getAttribute("productList");
 List<CategoryBean> categoryList = (ArrayList <CategoryBean>)request.getAttribute("categoryList");
 List<ProductBean> searchProducts = (ArrayList<ProductBean>)request.getAttribute("searchProducts");
 String  title = (String)request.getAttribute("title");
 String img = ""; 
 StatusText st = new StatusText();
/*  String genderStr = (String)request.getAttribute("gender");
 int gender = Integer.parseInt(genderStr);
 String categoryIdStr = (String)request.getAttribute("categoryId");
 int categoryId = Integer.parseInt(categoryIdStr); */
 boolean status = false;
 int targetProductId = -1;
 int searchProductId = -1;
 /* 
 int targetGender = -1;
 int targetCategoryId = -1; */
 int i = 0;
 %>
<!-- 管理者のセッション設定が必要 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>管理者削除商品一覧</title>
		 <link rel="icon" href="image/favicon.png" id="favicon">
		<link rel="stylesheet" href="css/admin-product-list.css">
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
								                            <li><a href='AdminProductListServlet'>商品一覧</a></li> 
								                            <li><a href='ProductAddServlet'>商品新規追加</a></li> 
								                            <li><a href='ProductCategoriesServlet'>カテゴリー追加 / 削除</a></li>
								                        </ul>
													</div>
												</div>
												
												<div class="option">
													<input type="checkbox" id="toggle2" class="toggle">
													<label class="category_title" for="toggle2"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16"><path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/></svg>　ユーザー管理</label>
													<div class="category_content">
								                        <ul class="category">
								                            <li><a href='AdminUserListServlet'>ユーザー一覧</a></li> 
								                            <li><a href='AdminUserRegisterServlet'>ユーザー新規登録</a></li>
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
					                
					      
	
	
						</div><!-- sidebar -->
	<div class="list_container">
			<div class="top_wrapper">
				<p class="section_title"><%= title %></p>
				
			</div>
			<div class="table_wrapper">
				<div class="search_header">
						 <form action="AdminProductListServlet" method="get">
			                   <input type="text" name="searchName" class="list_search_box" placeholder="キーワードでを検索">
			                   <input type="hidden" name="status" value="0">
			                   <input class="list_search_btn" type="submit" value="検索">
			             </form>
				</div>
				<div class="table_list">
							<div class="product_category">
					                 <ul id="category_list">
						                    <li class="gender list_top"><span>ALL</span>
						                        <ul class="category">
						                            <li><a href='AdminProductListServlet?status=0'>全ての商品</a></li>
						                            <% for ( CategoryBean columns : categoryList){ %>
						                             <li><a href='AdminProductListServlet?status=0&categoryId=<%= columns.getCategoryId() %>&gender=0&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
						                             <% } %>
						                        </ul>
						                    </li>
						                    
						                    <li class="gender"><span>MAN</span>
						                        <ul class="category">
						                             <% for ( CategoryBean columns : categoryList){ %>
						                            <li><a href='AdminProductListServlet?status=0&categoryId=<%= columns.getCategoryId() %>&gender=1&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
						                             <% } %>
						                        </ul>
						                    </li>
						                    
						                    <li class="gender"><span>WOMAN</span>
						                        <ul class="category">
						 							<% for ( CategoryBean columns : categoryList){ %>
						                            <li><a href='AdminProductListServlet?status=0&categoryId=<%= columns.getCategoryId() %>&gender=2&categoryName=<%= columns.getCategoryName() %>'><%= columns.getCategoryName() %></a></li>
						                            <% } %>
						                        </ul>
						                    </li>
						                    
						                </ul>
					                 </div>
					<table>
						<thead>
							<tr>
							　<th>商品ID</th>
						      <th>商品名</th>
						      <th>価格(税込)</th>
						      <th>登録日</th>
						      <th>復元</th>
						    </tr>
						</thead>
						<tbody>
				                     
						 <% if (searchProducts != null ){
		                     		for (ProductBean columns : productList) { 
		                     		   	for (ProductBean search : searchProducts) {
		                     			   		targetProductId = columns.getProductId();
		                     			  		 	searchProductId = search.getProductId();
		                     		    		 if (targetProductId == searchProductId){ 
		                     		    		 		 status = search.isStatus();
		                     		    		 			 if(status != true){ %>
																<tr class="list_tr">
																		<td><a href="AdminProductDetailServlet?productId=<%= columns.getProductId()%>"><%= columns.getProductId() %></a></td>
																		<td><a href="AdminProductDetailServlet?productId=<%= columns.getProductId()%>"><%= columns.getProductName() %></a></td>
																		<td><a href="AdminProductDetailServlet?productId=<%= columns.getProductId()%>">&yen; <%= String.format("%,d", columns.getPrice())%></a></td>
																		<td><a href="AdminProductDetailServlet?productId=<%= columns.getProductId()%>"><%= columns.getRegistrationDate()%></a></td>
																		<td><a href="ProductStatusChangeServlet?productId=<%= columns.getProductId()%>">復元</a></td>
																		
																	</tr>
																	
																									
													                    		  <%} } } } %>
										                     		 
										    	 
										                     	 <%  } else {//サーチ関係なし	%>         
											                        <% for (ProductBean columns : productList) {
							                       						 status = columns.isStatus();
							                       						 if(status != true){ %>
					                       						 
											            			  
											            			<tr class="list_tr">
																		<td><a href="AdminProductDetailServlet?productId=<%= columns.getProductId()%>"><%= columns.getProductId() %></a></td>
																		<td><a href="AdminProductDetailServlet?productId=<%= columns.getProductId()%>"><%= columns.getProductName()%></a></td>
																		<td><a href="AdminProductDetailServlet?productId=<%= columns.getProductId()%>">&yen; <%= String.format("%,d", columns.getPrice())%></a></td>
																		<td><a href="AdminProductDetailServlet?productId=<%= columns.getProductId()%>"><%= columns.getRegistrationDate()%></a></td>
																		<td><a href="ProductStatusChangeServlet?productId=<%= columns.getProductId()%>">復元</a></td></td>
																		
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
<%@ include file="includes/admin-footer.jsp" %> 

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