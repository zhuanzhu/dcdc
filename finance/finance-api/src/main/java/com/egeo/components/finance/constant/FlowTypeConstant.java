package com.egeo.components.finance.constant;

public enum FlowTypeConstant {
	/*
	 * 账户流水类型说明:
	 * CA开头:company account 公司账户
	 * OP开头:order pay 订单支付
	 * OR开头:order refund 订单退款
	 * UA开头:user account 用户账户
	 * UP开头:user praise 用户点赞
	 */

	CA_RECHARGE(0,"企业账户充值 "),
	CA_ADJUST_IN(1,"企业账户调整收入"),
	CA_DISABLE(2,"企业账户失效"),
	OP_CASH(3,"订单现金付款"),
	OP_FUBI (4,"订单积分付款"),
	OR_CASH(5,"订单现金退款"),
	OR_FUBI(6,"订单积分退款"),
	UA_RECHARGE(7,"用户积分充值"),
	UP_RECHARGE(8,"员工点赞福豆充值"),
	UA_LEAVE(9,"员工账户失效"),
	UP_PRAISE(10,"员工点赞"),
	CA_ADJUST_OUT(11,"企业账户调整支出"),
	CA_RECOVER(13,"企业账户恢复"),
	UP_RECOVER(14,"员工账户恢复"),
	OP_JIDIAN (15,"订单积点付款"),
	OR_JIDIAN (16,"订单积点退款"),
	OP_CGK (17,"订单卡劵付款"),
	OR_CGK (18,"订单卡劵退款"),
	OP_LPK (19,"订单礼品卡付款"),
	OR_LPK (20,"订单礼品卡退款"),
			;
	private FlowTypeConstant(int status, String comment) {
		this.status = status;
		this.comment = comment;
	}
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
	int status;
	String comment;

	public static String translate(int status){
		for(FlowTypeConstant f:FlowTypeConstant.values()){
			if(f.status==status){
				return f.getComment();
			}
		}
		return "";
	}
}
