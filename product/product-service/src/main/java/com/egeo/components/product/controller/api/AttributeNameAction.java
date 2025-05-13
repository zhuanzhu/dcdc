package com.egeo.components.product.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.AttributeNameManage;
import com.egeo.components.product.dto.AttValueDTO;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/product/attributeName")
public class AttributeNameAction extends BaseSpringController {

	@Resource(name = "attributeName")
	private AttributeNameManage attributeNameManage;

	/**
	 * 
	 * @param attributeNameVO
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "根据id查询属性名称信息")
	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findById(AttributeNameVO attributeNameVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			attributeNameVO.setPlatformId(platformId);
		}
		logger.info("开始根据id查询属性名称信息，属性id = {}",attributeNameVO.getId());
		JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
		AttributeNameVO attributeName = attributeNameManage.findById(attributeNameVO);
		Map<String, Object> map = new HashMap<>();
		map.put("id", attributeName.getId());
		map.put("name", attributeName.getName());
		map.put("specificationProperty", attributeName.getSpecificationProperty());
		map.put("parameterProperty", attributeName.getParameterProperty());
		if (EmptyUtil.isNotEmpty(attributeName.getMode())) {
			map.put("mode", attributeName.getMode().toString());
		} else {
			map.put("mode", "");
		}
		if (attributeName.getMode() == 1 || attributeName.getMode() == 3) {
			map.put("begin", "");
			map.put("finish", "");
		} else if (attributeName.getMode() == 2 || attributeName.getMode() == 4 || attributeName.getMode() == 5) {
			String begin = attributeName.getBegin().substring(0, attributeName.getBegin().length() - 3);
			String finish = attributeName.getFinish().substring(0, attributeName.getFinish().length() - 3);
			map.put("begin", Long.valueOf(begin));
			map.put("finish", Long.valueOf(finish));
		} else if (attributeName.getMode() == 6) {
			map.put("begin", attributeName.getBegin());
			map.put("finish", attributeName.getFinish());
		}

		map.put("unit", attributeName.getUnit());
		map.put("importHint", attributeName.getImportHint());
		Map<String, Object> attributeNameMap = new HashMap<>();
		attributeNameMap.put("attributeName", map);
		attributeNameMap.put("lists", attributeName.getLists());
		result.setData(attributeNameMap);
		return result;
	}

	@ApiOperation(value = "根据条件查询属性名称信息,通用接口，暂未启用")
	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<AttributeNameVO>> findAll(AttributeNameVO attributeNameVO, HttpServletRequest req) {
		logger.info("开始根据条件查询属性名称信息");
		JsonResult<List<AttributeNameVO>> result = new JsonResult<List<AttributeNameVO>>();
		List<AttributeNameVO> list = attributeNameManage.findAll(attributeNameVO);
		result.setData(list);
		return result;
	}

	@ApiOperation(value = "根据条件分页查询属性名称信息,通用接口，暂未启用")
	@RequestMapping(value = "findPageAttributeName")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findPageAttributeName(Pagination page,
			AttributeNameVO attributeNameVO, HttpServletRequest req) {
		logger.info("开始根据条件分页查询属性名称信息!");
		JsonResult<PageResult<Map<String, Object>>> result = new JsonResult<PageResult<Map<String, Object>>>();
		PageResult<Map<String, Object>> pageResult = attributeNameManage.findPageAttributeName(page, attributeNameVO);
		result.setData(pageResult);
		return result;
	}

	/**
	 * 类目添加属性属性显示、排除类目以选择的属性
	 * 
	 * @param categoryId
	 * @param name
	 * @param type
	 * @return
	 */
	@ApiOperation(value = "根据类目id显示可选属性")
	@RequestMapping(value = "attributeNameByCategoryId")
	@ResponseBody
	public JsonResult<List<AttributeNameDTO>> attributeNameByCategoryId(Long categoryId, String name, Integer type) {
		logger.info("开始根据类目id显示可选属性,类目id = {}",categoryId);
		JsonResult<List<AttributeNameDTO>> result = new JsonResult<List<AttributeNameDTO>>();
		Integer specificationProperty = null;
		Integer parameterProperty = null;
		switch (type) {
		case 2:
			specificationProperty = 1;
			break;

		case 3:
			parameterProperty = 1;
			break;

		default:
			break;
		}
		List<AttributeNameDTO> list = attributeNameManage.attributeNameByCategoryId(categoryId, name,
				specificationProperty, parameterProperty);
		result.setData(list);
		return result;
	}

	/*
	 * @RequestMapping(value = "findAllByName")
	 * 
	 * @ResponseBody public JsonResult<List<AttributeNameDTO>>
	 * findAllByName(Long categoryId, String name,Integer type) {
	 * logger.info("开始根据属性名称显示可选属性!"); Integer specificationProperty = null;
	 * Integer parameterProperty = null; switch (type) { case 1: break; case 2:
	 * specificationProperty = 1; break; case 3: parameterProperty = 1; break;
	 * 
	 * default: throw new BusinessException("未定义属性类型"); }
	 * JsonResult<List<AttributeNameDTO>> result = new
	 * JsonResult<List<AttributeNameDTO>>(); List<AttributeNameDTO> list =
	 * attributeNameManage.findAllByName(categoryId,
	 * name,specificationProperty,parameterProperty); result.setData(list);
	 * return result; }
	 */

	@ApiOperation(value = "新增属性名称信息")
	@RequestMapping(value = "saveAttributeName")
	@ResponseBody
	public JsonResult<Long> saveAttributeName(AttributeNameVO attributeNameVO, String list, String begin, String finish,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			attributeNameVO.setPlatformId(platformId);
		}
		logger.info("开始保存属性名称信息");
		if (EmptyUtil.isEmpty(attributeNameVO.getName())) {
			throw new BusinessException("属性名称不能为空");
		}
		if (attributeNameVO.getName().length() > 10) {
			throw new BusinessException("属性名称最多不超过10个字");
		}
		if (EmptyUtil.isEmpty(attributeNameVO.getMode())) {
			throw new BusinessException("属性填写方式为必选项");
		}
		logger.info("!!!!!!!!!!!!!!!!!!!!属性值填写方式为:"+attributeNameVO.getMode());
		//规格属性、参数属性：0为否
		int property=0;
		if(property==attributeNameVO.getSpecificationProperty() && property==attributeNameVO.getParameterProperty()){
			throw new BusinessException("属性设置为必选项");
		}
		List<AttValueDTO> lists = new ArrayList<>(JSONArray.parseArray(list, AttValueDTO.class));
		JsonResult<Long> result = new JsonResult<Long>();
		Long rows = attributeNameManage.saveAttributeName(attributeNameVO, lists, begin, finish);
		result.setData(rows);
		return result;
	}

	@ApiOperation(value = "根据属性id修改属性名称信息")
	@RequestMapping(value = "updateAttributeName")
	@ResponseBody
	public JsonResult<Long> updateAttributeName(AttributeNameVO attributeNameVO, String list, String begin,
			String finish, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			attributeNameVO.setPlatformId(platformId);
		}
		logger.info("根据属性id修改属性名称信息，属性id = {}",attributeNameVO.getId());
		if (EmptyUtil.isEmpty(attributeNameVO.getName())) {
			throw new BusinessException("属性名称不能为空");
		}
		if (attributeNameVO.getName().length() > 10) {
			throw new BusinessException("属性名称最多不超过10个字");
		}
		List<AttValueDTO> lists = new ArrayList<>(JSONArray.parseArray(list, AttValueDTO.class));
		Long rows = attributeNameManage.updateAttributeName(attributeNameVO, lists, begin, finish);
		return JsonResult.success(rows);
	}

	@ApiOperation(value = "根据id删除属性名称信息")
	@RequestMapping(value = "deleteById")
	@ResponseBody
	public JsonResult<Long> deleteById(AttributeNameVO attributeNameVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			attributeNameVO.setPlatformId(platformId);
		}
		logger.info("开始根据id删除属性名称信息");
		JsonResult<Long> result = new JsonResult<Long>();
		try {
			Long rows = attributeNameManage.deleteById(attributeNameVO);
			result.setData(rows);
			return result;
		} catch (Exception e) {
			logger.error("根据id删除属性名称信息异常！", e);
			result.setCode(1);
			result.setError("根据id删除属性名称信息异常：" + e.getMessage());
			return result;
		}
	}

	@ApiOperation(value = "根据spu id和类目id查询属性值信息")
	@RequestMapping(value = "attributeValueByProductIdAndCategoryId")
	@ResponseBody
	public JsonResult<List<AttributeNameDTO>> attributeValueByProductIdAndCategoryId(Long productId, Long categoryId) {
		logger.info("根据spu草稿id和类目id查询属性值信息,spu草稿id = {},类目id = {}",productId,categoryId);
		JsonResult<List<AttributeNameDTO>> result = new JsonResult<List<AttributeNameDTO>>();
		List<AttributeNameDTO> list = attributeNameManage.attributeValueByProductIdAndCategoryId(productId, categoryId);
		result.setData(list);
		return result;
	}
}
