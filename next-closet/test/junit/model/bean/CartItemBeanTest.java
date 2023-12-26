package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.bean.CartItemBean;

class CartItemBeanTest {

	@Test
    public void testCartItemBean() {
       
        CartItemBean cartItem = new CartItemBean();

        // テストデータ
        int cartItemId = 1;
    	int userId = 1;
    	int productId = 1;
    	int quantity = 1;
    	
        // セッターを使って値を設定
    	cartItem.setCartItemId(cartItemId);
    	cartItem.setUserId(userId);
    	cartItem.setProductId(productId);
    	cartItem.setQuantity(quantity);
    	
        // ゲッターを使って値を取得し、設定した値と一致することを確認
        assertEquals(cartItemId, cartItem.getCartItemId());
        assertEquals(userId, cartItem.getUserId());
        assertEquals(productId, cartItem.getProductId());
        assertEquals(quantity, cartItem.getQuantity());

    }
}
