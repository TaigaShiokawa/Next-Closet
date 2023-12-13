package model;

import java.sql.SQLException;

import model.dao.ProductDAO;

public class StockManager {
	
	//単品の商品
	public boolean productStock(int productId , int sizeId , int quantity)  {
		
		ProductDAO dao = new ProductDAO();
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
	
	
}

