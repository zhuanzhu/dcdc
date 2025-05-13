package com.egeo.components.product.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.ProductAttNameManage;
import com.egeo.components.product.vo.ProductAttNameVO;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/product/productAttName")
public class ProductAttNameAction extends BaseSpringController {

	@Resource(name = "productAttName")
	private ProductAttNameManage productAttNameManage;

	@RequestMapping(value = "saveProductAttName")
	@ResponseBody
	public JsonResult<Long> saveProductAttName(ProductAttNameVO productAttNameVO, HttpServletRequest req) {
		logger.info("开始添加产品与属性的关系!");
		JsonResult<Long> result = new JsonResult<Long>();
		Long id = productAttNameManage.saveProductAttName(productAttNameVO);
		result.setData(id);
		return result;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<ProductAttNameDTO>> findAll(ProductAttNameVO productAttNameVO, HttpServletRequest req) {
		logger.info("开始根据条件查询 产品与产品属性关系!");
		JsonResult<List<ProductAttNameDTO>> result = new JsonResult<List<ProductAttNameDTO>>();
		List<ProductAttNameDTO> list = productAttNameManage.findAll(productAttNameVO);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "deleteByProductId")
	@ResponseBody
	public JsonResult<String> deleteByProductId(Long productId) {
		logger.info("开始根据产品id删除产品与产品属性关系!");
		JsonResult<String> result = new JsonResult<String>();
		String rows = productAttNameManage.deleteByProductId(productId);
		result.setData(rows);
		return result;
	}
}
