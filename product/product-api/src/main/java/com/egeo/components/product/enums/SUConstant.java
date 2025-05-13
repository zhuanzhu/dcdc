package com.egeo.components.product.enums;

public enum SUConstant {

	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	SU_STATUS_PAYED(1,"待上架"),
	SU_STATUS_DELIVERED(2,"审核中"),
	SU_STATUS_RECEIVED_GOODS(3,"已上架"),
	SU_STATUS_RECEIVED_FINISHED(4,"已下架"),
	SU_STATUS_PRE_RETURN_CASH(5,"审核未通过");
	int status;
	String comment;
	
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	SUConstant(int status,String comment) {  
        this.status = status;  
        this.comment = comment;  
    }
	
	/**
	 * 翻译
	 * @param status
	 * @return
	 */
	public static String translate(int status){
		for(SUConstant o:SUConstant.values()){
			if(o.status==status){
				return o.getComment();
			}
		}
		return "";
	}
    
}
