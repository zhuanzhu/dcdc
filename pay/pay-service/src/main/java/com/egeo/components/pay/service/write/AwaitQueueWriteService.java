package com.egeo.components.pay.service.write;

import com.egeo.components.pay.dto.AwaitQueueDTO;


public interface AwaitQueueWriteService {

	public Long insertAwaitQueueWithTx(AwaitQueueDTO dto,Integer orderPayStatus);

	public int updateAwaitQueueWithTx(AwaitQueueDTO dto);

	public int deleteAwaitQueueWithTx(AwaitQueueDTO dto);
	/**
	 * 根据订单id删除订单等待队列
	 * @param orderId
	 * @return
	 */
	public int delByOrderIdWithTx(Long orderId);
	/**
	 * 根据订单id修改其订单等待队列中支付是否成功的值
	 * @param awaitQueueDTO
	 * @return
	 */
	public int updateAwaitQueueBySoIdWithTx(AwaitQueueDTO awaitQueueDTO);
}
	