package com.egeo.components.order.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.promotion.client.CouponBatchClient;



@Component
public class CouponShedulerFacade {

	@Autowired
	private CouponBatchClient couponBatchWriteService;

	/**
	 * 隐藏超过已领取日期的优惠卷
	 */
	public void hideCouponBatch() {
		couponBatchWriteService.hideCouponBatch();
	}


}
