package com.egeo.components.pay.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.ServerApp;
import com.egeo.components.pay.converter.AwaitQueueConverter;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.manage.write.AwaitQueueWriteManage;
import com.egeo.components.pay.po.AwaitQueuePO;
import com.egeo.components.pay.service.write.AwaitQueueWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.utils.log.XLogger;

@Service("awaitQueueWriteService")
public class AwaitQueueWriteServiceImpl  implements AwaitQueueWriteService {
	private static final XLogger logger = XLogger.getLogger(AwaitQueueWriteServiceImpl.class);
	@Autowired
	private AwaitQueueWriteManage awaitQueueWriteManage;

	@Override
	public Long insertAwaitQueueWithTx(AwaitQueueDTO dto,Integer orderPayStatus) {
		if(orderPayStatus != null){
			// 订单支付状态 0:未支付、1:已支付、2:已退款
			// 订单状态为未支付才添加入队列
			if(orderPayStatus == 0){
				AwaitQueuePO po = AwaitQueueConverter.toPO(dto);
				Long rt = awaitQueueWriteManage.insertAwaitQueueWithTx(po);	
				return rt;
			}
		}else{
			logger.info("订单编号"+dto.getOrderCode()+"缺少订单支付状态");
			throw new BusinessException("订单编号"+dto.getOrderCode()+"缺少订单支付状态");
		}
		
		return null;
	}

	@Override
	public int updateAwaitQueueWithTx(AwaitQueueDTO dto) {
		AwaitQueuePO po = AwaitQueueConverter.toPO(dto);
		int rt = awaitQueueWriteManage.updateAwaitQueueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAwaitQueueWithTx(AwaitQueueDTO dto) {
		AwaitQueuePO po = AwaitQueueConverter.toPO(dto);
		int rt = awaitQueueWriteManage.deleteAwaitQueueWithTx(po);		
		return rt;
	}
	/**
	 * 根据订单id删除订单等待队列
	 * @param orderId
	 * @return
	 */
	@Override
	public int delByOrderIdWithTx(Long orderId) {
		// TODO Auto-generated method stub
		return awaitQueueWriteManage.delByOrderIdWithTx(orderId);
	}

	@Override
	public int updateAwaitQueueBySoIdWithTx(AwaitQueueDTO awaitQueueDTO) {
		return awaitQueueWriteManage.updateAwaitQueueBySoIdWithTx(AwaitQueueConverter.toPO(awaitQueueDTO));
	}
}
	