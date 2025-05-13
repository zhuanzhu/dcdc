package com.egeo.components.product.controller.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.BrandManage;
import com.egeo.components.product.vo.BrandVO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

@Controller
@RequestMapping("/api/product/brand")
public class BrandAction extends BaseSpringController {

	@Resource(name = "brand")
	private BrandManage brandManage;

	@RequestMapping(value = "saveBrand")
	@ResponseBody
	public JsonResult<Long> saveBrand(BrandVO brandVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			brandVO.setPlatformId(platformId);
		}
		logger.info("开始添加品牌数据!");
		JsonResult<Long> result = new JsonResult<Long>();
		Long id = brandManage.saveBrand(brandVO);
		result.setData(id);
		return result;
	}

	@RequestMapping(value = "updateBrand")
	@ResponseBody
	public JsonResult<String> updateBrand(BrandVO brandVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			brandVO.setPlatformId(platformId);
		}
		logger.info("开始修改品牌数据!");
		JsonResult<String> result = new JsonResult<String>();
		String rows = brandManage.updateBrand(brandVO);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<BrandVO> findById(BrandVO brandVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			brandVO.setPlatformId(platformId);
		}
		logger.info("开始根据id查询品牌数据!");
		JsonResult<BrandVO> result = new JsonResult<BrandVO>();
		BrandVO vo = brandManage.findById(brandVO);
		result.setData(vo);
		return result;
	}
}
