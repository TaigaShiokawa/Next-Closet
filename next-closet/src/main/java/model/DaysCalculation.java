package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DaysCalculation {
	
	
	//当日かチェックする
	public boolean getTodayOrder(Date orderDate){	
		boolean date = false;
		
		String orderDateStr = String.valueOf(orderDate);
		LocalDate now = LocalDate.now(); 											//今日の日付を取得
		LocalDate order = LocalDate.parse(orderDateStr,DateTimeFormatter.ISO_DATE);   //deadlineをLocalDate型へ変換
		long longDays = ChronoUnit.DAYS.between(now , order);            		    //今日から期日までの日数
		
		String daysStr = String.valueOf(longDays); 									//今日から期日までの日数をString型へ変換
		int days = Integer.parseInt(daysStr);									    //今日から期日までの日数をint型へ変換
		
	    if( days == 0 ){
	    	date = true;
		}
		return date;
	}
	
	public boolean getYesterdayOrder(Date orderDate){	
		
		boolean date = false;
		
		String orderDateStr = String.valueOf(orderDate);
		LocalDate now = LocalDate.now(); 											//今日の日付を取得
		LocalDate order = LocalDate.parse(orderDateStr,DateTimeFormatter.ISO_DATE);   //deadlineをLocalDate型へ変換
		long longDays = ChronoUnit.DAYS.between(now , order);            		    //今日から期日までの日数
		
		String daysStr = String.valueOf(longDays); 									//今日から期日までの日数をString型へ変換
		int days = Integer.parseInt(daysStr);									    //今日から期日までの日数をint型へ変換
		
	    if( days == -1 ){
	    	date = true;
		}
		return date;

	}
	
}