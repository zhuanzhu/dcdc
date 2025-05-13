package com.egeo.components.product.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CategoryAttNameManage;
import com.egeo.components.product.vo.ApecificationAndAtt;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.vo.CategoryAttName;
import com.egeo.components.product.vo.CategoryAttNameVO;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api/product/categoryAttName")
public class CategoryAttNameAction extends BaseSpringController {

	@Resource(name = "categoryAttName")
	private CategoryAttNameManage categoryAttNameManage;

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@RequestMapping(value = "categoryAttNameByAttNameId")
	@ResponseBody
	public JsonResult<CategoryAttNameDTO> categoryAttNameByAttNameId(CategoryAttNameVO categoryAttNameVO,
			HttpServletRequest req) {
		logger.info("开始根据属性名称id查询节点属性名称!");
		JsonResult<CategoryAttNameDTO> result = new JsonResult<CategoryAttNameDTO>();
		CategoryAttNameDTO categoryAttNameDTO = categoryAttNameManage.categoryAttNameByAttNameId(categoryAttNameVO);
		result.setData(categoryAttNameDTO);
		return result;
	}

	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<CategoryAttNameDTO> findById(CategoryAttNameVO categoryAttNameVO, HttpServletRequest req) {
		logger.info("开始根据id查询节点属性与属性名称的关系!");
		JsonResult<CategoryAttNameDTO> result = new JsonResult<CategoryAttNameDTO>();
		CategoryAttNameDTO categoryAttNameDTO = categoryAttNameManage.findById(categoryAttNameVO);
		result.setData(categoryAttNameDTO);
		return result;
	}

	@RequestMapping(value = "addCategoryAttName")
	@ResponseBody
	public JsonResult<String> addCategoryAttName(CategoryAttNameDTO categoryAttNameDTO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryAttNameDTO.setPlatformId(platformId);
		}
		logger.info("新增属性!");

		JsonResult<String> result = new JsonResult<String>();

		if (EmptyUtil.isEmpty(categoryAttNameDTO.getCategoryId())) {
			result.setCode(1);
			result.setError("缺少类目id");
			return result;
		}
		if (EmptyUtil.isEmpty(categoryAttNameDTO.getAttNameId())) {
			result.setCode(1);
			result.setError("缺少属性id");
			return result;
		}
		String rt = categoryAttNameManage.addCategoryAttName(categoryAttNameDTO, req);
		result.setData(rt);
		return result;
	}

	@RequestMapping(value = "deleteCategoryAttName")
	@ResponseBody
	public JsonResult<String> deleteCategoryAttName(CategoryAttNameDTO categoryAttNameDTO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryAttNameDTO.setPlatformId(platformId);
		}
		logger.info("删除属性与类目的关系!");
		JsonResult<String> result = new JsonResult<String>();
		String rt = categoryAttNameManage.deleteCategoryAttName(categoryAttNameDTO);
		result.setData(rt);
		return result;
	}

	@RequestMapping(value = "categoryAttNameByCId")
	@ResponseBody
	public JsonResult<ApecificationAndAtt> categoryAttNameByCId(CategoryAttNameVO categoryAttNameVO,
			HttpServletRequest req) {
		logger.info("开始根据节点属性id查询节点产品属性与产品属性值!");
		if (categoryAttNameVO.getCategoryId() == null || categoryAttNameVO.getCategoryId().equals(0L)) {
			return JsonResult.fail("节点属性编号不能为空");
		}
		JsonResult<ApecificationAndAtt> result = new JsonResult<ApecificationAndAtt>();
		ApecificationAndAtt apecificationAndAtt = categoryAttNameManage.categoryAttNameByCId(categoryAttNameVO);
		result.setData(apecificationAndAtt);
		return result;
	}

	@RequestMapping(value = "merchantProductAttNameByCId")
	@ResponseBody
	public JsonResult<List<AttributeNameVO>> merchantProductAttNameByCId(CategoryAttNameVO categoryAttNameVO,
			HttpServletRequest req) {
		logger.info("开始根据节点属性id查询节点商品属性与商品属性值!");
		JsonResult<List<AttributeNameVO>> result = new JsonResult<List<AttributeNameVO>>();
		List<AttributeNameVO> list = categoryAttNameManage.merchantProductAttNameByCId(categoryAttNameVO);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<CategoryAttNameDTO>> findAll(CategoryAttNameVO categoryAttNameVO, HttpServletRequest req) {
		logger.info("开始根据条件查询节点属性与属性名称的关系!");
		JsonResult<List<CategoryAttNameDTO>> result = new JsonResult<List<CategoryAttNameDTO>>();
		List<CategoryAttNameDTO> list = categoryAttNameManage.findAll(categoryAttNameVO);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "saveCategoryAttNameByCategoryId")
	@ResponseBody
	public JsonResult<String> saveCategoryAttNameByCategoryId(Long categoryId, Integer type, String list,
			HttpServletRequest req) {
		Long platformId = null;
		logger.info("批量新增类目属性!");
		List<CategoryAttNameDTO> lists = null;
		JsonResult<String> result = new JsonResult<String>();
		try {
			JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, CategoryAttNameDTO.class);
			lists = MAPPER.readValue(list, javaType);

			String rt = categoryAttNameManage.saveCategoryAttNameByCategoryId(categoryId, type, lists, platformId, req);
			result.setData(rt);
			return result;
		} catch (Exception e) {
			logger.error("批量新增类目属性异常！", e);
			result.setCode(1);
			result.setError("批量新增类目属性异常：" + e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "isRequired")
	@ResponseBody
	public JsonResult<String> updateCategoryAttNameByCategoryId(String list) {
		logger.info("批量修改类目属性!");
		List<CategoryAttName> lists = null;
		JsonResult<String> result = new JsonResult<String>();
		try {
			JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, CategoryAttName.class);
			lists = MAPPER.readValue(list, javaType);

			String rt = categoryAttNameManage.updateCategoryAttNameByCategoryId(lists);
			result.setData(rt);
			return result;
		} catch (Exception e) {
			logger.error("批量新增类目属性异常！", e);
			result.setCode(1);
			result.setError("批量新增类目属性异常：" + e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "showCategoryAttName")
	@ResponseBody
	public JsonResult<List<CategoryAttName>> showCategoryAttName(Long categoryId, Integer type) {
		logger.info("显示是否必填类目属性列表!");
		JsonResult<List<CategoryAttName>> result = new JsonResult<List<CategoryAttName>>();
		try {

			List<CategoryAttName> list = categoryAttNameManage.showCategoryAttName(categoryId, type);
			result.setData(list);
			return result;
		} catch (Exception e) {
			logger.error("显示是否必填类目属性列表异常！", e);
			result.setCode(1);
			result.setError("显示是否必填类目属性列表异常：" + e.getMessage());
			return result;
		}
	}

}
