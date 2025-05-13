package com.egeo.components.product.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CardThirdpartyAttValueManage;
import com.egeo.components.product.converter.CardThirdpartyAttValueConverter;
import com.egeo.components.product.vo.CardThirdpartyAttValueVO;
import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/cardThirdpartyAttValue")
public class CardThirdpartyAttValueAction extends BaseSpringController {
	
	@Resource(name="cardThirdpartyAttValue")
	private CardThirdpartyAttValueManage cardThirdpartyAttValueManage;


	// 业务方法：
	@RequestMapping(value = "/findCardThirdpartyAttValueById")
	@ResponseBody
	public JsonResult<CardThirdpartyAttValueVO> findCardThirdpartyAttValueById(Long id ) {
		
		CardThirdpartyAttValueDTO dto = new CardThirdpartyAttValueDTO();
		dto.setId(id);
		CardThirdpartyAttValueDTO rt = cardThirdpartyAttValueManage.findCardThirdpartyAttValueById(dto);		
		return JsonResult.success(CardThirdpartyAttValueConverter.toVO(rt));
					 
	}
}
	