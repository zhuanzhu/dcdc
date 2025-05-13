package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitClientRecordManage;
import com.egeo.components.product.converter.StandardUnitClientRecordConverter;
import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.components.product.vo.StandardUnitClientRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitClientRecord")
public class StandardUnitClientRecordAction extends BaseSpringController {
	
	@Resource(name="standardUnitClientRecord")
	private StandardUnitClientRecordManage standardUnitClientRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitClientRecordById")
	@ResponseBody
	public JsonResult<StandardUnitClientRecordVO> findStandardUnitClientRecordById(Long id ) {
		
		StandardUnitClientRecordDTO dto = new StandardUnitClientRecordDTO();
		dto.setId(id);
		StandardUnitClientRecordDTO rt = standardUnitClientRecordManage.findStandardUnitClientRecordById(dto);		
		return JsonResult.success(StandardUnitClientRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitClientRecordAll")
	@ResponseBody
	public JsonResult<List<StandardUnitClientRecordVO>> findStandardUnitClientRecordAll(StandardUnitClientRecordVO vo,HttpServletRequest req ) {
		StandardUnitClientRecordDTO dto = StandardUnitClientRecordConverter.toDTO(vo);
		List<StandardUnitClientRecordDTO> rt = standardUnitClientRecordManage.findStandardUnitClientRecordAll(dto);	
		return JsonResult.success(StandardUnitClientRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitClientRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitClientRecordVO>> findStandardUnitClientRecordOfPage(StandardUnitClientRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitClientRecordDTO dto = StandardUnitClientRecordConverter.toDTO(vo);
		PageResult<StandardUnitClientRecordDTO> rt = standardUnitClientRecordManage.findStandardUnitClientRecordOfPage(dto, page);
        List<StandardUnitClientRecordVO> list = StandardUnitClientRecordConverter.toVO(rt.getList());
        PageResult<StandardUnitClientRecordVO> result = new PageResult<StandardUnitClientRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitClientRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitClientRecordWithTx(StandardUnitClientRecordVO vo,HttpServletRequest req ) {
		StandardUnitClientRecordDTO dto = StandardUnitClientRecordConverter.toDTO(vo);
		Long rt = standardUnitClientRecordManage.insertStandardUnitClientRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitClientRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitClientRecordByIdWithTx(StandardUnitClientRecordVO vo,HttpServletRequest req ) {
		StandardUnitClientRecordDTO dto = StandardUnitClientRecordConverter.toDTO(vo);
		int rt = standardUnitClientRecordManage.updateStandardUnitClientRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitClientRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitClientRecordWithTx(StandardUnitClientRecordVO vo,HttpServletRequest req ) {
		StandardUnitClientRecordDTO dto = StandardUnitClientRecordConverter.toDTO(vo);
		int rt = standardUnitClientRecordManage.deleteStandardUnitClientRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	