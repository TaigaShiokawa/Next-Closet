package junit.model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import model.SizeText;

class SizeTextTest {

	SizeText st = new SizeText();

	@Test
	public void sizeTextTest() {
		
		String sSize = st.sizeText(1);
		String mSize = st.sizeText(2);
		String lSize = st.sizeText(3);
	    	
	    assertEquals(sSize,"S");
	    assertEquals(mSize,"M");
	    assertEquals(lSize,"L");
	}
	
	
	@Test
	public void failureSizeTextTest() {
		
		String sSize = st.sizeText(1);
		String mSize = st.sizeText(2);
		String lSize = st.sizeText(3);
	    	
	    assertNotEquals(sSize,"1");
	    assertNotEquals(mSize,"2");
	    assertNotEquals(lSize,"3");
	}

}
