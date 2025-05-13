package com.egeo.components.pay.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.pay.po.AwaitQueuePO;
import com.egeo.orm.BaseReadDAO;

public interface AwaitQueueReadDAO extends BaseReadDAO<AwaitQueuePO>{
	/**
	 * 根据订单编号查询是否处于支付等待列表
	 * @param orderCode
	 * @return
	 */
	AwaitQueuePO findByOrderCode(@Param("orderCode")String orderCode);

	/**
	 * 根据订单id获取支付等待队列
	 * @param orderId
	 * @return
	 */
	AwaitQueuePO queryAwaitQueueByOrderId(@Param("orderId")Long orderId);
}
	