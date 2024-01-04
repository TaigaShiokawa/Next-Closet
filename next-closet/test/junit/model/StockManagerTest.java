package junit.model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import model.StockManager;

class StockManagerTest {

	StockManager sm = new StockManager();


	
	@Test
	public void productStockTrueTest() {
		
		boolean test = false;
		try {
	    test = sm.productStock(1,1,1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	    	
	    assertEquals(test,true);
	}
	
	@Test
	public void productStockFalseTest() {
		
		boolean test = false;
		try {
	    test = sm.productStock(1,1,100);
		}catch(Exception e) {
			e.printStackTrace();
		}
	    	
	    assertEquals(test,false);
	}
	
	//decrementStock,orderRegistration ,cartDecrementStock cartRegistrationはDBが実際動くため未実装
	

	

}
