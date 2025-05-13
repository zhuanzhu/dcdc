package com.egeo.components.product.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.ProductAttValueManage;
import com.egeo.components.product.vo.ProductAttValueVO;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

@Controller
@RequestMapping("/api/product/productAttValue")
public class ProductAttValueAction extends BaseSpringController {

	@Resource(name = "productAttValue")
	private ProductAttValueManage productAttValueManage;

	@RequestMapping(value = "saveProductAttValue")
	@ResponseBody
	public JsonResult<Long> saveProductAttValue(ProductAttValueVO productAttValueVO, HttpServletRequest req) {
		logger.info("开始添加产品属性与属性值的关系!");
		JsonResult<Long> result = new JsonResult<Long>();
		Long id = productAttValueManage.saveProductAttValue(productAttValueVO);
		result.setData(id);
		return result;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<ProductAttValueDTO>> findAll(ProductAttValueVO productAttValueVO, HttpServletRequest req) {
		logger.info("开始根据条件查询 属性与属性值的关系!");
		JsonResult<List<ProductAttValueDTO>> result = new JsonResult<List<ProductAttValueDTO>>();
		List<ProductAttValueDTO> list = productAttValueManage.findAll(productAttValueVO);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "deleteByProductAttNameId")
	@ResponseBody
	public JsonResult<String> deleteByProductAttNameId(ProductAttValueVO productAttValueVO, HttpServletRequest req) {
		logger.info("开始根据产品属性id删除属性与属性值的关系!");
		JsonResult<String> result = new JsonResult<String>();
		String rows = productAttValueManage.deleteByProductAttNameId(productAttValueVO);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "deleteByMuchProductAttNameId")
	@ResponseBody
	public JsonResult<String> deleteByMuchProductAttNameId(String productAttNameIds) {
		logger.info("开始根据产品属性id批量删除属性与属性值的关系!");
		JsonResult<String> result = new JsonResult<String>();
		String rows = productAttValueManage.deleteByMuchProductAttNameId(productAttNameIds);
		result.setData(rows);
		return result;
	}
	
	@RequestMapping(value = "saveProductAttValueByProductIdAndAttnameId")
	@ResponseBody
	public JsonResult<Long> saveProductAttValueByProductIdAndAttnameId(Long productId,String productAttValueList,HttpServletRequest req) {
		logger.info("开始根据产品id和属性id保存产品属性值信息");
		Long platformId = null;
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		JsonResult<Long> result = new JsonResult<Long>();
		List<ProductAttValueVO> productAttValueVOList = null;
		if(EmptyUtil.isNotEmpty(productAttValueList)){
			productAttValueVOList = new ArrayList<ProductAttValueVO>(JSONArray.parseArray(productAttValueList, ProductAttValueVO.class));
		}
		if(EmptyUtil.isEmpty(productAttValueVOList)){
			throw new BusinessException(BusinessExceptionConstant.NO_CHOICE_ATT_VALUE,"请选择属性值");
		}
		Long product = productAttValueManage.saveProductAttValueByProductIdAndAttnameId(productId,productAttValueVOList,platformId);
		result.setData(product);
		return result;
	}
	
	@RequestMapping(value = "deleteById")
	@ResponseBody
	public JsonResult<Integer> deleteById(ProductAttValueVO productAttValueVO, Long productId, HttpServletRequest req) {
		logger.info("开始根据产品属性值id删除产品属性值的关系!");
		JsonResult<Integer> result = new JsonResult<Integer>();
		int rows = productAttValueManage.deleteById(productAttValueVO,productId);
		result.setData(rows);
		return result;
	}
	/**
	 * 根据spu规格属性值草稿id保存spu规格图片
	 * @param productAttValueId
	 * @param pictureUrl
	 * @return
	 */
	@RequestMapping(value = "saveImgByProductAttValueId")
	@ResponseBody
	public JsonResult<Boolean> saveImgByProductAttValueId(Long productAttValueId, String pictureUrl) {
		logger.info("根据spu规格属性值草稿id保存spu规格图片，spu规格属性值草稿id："+productAttValueId+",spu规格图片:"+pictureUrl);
		boolean rows = productAttValueManage.saveImgByProductAttValueId(productAttValueId,pictureUrl);
		return JsonResult.success(rows);
	}
	
	/**
	 * 根据spu规格属性草稿id删除spu规格图片
	 * @param productAttValueId
	 * @param pictureUrl
	 * @return
	 */
	@RequestMapping(value = "delImgByProductAttNameId")
	@ResponseBody
	public JsonResult<Boolean> delImgByProductAttNameId(Long productAttNameId) {
		logger.info("根据spu规格属性草稿id删除spu规格图片，spu规格属性草稿id："+productAttNameId);
		boolean rows = productAttValueManage.delImgByProductAttNameId(productAttNameId);
		return JsonResult.success(rows);
	}
	
	/**
	 * 根据属性属性值和spuid删除规格信息
	 * @param productId
	 * @param attributeNameId
	 * @param attributeValueId
	 * @return
	 */
	@RequestMapping(value = "delByAttIdAndSpuId")
	@ResponseBody
	public JsonResult<Boolean> delByAttIdAndSpuIdWithTx(Long productId,Long attributeNameId,Long attributeValueId,Long productAttValueId) {
		logger.info("根据属性属性值和puid删除规格信息，puId："+productId+",属性id："+attributeNameId+",属性值id："+attributeValueId+"，pu属性值id："+productAttValueId);
		if(EmptyUtil.isEmpty(productId)){
			return JsonResult.fail("puId不能为空");
		}
		if(EmptyUtil.isEmpty(attributeNameId)){
			return JsonResult.fail("属性Id不能为空");
		}
		if(EmptyUtil.isEmpty(attributeValueId)){
			return JsonResult.fail("属性值Id不能为空");
		}
		if(EmptyUtil.isEmpty(productAttValueId)){
			return JsonResult.fail("pu属性值不能为空");
		}
		
		boolean rows = productAttValueManage.delByAttIdAndSpuIdWithTx(productId,attributeNameId,attributeValueId,productAttValueId);
		return JsonResult.success(rows);
	}
}
