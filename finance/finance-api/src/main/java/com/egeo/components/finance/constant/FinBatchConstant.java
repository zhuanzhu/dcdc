package com.egeo.components.finance.constant;

/**
 * 财务批次类型常量
 * @author graci
 *
 */
public class FinBatchConstant {

	/**
	 * 公司账户充值
	 */
	public static final int COMPANY_ACCOUNT_CHARGE=1;

	/**
	 * 账户调整收入
	 */
	public static final int COMPANY_ACCOUNT_INCREASE=2;

	/**
	 * 账户调整支出
	 */
	public static final int COMPANY_ACCOUNT_DECREASE=3;

	/**
	 * 企业账户失效
	 */
	public static final int COMPANY_DESABLE=4;

	/**
	 * 订单现金付款
	 */
	public static final int ORDER_PAY_CASH=5;

	/**
	 * 订单积分付款
	 */
	public static final int ORDER_PAY_FUBI=6;

	/**
	 * 订单现金退款
	 */
	public static final int ORDER_REFUND_CASH=7;

	/**
	 * 订单积分退款
	 */
	public static final int ORDER_REFUND_FUBI=8;

	/**
	 * 员工积分充值
	 */
	public static final int FUBI_RECHARGE=9;

	/**
	 * 员工点赞福豆充值
	 */
	public static final int PRAISE_RECHARGE=10;

	/**
	 * 账户失效
	 */
	public static final int USER_ACCOUNT_DISABLE=11;

	/**
	 * 员工点赞
	 */
	public static final int PRAISE=12;

	/**
	 * 企业账户恢复
	 */
	public static final int COMPANY_DESABLE_RECOVER=13;

	/**
	 * 员工账户恢复
	 */
	public static final int USER_ACCOUNT_DISABLE_RECOVER=14;

	/**
	 * 订单积点付款
	 */
	public static final int ORDER_PAY_JIDIAN=15;

	/**
	 * 订单积点退款
	 */
	public static final int ORDER_REFUND_JIDIAN=16;

	/**
	 * 订单畅购卡付款
	 */
	public static final int ORDER_PAY_CGK=17;

	/**
	 * 订单畅购卡退款
	 */
	public static final int ORDER_REFUND_CGK=18;


	/**
	 * 订单礼品卡付款
	 */
	public static final int ORDER_PAY_LPK=19;

	/**
	 * 订单礼品卡退款
	 */
	public static final int ORDER_REFUND_LPK=20;

	/**
	 * 翻译流水类型至财务批次类型
	 * @param type
	 * @return
	 *
	 * //暂时仅翻译几个有用的类型
		CA_RECHARGE(0,"企业账户充值 "),
		CA_ADJUST_IN(1,"企业账户调整收入"),
		CA_DISABLE(2,"企业失效"),
		OP_CASH(3,"订单现金付款"),
		OP_FUBI (4,"订单积分付款"),
		OR_CASH(5,"订单现金退款"),
		OR_FUBI(6,"订单积分退款"),
		UA_RECHARGE(7,"用户积分充值"),
		UP_RECHARGE(8,"员工点赞福豆充值"),
		UA_LEAVE(9,"员工离职"),
		UP_PRAISE(10,"员工点赞"),
		CA_ADJUST_OUT(11,"企业账户调整支出");
	 */
	public static int translateFlowType(int flowType) {

		switch (flowType) {
		case 0:
			return COMPANY_ACCOUNT_CHARGE;
		case 1:
			return COMPANY_ACCOUNT_INCREASE;
		case 11:
			return COMPANY_ACCOUNT_DECREASE;
		case 7:
			return FUBI_RECHARGE;
		case 8:
			return PRAISE_RECHARGE;
		case 2:
			return COMPANY_DESABLE;
		case 9:
			return USER_ACCOUNT_DISABLE;
		case 13:
			return COMPANY_DESABLE_RECOVER;
		case 14:
			return USER_ACCOUNT_DISABLE_RECOVER;
		default:
			break;
		}
		return 0;
	}
}
