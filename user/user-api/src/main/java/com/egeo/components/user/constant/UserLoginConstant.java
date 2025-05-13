package com.egeo.components.user.constant;

public enum UserLoginConstant {
	
	LOGIN_TYPE_FREE("free","免登陆"),
	LOGIN_TYPE_NORMAL("normal","普通登陆"),
	LOGIN_TYPE_COUPON_UNIT("coupon_unit","优惠券unit 二维码"),
	LOGIN_TYPE_PUO("puo","商品订单二维码"),
	LOGIN_TYPE_PUC("puc","商品购物车二维码"),
	LOGIN_TYPE_SU("su","商品二维码"),
	LOGIN_TYPE_COUPON("coupon","优惠券二维码"),
	LOGIN_TYPE_COUPON_GROUP("coupon_group","优惠券组二维码"),
	LOGIN_TYPE_MAIN_STORE("main_store","总店二维码"),
	LOGIN_TYPE_BRANCH("branch","分店二维码");
	String status;
	String comment;
	
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	UserLoginConstant(String status,String comment) {  
        this.status = status;  
        this.comment = comment;  
    }
	
	/**
	 * 翻译
	 * @param status
	 * @return
	 */
	public static String translate(String status){
		for(UserLoginConstant o:UserLoginConstant.values()){
			if(o.status.equals(status)){
				return o.getComment();
			}
		}
		return "";
	}
	
}
