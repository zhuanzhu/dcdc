package com.egeo.components.order.service.write;

public interface MerchantProdSalesRecordCoreWriteService {
	/**
	 * 增加订单对应pu相应的销量
	 * @param soId
	 * @return
	 */
	public boolean recordSalesVolume(Long soId);
}

	