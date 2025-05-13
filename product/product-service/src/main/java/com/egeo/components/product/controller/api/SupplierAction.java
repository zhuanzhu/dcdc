package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SupplierManage;
import com.egeo.components.product.converter.SupplierConverter;
import com.egeo.components.product.dto.SupplierDTO;
import com.egeo.components.product.vo.SupplierVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/supplier")
public class SupplierAction extends BaseSpringController {
	
	@Resource(name="supplier")
	private SupplierManage supplierManage;


	// 业务方法：
	@RequestMapping(value = "/findSupplierById")
	@ResponseBody
	public JsonResult<SupplierVO> findSupplierById(Long id ) {
		
		SupplierDTO dto = new SupplierDTO();
		dto.setId(id);
		SupplierDTO rt = supplierManage.findSupplierById(dto);		
		return JsonResult.success(SupplierConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSupplierAll")
	@ResponseBody
	public JsonResult<List<SupplierVO>> findSupplierAll(SupplierVO vo,HttpServletRequest req ) {
		SupplierDTO dto = SupplierConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SupplierDTO> rt = supplierManage.findSupplierAll(dto);	
		return JsonResult.success(SupplierConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSupplierOfPage")
	@ResponseBody
	public JsonResult<PageResult<SupplierVO>> findSupplierOfPage(SupplierVO vo,Pagination page,HttpServletRequest req ) {
		SupplierDTO dto = SupplierConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SupplierDTO> rt = supplierManage.findSupplierOfPage(dto, page);
        List<SupplierVO> list = SupplierConverter.toVO(rt.getList());
        PageResult<SupplierVO> result = new PageResult<SupplierVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSupplierWithTx")
	@ResponseBody
	public JsonResult<Long> insertSupplierWithTx(SupplierVO vo,HttpServletRequest req ) {
		SupplierDTO dto = SupplierConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = supplierManage.insertSupplierWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSupplierByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSupplierByIdWithTx(SupplierVO vo,HttpServletRequest req ) {
		SupplierDTO dto = SupplierConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = supplierManage.updateSupplierWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSupplierWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSupplierWithTx(SupplierVO vo,HttpServletRequest req ) {
		SupplierDTO dto = SupplierConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = supplierManage.deleteSupplierWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	