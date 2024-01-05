package model;

public class StatusText {

    public String productStatusText ( boolean status ) {
    	
    	String text = "";

    	if ( status == true) {
    		text = "販売中";
    	}else if( status == false) {
    		text = "削除済み";
    	}
    	return text;
    }
    
public String userStatusText ( boolean status ) {
    	
    	String text = "";

    	if ( status == true) {
    		text = "";
    	}else if( status == false) {
    		text = "削除済み";
    	}
    	return text;
    }

public String adminStatusText ( boolean status ) {
	
	String text = "";

	if ( status == true) {
		text = "既存";
	}else if( status == false) {
		text = "削除済み";
	}
	return text;
}

	public String genderText ( int genderId ) {
		
		String text = "";
		
		if( genderId == 1) {
			text = "MAN";
		} else if( genderId == 2) {
			text = "WOMAN";
		}
	
		return text;
	}

}