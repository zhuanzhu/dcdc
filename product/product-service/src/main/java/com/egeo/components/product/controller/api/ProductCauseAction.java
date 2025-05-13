package com.egeo.components.product.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.ProductCauseManage;
import com.egeo.components.product.vo.ProductCauseVO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

@Controller
@RequestMapping("/api/product/productCause")
public class ProductCauseAction extends BaseSpringController {

	@Resource(name = "productCause")
	private ProductCauseManage productCauseManage;

	@RequestMapping(value = "findByProductId")
	@ResponseBody
	public JsonResult<List<ProductCauseVO>> findByProductId(Long productId) {
		logger.info("开始根据产品id查询产品不通过原因");
		JsonResult<List<ProductCauseVO>> result = new JsonResult<List<ProductCauseVO>>();
		List<ProductCauseVO> list = productCauseManage.findByProductId(productId);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "saveProductCause")
	@ResponseBody
	public JsonResult<Long> saveProductCause(ProductCauseVO productCauseVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productCauseVO.setPlatformId(platformId);
		}
		logger.info("开始保存产品不通过原因");
		JsonResult<Long> result = new JsonResult<Long>();
		Long rows = productCauseManage.saveProductCause(productCauseVO, req);
		result.setData(rows);
		return result;
	}
}
