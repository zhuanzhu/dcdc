package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.ProductPictureManage;
import com.egeo.components.product.converter.ProductPictureConverter;
import com.egeo.components.product.vo.ProductPictureVO;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/productPicture")
public class ProductPictureAction extends BaseSpringController {
	
	@Resource(name="productPicture")
	private ProductPictureManage productPictureManage;


	// 业务方法：
	@RequestMapping(value = "/findProductPictureById")
	@ResponseBody
	public JsonResult<ProductPictureVO> findProductPictureById(Long id ) {
		
		ProductPictureDTO dto = new ProductPictureDTO();
		dto.setId(id);
		ProductPictureDTO rt = productPictureManage.findProductPictureById(dto);		
		return JsonResult.success(ProductPictureConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findProductPictureAll")
	@ResponseBody
	public JsonResult<List<ProductPictureVO>> findProductPictureAll(ProductPictureVO vo,HttpServletRequest req ) {
		ProductPictureDTO dto = ProductPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<ProductPictureDTO> rt = productPictureManage.findProductPictureAll(dto);	
		return JsonResult.success(ProductPictureConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findProductPictureOfPage")
	@ResponseBody
	public JsonResult<PageResult<ProductPictureVO>> findProductPictureOfPage(ProductPictureVO vo,Pagination page,HttpServletRequest req ) {
		ProductPictureDTO dto = ProductPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<ProductPictureDTO> rt = productPictureManage.findProductPictureOfPage(dto, page);
        List<ProductPictureVO> list = ProductPictureConverter.toVO(rt.getList());
        PageResult<ProductPictureVO> result = new PageResult<ProductPictureVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertProductPictureWithTx")
	@ResponseBody
	public JsonResult<Long> insertProductPictureWithTx(ProductPictureVO vo,HttpServletRequest req ) {
		ProductPictureDTO dto = ProductPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = productPictureManage.insertProductPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateProductPictureByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateProductPictureByIdWithTx(ProductPictureVO vo,HttpServletRequest req ) {
		ProductPictureDTO dto = ProductPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = productPictureManage.updateProductPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteProductPictureWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteProductPictureWithTx(ProductPictureVO vo,HttpServletRequest req ) {
		ProductPictureDTO dto = ProductPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = productPictureManage.deleteProductPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	