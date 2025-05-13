package com.egeo.components.product.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CategoryAttValueManage;
import com.egeo.components.product.vo.CategoryAttValueVO;
import com.egeo.components.product.dto.CategoryAttValueDTO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

@Controller
@RequestMapping("/api/product/categoryAttValue")
public class CategoryAttValueAction extends BaseSpringController {

	@Resource(name = "categoryAttValue")
	private CategoryAttValueManage categoryAttValueManage;

	@RequestMapping(value = "saveCategoryAttValue")
	@ResponseBody
	public JsonResult<Long> saveCategoryAttValue(CategoryAttValueVO categoryAttValueVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryAttValueVO.setPlatformId(platformId);
		}
		logger.info("开始添加节点属性值数据!");
		JsonResult<Long> result = new JsonResult<Long>();
		Long id = categoryAttValueManage.saveCategoryAttValue(categoryAttValueVO);
		result.setData(id);
		return result;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<CategoryAttValueDTO>> findAll(CategoryAttValueVO categoryAttValueVO,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryAttValueVO.setPlatformId(platformId);
		}
		logger.info("开始根据条件查询产品与属性和属性值的关系!");
		JsonResult<List<CategoryAttValueDTO>> result = new JsonResult<List<CategoryAttValueDTO>>();
		List<CategoryAttValueDTO> list = categoryAttValueManage.findAll(categoryAttValueVO);
		result.setData(list);
		return result;
	}
}
