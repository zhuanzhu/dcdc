package com.egeo.components.stock.constant;

public enum StockConstant {

	/**
	 * 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 签收 101进货  102出货  103库存同步
	 */
	STOCK_STATUS_ORDERCREATE(1,"提交订单"),
	STOCK_STATUS_PAYED(2,"支付"),
	STOCK_STATUS_CANCEL_ORDER_NO_PAYED(3,"取消订单（未支付）"),
	STOCK_STATUS_CANCEL_ORDER_PAYED(4,"取消订单（已支付）"),
	STOCK_STATUS_STOCK(101,"进货"),
	STOCK_STATUS_SHIPMENT(102,"出货"),
	STOCK_STATUS_SYNC_STOCK(103,"库存同步"),
	STOCK_STATUS_CONTACT_STOCK(104,"共用库存"),
	STOCK_STATUS_RECEIVE(5,"签收");
	Integer status;
	String comment;
	
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	StockConstant(Integer status,String comment) {  
        this.status = status;  
        this.comment = comment;  
    }
	
	/**
	 * 翻译
	 * @param status
	 * @return
	 */
	public static String translate(Integer status){
		for(StockConstant o:StockConstant.values()){
			if(o.status==status){
				return o.getComment();
			}
		}
		return "";
	}
    
}
