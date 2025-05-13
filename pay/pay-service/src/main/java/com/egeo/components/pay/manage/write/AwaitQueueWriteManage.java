package com.egeo.components.pay.manage.write;

import com.egeo.components.pay.po.AwaitQueuePO;


public interface AwaitQueueWriteManage {

	Long insertAwaitQueueWithTx(AwaitQueuePO po);

	int updateAwaitQueueWithTx(AwaitQueuePO po);

	int deleteAwaitQueueWithTx(AwaitQueuePO po);
	/**
	 * 根据订单id删除订单等待队列
	 * @param orderId
	 * @return
	 */
	int delByOrderIdWithTx(Long orderId);
	/**
	 * 根据订单id修改其订单等待队列中支付是否成功的值
	 * @param po
	 * @return
	 */
	int updateAwaitQueueBySoIdWithTx(AwaitQueuePO po);
}
	