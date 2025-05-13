package com.egeo.components.order.client;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.order.dto.DeliverOrderWithTxDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildPageDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.orm.PageResult;


@FeignClient(name = "service-order-fgj",contextId="SoChildClient")
public interface SoChildClient {

	@RequestMapping(value = { "/client/order/soChild/deliverOrderWithTx" }, method = { RequestMethod.POST })
	public void deliverOrderWithTx(DeliverOrderWithTxDTO dto);


	@RequestMapping(value = { "/client/order/soChild/querySoChildListBySoId" }, method = { RequestMethod.POST })
	public List<SoChildDTO> querySoChildListBySoId(Long id);


	@RequestMapping(value = { "/client/order/soChild/findMerchantChildOrderOfPage" }, method = { RequestMethod.POST })
	public PageResult<SoChildDTO> findMerchantChildOrderOfPage(SoChildPageDTO dto);


	@RequestMapping(value = { "/client/order/soChild/findSoChildAll" }, method = { RequestMethod.POST })
	public List<SoChildDTO> findSoChildAll(SoChildDTO dto);

	@RequestMapping(value = { "/client/order/soChild/findSoChildByChildCode" }, method = { RequestMethod.POST })
	public SoChildDTO findSoChildByChildCode(String childCode);

	@RequestMapping(value = { "/client/order/soChild/findSoChildById" }, method = { RequestMethod.POST })
	public SoChildDTO findSoChildById(Long id);
}
