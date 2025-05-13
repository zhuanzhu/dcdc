package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitTagRecordManage;
import com.egeo.components.product.converter.StandardUnitTagRecordConverter;
import com.egeo.components.product.dto.StandardUnitTagRecordDTO;
import com.egeo.components.product.vo.StandardUnitTagRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitTagRecord")
public class StandardUnitTagRecordAction extends BaseSpringController {
	
	@Resource(name="standardUnitTagRecord")
	private StandardUnitTagRecordManage standardUnitTagRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitTagRecordById")
	@ResponseBody
	public JsonResult<StandardUnitTagRecordVO> findStandardUnitTagRecordById(Long id ) {
		
		StandardUnitTagRecordDTO dto = new StandardUnitTagRecordDTO();
		dto.setId(id);
		StandardUnitTagRecordDTO rt = standardUnitTagRecordManage.findStandardUnitTagRecordById(dto);		
		return JsonResult.success(StandardUnitTagRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitTagRecordAll")
	@ResponseBody
	public JsonResult<List<StandardUnitTagRecordVO>> findStandardUnitTagRecordAll(StandardUnitTagRecordVO vo,HttpServletRequest req ) {
		StandardUnitTagRecordDTO dto = StandardUnitTagRecordConverter.toDTO(vo);
		List<StandardUnitTagRecordDTO> rt = standardUnitTagRecordManage.findStandardUnitTagRecordAll(dto);	
		return JsonResult.success(StandardUnitTagRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitTagRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitTagRecordVO>> findStandardUnitTagRecordOfPage(StandardUnitTagRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitTagRecordDTO dto = StandardUnitTagRecordConverter.toDTO(vo);
		PageResult<StandardUnitTagRecordDTO> rt = standardUnitTagRecordManage.findStandardUnitTagRecordOfPage(dto, page);
        List<StandardUnitTagRecordVO> list = StandardUnitTagRecordConverter.toVO(rt.getList());
        PageResult<StandardUnitTagRecordVO> result = new PageResult<StandardUnitTagRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitTagRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitTagRecordWithTx(StandardUnitTagRecordVO vo,HttpServletRequest req ) {
		StandardUnitTagRecordDTO dto = StandardUnitTagRecordConverter.toDTO(vo);
		Long rt = standardUnitTagRecordManage.insertStandardUnitTagRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitTagRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitTagRecordByIdWithTx(StandardUnitTagRecordVO vo,HttpServletRequest req ) {
		StandardUnitTagRecordDTO dto = StandardUnitTagRecordConverter.toDTO(vo);
		int rt = standardUnitTagRecordManage.updateStandardUnitTagRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitTagRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitTagRecordWithTx(StandardUnitTagRecordVO vo,HttpServletRequest req ) {
		StandardUnitTagRecordDTO dto = StandardUnitTagRecordConverter.toDTO(vo);
		int rt = standardUnitTagRecordManage.deleteStandardUnitTagRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	