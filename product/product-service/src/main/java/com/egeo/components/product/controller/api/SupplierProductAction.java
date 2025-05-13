package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SupplierProductManage;
import com.egeo.components.product.converter.SupplierProductConverter;
import com.egeo.components.product.dto.SupplierProductDTO;
import com.egeo.components.product.vo.SupplierProductVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/supplierProduct")
public class SupplierProductAction extends BaseSpringController {
	
	@Resource(name="supplierProduct")
	private SupplierProductManage supplierProductManage;


	// 业务方法：
	@RequestMapping(value = "/findSupplierProductById")
	@ResponseBody
	public JsonResult<SupplierProductVO> findSupplierProductById(Long id ) {
		
		SupplierProductDTO dto = new SupplierProductDTO();
		dto.setId(id);
		SupplierProductDTO rt = supplierProductManage.findSupplierProductById(dto);		
		return JsonResult.success(SupplierProductConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSupplierProductAll")
	@ResponseBody
	public JsonResult<List<SupplierProductVO>> findSupplierProductAll(SupplierProductVO vo,HttpServletRequest req ) {
		SupplierProductDTO dto = SupplierProductConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SupplierProductDTO> rt = supplierProductManage.findSupplierProductAll(dto);	
		return JsonResult.success(SupplierProductConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSupplierProductOfPage")
	@ResponseBody
	public JsonResult<PageResult<SupplierProductVO>> findSupplierProductOfPage(SupplierProductVO vo,Pagination page,HttpServletRequest req ) {
		SupplierProductDTO dto = SupplierProductConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SupplierProductDTO> rt = supplierProductManage.findSupplierProductOfPage(dto, page);
        List<SupplierProductVO> list = SupplierProductConverter.toVO(rt.getList());
        PageResult<SupplierProductVO> result = new PageResult<SupplierProductVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSupplierProductWithTx")
	@ResponseBody
	public JsonResult<Long> insertSupplierProductWithTx(SupplierProductVO vo,HttpServletRequest req ) {
		SupplierProductDTO dto = SupplierProductConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = supplierProductManage.insertSupplierProductWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSupplierProductByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSupplierProductByIdWithTx(SupplierProductVO vo,HttpServletRequest req ) {
		SupplierProductDTO dto = SupplierProductConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = supplierProductManage.updateSupplierProductWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSupplierProductWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSupplierProductWithTx(SupplierProductVO vo,HttpServletRequest req ) {
		SupplierProductDTO dto = SupplierProductConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = supplierProductManage.deleteSupplierProductWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	