package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import model.bean.OrderBean;

class OrderBeanTest {

	@Test
    public void testOrderBean() {
       
        OrderBean order = new OrderBean();

        // テストデータ
       int orderItemId = 1;
       int productId = 1;
       int quantity = 1;
       int sizeId = 1;
       int userId = 1;
       int totalAmount = 1;
       Date orderDate =  new Date(System.currentTimeMillis());
       String deliveryAddress = "兵庫県神戸市";
    	
        // セッターを使って値を設定
        order.setOrderItemId(orderItemId);
    	order.setProductId(productId);
    	order.setQuantity(quantity);
    	order.setSizeId(sizeId);
    	order.setUserId(userId);
    	order.setTotalAmount(totalAmount);
    	order.setOrderDate(orderDate);
    	order.setDeliveryAddress(deliveryAddress);
    	
        // ゲッターを使って値を取得し、設定した値と一致することを確認
        assertEquals(orderItemId, order.getOrderItemId());
        assertEquals(productId, order.getProductId());
        assertEquals(quantity, order.getQuantity());
        assertEquals(sizeId, order.getSizeId());
        assertEquals(userId, order.getUserId());
        assertEquals(totalAmount, order.getTotalAmount());
        assertEquals(orderDate, order.getOrderDate());
        assertEquals(deliveryAddress, order.getDeliveryAddress());
        
        // 失敗ケース
        assertEquals(2, order.getOrderItemId());
        assertEquals(2, order.getProductId());
        assertEquals(2, order.getQuantity());
        assertEquals(2, order.getSizeId());
        assertEquals(2, order.getUserId());
        assertEquals(2, order.getTotalAmount());
        assertEquals("2023/12/25", order.getOrderDate());
        assertEquals("大阪府", order.getDeliveryAddress());

    }
}
