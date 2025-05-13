package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitCompanyRecordManage;
import com.egeo.components.product.converter.StandardUnitCompanyRecordConverter;
import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;
import com.egeo.components.product.vo.StandardUnitCompanyRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitCompanyRecord")
public class StandardUnitCompanyRecordAction extends BaseSpringController {
	
	@Resource(name="standardUnitCompanyRecord")
	private StandardUnitCompanyRecordManage standardUnitCompanyRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCompanyRecordById")
	@ResponseBody
	public JsonResult<StandardUnitCompanyRecordVO> findStandardUnitCompanyRecordById(Long id ) {
		
		StandardUnitCompanyRecordDTO dto = new StandardUnitCompanyRecordDTO();
		dto.setId(id);
		StandardUnitCompanyRecordDTO rt = standardUnitCompanyRecordManage.findStandardUnitCompanyRecordById(dto);		
		return JsonResult.success(StandardUnitCompanyRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCompanyRecordAll")
	@ResponseBody
	public JsonResult<List<StandardUnitCompanyRecordVO>> findStandardUnitCompanyRecordAll(StandardUnitCompanyRecordVO vo,HttpServletRequest req ) {
		StandardUnitCompanyRecordDTO dto = StandardUnitCompanyRecordConverter.toDTO(vo);
		List<StandardUnitCompanyRecordDTO> rt = standardUnitCompanyRecordManage.findStandardUnitCompanyRecordAll(dto);	
		return JsonResult.success(StandardUnitCompanyRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCompanyRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitCompanyRecordVO>> findStandardUnitCompanyRecordOfPage(StandardUnitCompanyRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitCompanyRecordDTO dto = StandardUnitCompanyRecordConverter.toDTO(vo);
		PageResult<StandardUnitCompanyRecordDTO> rt = standardUnitCompanyRecordManage.findStandardUnitCompanyRecordOfPage(dto, page);
        List<StandardUnitCompanyRecordVO> list = StandardUnitCompanyRecordConverter.toVO(rt.getList());
        PageResult<StandardUnitCompanyRecordVO> result = new PageResult<StandardUnitCompanyRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitCompanyRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordVO vo,HttpServletRequest req ) {
		StandardUnitCompanyRecordDTO dto = StandardUnitCompanyRecordConverter.toDTO(vo);
		Long rt = standardUnitCompanyRecordManage.insertStandardUnitCompanyRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitCompanyRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitCompanyRecordByIdWithTx(StandardUnitCompanyRecordVO vo,HttpServletRequest req ) {
		StandardUnitCompanyRecordDTO dto = StandardUnitCompanyRecordConverter.toDTO(vo);
		int rt = standardUnitCompanyRecordManage.updateStandardUnitCompanyRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitCompanyRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordVO vo,HttpServletRequest req ) {
		StandardUnitCompanyRecordDTO dto = StandardUnitCompanyRecordConverter.toDTO(vo);
		int rt = standardUnitCompanyRecordManage.deleteStandardUnitCompanyRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	