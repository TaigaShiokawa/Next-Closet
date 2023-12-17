<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
</head>
<body>
	<h1>新規登録</h1>
	<form action="ProductAddServlet" method="post" enctype="multipart/form-data">
	<label for="p_name">商品名</label>
		<input type="text" id="p_name" name="productName" placeholder="商品名" required>
		<span id="p_name_count">0/65</span><br>
		
		<label for="p_description">商品説明</label>
		<textarea id="p_description" name="description" placeholder="商品説明" required></textarea>
		<span id="p_description_count">0/1000</span><br>
		
		<label for="p_category">カテゴリー</label>
		<select id="p_category" name="category">
		<option>カテゴリーを選択</option>
		<option value="tops">tops</option>
		<option value="bottoms">bottoms</option>
		<option value="shoes">shoes</option>
		</select><br>
		
		<label for="p_gender">性別</label>
		<select id="p_gender" name="gender">
		<option>性別を選択</option>
		<option value="1">WOMAN</option>
		<option value="2">MAN</option>
		</select><br>
		
		<label for="p_price">金額</label>
		<input type="text" id="p_price" name="price" placeholder="0" required><br>
		
		<label for="p_image">画像</label>
		<input type="file" id="p_image" name="image" required><br>
		
		<label for=s_size>Sサイズ 在庫数</label>
		<input type="number" id="s_size" name="s_size_inventory" placeholder="0" required><br>
		<label for=m_size>Mサイズ 在庫数</label>
		<input type="number" id="m_size" name="m_size_inventory" placeholder="0" required><br>
		<label for=l_size>Lサイズ 在庫数</label>
		<input type="number" id="l_size" name="l_size_inventory" placeholder="0" required><br>
		
		<input type="submit" value="登録">
		</form>
		
		<script>
		document.getElementById('p_name').addEventListener('input', function() {
	        var textLength = this.value.length;
	        var nameCounter = document.getElementById('p_name_count');
	        document.getElementById('p_name_count').textContent = textLength + '/65'; 

	        if(textLength > 65) {
	        	nameCounter.style.color = 'red';
		    } else {
			    nameCounter.style.color = 'initial';
			}
	       
	    });

	    // 商品説明フィールドの文字数を追跡
	    document.getElementById('p_description').addEventListener('input', function() {
	        var textLength = this.value.length;
	        var descriptionCounter = document.getElementById('p_description_count');
	        document.getElementById('p_description_count').textContent = textLength + '/1000';

	        if(textLength > 1000) {
	        	descriptionCounter.style.color = 'red';
		    } else {
		    	descriptionCounter.style.color = 'initial';
			}
	    });
		</script>
</body>
</html>