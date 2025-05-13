package com.egeo.components.finance.constant;

public class AccountConstant {

	/**
	 * 批次/明细状态:待审核
	 */
	public static final int ACCOUNT_STATUS_EXAM=0;
	
	/**
	 * 批次/明细状态:已通过
	 */
	public static final int ACCOUNT_STATUS_PASSED=1;
	
	/**
	 * 批次/明细状态:未通过
	 */
	public static final int ACCOUNT_STATUS_FAIL=2;
	
	/**
	 * 批次/明细状态:已完成
	 */
	public static final int ACCOUNT_STATUS_FINISHED=3;
	
	/**
	 * 账户类型:迩格积分发放账户
	 */
	public static final int ACCOUNT_TYPE_FUBI_OUT=0;
	
	/**
	 * 账户类型:迩格积分收入账户
	 */
	public static final int ACCOUNT_TYPE_FUBI_IN=1;
	
	/**
	 * 账户类型:迩格现金收入账户
	 */
	public static final int ACCOUNT_TYPE_CASH_IN=2;
	
	/**
	 * 普通公司账户
	 */
	public static final int ACCOUNT_TYPE_NORMAL=20;
	/**
	 * 购买商品积分扣除原因
	 */
	public static final String FUBI_CHANGE_BUY_SU="购买商品积分扣除";
	/**
	 * 积分扣除原因--》资产回收
	 */
	public static final String FUBI_CHANGE_ACCOUNT_RECYCLE="资产回收积分扣除";
	
	/**
	 * 点赞福豆增加原因--》用户点赞
	 */
	public static final String ADD_PRAISE_FUBI_CHANGE_ACCOUNT_RECYCLE="被用户点赞福豆增加";
	
	/**
	 * 点赞福豆扣除原因--》用户点赞
	 */
	public static final String DEDUCT_PRAISE_FUBI_CHANGE_ACCOUNT_RECYCLE="给用户点赞福豆扣除";
	
	/**
	 * 公司积分增加原因--》用户资产回收
	 */
	public static final String ADD_COMPANY_FUBI_CHANGE_USER_ACCOUNT_RECYCLE="员工资产回收积分增加";
}
