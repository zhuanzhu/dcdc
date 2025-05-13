package com.egeo.components.product.client;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.vo.JdProductStatStockAndPriceReqVO;
import com.egeo.components.product.vo.JdProductVO2;


@FeignClient(name = "service-product-fgj",contextId="JdProductClient")
public interface JdProductClient {

	@RequestMapping(value = { "/client/product/jdProduct/findJdProductById" }, method = { RequestMethod.POST }) 
	public JdProductDTO findJdProductById(JdProductDTO dto); 
	@RequestMapping(value = { "/client/product/jdProduct/findJdProductById2" }, method = { RequestMethod.POST }) 
	public JdProductDTO findJdProductById2(JdProductVO2 dto); 
	@RequestMapping(value = { "/client/product/jdProduct/findJdProductByIds" }, method = { RequestMethod.POST }) 
	public List<JdProductDTO> findJdProductByIds(List<JdProductVO2> dto); 
	@RequestMapping(value = { "/client/product/jdProduct/getJdProductStatStockAndPrice" }, method = { RequestMethod.POST }) 
	public Map<String,JdProductDTO> getJdProductStatStockAndPrice(JdProductStatStockAndPriceReqVO vo);

	@RequestMapping(value = { "/client/product/jdProduct/getJdCategoryName" }, method = { RequestMethod.POST })
	String getJdCategoryName(@RequestParam("jdCategoryId") Long jdCategoryId);
 
	 
}