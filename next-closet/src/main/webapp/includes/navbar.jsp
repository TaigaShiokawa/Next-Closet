<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// Icons by Bootstrap (https://icons.getbootstrap.com/) - licensed under MIT (https://opensource.org/licenses/MIT)
       String searchName = (String)request.getAttribute("searchName");
	    String searchText = "" ;
	    if( searchName != null ){
	    	searchText = searchName;
	    }
	    %>
<!-- Icons by Bootstrap (https://icons.getbootstrap.com/) - licensed under MIT (https://opensource.org/licenses/MIT)  -->
<header>
        <div class="header_inner">
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
								<li><a class="nav_text" href="ProductListServlet">商品一覧</a></li>
								<li class="icon">
	                        		<a  href="MypageServlet?<%=loginUser.getUserId()%>"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
		                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/></svg><br>
		                                <label>マイページ</label>
	                                </a>                            
                      			</li>
                      			<li class="icon">
                            		<a  href="AddToCartServlet?<%=loginUser.getUserId()%>"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-check" viewBox="0 0 16 16">
	                                	<path d="M11.354 6.354a.5.5 0 0 0-.708-.708L8 8.293 6.854 7.146a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z"/>
	                                	<path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/><br></svg>
	   		                            <label>カート</label>
        		                    </a>
                      		  </li>
								<!-- <li><a class="nav_text" href="LogoutServlet">logout</a></li> -->
								<div class="search">
				                    	<form action="ProductListServlet" method="get">
				                        <input type="text" name="searchName" class="search_box" value="<%= searchText %>" placeholder="キーワードで商品名を検索">
				                        <input class="sarch_btn" type="submit" value="検索">
				                    	</form>
				                </div>
			
								<!-- ログインしていない -->
								 <% } else { %>
								 <li><a class="nav_text" href="ProductListServlet">商品一覧</a></li> 
								<li>
									<a class="nav_text" href="login.jsp" class="nav_text">login</a>
								</li>
								<li>
								　  <div class="form_box">
							        	<button id="formModalOpen">お問い合わせ先</button>
							        </div>
									  <div id="formEasyModal" class="modal">
									    <div class="formModalContent">
									      <div class="modal-header">
									        <span class="formModalClose">×</span>
									      </div>
									      <div class="modal-body">
									        <p>お問い合わせ先</p><br>
									        <p>078-123-1234</p>
									        <p>next_closet@co.jp</p>
									      </div>
									    </div>
									  </diV>
		                        </li>
		                        <div class="search">
				                    	<form action="ProductListServlet" method="get">
				                        <input type="text" name="searchName" class="search_box" value="<%= searchText %>" placeholder="キーワードで商品名を検索">
				                        <input class="sarch_btn" type="submit" value="検索">
				                    	</form>
				                </div>
						 <% } %>
					</ul>
				</nav>               
            </div>
        </div>
    </header>
	
	
	
