package com.egeo.components.order.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.CardClient;
import com.egeo.components.order.service.write.CardWriteService;

@Controller
@RequestMapping("/client/order/card") 
public class CardController implements CardClient{ 

	@Autowired
	private CardWriteService cardWriteService;


	@Override
	@RequestMapping(value = "/allocationCardAndTakeStock", method = { RequestMethod.POST })
	@ResponseBody
	public Boolean allocationCardAndTakeStock(@RequestParam("orderId") Long orderId, @RequestParam("orderCode") String orderCode, @RequestParam("userId") Long userId, @RequestParam("userName") String userName,@RequestParam("ip")  String ip,@RequestParam("mac")  String mac,@RequestParam("companyType") Integer companyType) {
		return cardWriteService.allocationCardAndTakeStock(orderId, orderCode, userId, userName, ip, mac, companyType);
	}


 
}