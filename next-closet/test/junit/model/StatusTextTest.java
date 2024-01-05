package junit.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.StatusText;

class StatusTextTest {

	StatusText st = new StatusText();

	@Test
	public void productStatusTextTest() {
		
		String t = st.productStatusText(true);
		String f = st.productStatusText(false);
	    	
	    assertEquals(t,"販売中");
	    assertEquals(f,"削除済み");
	}
	
	@Test
	public void userStatusTextTest() {
		
		String t = st.userStatusText(true);
		String f = st.userStatusText(false);
	    	
	    assertEquals(t,"");
	    assertEquals(f,"削除済み");
	}
	
	@Test
	public void adminStatusTextTest() {
		
		String t = st.adminStatusText(true);
		String f = st.adminStatusText(false);
	    	
	    assertEquals(t,"既存");
	    assertEquals(f,"削除済み");
	}
	
	@Test
	public void genderTextTest() {
		
		String man = st.genderText(1);
	    assertEquals(man,"MAN");
	    assertNotEquals(man,1);
	    
	    String woman = st.genderText(2);
	    assertEquals(woman,"WOMAN");
	    assertNotEquals(woman,2);
	}
	
	
	
}