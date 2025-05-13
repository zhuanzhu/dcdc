package com.egeo.components.product.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.AttributeValueManage;
import com.egeo.components.product.vo.AttributeValueVO;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

@Controller
@RequestMapping("/api/product/attributeValue")
public class AttributeValueAction extends BaseSpringController {

	@Resource(name = "attributeValue")
	private AttributeValueManage attributeValueManage;

	@RequestMapping(value = "saveAttributeValue")
	@ResponseBody
	public JsonResult<Long> saveAttributeValue(AttributeValueVO attributeValueVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			attributeValueVO.setPlatformId(platformId);
		}
		logger.info("开始添加节点属性id与属性值数据!");
		JsonResult<Long> result = new JsonResult<Long>();
		Long id = attributeValueManage.saveAttributeValue(attributeValueVO);
		result.setData(id);
		return result;
	}

	@RequestMapping(value = "deleteById")
	@ResponseBody
	public JsonResult<Integer> deleteById(AttributeValueVO attributeValueVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			attributeValueVO.setPlatformId(platformId);
		}
		logger.info("开始根据属性id删除属性数据!");
		JsonResult<Integer> result = new JsonResult<Integer>();
		Integer id = attributeValueManage.deleteById(attributeValueVO);
		result.setData(id);
		return result;
	}

	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<AttributeValueDTO> findById(AttributeValueVO attributeValueVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			attributeValueVO.setPlatformId(platformId);
		}
		logger.info("开始根据id属性值信息,属性值id = {}",attributeValueVO.getId());
		JsonResult<AttributeValueDTO> result = new JsonResult<AttributeValueDTO>();
		AttributeValueDTO attributeValueDTO = attributeValueManage.findById(attributeValueVO);
		result.setData(attributeValueDTO);
		return result;
	}
	/**
	 * 根据属性id查询属性值信息
	 * @param attributeValueVO
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "findByAttributeNameId")
	@ResponseBody
	public JsonResult<List<AttributeValueVO>> findByAttributeNameId(AttributeValueVO attributeValueVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			attributeValueVO.setPlatformId(platformId);
		}
		logger.info("根据属性id查询属性值信息,属性id = {}",attributeValueVO.getAttributeNameId());
		JsonResult<List<AttributeValueVO>> result = new JsonResult<List<AttributeValueVO>>();
		List<AttributeValueVO> List = attributeValueManage.findByAttributeNameId(attributeValueVO);
		result.setData(List);
		return result;
	}
	
	@RequestMapping(value = "findByProductIdAndAttNameId")
	@ResponseBody
	public JsonResult<List<AttributeValueVO>> findByProductIdAndAttNameId(Long productId,Long attNameId) {
		logger.info("开始排除根据spu草稿id和属性id查询属性值查询属性值信息,spu草稿id = {},属性id = {}",productId,attNameId);
		JsonResult<List<AttributeValueVO>> result = new JsonResult<List<AttributeValueVO>>();
		List<AttributeValueVO> list = attributeValueManage.findByProductIdAndAttNameId(productId,attNameId);
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value = "attributeValueByProductIdAndAttNameId")
	@ResponseBody
	public JsonResult<List<AttributeValueVO>> attributeValueByProductIdAndAttNameId(Long productId,Long categoryId) {
		logger.info("根据spu草稿id和属性id查询属性值信息,spu草稿id = {},属性id = {}",productId,categoryId);
		JsonResult<List<AttributeValueVO>> result = new JsonResult<List<AttributeValueVO>>();
		List<AttributeValueVO> list = attributeValueManage.attributeValueByProductIdAndAttNameId(productId,categoryId);
		result.setData(list);
		return result;
	}
}
