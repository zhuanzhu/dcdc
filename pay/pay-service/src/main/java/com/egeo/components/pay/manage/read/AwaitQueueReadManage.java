package com.egeo.components.pay.manage.read;

import java.util.List;

import com.egeo.components.pay.po.AwaitQueuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AwaitQueueReadManage {

	public AwaitQueuePO findAwaitQueueById(AwaitQueuePO po);

	public PageResult<AwaitQueuePO> findAwaitQueueOfPage(AwaitQueuePO po,Pagination page);

	public List<AwaitQueuePO> findAwaitQueueAll(AwaitQueuePO po);
	/**
	 * 根据订单编号查询是否处于支付等待列表
	 * @param orderCode
	 * @return
	 */
	public AwaitQueuePO findByOrderCode(String orderCode);

	/**
	 * 根据订单id获取支付等待队列
	 * @param orderId
	 * @return
	 */
	public AwaitQueuePO queryAwaitQueueByOrderId(Long orderId);
}
	