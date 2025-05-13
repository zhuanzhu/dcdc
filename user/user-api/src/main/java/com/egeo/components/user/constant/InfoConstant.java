package com.egeo.components.user.constant;

public enum InfoConstant {

	ORDER_STATUS_PAYED_INFO_ID(1L,"订单支付状态变更消息id"),
	
	ORDER_CONFIRM_STATUS_INFO_ID(2L,"订单确认状态变更消息id"),
	
	ORDER_DELIVERY_STATUS_INFO_ID(3L,"订单物流状态变更消息id"),
	
	USER_FUBI_CHANGE_INFO_ID(4L,"用户积分变更消息id"),
	
	USER_PRAISE_FUBI_CHANGE_INFO_ID(5L,"用户点赞福豆变更消息id"),
	
	COMPANY_FUBI_CHANGE_INFO_ID(6L,"公司积分变更消息id"),
	
	USER_STATE_CHANGE_INFO_ID(7L,"用户状态变更消息id"),
	
	USER_DIMISSION_CHANGE_INFO_ID(8L,"用户离职状态变更消息id"),
	
	RELEVANCE_USER_INFO_ID(9L,"关联用户消息id"),
	
	UNBIND_USER_INFO_ID(10L,"解绑用户消息id"),
	
	SEND_COUPON_INFO_ID(11L,"发放优惠券消息id"),
	
	UPDATE_USER_PASSWORD_INFO_ID(12L,"用户修改密码消息id"),
	
	UPDATE_USER_PAY_PASSWORD_INFO_ID(13L,"用户修改支付密码消息id"),
	
	SIGN_ACTIVITY_INFO_ID(14L,"用户报名活动消息id"),
	
	FGJ_CANCEL_PAYED_ORDER(101L, "取消已付款订单"),
	FGJ_BACK_REFUND_CUSTOMER(102L, "后台主动退款"),
	FGJ_PENDING_ORDER(103L, "待处理订单提醒"),
	FGJ_GIVE_POINTS(104L, "企业发放积分"),
	FGJ_GIVE_FU_BEAN(105L, "企业发放点赞福豆"),
	FGJ_COMPANY_BALANCE_DECREASE(106L, "公司余额减少"),
	FGJ_COMPANY_BALANCE_RECHARGE(107L, "公司余额充值"),
	FGJ_ACCOUNT_STATUS_CHANGE(108L, "账号状态变更（失效）"),
	FGJ_ACCOUNT_DISABLE(109L, "账户失效的提醒"),
	FGJ_BIND_MOBILE(110L, "绑定手机号码"),
	FGJ_UNBIND_MOBILE(111L, "解绑手机号码"),
	FGJ_LOGIN_PASSWORD_CHANGE(112L, "登录密码变更"),
	FGJ_PAY_PASSWORD_CHANGE(113L, "支付密码变更"),
	FGJ_APPLY_ACTIVITY_SUCCESS(114L, "成功报名活动"),
	FGJ_NOTIFY_COMING_ACTIVITY(115L, "提醒参加将开始的活动"),
	FGJ_ACTIVITY_INFO_CHANGE(116L, "活动信息变更"),
	FGJ_CANCEL_ACTIVITY(117L, "已报名的活动被取消"),
	FGJ_SEND_PRAISE(118L, "发送点赞"),
	FGJ_BIRTHDAY_WISHES(119L, "发送生日祝福"),
	FGJ_GIVE_COUPON(120L, "系统发放单张优惠券（含兑换券）"),
	FGJ_GIVE_COUPON_GROUP(121L, "系统发放优惠券组（含兑换券）"),
	FGJ_VIP_LEFT_30DAYS(122L, "会籍有效期剩余30天"),
	FGJ_VIP_LEFT_1DAYS(123L, "会籍有效期剩余1天"),
	FGJ_VIP_AUTH_CHANGE(124L, "会籍权限更新"),
	FGJ_PRE_SELL_SEND(125L, "预售商品发货"),
	
	MYY_CANCEL_PAYED_ORDER(126L, "取消已付款订单"),
	MYY_BACK_REFUND_CUSTOMER(127L, "后台主动退款"),
	MYY_PENDING_ORDER(128L, "待处理订单提醒"),
	MYY_GIVE_POINTS(129L, "企业发放积分"),
	MYY_GIVE_FU_BEAN(130L, "企业发放点赞福豆"),
	MYY_COMPANY_BALANCE_DECREASE(131L, "公司余额减少"),
	MYY_COMPANY_BALANCE_RECHARGE(132L, "公司余额充值"),
	MYY_ACCOUNT_STATUS_CHANGE(133L, "账号状态变更（失效）"),
	MYY_ACCOUNT_DISABLE(134L, "账户失效的提醒"),
	MYY_BIND_MOBILE(135L, "绑定手机号码"),
	MYY_UNBIND_MOBILE(136L, "解绑手机号码"),
	MYY_LOGIN_PASSWORD_CHANGE(137L, "登录密码变更"),
	MYY_PAY_PASSWORD_CHANGE(138L, "支付密码变更"),
	MYY_APPLY_ACTIVITY_SUCCESS(139L, "成功报名活动"),
	MYY_NOTIFY_COMING_ACTIVITY(140L, "提醒参加将开始的活动"),
	MYY_ACTIVITY_INFO_CHANGE(141L, "活动信息变更"),
	MYY_CANCEL_ACTIVITY(142L, "已报名的活动被取消"),
	MYY_SEND_PRAISE(143L, "发送点赞"),
	MYY_BIRTHDAY_WISHES(144L, "发送生日祝福"),
	MYY_GIVE_COUPON(145L, "系统发放单张优惠券（含兑换券）"),
	MYY_GIVE_COUPON_GROUP(146L, "系统发放优惠券组（含兑换券）"),
	MYY_VIP_LEFT_30DAYS(147L, "会籍有效期剩余30天"),
	MYY_VIP_LEFT_1DAYS(148L, "会籍有效期剩余1天"),
	MYY_VIP_AUTH_CHANGE(149L, "会籍权限更新"),
	MYY_PRE_SELL_SEND(150L, "预售商品发货"),
	
	FGJ_BACK_REFUND_OPERATOR(151L, "后台主动退款"),
	MYY_BACK_REFUND_OPERATOR(152L, "后台主动退款"),

	
	// 发送方式字典
	SYSTEM_INFO_ID(1L,"系统消息id"),
	INFO_INFORM_INFO_ID(2L,"消息通知消息id"),
	MOBLIE__INFO_ID(3L,"短信消息id"),
	WECHAT_OFFICIAL_INFO_ID(4L,"微信公众号消息id"),
	MAIL_INFO_ID(5L,"邮件消息id");
	
	Long status;
	String comment;
	
    public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	InfoConstant(Long status,String comment) {  
        this.status = status;  
        this.comment = comment;  
    }
	
	/**
	 * 翻译
	 * @param status
	 * @return
	 */
	public static String translate(Long status){
		for(InfoConstant o:InfoConstant.values()){
			if(o.status.doubleValue()==status.doubleValue()){
				return o.getComment();
			}
		}
		return "";
	}
    
}
