package com.egeo.components.order.client;
import java.util.List;

import com.egeo.components.order.dto.SoRefundDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "service-order-fgj",contextId="SoRefundClient")
public interface SoRefundClient {

	@RequestMapping(value = { "/client/order/soRefund/genSoRefundNO" }, method = { RequestMethod.POST })
	public List<String> genSoRefundNO();

	@RequestMapping(value = { "/client/order/soRefund/genSoRefundById" }, method = { RequestMethod.POST })
	public SoRefundDTO getSoRefundById(Long id);

	@RequestMapping(value = { "/client/order/soRefund/changeRefundState" }, method = { RequestMethod.POST })
	public Integer changeRefundState(SoRefundDTO dto);

}
