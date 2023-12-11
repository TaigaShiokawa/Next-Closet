<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>next closet ...</title>
    <link rel="stylesheet" href="product-list.css">
    <%
    List <Product> productList = (ArrayList <Product>)request.getAttribute("productList");
    List <String> categoryList = (ArrayList <String>)request.getAttribute("categoryList");
    %>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<header>
        <div class="header_innner">
            <div class="header_left">
                <h1>
                    next closet...
                </h1>
            </div>
            <div class="header_right">
                <nav>
                    <ul class="nav_list">
                        <li>
                            <a href="#">logout</a>
                        </li>
                        <li>
                            <a href="#"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
                              </svg></a>
                        </li>
                        <li>
                            <a href="#"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-check" viewBox="0 0 16 16">
                                <path d="M11.354 6.354a.5.5 0 0 0-.708-.708L8 8.293 6.854 7.146a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z"/>
                                <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                              </svg></a>
                        </li>
                    </ul>
                </nav>
                <div class="search">
                    <form action="/search" method="post">
                        <input type="text" class="search_box" placeholder="キーワードで商品名を検索
                        ">
                        <input class="sarch_btn" type="submit" value="検索">
                    </form>
                </div>
            </div>
        </div>
       
    </header>
    <main>
        <div class = "container" >
            <div class="side_bar">
                <ul id="category_list">
                    <li class="gender list_top"><span>ALL</span>
                        <ul class="category">
                            <% for ( String colums : categoryList){ %>
                             <li><a href="/sort"><%= colums %></a></li>
                             <% } %>
                        </ul>
                    </li>
                    <li class="gender"><span>MAN</span>
                        <ul class="category">
                             <% for ( String colums : categoryList){ %>
                             <li><a href="/sort"><%= colums %></a></li>
                             <% } %>
                        </ul>
                    </li>
                    <li class="gender"><span>WOMAN</span>
                        <ul class="category">
 							<% for ( String colums : categoryList){ %>
                             <li><a href="/sort"><%= colums %></a></li>
                            <% } %>
                        </ul>
                    </li>
                </ul>
            </div>
                <div class="content wrapper">
                    <h1 class="page-title">Product</h1>
                    <ul class="product-list">
                      <li>
                        <a href="#">
                          <img src="../img/1.jpg" alt="">
                          <p>プロダクトタイトルプロダクトタイトル</p>
                          <p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <img src="../img/2.jpg" alt="">
                          <p>プロダクトタイトルプロダクトタイトル</p>
                          <p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <img src="../img/3.jpg" alt="">
                          <p>プロダクトタイトルプロダクトタイトル</p>
                          <p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <img src="../img/4.jpg" alt="">
                          <p>プロダクトタイトルプロダクトタイトル</p>
                          <p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <img src="../img/5.jpg" alt="">
                          <p>プロダクトタイトルプロダクトタイトル</p>
                          <p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <img src="../img/6.jpg" alt="">
                          <p>プロダクトタイトルプロダクトタイトル</p>
                          <p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <img src="../img/7.jpg" alt="">
                          <p>プロダクトタイトルプロダクトタイトル</p>
                          <p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <img src="../img/1.jpg" alt="">
                          <p>プロダクトタイトルプロダクトタイトル</p>
                          <p>&yen;99,999 +tax</p>
                        </a>
                      </li>
                    </ul>
            </div>
        </div>
    </main>
    <footer>
        <p>
            <small>
                &copy; hogehoge
            </small>
        </p>
    </footer>
</body>
</html>