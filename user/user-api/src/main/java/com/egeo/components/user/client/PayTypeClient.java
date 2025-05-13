package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.PayTypeDTO;


@FeignClient(name = "service-user-fgj",contextId="PayTypeClient")
public interface PayTypeClient {

	@RequestMapping(value = { "/client/user/payType/findPayTypeByCode" }, method = { RequestMethod.POST }) 
	public PayTypeDTO findPayTypeByCode(Integer code); 
 
 
	@RequestMapping(value = { "/client/user/payType/findPayTypeByCodes" }, method = { RequestMethod.POST }) 
	public List<PayTypeDTO> findPayTypeByCodes(List<Integer> codes); 
 
 
}