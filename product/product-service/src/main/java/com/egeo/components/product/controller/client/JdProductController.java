package com.egeo.components.product.controller.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.client.JdProductClient;
import com.egeo.components.product.converter.JdProductConverter;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.vo.JdProductStatStockAndPriceReqVO;
import com.egeo.components.product.vo.JdProductVO2;

@Controller
@RequestMapping("/client/product/jdProduct") 
public class JdProductController implements JdProductClient{ 

	@Autowired
	private JdProductManage jdProductReadService;


	@Override
	@RequestMapping(value = "/findJdProductById", method = { RequestMethod.POST })
	@ResponseBody
	public JdProductDTO findJdProductById(@RequestBody JdProductDTO dto){
		return jdProductReadService.findJdProductById(dto);
	} 

	@Override
	@RequestMapping(value = "/findJdProductById2", method = { RequestMethod.POST })
	@ResponseBody
	public JdProductDTO findJdProductById2(@RequestBody JdProductVO2 vo){
		JdProductDTO dto = JdProductConverter.toDTO(vo);
		return jdProductReadService.findJdProductById2(dto);
	} 
	@Override
	@RequestMapping(value = "/findJdProductByIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<JdProductDTO> findJdProductByIds(@RequestBody List<JdProductVO2> vo){
		List<JdProductDTO> dtos = JdProductConverter.toDTO2s(vo);
		return jdProductReadService.findJdProductByIds(dtos);
	}

	@Override
	@RequestMapping(value = "/getJdProductStatStockAndPrice", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,JdProductDTO> getJdProductStatStockAndPrice(@RequestBody JdProductStatStockAndPriceReqVO vo) {
		// TODO Auto-generated method stub
		ReceiverAddressDTO addressDto = JSON.parseObject(vo.getAddressStr(), ReceiverAddressDTO.class);
		return jdProductReadService.getJdProductStatStockAndPrice(vo.getCompanyId(),vo.getEnterpriseId(), vo.getSkuNumMap(), addressDto, vo.getPrice(), vo.getStock());
	}

	@Override
	@RequestMapping(value = "/getJdCategoryName", method = { RequestMethod.POST })
	@ResponseBody
	public String getJdCategoryName(@RequestParam("jdCategoryId") Long jdCategoryId) {
		return jdProductReadService.getCategoryName(jdCategoryId);
	}
}
