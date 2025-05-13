package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitClientManage;
import com.egeo.components.product.converter.StandardUnitClientConverter;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.vo.StandardUnitClientVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitClient")
public class StandardUnitClientAction extends BaseSpringController {
	
	@Resource(name="standardUnitClient")
	private StandardUnitClientManage standardUnitClientManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitClientById")
	@ResponseBody
	public JsonResult<StandardUnitClientVO> findStandardUnitClientById(Long id ) {
		
		StandardUnitClientDTO dto = new StandardUnitClientDTO();
		dto.setId(id);
		StandardUnitClientDTO rt = standardUnitClientManage.findStandardUnitClientById(dto);		
		return JsonResult.success(StandardUnitClientConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitClientAll")
	@ResponseBody
	public JsonResult<List<StandardUnitClientVO>> findStandardUnitClientAll(StandardUnitClientVO vo,HttpServletRequest req ) {
		StandardUnitClientDTO dto = StandardUnitClientConverter.toDTO(vo);
		List<StandardUnitClientDTO> rt = standardUnitClientManage.findStandardUnitClientAll(dto);	
		return JsonResult.success(StandardUnitClientConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitClientOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitClientVO>> findStandardUnitClientOfPage(StandardUnitClientVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitClientDTO dto = StandardUnitClientConverter.toDTO(vo);
		PageResult<StandardUnitClientDTO> rt = standardUnitClientManage.findStandardUnitClientOfPage(dto, page);
        List<StandardUnitClientVO> list = StandardUnitClientConverter.toVO(rt.getList());
        PageResult<StandardUnitClientVO> result = new PageResult<StandardUnitClientVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitClientWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitClientWithTx(StandardUnitClientVO vo,HttpServletRequest req ) {
		StandardUnitClientDTO dto = StandardUnitClientConverter.toDTO(vo);
		Long rt = standardUnitClientManage.insertStandardUnitClientWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitClientByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitClientByIdWithTx(StandardUnitClientVO vo,HttpServletRequest req ) {
		StandardUnitClientDTO dto = StandardUnitClientConverter.toDTO(vo);
		int rt = standardUnitClientManage.updateStandardUnitClientWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitClientWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitClientWithTx(StandardUnitClientVO vo,HttpServletRequest req ) {
		StandardUnitClientDTO dto = StandardUnitClientConverter.toDTO(vo);
		int rt = standardUnitClientManage.deleteStandardUnitClientWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	