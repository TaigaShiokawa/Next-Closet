package model;

public class SizeText {
	
	//size_idをテキストに置換するメソッド
    public String sizeText ( int sizeId ) {
    	
    	String sizeText = "";
    	
    	if (sizeId == 1) {
    		sizeText="S";
    	}else if( sizeId == 2) {
    		sizeText = "M";
    	}else if(sizeId == 3 ) {
    		sizeText = "L";
    	}
    	return sizeText;
    }
}