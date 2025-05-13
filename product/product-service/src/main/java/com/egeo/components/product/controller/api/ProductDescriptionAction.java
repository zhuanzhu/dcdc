package com.egeo.components.product.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.ProductDescriptionManage;
import com.egeo.components.product.vo.ProductDescriptionVO;
import com.egeo.components.product.dto.ProductDescriptionDTO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

@Controller
@RequestMapping("/api/product/productDescription")
public class ProductDescriptionAction extends BaseSpringController {

	@Resource(name = "productDescription")
	private ProductDescriptionManage productDescriptionManage;

	@RequestMapping(value = "saveProductDescription")
	@ResponseBody
	public JsonResult<Long> saveProductDescription(ProductDescriptionVO productDescriptionVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productDescriptionVO.setPlatformId(platformId);
		}
		logger.info("开始保存产品描述信息!");
		Long rows = null;
		JsonResult<Long> result = new JsonResult<Long>();
		rows = productDescriptionManage.saveProductDescription(productDescriptionVO);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<ProductDescriptionDTO>> findAll(ProductDescriptionVO productDescriptionVO,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productDescriptionVO.setPlatformId(platformId);
		}
		logger.info("开始根据条件查询产品描述信息!");
		JsonResult<List<ProductDescriptionDTO>> result = new JsonResult<List<ProductDescriptionDTO>>();
		List<ProductDescriptionDTO> list = productDescriptionManage.findAll(productDescriptionVO);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "updateProductDescription")
	@ResponseBody
	public JsonResult<String> updateProductDescription(ProductDescriptionVO productDescriptionVO,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productDescriptionVO.setPlatformId(platformId);
		}
		logger.info("开始修改产品描述信息!");
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		rows = productDescriptionManage.updateProductDescription(productDescriptionVO);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<ProductDescriptionDTO> findById(ProductDescriptionVO productDescriptionVO,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productDescriptionVO.setPlatformId(platformId);
		}
		logger.info("开始根据id查询产品描述信息!");
		JsonResult<ProductDescriptionDTO> result = new JsonResult<ProductDescriptionDTO>();
		ProductDescriptionDTO productDescriptionDTO = productDescriptionManage.findById(productDescriptionVO);
		result.setData(productDescriptionDTO);
		return result;
	}

	@RequestMapping(value = "deleteByProductId")
	@ResponseBody
	public JsonResult<String> deleteByProductId(Long productId) {
		logger.info("开始根据产品id删除产品描述信息!");
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		rows = productDescriptionManage.deleteByProductId(productId);
		result.setData(rows);
		return result;
	}
}
