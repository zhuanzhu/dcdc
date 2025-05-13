package com.egeo.components.product.enums;

public enum JdPriceType {

	IncreaseFixedValue(1,"增加固定值"),
	IncreaseFixedRatio(2,"增加固定比例"),
	FixedPrice(3,"固定价格");
	int status;
	String comment;
	
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public boolean equal(Integer other) {
		if(other!=null) {
			return other.intValue()==status;
		}
		return false;
		
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	JdPriceType(int status,String comment) {  
        this.status = status;  
        this.comment = comment;  
    }
	

    public static boolean isValidate(Integer other) {
    	if(other!=null) {

    		JdPriceType[] all_type = JdPriceType.values();
	    	for(JdPriceType one:all_type){
	    		if(one.getStatus()==other.intValue()){
	    			return true;
	    		}
	    	}
    	}
    	return false;
    }
}
