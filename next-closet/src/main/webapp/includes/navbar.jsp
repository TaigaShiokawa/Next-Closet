<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header>
        <div class="header_innner">
            <div class="header_left">
                <h1 id="site-title">
                    <a href = "ProductListServlet" >next closet...</a>
                </h1>
            </div>
            <div class="header_right">
                <nav>
                    <ul class="nav_list">
                       <% if(loginUser != null) { %> 
                    	 <!-- 一般ユーザー ログイン済み -->
								<li><a class="nav_text" href="product-list.jsp">商品一覧</a></li>
								<li class="icon">
	                        		<a  href="MypageServlet?<%=loginUser.getUserId()%>"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
		                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/></svg><br>
		                                <label>マイページ</label>
	                                </a>                            
                      			</li>
                      			<li class="icon">
                            		<a  href="cart.jsp"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-check" viewBox="0 0 16 16">
	                                	<path d="M11.354 6.354a.5.5 0 0 0-.708-.708L8 8.293 6.854 7.146a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z"/>
	                                	<path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/><br></svg>
	   		                            <label>カート</label>
        		                    </a>
                      		  </li>
								<li><a class="nav_text" href="LogoutServlet">logout</a></li>
								
								<!-- 管理者 ログイン済み -->
								<%-- <% } else if (admin != null) { %>
								<li><a href="product-list.jsp">商品一覧</a></li>
								<li><a href="LogoutServlet">ログアウト</a></li>
								<li><a href="#">ユーザー編集</a></li>
								<li><a href="#">管理者編集</a></li>
								<li><a href="#">購入通知</a></li> --%>
								
								<!-- ログインしていない -->
								 <% } else { %> 
								<li>
		                            <a class="nav_text" href="login.jsp" class="nav_text">login</a>
		                            <a href="#" class="nav_text">お問い合わせ</a> <!-- jsで処理するためどこにも遷移しない -->
		                        </li>
								 <div class="search">
				                    	<form action="/search" method="post">
				                        <input type="text" class="search_box" placeholder="キーワードで商品名を検索">
				                        <input class="sarch_btn" type="submit" value="検索">
				                    </form>
				                </div>
						 <% } %>
					</ul>
				</nav>               
            </div>
        </div>
    </header>
	
	
	
