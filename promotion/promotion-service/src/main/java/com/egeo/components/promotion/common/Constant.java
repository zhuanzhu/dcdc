package com.egeo.components.promotion.common;


/**
 * Created by paul on 2018/8/29.
 */
public class Constant {
    /**
     * 无效状态
     */
    public static final int INVALID_STATUS = 0;
    /**
     * 有效状态
     */
    public static final int VALID_STATUS = 1;
    /**
     * 卡券没有分配  isAllocation
     */
    public static final int ECARD_NOT_ALLOCATION = 0;
    /**
     * 卡券已分配
     */
    public static final int ECARD_IS_ALLOCATION = 1;
    /**
     * 卡券unit状态更新异常
     */
    public static final String ECARD_UPDATE_FAIL = "卡券unit状态更新异常";
    /**
     * exception msg
     */
    public static final String REQUEST_PARAM_ERROR = "请求参数异常";
    /**
     * exception msg
     */
    public static final String SELECT_VALID_ECARD_ERROR = "列表包含了有效卡券,请重新选择";
    /**
     * exception msg
     */
    public static final String SELECT_INVALID_ECARD_ERROR = "列表包含了无效卡券,请重新选择";
    /**
     * exception msg
     */
    public static final String INVALID_ECARD_SKU_ERROR = "卡券异常,无sku信息";
    /**
     * exception msg
     */
    public static final String INVALID_ECARD_STOCK_ERROR = "卡券库存异常";
    /**
     * exception msg
     */
    public static final String SET_INVALID_ECARD_ERROR = "该操作可能导致该SKU超卖";
    /**
     * exception msg
     */
    public static final String ECARD_PU_NOT_EXIST = "PU信息不存在";
    /**
     * exception msg
     */
    public static final String ECARD_VALID_DATE_ERROR = "卡券有效期开始日期不能大于结束日期";
    /**
     * exception msg
     */
    public static final String ECARD_VALID_NOW_DATE_ERROR = "卡券结束日期不能小于当前日期";

}

