package com.egeo.components.promotion.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.FuCoinClient;
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.components.promotion.service.read.FuCoinReadService;
import com.egeo.components.promotion.service.write.FuCoinWriteService;

@Controller
@RequestMapping("/client/promotion/fuCoin") 
public class FuCoinController implements FuCoinClient{ 

	@Autowired
	private FuCoinReadService fuCoinReadService;
	@Autowired
	private FuCoinWriteService fuCoinWriteService;


	@Override
	@RequestMapping(value = "/findFCoinByUserId", method = { RequestMethod.POST })
	@ResponseBody
	public FuCoinDTO findFCoinByUserId(@RequestParam("memberId") Long memberId,@RequestParam("platformId")  Long platformId) {
		return fuCoinReadService.findFCoinByUserId(memberId, platformId);
	} 
}  
