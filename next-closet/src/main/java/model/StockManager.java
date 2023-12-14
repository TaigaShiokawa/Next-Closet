package model;

import java.sql.SQLException;
import java.util.List;

import model.bean.CartItemBean;
import model.dao.CartDAO;
import model.dao.ProductDAO;

public class StockManager {
	
		ProductDAO dao = new ProductDAO();
	
	//単品の商品の在庫と購入数を比較して、たりたらtrue たりなかったらfalse
	public boolean productStock(int productId , int sizeId , int quantity)  {
		
		boolean stock = true;
		int stockQuantity = 0;
		try {
		stockQuantity = dao.getStockItem( productId , sizeId );
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(stockQuantity -  quantity  < 0 ) {
			stock = false;
		}
		
		return stock;
	}
	
	//在庫数 - 購入数　を　在庫数に更新するメソッド(単品)
	public void decrementStock(int productId , int sizeId , int buyQuantity ) {
		
		int  nowStockQuantity = -1;
		
		try {
			 nowStockQuantity = dao.nowStockQuantity(productId , sizeId );
			
			 int quantity = nowStockQuantity - buyQuantity;
			 dao.changeStock( productId , sizeId , quantity );
		} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
		}
	
	}
	
	//ordet_itemsテーブルに追加（単品）
	public void orderRegistration(int productId , int sizeId , int buyQuantity , int userId , int totalAmount , String delivery_address) {
		
		try {
			 dao.orderRegistration(productId , sizeId , buyQuantity , userId ,totalAmount , delivery_address);
			 
		} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
		}
	
	
	}
	
	//カート全ての在庫数 - 購入数　を　在庫数に更新するメソッド
		public void cartDecrementStock( int userId ) {
			
			int nowStockQuantity = -1;
			int productId = -1;
			int sizeId = -1;
			int buyQuantity = -1;
			
			CartDAO cartDao = new CartDAO();
			List <CartItemBean> cartList = null;
			try {
			cartList = cartDao.getCartItems(userId);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			 for (CartItemBean item : cartList) {
				 
					 productId = item.getProductId();
					 sizeId = item.getSizeId();
					 buyQuantity = item.getQuantity();
				 
				 try {
					 nowStockQuantity = dao.nowStockQuantity(productId , sizeId );
					 int quantity = nowStockQuantity - buyQuantity;
					 dao.changeStock( productId , sizeId , quantity );
				} catch (SQLException | ClassNotFoundException e) {

						e.printStackTrace();
				}

			 }
		}
		
		public void cartRegistration( int userId , String delivery_address) { //カート内のアイテムを全て登録
			int totalAmount = 0;
			int price = -1;
			
			int productId = -1;
			int sizeId = -1;
			int buyQuantity = -1;
			
			CartDAO cartDao = new CartDAO();
			List <CartItemBean> cartList = null;
			try {
			cartList = cartDao.getCartItems(userId);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			 for (CartItemBean item : cartList) {
				 
				 productId = item.getProductId();
				 sizeId = item.getSizeId();
				 buyQuantity = item.getQuantity();
				 price = item.getProduct().getPrice();
				 totalAmount += (buyQuantity * price);
				 
				 System.out.println(productId);
				 System.out.println(sizeId);
					
					try {
						 dao.orderRegistration(productId , sizeId , buyQuantity , userId ,totalAmount , delivery_address);
						 
					} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
					}
				 
			 }
			
			
			
		
		}

	
}

