package com.egeo.components.order.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.order.dto.DeliverOrderWithTxDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildPageDTO;
import com.egeo.orm.PageResult;


@FeignClient(name = "service-order-fgj",contextId="MerchantProdSalesRecordCoreClient")
public interface MerchantProdSalesRecordCoreClient {

	/**
	 * 增加订单对应pu相应的销量
	 * @param soId
	 * @return
	 */
	@RequestMapping(value = { "/client/order/merchantProdSalesRecordCore/recordSalesVolume" }, method = { RequestMethod.POST }) 
	public boolean recordSalesVolume(Long soId); 
 
 
 
 
}