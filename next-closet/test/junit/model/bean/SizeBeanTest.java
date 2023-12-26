package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.bean.CartItemBean;

class SizeBeanTest {

	@Test
    public void testCartItemBean() {
       
        CartItemBean cartItem = new CartItemBean();

        // テストデータ
        int sizeId = 1;
    	String sizeName = "S";
    	
        // セッターを使って値を設定
    	cartItem.setSizeId(sizeId);
    	cartItem.setSizeName(sizeName);
    	
        // ゲッターを使って値を取得し、設定した値と一致することを確認
        assertEquals(sizeId, cartItem.getSizeId());
        assertEquals(sizeName, cartItem.getSizeName());


    }
}
