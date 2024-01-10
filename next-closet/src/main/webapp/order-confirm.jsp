<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , java.util.ArrayList,model.bean.*, java.util.List , model.SizeText" %>
<%@ page import="junit.model.dao.*" %>

<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); 
	if(loginUser == null) {
		response.sendRedirect("productListServlet");  
	} 
    String order = (String)request.getAttribute("order");
    String img = null;
	SizeText st = new SizeText();
    int totalAmount = 0 ;
    int adressCartItemId = -1;
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>購入確認画面</title>
		<link rel="icon" href="image/favicon.png" id="favicon">
		<link rel="stylesheet" href="css/order-confirm.css">
		<link rel = "stylesheet" href = "css/navbar.css">
	</head>
	<body>
		<%@ include file="includes/navbar.jsp" %>
		<main>
			<div class="container">
				<div　class="order">
					<img class="step_image" src="image/decoration/step1.jpg">
					<h3 id="order_confilm">まだ注文が確定していません。この商品を購入しますか？</h3>
					<p class="error_message">${error_message}</p>
					<p class="message">${message}</p>
					 
				</div>
				<div class="border">
					<form action="OrderConfilmServlet" method="post">
						<% if(order.equals("order")) { //商品詳細から今すぐ購入にした場合 
						   List <ProductBean> productList = (List <ProductBean>)request.getAttribute("productList"); %>
								<div class="flex">
									<% for(ProductBean columns : productList ) {
									 		img = columns.getImage();
									 		 if (img.isEmpty()){
				                            	   img = "image/noimg.jpg";
					                        } else {
					                     	   img = "image/" + img;
					                        }
									   int price = columns.getPrice();
									   int sizeId = (int)request.getAttribute("sizeId");
									   int quantity =  (int)request.getAttribute("quantity");
									   totalAmount += price * quantity; %>
									    <% String formattedItemPrice = String.format("%,d", columns.getPrice()); %>
					 			
							  		<div class="right">
							  			<div class="order_box">
							  				<figure class="image"><img src='<%= img %>' alt="商品画像"></figure>
								  			<div class="text">
											    <p class="product">商品名：<%= columns.getProductName() %></p>
											    <p class="size">サイズ：<%= st.sizeText(sizeId) %></p>
											    <p class="quantity">数量：<%= quantity %></p>
											    <p class="price">&yen; <%= formattedItemPrice %> 税込</p>
										    </div>
										</div>
									    <input type="hidden" name="productId" value="<%= columns.getProductId() %>">
									    <input type="hidden" name="sizeId" value="<%= sizeId %>">
									    <input type="hidden" name="order" value="order">
									    <input type="hidden" name="quantity" value="<%= quantity %>">
									    <input type="hidden" name="totalAmount" value='<%= totalAmount %>'>　
							  		</div>	
								</div>
								<% } %>
						  <% } else if(order.equals("singleCartItem")){ //カートのうち一つの商品のみ購入%>
									  <% CartItemBean cartItem = (CartItemBean)request.getAttribute("cartItem");
									     List <ProductBean> productList = (List <ProductBean>)request.getAttribute("productList"); 
									     int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
									     adressCartItemId = cartItemId;
									     %>
				
								<div class="flex">		
									<% for(ProductBean pl : productList ) { 
									    img = pl.getImage();
				                        if ( img == null ){
				                     	   img = "https://placehold.jp/480x640.png";
				                        } else {
				                     	   img = "image/" + img;
				                        } 
										 int price = pl.getPrice();
										 int sizeId = cartItem.getSizeId();
										 int quantity = cartItem.getQuantity();
										 totalAmount += price * quantity; 
										 String formattedItemPrice = String.format("%,d", pl.getPrice()); 
									%>
									<div class="right">
									 	<div class="order_box">
										<figure class="image"><img src="<%= img %>" alt="商品画像"></figure>
											 <div class="text">
												 <p class="product">商品名：<%= pl.getProductName() %></p>
												 <p class="size">サイズ：<%= st.sizeText(sizeId) %></p>
												 <p class="quantity">数量：<%= quantity %></p>
												 <p class="price">&yen; <%= formattedItemPrice %> 税込</p>
											 </div>
										 </div>
										 <input type="hidden" name="productId" value="<%= pl.getProductId() %>">
									     <input type="hidden" name="sizeId" value="<%= sizeId %>">	
									     <input type="hidden" name="order" value="singleCartItem">
									     <input type="hidden" name="quantity" value="<%= quantity %>">
									     <input type="hidden" name="totalAmount" value='<%= totalAmount %>'>
									     <input type="hidden" name="cartItemId" value='<%= cartItemId %>'>
									     <input type="hidden" name="productList" value='<%= productList %>'>
									 </div>
								<% } 
						  		} else if(order.equals("allCartItems")) {//もしカート全て購入だったら 
							        List <CartItemBean> cartAllItemList = (ArrayList<CartItemBean>) request.getAttribute("cartAllItemList");
									List <ProductBean> productList = null;
									int allCartProductId = -1;
									/* ProductDAO dao = new ProductDAO(); */
									int getProductId = 1;
						
						       		 for (CartItemBean item : cartAllItemList) {  
							       			 img = item.getProduct().getImage();
							                 if ( img == null ){
							              	   img = "https://placehold.jp/480x640.png";
							                 } else {
							              	   img = "image/" + img;
							                 } 
						      			     int price =  item.getProduct().getPrice();
								  		     int sizeId = item.getSizeId();
								  		     int quantity = item.getQuantity(); 
								  		     totalAmount = totalAmount + (price * quantity); 
								  		     String formattedItemPrice = String.format("%,d", price); 
								 %>
			  		     	  		    
							      	  <div class="right">
							      	  	  <div class="order_box">
							      	  	  <figure class="image"><img src="<%= img %>" alt="商品画像"></figure>
					  						 <div class="text">
						  						 <p class="product">商品名： <%= item.getProduct().getProductName() %></p>
						  						 <p class="size">サイズ：<%= st.sizeText(sizeId) %></p>
						  						 <p class="quantity">数量：<%= quantity %></p>	
						  						 <p class="price">&yen; <%= formattedItemPrice %> 税込</p>
					  						 </div>
							      	  	  </div>
					  						 
								 <% } %>	        
						        <input type="hidden" name="order" value="allCartItems"> 
			  					<input type="hidden" name="totalAmount" value="<%= totalAmount %>">
	     				 <% } %> 
	    				  </div>
						<div class=delivery>
						<% AddressBean address = (AddressBean)request.getAttribute("address");//メイン住所 %>
							<label class="bold">配送先</label><br>
							<input class="check" type="checkbox" name="address" checked value='<%= (address.getPrefectures() + address.getAddress()) %>'><label><%= (address.getPrefectures() + address.getAddress())%></label>	
						</div>
					</div>
				<div class=changeDelivery>
					<span class="bold">別の配送先を指定する</span><br>
				<% List <AddressBean> addAddresses = ( ArrayList<AddressBean> )request.getAttribute("addAddresses");//サブ住所 
				   for( AddressBean add : addAddresses) { %> 	
					<input class="check" type="checkbox" name="address" value="<%= add.getPrefectures() + add.getAddress() %>"><label class="sub_address"><%= add.getPrefectures() + add.getAddress() %> </label><br>
				<% } %> 
				
				<div class="form_box">
			        	<label id="AddressModalOpen">新しい配送先住所を追加</label>
			     </div>
									
									
									  
				<div class="total">
					<span class="bold">ご注文金額</span>
					<% String formattedTolalAmount = String.format("%,d", totalAmount); %>
					<p>合計金額 &yen;<%= formattedTolalAmount %> 税込</p>
				</div>
			</div>
			
			<div class="submit_box"> 
				<input class="submit" type="submit" value="購入確定">
				</form>
				<br>
				<a class="back" href="ProductListServlet">商品一覧に戻る</a>
				  <div id="AddressEasyModal" class="modal">
									    <div class="formModalContent">
									      <div class="modal-body">
									        <form action="OrderConfilmServlet" method="get">
													<label>郵便番号</label><br><input type="text" id="postcode" name="postcode" placeholder="例) 0000000" required><br> 
													<label>都道府県</label><br><select id="prefectures" name="prefectures" required>
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
												<label>住所</label><br> 
												<input type="text" class="address_input" name="address" placeholder="例) 〇〇市〇〇区〇丁目" required></input><br>
											    <% if(order.equals("order")){//もし直接購入だったら %>
											   
											   	 	<input type="hidden" name="productId" value="${orderProductId}">
											    	<input type="hidden" name="sizeId" value="${sizeId}">
											    	<input type="hidden" name="quantity" value="${quantity}">
											    	<input type="hidden" name="order" value="order">
											    <% }else if(order.equals("singleCartItem")){//もしカート内から一つのアイテムのみ購入だったら %>
											    	<input type="hidden" name="cartItemId" value="<%= adressCartItemId %>">
											    <% } %>
											    <input type="hidden" name="addAddress" value="address">
											    <button class="address_submit" type="submit">登録</button><br>
											    <p id="AddressModalClose">キャンセル</p>
											</form>
											
											    
									      </div>
									    </div>
									  </diV>
			</div>
	</div>
	</main>
	<%@ include file="includes/footer.jsp" %>
	<script>
			const checkGet = document.getElementsByName('address');
			  checkGet.forEach((check) => {
				    check.addEventListener('click', () => {
				      if(check.checked) {
				        checkGet.forEach((allChecks) => {
				          allChecks.checked = false;
				        });
				        check.checked = true;
				      }
				    });
				  });

			  const buttonOpen = document.getElementById("AddressModalOpen");
				const modal = document.getElementById("AddressEasyModal");
				const buttonClose = document.getElementById('AddressModalClose');
			
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
				  }} 


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