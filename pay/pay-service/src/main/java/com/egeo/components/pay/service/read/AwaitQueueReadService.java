package com.egeo.components.pay.service.read;


import java.util.List;

import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AwaitQueueReadService {

	public AwaitQueueDTO findAwaitQueueById(AwaitQueueDTO dto);

	public PageResult<AwaitQueueDTO> findAwaitQueueOfPage(AwaitQueueDTO dto,Pagination page);

	public List<AwaitQueueDTO> findAwaitQueueAll(AwaitQueueDTO dto);
	/**
	 * 根据订单编号查询是否处于支付等待列表
	 * @param orderCode
	 * @return
	 */
	public AwaitQueueDTO findByOrderCode(String orderCode);
	/**
	 * 根据订单号查询支付状态
	 * @param orderCode
	 * @return
	 */
	@Deprecated
	public Integer getOrderPayIsSuccess(AwaitQueueDTO awaitQueueDTO);
	
	/**
	 * 根据订单编号撤销订单支付
	 * @param orderCode
	 * @return
	 */
	@Deprecated
	public boolean revocationOrderPay(AwaitQueueDTO awaitQueueDTO, Long platformId);
	
	/**
	 * 根据订单id获取支付等待队列
	 * @param orderId
	 * @return
	 */
	public AwaitQueueDTO queryAwaitQueueByOrderId(Long orderId);
	
	
}
	