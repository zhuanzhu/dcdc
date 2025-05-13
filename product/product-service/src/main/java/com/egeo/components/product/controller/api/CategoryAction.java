package com.egeo.components.product.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.CategoryManage;
import com.egeo.components.product.vo.CategoryVO;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.exception.BusinessException;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Controller
@RequestMapping("/api/product/category")
public class CategoryAction extends BaseSpringController {

	public XLogger logger = XLogger.getLogger(this.getClass().getName());
	
	@Resource(name = "category")
	private CategoryManage categoryManage;

	/*
	 * 根据条件查询类目（递归成树结构，只返回id和名称）
	 */
	@RequestMapping(value = "categoryList")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findPage(CategoryVO categoryVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryVO.setPlatformId(platformId);
		}
		logger.info("开始查询所有类目!");
		long start = System.currentTimeMillis();
		JsonResult<List<Map<String, Object>>> result = new JsonResult<List<Map<String, Object>>>();
		List<Map<String, Object>> list = categoryManage.findAll(categoryVO);
		result.setData(list);
		logger.info("开始所有类目耗时:"+(System.currentTimeMillis()-start));
		return result;
	}

	/*
	 * 根据条件查询类目
	 */
	@RequestMapping(value = "findAllList")
	@ResponseBody
	public JsonResult<List<CategoryDTO>> findAllList(CategoryVO categoryVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryVO.setPlatformId(platformId);
		}
		logger.info("开始根据条件查询所有类目!");
		JsonResult<List<CategoryDTO>> result = new JsonResult<List<CategoryDTO>>();
		List<CategoryDTO> list = categoryManage.findAllList(categoryVO);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "idToName")
	@ResponseBody
	public JsonResult<List<String>> idToName(String ids) {
		logger.info("开始把id转换成name!");
		JsonResult<List<String>> result = new JsonResult<List<String>>();
		List<String> list = categoryManage.idToName(ids);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "idsByCategoryId")
	@ResponseBody
	public JsonResult<List<Long>> idsByCategoryId(Long categoryId) {
		logger.info("开始根据类目id查询所有父类id!");
		JsonResult<List<Long>> result = new JsonResult<List<Long>>();
		List<Long> list = categoryManage.idsByCategoryId(categoryId);
		//bug3055Test
		logger.info("父类ID:");
		for ( int i = 0 ; i < list.size() ; i++) {
			logger.info(list.get(i).toString());
		}
		//test end
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "modifyCategory")
	@ResponseBody
	public JsonResult<String> modifyCategory(CategoryVO categoryVO, String listSort,String tagIds, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryVO.setPlatformId(platformId);
		}
		logger.info("修改类目!");
		JsonResult<String> result = new JsonResult<String>();
		if (EmptyUtil.isEmpty(categoryVO.getId())) {
			return JsonResult.fail("缺少类目id");
		}
		if (EmptyUtil.isEmpty(categoryVO.getName())) {
			return JsonResult.fail("类目名称不能为空");
		}
		if (EmptyUtil.isNotEmpty(listSort)) {
			for (int i = 0; i < listSort.length(); i++) {
				if (!Character.isDigit(listSort.charAt(i))) {
					return JsonResult.fail("类目排序必须为数字");
				}
			}
		}
		if(categoryVO.getName().length() > 30){
			throw new BusinessException("类目名称长度不得超过30位");
		}
		List<Long> tagIdList = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(tagIds)){
			tagIdList = new ArrayList<>(JSONArray.parseArray(tagIds, Long.class));
		}
		String rt = categoryManage.modifyCategory(categoryVO, listSort,tagIdList, req);
		result.setData(rt);
		return result;
	}

	@RequestMapping(value = "deleteCategory")
	@ResponseBody
	public JsonResult<String> deleteCategory(CategoryVO categoryVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryVO.setPlatformId(platformId);
		}
		logger.info("删除类目id = !" + categoryVO.getId());
		JsonResult<String> result = new JsonResult<String>();
		if (EmptyUtil.isEmpty(categoryVO.getId())) {
			result.setCode(1);
			result.setError("缺少类目id");
			return result;
		}
		String rt = categoryManage.deleteCategory(categoryVO, req);
		result.setData(rt);
		return result;
	}

	// 根据类目id查询类目
	@RequestMapping(value = "findCategoryById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCategoryById(Long id) {
		logger.info("根据类目id查询类目! id = " + id);
		if (EmptyUtil.isEmpty(id)) {
			return JsonResult.fail("缺少类目id");
		}
		Map<String, Object> rt = categoryManage.findCategorybyId(id);
		return JsonResult.success(rt);
	}

}
