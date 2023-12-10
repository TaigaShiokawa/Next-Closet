<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フォーム</title>

</head>
<body>

    <h2>マイページ</h2>
        <table>
    <tr>
        <td><label for="name">お名前</label></td>
        <td><!-- Javaでユーザー名を表示 --></td>
    </tr>
    <tr>
        <td><label for="furigana">フリガナ</label></td>
        <td><!-- Javaでフリガナを表示 --></td>
    </tr>
    <tr>
        <td><label for="postalcode">郵便番号</label></td>
        <td><!-- Javaで郵便番号を表示 --></td>
    </tr>
    <tr>
        <td><label for="address">都道府県</label></td>
        <td><!-- Javaで都道府県を表示 --></td>
    </tr>
    <tr>
        <td><label for="city">住所</label></td>
        <td><!-- Javaで市区町村を表示 --></td>
    </tr>
    <tr>
        <td><label for="phone">電話番号</label></td>
        <td><!-- Javaで電話番号を表示 --></td>
    </tr>
    <tr>
        <td><label for="email">メールアドレス</label></td>
        <td><!-- Javaでメールアドレスを表示 --></td>
    </tr>
</table>

        <p>
            <button type="submit"><a href="mypage-edit.jsp">変更</a></button>
            <button type="submit"><a href="order-history.jsp">購入履歴</a></button>
        </p>
        
        <a href="product-list.jsp">商品一覧へ戻る</a>

</body>
</html>
