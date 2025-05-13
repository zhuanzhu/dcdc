package com.egeo.components.pay.enums;

public enum OrderConstant {

	/**
	 * 订单状态
	 * (10-已取消;
	 * 0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;
	 * 5-预退款;6已退款;7-部分发货;8-)
	 */
	ORDER_STATUS_UNPAY(0,"待付款"),
	ORDER_STATUS_PAYED(1,"已付款"),
	ORDER_STATUS_DELIVERED(2,"已发货"),
	ORDER_STATUS_RECEIVED_GOODS(3,"已收货"),
	ORDER_STATUS_RECEIVED_FINISHED(4,"已完成"),
	ORDER_STATUS_PRE_RETURN_CASH(5,"预退款"),
	ORDER_STATUS_RETURN_CASH_FINISHED(6,"已退款"),
	ORDER_STATUS_DELIVERED_PART_GOODS(7,"部分发货"),
	ORDER_STATUS_EXCHAGING_GOODS(8,"换货中"),
	ORDER_STATUS_CANCELED(10,"已取消"),
	
	// 发货状态，0：未发货 10：部分发货 20：已发货
	ORDER_DELIVERY_STATUS_NO_DELIVERY(0,"未发货"),
	ORDER_DELIVERY_STATUS_part_DELIVERY(10,"部分发货"),
	ORDER_DELIVERY_STATUS_DELIVERED(20,"已发货"),
	
	// 订单确认状态  0:未确认，1:已确认，2:已取消 3:已完成
	ORDER_CONFIRM_STATUS_NO_CONFIRM(0,"未确认"),
	ORDER_CONFIRM_STATUS_ALREADY_CONFIRM(1,"已确认"),
	ORDER_CONFIRM_STATUS_ALREADY_CANCEL(2,"已取消"),
	ORDER_CONFIRM_STATUS_ALREADY_ACCOMPLISH(3,"已完成");
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

	OrderConstant(int status,String comment) {  
        this.status = status;  
        this.comment = comment;  
    }
	
	/**
	 * 翻译
	 * @param status
	 * @return
	 */
	public static String translate(int status){
		for(OrderConstant o:OrderConstant.values()){
			if(o.status==status){
				return o.getComment();
			}
		}
		return "";
	}
    
}
