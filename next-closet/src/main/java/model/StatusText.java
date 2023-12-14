package model;

public class StatusText {
	
	//size_idをテキストに置換するメソッド
    public String statusText ( boolean status ) {
    	
    	String text = "";

    	if ( status == true) {
    		text = "販売中";
    	}else if( status == false) {
    		text = "削除済み";
    	}
    	return text;
    }
}