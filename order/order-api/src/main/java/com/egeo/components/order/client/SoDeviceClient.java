package com.egeo.components.order.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.components.order.dto.SoDevicePageDTO;
import com.egeo.orm.PageResult;


@FeignClient(name = "service-order-fgj",contextId="SoDeviceClient")
public interface SoDeviceClient {
	
 
	@RequestMapping(value = { "/client/order/soDevice/findSoDeviceById" }, method = { RequestMethod.POST })
	public SoDeviceDTO findSoDeviceById(SoDeviceDTO dto);
	
	@RequestMapping(value = { "/client/order/soDevice/findSoDeviceOfPage" }, method = { RequestMethod.POST })
	public PageResult<SoDeviceDTO> findSoDeviceOfPage(SoDevicePageDTO dto);
	
	@RequestMapping(value = { "/client/order/soDevice/findSoDeviceAll" }, method = { RequestMethod.POST })
	public List<SoDeviceDTO> findSoDeviceAll(SoDeviceDTO dto);
	
	
}