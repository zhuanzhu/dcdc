package com.egeo.components.cms.common;

import java.util.Arrays;
import java.util.List;

/**
 * cms常量类
 * @author tan
 *
 */
public class CmsConstant {

	/**************************CMS属性配置类型 0:Banner图（单个），1：下拉框 ,2 ： 文本框，3:轮播图（多个）****************************/
	/**
	 * 0:Banner图（单个）
	 */
	public static final int CMS_CFG_KEY_TYPE_BANNER = 0;
	/**
	 * 1：下拉框
	 */
	public static final int CMS_CFG_KEY_TYPE_SELECT = 1;
	/**
	 * 2 ： 文本框
	 */
	public static final int CMS_CFG_KEY_TYPE_TEXT_INPUT = 2;
	/**
	 * 3:轮播图（多个）
	 */
	public static final int CMS_CFG_KEY_TYPE_BANNER_LIST = 3;
	/**
	 * 4:复选框checkbox
	 */
	public static final int CMS_CFG_KEY_TYPE_CHECKBOX = 4;
	/**
	 * 5:单选框radio
	 */
	public static final int CMS_CFG_KEY_TYPE_RADIO = 5;
	/**
	 * 6:单张图片image
	 */
	public static final int CMS_CFG_KEY_TYPE_IMAGE = 6;
	/**
	 * 7:跳转配置
	 */
	public static final int CMS_CFG_KEY_TYPE_JUMP = 7;
	/**
	 * 8:object属性
	 */
	public static final int CMS_CFG_KEY_TYPE_OBJECT = 8;
	/**
	 * 9:选色盘
	 */
	public static final int CMS_CFG_KEY_TYPE_COLOR_SELECT = 9;
	/**
	 * 10:选择商品组
	 */
	public static final int CMS_CFG_KEY_TYPE_SU_COMBINATION = 10;
	
	//客户端类型
	/**
	 * PC端
	 */
	public static final int CMS_CLINTE_TYPE_PC = 5;
	
	/**
	 * 移动端
	 */
	public static final int CMS_CLINTE_TYPE_MOBILE = 6;
	
	//跳转类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品池组(商品列表)、5.商品、6.无效果  7.分页tab
	
	/**
	 * 本地参数
	 */
	public static final int CMS_LINK_TYPE_LOCAL_PARAM = 1;
	
	/**
	 * 内部H5链接
	 */
	public static final int CMS_LINK_TYPE_H5_INNER = 2;
	//external
	/**
	 * 外部H5链接
	 */
	public static final int CMS_LINK_TYPE_H5_EXTERNAL = 3;
	
	/**
	 * 商品列表
	 */
	public static final int CMS_LINK_TYPE_SU_LIST = 4;
	
	/**
	 * 单个商品
	 */
	public static final int CMS_LINK_TYPE_SU_SINGLE = 5;
	//No effect 
	/**
	 * 无效果
	 */
	public static final int CMS_LINK_TYPE_NO_EFFECT = 6;
	
	/**
	 * 分页tab
	 */
	public static final int CMS_LINK_TYPE_TAB = 7;
	
	/**
	 * 操作配置
	 */
	public static final int CMS_LINK_TYPE_OPERATE = 8;
	
	/**
	 * 福管加
	 */
	public static final long CMS_PLATFORM_FGJ = 7;
	
	/**
	 * 慢有优
	 */
	public static final long CMS_PLATFORM_MYY = 2;
	
	/**
	 * 福管加 PC端默认商品页面
	 */
	public static final long CMS_DEFAULT_PAGE_SU_2 = 2;
	/**
	 * 福管加移动端默认商品页面
	 */
	public static final long CMS_DEFAULT_PAGE_SU_1 = 1;
	
	/**
	 * 慢有优 PC端默认商品页面
	 */
	public static final long CMS_DEFAULT_PAGE_SU_10 = 10;
	
	/**
	 * 慢有优 移动端默认商品页面
	 */
	public static final long CMS_DEFAULT_PAGE_SU_9 = 9;
	
	
	/**
	 * 原始客户端满减券 福管加
	 */
	public static final long CMS_DEFAULT_PAGE_COUPON_3 = 3;
	/**
	 * 原始客户端兑换券 福管加
	 */
	public static final long CMS_DEFAULT_PAGE_COUPON_4 = 4;
	
	/**
	 * 原始PC端满减券福管加
	 */
	public static final long CMS_DEFAULT_PAGE_COUPON_5 = 5;
	
	/**
	 * 原始PC端兑换券福管加
	 */
	public static final long CMS_DEFAULT_PAGE_COUPON_6 = 6;
	
	/**
	 * 原始客户端满减券 慢有优
	 */
	public static final long CMS_DEFAULT_PAGE_COUPON_11 = 11;
	/**
	 * 原始客户端兑换券 慢有优
	 */
	public static final long CMS_DEFAULT_PAGE_COUPON_12 = 12;
	
	/**
	 * 原始PC端满减券 慢有优
	 */
	public static final long CMS_DEFAULT_PAGE_COUPON_13 = 13;
	
	/**
	 * 原始PC端兑换券 慢有优
	 */
	public static final long CMS_DEFAULT_PAGE_COUPON_14 = 14;
	
	/**
	 * 满减券
	 */
	public static final int CMS_COUPON_TYPE_FULL_REDUCED = 0;
	
	/**
	 * 兑换券
	 */
	public static final int CMS_COUPON_TYPE_EXCHANGE = 1;
	
	/**
	 * 首页
	 */
	public static final int CMS_PAGE_TYPE_HOME = 2;
	/**
	 * 商城
	 */
	public static final int CMS_PAGE_TYPE_MALL = 3;
	/**
	 * 应用
	 */
	public static final int CMS_PAGE_TYPE_APPLICATION = 4;
	/**
	 * 活动
	 */
	public static final int CMS_PAGE_TYPE_ACTIVE = 5;
	
	/**
	 * （tab页类型）首页
	 */
	public static final String CMS_PAGE_TAB_TYPE_HOME = "1";
	/**
	 * （tab页类型）商城
	 */
	public static final String CMS_PAGE_TAB_TYPE_MALL = "2";
	/**
	 * （tab页类型）应用
	 */
	public static final String CMS_PAGE_TAB_TYPE_APPLICATION = "3";
	/**
	 * （tab页类型）我的
	 */
	public static final String CMS_PAGE_TAB_TYPE_MINE = "4";
	
}
