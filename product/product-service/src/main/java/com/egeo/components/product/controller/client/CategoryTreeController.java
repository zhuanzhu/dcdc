package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.CategoryTreeClient;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.service.read.CategoryTreeReadService;
import com.egeo.components.product.service.write.CategoryTreeWriteService;

@Controller
@RequestMapping("/client/product/categoryTree") 
public class CategoryTreeController implements CategoryTreeClient{ 

	@Autowired
	private CategoryTreeReadService categoryTreeReadService;
	@Autowired
	private CategoryTreeWriteService categoryTreeWriteService;


	@Override
	@RequestMapping(value = "/findByCategoryTreeId", method = { RequestMethod.POST })
	@ResponseBody
	public CategoryTreeDTO findByCategoryTreeId(@RequestBody  Long categoryTreeId) {
		return categoryTreeReadService.findByCategoryTreeId(categoryTreeId);
	} 
 
	@Override
	@RequestMapping(value = "/findCategoryAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CategoryTreeDTO> findCategoryAll(@RequestBody CategoryTreeDTO categoryTreeDTO) {
		return categoryTreeReadService.findCategoryAll(categoryTreeDTO);
	} 
} 
