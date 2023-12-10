<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav>
<a href="product-list.jsp"><h1><li>next closet...</li></h1></a>
<ul>

<!-- 一般ユーザー ログイン済み -->
<% if(loginUser != null) { %>
<li><a href="product-list.jsp">商品一覧</a></li>
<li><a href="mypage.jsp">マイページ<!-- 画像とテキストで縦並び --></a></li>
<li><a href="cart.jsp">カート<!-- 画像とテキストで縦並び --></a></li>
<li><a href="LogoutServlet">ログアウト</a></li>

<!-- 管理者 ログイン済み -->
<%-- <% } else if (admin != null) { %>
<li><a href="product-list.jsp">商品一覧</a></li>
<li><a href="LogoutServlet">ログアウト</a></li>
<li><a href="#">ユーザー編集</a></li>
<li><a href="#">管理者編集</a></li>
<li><a href="#">購入通知</a></li> --%>

<!-- ログインしていない -->
<% } else { %>
<li><a href="product-list.jsp">商品一覧</a></li>
<li><a href="login.jsp">ログイン</a></li>
<form action="" metod="">
<input type="text" name="">
<button type="submit">検索</button>
</form>
<% } %>
</ul>
</nav>
