package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitRecordManage;
import com.egeo.components.product.converter.StandardUnitRecordConverter;
import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.components.product.vo.StandardUnitRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitRecord")
public class StandardUnitRecordAction extends BaseSpringController {
	
	@Resource(name="standardUnitRecord")
	private StandardUnitRecordManage standardUnitRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordById")
	@ResponseBody
	public JsonResult<StandardUnitRecordVO> findStandardUnitRecordById(Long id ) {
		
		StandardUnitRecordDTO dto = new StandardUnitRecordDTO();
		dto.setId(id);
		StandardUnitRecordDTO rt = standardUnitRecordManage.findStandardUnitRecordById(dto);		
		return JsonResult.success(StandardUnitRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordAll")
	@ResponseBody
	public JsonResult<List<StandardUnitRecordVO>> findStandardUnitRecordAll(StandardUnitRecordVO vo,HttpServletRequest req ) {
		StandardUnitRecordDTO dto = StandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardUnitRecordDTO> rt = standardUnitRecordManage.findStandardUnitRecordAll(dto);	
		return JsonResult.success(StandardUnitRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitRecordVO>> findStandardUnitRecordOfPage(StandardUnitRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitRecordDTO dto = StandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardUnitRecordDTO> rt = standardUnitRecordManage.findStandardUnitRecordOfPage(dto, page);
        List<StandardUnitRecordVO> list = StandardUnitRecordConverter.toVO(rt.getList());
        PageResult<StandardUnitRecordVO> result = new PageResult<StandardUnitRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitRecordWithTx(StandardUnitRecordVO vo,HttpServletRequest req ) {
		StandardUnitRecordDTO dto = StandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitRecordManage.insertStandardUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitRecordByIdWithTx(StandardUnitRecordVO vo,HttpServletRequest req ) {
		StandardUnitRecordDTO dto = StandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitRecordManage.updateStandardUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitRecordWithTx(StandardUnitRecordVO vo,HttpServletRequest req ) {
		StandardUnitRecordDTO dto = StandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitRecordManage.deleteStandardUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	