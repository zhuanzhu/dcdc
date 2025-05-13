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
import com.egeo.components.product.business.ProductCauseManage;
import com.egeo.components.product.business.ProductManage;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.vo.AttName;
import com.egeo.components.product.vo.AttNameValueVO;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.vo.AttributeValueVO;
import com.egeo.components.product.vo.BrandVO;
import com.egeo.components.product.vo.CategoryAttValueVO;
import com.egeo.components.product.vo.PictureVO;
import com.egeo.components.product.vo.ProductCauseVO;
import com.egeo.components.product.vo.ProductDescriptionVO;
import com.egeo.components.product.vo.ProductPictureVO;
import com.egeo.components.product.vo.ProductVO;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/product/product")
public class ProductAction extends BaseSpringController {

	@Resource(name = "product")
	private ProductManage productManage;

	@Resource(name = "productCause")
	private ProductCauseManage productCauseManage;

	@RequestMapping(value = "productList")
	@ResponseBody
	public JsonResult<PageResult<ProductDTO>> findPage(Pagination page, ProductVO productVO, HttpServletRequest req) {
		logger.info("开始查询所有产品!");
		String preLineIdStr = productVO.getPrecautiousLineIdStr();
		String[] ids = preLineIdStr.split(",");
		ArrayList<Long> idList = new ArrayList<>();
		for (int i=0; i<ids.length ; i++) {
			idList.add(Long.parseLong(ids[i]));
		}
		productVO.setPrecautiousLineIds(idList);
		/*logger.info("预警线id集合："+ productVO.getPrecautiousLineIds());
		logger.info("预警天数："+ productVO.getPrecautiousStart() + "," + productVO.getPrecautiousEnd() );*/
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productVO.setPlatformId(platformId);
		}

		List<String> nameList = JSONArray.parseArray(productVO.getName(),String.class);
		JsonResult<PageResult<ProductDTO>> result = new JsonResult<PageResult<ProductDTO>>();
		productVO.setEnterpriseId(null);
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				productVO.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				productVO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());	
			}
		}else {
			return JsonResult.fail("接口异常");
		}
		PageResult<ProductDTO> pageResult = productManage.findPage(page, productVO,nameList);
		result.setData(pageResult);
		return result;
	}

	@RequestMapping(value = "productByCategoryId")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitDTO>> productByCategoryId(Pagination page, ProductVO productVO,
			Long categoryId, HttpServletRequest req) {
		logger.info("开始根据类目id查询产品,类目id: {}", categoryId);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productVO.setPlatformId(platformId);
		}
		JsonResult<PageResult<StandardProductUnitDTO>> result = new JsonResult<PageResult<StandardProductUnitDTO>>();
		productVO.setEnterpriseId(null);
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				productVO.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				productVO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());	
			}
		}
		PageResult<StandardProductUnitDTO> pageResult = productManage.productByCategoryId(page, productVO, categoryId);
		result.setData(pageResult);
		return result;
	}

	@RequestMapping(value = "productByCategoryIdQuick")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> productByCategoryIdQuick(Pagination page, ProductVO productVO,
			Long categoryId, HttpServletRequest req) {
		logger.info("开始根据类目id查询产品,类目id: {}", categoryId);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productVO.setPlatformId(platformId);
		}
		JsonResult<PageResult<Map<String, Object>>> result = new JsonResult<PageResult<Map<String, Object>>>();
		productVO.setEnterpriseId(null);
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				productVO.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				productVO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());	
			}
		}
		PageResult<Map<String, Object>> pageResult = productManage.productByCategoryIdQuick(page, productVO, categoryId);
		result.setData(pageResult);
		return result;
	}
	@RequestMapping(value = "saveProduct")
	@ResponseBody
	public JsonResult<String> saveProduct(ProductVO productVO, ProductDescriptionVO productDescriptionVO,
			String parameterAttList, PictureVO pictureVO, BrandVO brandVO, String listss, String listStyleImage,
			String apecifications, HttpServletRequest req) {
		logger.info("开始保存产品信息!");
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		List<AttNameValueVO> list = new ArrayList<AttNameValueVO>(JSONArray.parseArray(listss, AttNameValueVO.class));
		List<PictureVO> lists = new ArrayList<PictureVO>(JSONArray.parseArray(listStyleImage, PictureVO.class));
		List<AttName> apecification = new ArrayList<AttName>(JSONArray.parseArray(apecifications, AttName.class));
		List<AttNameValueVO> parameterAtts = new ArrayList<AttNameValueVO>(
				JSONArray.parseArray(parameterAttList, AttNameValueVO.class));
		pictureVO.setStyleImage(lists);
		productVO.setEnterpriseId(null);
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				productVO.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				productVO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());	
			}
		}
		rows = productManage.saveProduct(productVO, productDescriptionVO, pictureVO, brandVO, list, apecification,
				parameterAtts);
		result.setData(rows);
		return result;
	}



	@RequestMapping(value = "productById")
	@ResponseBody
	public JsonResult<ProductVO> productById(ProductVO productVO, CategoryAttValueVO categoryAttValueVO,
			ProductDescriptionVO productDescriptionVO, PictureVO pictureVO, ProductPictureVO productPictureVO,
			AttributeValueVO attributeValueVO, AttributeNameVO attributeNameVO, HttpServletRequest req) {
		logger.info("开始根据产品id查询产品信息,产品id：{}", productVO.getId());
		JsonResult<ProductVO> result = new JsonResult<ProductVO>();
		ProductVO product = productManage.productById(productVO, categoryAttValueVO, productDescriptionVO, pictureVO,
				productPictureVO, attributeValueVO, attributeNameVO);
		result.setData(product);
		return result;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<ProductVO>> findAll(ProductVO productVO, HttpServletRequest req) {
		logger.info("开始根据产品条件查询产品信息!");
		JsonResult<List<ProductVO>> result = new JsonResult<List<ProductVO>>();
		productVO.setEnterpriseId(null);
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				productVO.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				productVO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());	
			}
		}
		List<ProductVO> list = productManage.findAll(productVO);
		result.setData(list);
		return result;
	}

	/**
	 *预警天数校验 true :校验通过，预警级别天数设置正确
	 */
	private boolean checkDays (List<Integer> days) {
		boolean flag = true;
		for (Integer e : days) {
			if (e <= 0 ) {
				flag = false;
				return  flag;
			}
		}

		for (int i = 0; i < days.size()-1 ; i++) {
			for (int j = i+1; j < days.size(); j++) {
				if (days.get(i) <= days.get(j) ) {
					flag = false;
					break;
				}
			}
			if (!flag) {
				break;
			}
		}
		return flag;
	}

	@RequestMapping(value = "updateReferLink")
	@ResponseBody
	public JsonResult<String> updateReferLink(Long productId, String link, HttpServletRequest req) {
		if (EmptyUtil.isEmpty(productId) ) {

			return JsonResult.fail("参数错误"); 
		}
		ProductVO productVO = new ProductVO();
		productVO.setId(productId);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productVO.setPlatformId(platformId);
		}
		if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getType()==null) {
			return JsonResult.fail("不允许操作"); 
		}
		if(RuntimeContext.cacheUser().getType().intValue()==5) {
			logger.info("开始修改产品参考链接,产品id：" + productId +" link:"+link);
			if(EmptyUtil.isEmpty(link)) {
				return JsonResult.fail("参数错误");
			}
			ProductVO checkProduct = productManage.ProductById(productVO);
			if(checkProduct!=null && checkProduct.getId()!=null && checkProduct.getSupplierId()!=null &&checkProduct.getSupplierId().longValue()==RuntimeContext.cacheUser().getEnterpriseId().longValue()) {
				productVO.setReferlink(link);
				int rows = productManage.updateProductReferLink(productVO);
				JsonResult<String> result = new JsonResult<String>();
				result.setData(rows+"");
				return result;
			}
		}else if(RuntimeContext.cacheUser().getType().intValue()==1) {
			logger.info("开始清除产品参考链接,产品id：" + productId );
				if(!EmptyUtil.isEmpty(link)) {
					return JsonResult.fail("平台用户只能执行清除操作");
				}
				ProductVO checkProduct = productManage.ProductById(productVO);
				if(checkProduct!=null && checkProduct.getId()!=null) {
					productVO.setReferlink(null);
					int rows = productManage.updateProductReferLink(productVO);
					JsonResult<String> result = new JsonResult<String>();
					result.setData(rows+"");
					return result;
				}
		}
		return JsonResult.fail("不允许操作"); 
	}
	@RequestMapping(value = "updateProduct")
	@ResponseBody
	public JsonResult<String> updateProduct(ProductVO productVO, ProductDescriptionVO productDescriptionVO,
			String parameterAttList, PictureVO pictureVO, BrandVO brandVO, String listss, String priceJson,String listStyleImage,
			Long showProductAttNameId, HttpServletRequest req) {
		logger.info("开始修改产品信息,产品id：" + productVO.getId());
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productVO.setPlatformId(platformId);
		}
		List<AttNameValueVO> list = new ArrayList<>();
		List<PictureVO> lists = new ArrayList<>();
		List<AttNameValueVO> parameterAtts = new ArrayList<>();
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		if (EmptyUtil.isNotEmpty(listss)) {
			list = new ArrayList<AttNameValueVO>(JSONArray.parseArray(listss, AttNameValueVO.class));
		}
		if (EmptyUtil.isNotEmpty(listStyleImage)) {
			lists = new ArrayList<PictureVO>(JSONArray.parseArray(listStyleImage, PictureVO.class));
		}
		if (EmptyUtil.isNotEmpty(priceJson)) {
			productVO.setPriceDetail(priceJson);;
		}
		if (EmptyUtil.isNotEmpty(parameterAttList)) {
			parameterAtts = new ArrayList<AttNameValueVO>(JSONArray.parseArray(parameterAttList, AttNameValueVO.class));
		}
		if (EmptyUtil.isEmpty(pictureVO.getUrl())) {
			return JsonResult.fail("列表图片不能为空");
		}
		if (EmptyUtil.isEmpty(lists)) {
			return JsonResult.fail("轮播图图片不能为空");
		}

		//天数校验 List<AttNameValueVO> list
		List<Integer> days = new ArrayList<>();
		for ( AttNameValueVO attName : list) {
			if (Integer.parseInt(attName.getKey()) == 6 ||
					Integer.parseInt(attName.getKey()) == 7 ||
						Integer.parseInt(attName.getKey()) == 8 ) {
				if (EmptyUtil.isNotEmpty(attName.getValue())) {
					days.add(Integer.parseInt(attName.getValue()));
				}
			}

		}

		boolean flag = checkDays(days);
		if (!flag) {
			result.setCode(-1);
			result.setError("预警期设置不规范，请按提示重新填写");
			return result;
		}
		if (!productManage.checkProductPriceDetail(productVO)){
			result.setCode(-1);
			result.setError("商品税率信息不完善，请重新填写");
			return result;
		}
		pictureVO.setStyleImage(lists);

		
		productVO.setEnterpriseId(null);
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				productVO.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				productVO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());	
			}
		}
		rows = productManage.updateProduct(productVO, productDescriptionVO, pictureVO, brandVO, list, parameterAtts,
				showProductAttNameId);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "updateAndAuditingProduct")
	@ResponseBody
	public JsonResult<String> updateAndAuditingProduct(ProductVO productVO, ProductDescriptionVO productDescriptionVO,
			String parameterAttList, PictureVO pictureVO, BrandVO brandVO, String listss, String listStyleImage,
			Long showProductAttNameId, HttpServletRequest req) {
		logger.info("开始修改产品信息,产品id：" + productVO.getId());
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productVO.setPlatformId(platformId);
		}

		List<AttNameValueVO> list = new ArrayList<>();
		List<PictureVO> lists = new ArrayList<>();
		List<AttNameValueVO> parameterAtts = new ArrayList<>();
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		if (EmptyUtil.isNotEmpty(listss)) {
			list = new ArrayList<AttNameValueVO>(JSONArray.parseArray(listss, AttNameValueVO.class));
		}
		if (EmptyUtil.isNotEmpty(listStyleImage)) {
			lists = new ArrayList<PictureVO>(JSONArray.parseArray(listStyleImage, PictureVO.class));
		}
		if (EmptyUtil.isNotEmpty(parameterAttList)) {
			parameterAtts = new ArrayList<AttNameValueVO>(JSONArray.parseArray(parameterAttList, AttNameValueVO.class));
		}
		if (EmptyUtil.isEmpty(pictureVO.getUrl())) {
			return JsonResult.fail("列表图片不能为空");
		}
		if (EmptyUtil.isEmpty(lists)) {
			return JsonResult.fail("轮播图图片不能为空");
		}
		CacheUser userCache = this.getCacheUser();
		// 获得客户端的ip地址
		String ip = req.getRemoteAddr();
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());

		}
		Long userId = userCache.getId();
		String userName = userCache.getName();
		//天数校验 List<AttNameValueVO> list
		List<Integer> days = new ArrayList<>();
		for ( AttNameValueVO attName : list) {
			if (Integer.parseInt(attName.getKey()) == 6 ||
					Integer.parseInt(attName.getKey()) == 7 ||
						Integer.parseInt(attName.getKey()) == 8 ) {
				if (EmptyUtil.isNotEmpty(attName.getValue())) {
					days.add(Integer.parseInt(attName.getValue()));
				}
			}

		}

		boolean flag = checkDays(days);
		if (!flag) {
			result.setCode(-1);
			result.setError("预警期设置不规范，请按提示重新填写");
			return result;
		}
		pictureVO.setStyleImage(lists);

		productVO.setEnterpriseId(null);
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				productVO.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				productVO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());	
			}
		}
		rows = productManage.updateProduct(productVO, productDescriptionVO, pictureVO, brandVO, list, parameterAtts,
				showProductAttNameId);
		ProductVO upStatusVo = new ProductVO();
		upStatusVo.setId(productVO.getId());
		
		upStatusVo.setStatus(1);
		logger.info("开始修改产品为审核状态!");
		productManage.audit(upStatusVo);
		upStatusVo.setStatus(3);
		logger.info("开始修改产品状态通过,产品id：" + upStatusVo.getId());
		boolean i = productManage.updateStatus(upStatusVo, userId, userName, ip, mac);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "updateStatus")
	@ResponseBody
	public JsonResult<String> updateStatus(ProductVO productVO, HttpServletRequest req) {
		logger.info("开始修改产品状态是否通过,产品id：" + productVO.getId());
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productVO.setPlatformId(platformId);
		}
		CacheUser userCache = this.getCacheUser();
		// 获得客户端的ip地址
		String ip = req.getRemoteAddr();
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());

		}
		Long userId = userCache.getId();
		String userName = userCache.getName();
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		boolean i = productManage.updateStatus(productVO, userId, userName, ip, mac);
		if (i) {
			rows = "审核成功";
		} else {
			rows = "审核失败";
		}
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "audit")
	@ResponseBody
	public JsonResult<String> audit(ProductVO productVO, HttpServletRequest req) {
		JsonResult<String> result = new JsonResult<String>();
		if (productVO.getStatus() == 1) {
			logger.info("开始修改产品为审核状态!");
			productManage.audit(productVO);
			result.setData("提交审核成功");
			return result;
		} else if (productVO.getStatus() == 4) {
			List<ProductCauseVO> list = productCauseManage.findByProductId(productVO.getId());
			result.setData(list.get(list.size() - 1).getCause());
			return result;
		} else {
			result.setCode(1);
			result.setError("修改产品状态异常：");
			return result;
		}
	}

	@RequestMapping(value = "auditList")
	@ResponseBody
	public JsonResult<PageResult<ProductDTO>> auditList(Pagination page, ProductVO productVO, HttpServletRequest req) {
		logger.info("开始查询所有需要审核产品!");
		//初始化预警属性id
		String preLineIdStr = productVO.getPrecautiousLineIdStr();
		String[] ids = preLineIdStr.split(",");
		ArrayList<Long> idList = new ArrayList<>();
		for (int i=0; i<ids.length ; i++) {
			idList.add(Long.parseLong(ids[i]));
		}
		productVO.setPrecautiousLineIds(idList);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			productVO.setPlatformId(platformId);
		}
		JsonResult<PageResult<ProductDTO>> result = new JsonResult<PageResult<ProductDTO>>();
		// 所有状态为2的所有产品为审核产品
		productVO.setStatus(2);
		PageResult<ProductDTO> pageResult = productManage.findPage(page, productVO, null);
		result.setData(pageResult);
		return result;
	}

	@RequestMapping(value = "passAllAudit")
	@ResponseBody
	public JsonResult<String> passAllAudit(String ids, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		Long platformId = null;
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		CacheUser userCache = this.getCacheUser();
		// 获得客户端的ip地址
		String ip = req.getRemoteAddr();
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());

		}
		Long userId = userCache.getId();
		String userName = userCache.getName();

		logger.info("开始批量通过需要审核产品!");
		JsonResult<String> result = new JsonResult<String>();
		productManage.passAllAudit(ids, userId, userName, ip, mac,platformId);
		result.setData("批量通过需要审核产品成功！");
		return result;
	}

	@RequestMapping(value = "deleteProduct")
	@ResponseBody
	public JsonResult<String> deleteProduct(ProductVO productVO, HttpServletRequest req) {
		logger.info("开始删除产品信息!");
		String rows = null;
		JsonResult<String> result = new JsonResult<String>();
		rows = productManage.deleteProduct(productVO);
		result.setData(rows);
		return result;
	}

	/*
	 * @RequestMapping(value = "ProductById")
	 * 
	 * @ResponseBody public JsonResult<ProductVO> ProductById(ProductVO
	 * productVO, HttpServletRequest req) { logger.info("根据id查询产品信息!");
	 * JsonResult<ProductVO> result = new JsonResult<ProductVO>(); ProductVO
	 * product = productManage.ProductById(productVO); result.setData(product);
	 * return result; }
	 */
	@RequestMapping(value = "updateAvailable")
	@ResponseBody
	public JsonResult<Integer> updateAvailable(ProductVO productVO, String ids, HttpServletRequest req) {
		logger.info("设置是否启用");
		JsonResult<Integer> result = new JsonResult<Integer>();
		Integer i = productManage.updateAvailable(productVO, ids);
		result.setData(i);
		return result;
	}

}
