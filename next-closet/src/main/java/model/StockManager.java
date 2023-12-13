package model;

import java.sql.SQLException;

import model.dao.ProductDAO;

public class StockManager {
	
		ProductDAO dao = new ProductDAO();
	
	//単品の商品の在庫と購入数を比較して、たりたらtrue たりなかったらfalse
	public boolean productStock(int productId , int sizeId , int quantity)  {
		
		boolean stock = true;
		int stockQuantity = 0;
		try {
		stockQuantity = dao.getStockItem(productId , sizeId );
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if( quantity - stockQuantity < 0 ) {
			stock = false;
		}
		
		return stock;
	}
	
	//在庫数 - 購入数　を　在庫数に更新するメソッド
	public void decrementStock(int productId , int sizeId , int buyQuantity , String delivery_address) {
		
		int  nowStockQuantity = -1;
		try {
			 nowStockQuantity = dao.nowStockQuantity(productId , sizeId );
			 int quantity = nowStockQuantity - buyQuantity;
			 dao.changeStock( quantity , productId , sizeId , delivery_address);
		} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
		}
	
	}
	
	
}

