<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- 一般ユーザーと管理者のsessionを使用するためのimportが必要 -->

<nav>
<a href="product-list.jsp"><h1><li>next closet...</li></h1></a>
<ul>
<!-- 一般ユーザーの場合 -->

<!-- ログイン済みの場合 -->
<% if(loginUser != null) { %>
<li><a href="product-list.jsp">商品一覧</a></li>
<li><a href="mypage.jsp">マイページ<!-- 画像とテキストで縦並び --></a></li>
<li><a href="cart.jsp">カート<!-- 画像とテキストで縦並び --></a></li>
<li><a href="LogoutServlet">ログアウト</a></li>
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
