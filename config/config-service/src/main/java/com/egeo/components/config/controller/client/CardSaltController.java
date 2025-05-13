package com.egeo.components.config.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.client.CardSaltClient;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.service.read.CardSaltReadService;
import com.egeo.components.config.service.write.CardSaltWriteService;

@Controller
@RequestMapping("/client/config/cardSalt") 
public class CardSaltController implements CardSaltClient{ 

	@Autowired
	private CardSaltReadService cardSaltReadService;
	@Autowired
	private CardSaltWriteService cardSaltWriteService;




	@Override
	@RequestMapping(value = "/queryCardSaltByUUID", method = { RequestMethod.POST })
	@ResponseBody
	public CardSaltDTO queryCardSaltByUUID(@RequestBody String uuid) {
		// TODO Auto-generated method stub
		return cardSaltReadService.queryCardSaltByUUID(uuid);
	}


	@Override
	@RequestMapping(value = "/insertCardSaltWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertCardSaltWithTx(@RequestBody CardSaltDTO cardSaltDTO) {
		// TODO Auto-generated method stub
		return cardSaltWriteService.insertCardSaltWithTx(cardSaltDTO);
	} 
 
 
}