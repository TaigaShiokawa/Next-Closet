package junit.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import model.DaysCalculation;

class DaysCalculationTest {

	DaysCalculation dc = new DaysCalculation();

	@Test
	public void getTodayOrderFalseTest() {
		
		boolean date = false;
		String orderDateStr = String.valueOf("2024-01-01");
		LocalDate now = LocalDate.now(); 				
		LocalDate order = LocalDate.parse(orderDateStr,DateTimeFormatter.ISO_DATE); 
		long longDays = ChronoUnit.DAYS.between(now , order);
		String daysStr = String.valueOf(longDays); 								
		int days = Integer.parseInt(daysStr);									    
		
	    if( days == 0 ){
	    	date = true;
		}
	    
	    assertEquals(date,false);
	}
	
	@Test
	public void getTodayOrderTrueTest() {
		
		boolean date = false;
		String orderDateStr = String.valueOf(LocalDate.now());
		LocalDate now = LocalDate.now(); 				
		LocalDate order = LocalDate.parse(orderDateStr,DateTimeFormatter.ISO_DATE); 
		long longDays = ChronoUnit.DAYS.between(now , order);
		String daysStr = String.valueOf(longDays); 								
		int days = Integer.parseInt(daysStr);									    
		
	    if( days == 0 ){
	    	date = true;
		}
	    
	    assertEquals(date,true);
	}
	
	@Test
	public void getYesterdayOrderFalseTest() {
		
		boolean date = false;
		String orderDateStr = String.valueOf("2024-01-01");
		LocalDate now = LocalDate.now(); 				
		LocalDate order = LocalDate.parse(orderDateStr,DateTimeFormatter.ISO_DATE); 
		long longDays = ChronoUnit.DAYS.between(now , order);
		String daysStr = String.valueOf(longDays); 								
		int days = Integer.parseInt(daysStr);									    
		
	    if( days == 0 ){
	    	date = true;
		}
	    
	    assertEquals(date,false);
	}
	
	@Test
	public void getYesterdayOrderTrueTest() {
		

		boolean date = false;
		String orderDateStr = String.valueOf("2024-01-03");
		LocalDate now = LocalDate.now(); 				
		LocalDate order = LocalDate.parse(orderDateStr,DateTimeFormatter.ISO_DATE); 
		long longDays = ChronoUnit.DAYS.between(now , order);
		String daysStr = String.valueOf(longDays); 								
		int days = Integer.parseInt(daysStr);
		
	    if( days == -1 ){
	    	date = true;
		}
	    
	    assertEquals(date,true);
	}

}
