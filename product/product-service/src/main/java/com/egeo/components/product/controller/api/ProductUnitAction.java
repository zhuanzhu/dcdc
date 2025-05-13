package com.egeo.components.product.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.ProductUnitManage;
import com.egeo.components.product.converter.ProductUnitConverter;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.vo.ProductUnitVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/productUnit")
public class ProductUnitAction extends BaseSpringController {
	
	@Resource(name="productUnit")
	private ProductUnitManage productUnitManage;


	// 业务方法：
	@RequestMapping(value = "/findProductUnitById")
	@ResponseBody
	public JsonResult<ProductUnitVO> findProductUnitById(Long id ) {
		
		ProductUnitDTO dto = new ProductUnitDTO();
		dto.setId(id);
		ProductUnitDTO rt = productUnitManage.findProductUnitById(dto);		
		return JsonResult.success(ProductUnitConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findProductUnitAll")
	@ResponseBody
	public JsonResult<List<ProductUnitVO>> findProductUnitAll(ProductUnitVO vo,HttpServletRequest req ) {
		ProductUnitDTO dto = ProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<ProductUnitDTO> rt = productUnitManage.findProductUnitAll(dto);	
		return JsonResult.success(ProductUnitConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findProductUnitOfPage")
	@ResponseBody
	public JsonResult<PageResult<ProductUnitVO>> findProductUnitOfPage(ProductUnitVO vo,Pagination page,HttpServletRequest req ) {
		ProductUnitDTO dto = ProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<ProductUnitDTO> rt = productUnitManage.findProductUnitOfPage(dto, page);
        List<ProductUnitVO> list = ProductUnitConverter.toVO(rt.getList());
        PageResult<ProductUnitVO> result = new PageResult<ProductUnitVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertProductUnitWithTx")
	@ResponseBody
	public JsonResult<Long> insertProductUnitWithTx(ProductUnitVO vo,HttpServletRequest req ) {
		ProductUnitDTO dto = ProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = productUnitManage.insertProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateProductUnitByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateProductUnitByIdWithTx(ProductUnitVO vo,HttpServletRequest req ) {
		CacheUser cacheUser = getCacheUser();
		cacheUser.getCompanyId();
		ProductUnitDTO dto = ProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = productUnitManage.updateProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteProductUnitWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteProductUnitWithTx(ProductUnitVO vo,HttpServletRequest req ) {
		ProductUnitDTO dto = ProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = productUnitManage.deleteProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据puid查询pu所有pu信息
	 * @return
	 */
	@RequestMapping(value = "/productUnitAllByMerchantProductId")
	@ResponseBody
	public JsonResult<Map<String, Object>> productUnitAllByMerchantProductId(Long merchantProductId,
			HttpServletRequest req) {
		String str = req.getHeader("f");
		Integer f = null;
		if (EmptyUtil.isNotEmpty(str)) {
			f = Integer.valueOf(str);
		}
		if(EmptyUtil.isEmpty(f)){
			throw new BusinessException("请求来源不能为空");
		}
		Map<String, Object> rt = productUnitManage.findCommodityProductUnitAllByStandardUnitId(merchantProductId,f);
		return JsonResult.success(rt);

	}
		
}
	