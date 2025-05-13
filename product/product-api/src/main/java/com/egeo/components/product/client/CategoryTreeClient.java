package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.CategoryTreeDTO;


@FeignClient(name = "service-product-fgj",contextId="CategoryTreeClient")
public interface CategoryTreeClient {

	@RequestMapping(value = { "/client/product/categoryTree/findByCategoryTreeId" }, method = { RequestMethod.POST }) 
	CategoryTreeDTO findByCategoryTreeId(Long categoryTreeId); 
 
 
	@RequestMapping(value = { "/client/product/categoryTree/findCategoryAll" }, method = { RequestMethod.POST }) 
	List<CategoryTreeDTO> findCategoryAll(CategoryTreeDTO categoryTreeDTO); 
 
 
	 
	}