package com.egeo.components.pay.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.pay.po.PayWeixinLogPO;
import com.egeo.orm.BaseReadDAO;

public interface PayWeixinLogReadDAO extends BaseReadDAO<PayWeixinLogPO>{

	/**
	 * 通过订单编号查询微信支付日志
	 * @param orderCode
	 * @return
	 */
	PayWeixinLogPO queryPayWeixinLogByOrderCode(@Param("orderCode")String orderCode);
}
	