package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitRecordStoreManage;
import com.egeo.components.product.converter.StandardUnitRecordStoreConverter;
import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;
import com.egeo.components.product.vo.StandardUnitRecordStoreVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitRecordStore")
public class StandardUnitRecordStoreAction extends BaseSpringController {
	
	@Resource(name="standardUnitRecordStore")
	private StandardUnitRecordStoreManage standardUnitRecordStoreManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordStoreById")
	@ResponseBody
	public JsonResult<StandardUnitRecordStoreVO> findStandardUnitRecordStoreById(Long id ) {
		
		StandardUnitRecordStoreDTO dto = new StandardUnitRecordStoreDTO();
		dto.setId(id);
		StandardUnitRecordStoreDTO rt = standardUnitRecordStoreManage.findStandardUnitRecordStoreById(dto);		
		return JsonResult.success(StandardUnitRecordStoreConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordStoreAll")
	@ResponseBody
	public JsonResult<List<StandardUnitRecordStoreVO>> findStandardUnitRecordStoreAll(StandardUnitRecordStoreVO vo,HttpServletRequest req ) {
		StandardUnitRecordStoreDTO dto = StandardUnitRecordStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardUnitRecordStoreDTO> rt = standardUnitRecordStoreManage.findStandardUnitRecordStoreAll(dto);	
		return JsonResult.success(StandardUnitRecordStoreConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordStoreOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitRecordStoreVO>> findStandardUnitRecordStoreOfPage(StandardUnitRecordStoreVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitRecordStoreDTO dto = StandardUnitRecordStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardUnitRecordStoreDTO> rt = standardUnitRecordStoreManage.findStandardUnitRecordStoreOfPage(dto, page);
        List<StandardUnitRecordStoreVO> list = StandardUnitRecordStoreConverter.toVO(rt.getList());
        PageResult<StandardUnitRecordStoreVO> result = new PageResult<StandardUnitRecordStoreVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitRecordStoreWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitRecordStoreWithTx(StandardUnitRecordStoreVO vo,HttpServletRequest req ) {
		StandardUnitRecordStoreDTO dto = StandardUnitRecordStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitRecordStoreManage.insertStandardUnitRecordStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitRecordStoreByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitRecordStoreByIdWithTx(StandardUnitRecordStoreVO vo,HttpServletRequest req ) {
		StandardUnitRecordStoreDTO dto = StandardUnitRecordStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitRecordStoreManage.updateStandardUnitRecordStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitRecordStoreWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitRecordStoreWithTx(StandardUnitRecordStoreVO vo,HttpServletRequest req ) {
		StandardUnitRecordStoreDTO dto = StandardUnitRecordStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitRecordStoreManage.deleteStandardUnitRecordStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	