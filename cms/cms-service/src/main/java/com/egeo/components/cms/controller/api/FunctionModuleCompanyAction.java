package com.egeo.components.cms.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.FunctionModuleCompanyManage;
import com.egeo.components.cms.converter.FunctionModuleCompanyConverter;
import com.egeo.components.cms.dto.FunctionModuleCompanyDTO;
import com.egeo.components.cms.vo.FunctionModuleCompanyVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/functionModuleCompany")
public class FunctionModuleCompanyAction extends BaseSpringController {
	
	@Resource(name="functionModuleCompany")
	private FunctionModuleCompanyManage functionModuleCompanyManage;


	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleCompanyById")
	@ResponseBody
	public JsonResult<FunctionModuleCompanyVO> findFunctionModuleCompanyById(Long id ) {
		
		FunctionModuleCompanyDTO dto = new FunctionModuleCompanyDTO();
		dto.setId(id);
		FunctionModuleCompanyDTO rt = functionModuleCompanyManage.findFunctionModuleCompanyById(dto);		
		return JsonResult.success(FunctionModuleCompanyConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleCompanyAll")
	@ResponseBody
	public JsonResult<List<FunctionModuleCompanyVO>> findFunctionModuleCompanyAll(FunctionModuleCompanyVO vo,HttpServletRequest req ) {
		FunctionModuleCompanyDTO dto = FunctionModuleCompanyConverter.toDTO(vo);
		List<FunctionModuleCompanyDTO> rt = functionModuleCompanyManage.findFunctionModuleCompanyAll(dto);	
		return JsonResult.success(FunctionModuleCompanyConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleCompanyOfPage")
	@ResponseBody
	public JsonResult<PageResult<FunctionModuleCompanyVO>> findFunctionModuleCompanyOfPage(FunctionModuleCompanyVO vo,Pagination page,HttpServletRequest req ) {
		FunctionModuleCompanyDTO dto = FunctionModuleCompanyConverter.toDTO(vo);
		PageResult<FunctionModuleCompanyDTO> rt = functionModuleCompanyManage.findFunctionModuleCompanyOfPage(dto, page);
        List<FunctionModuleCompanyVO> list = FunctionModuleCompanyConverter.toVO(rt.getList());
        PageResult<FunctionModuleCompanyVO> result = new PageResult<FunctionModuleCompanyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertFunctionModuleCompanyWithTx")
	@ResponseBody
	public JsonResult<Long> insertFunctionModuleCompanyWithTx(FunctionModuleCompanyVO vo,HttpServletRequest req ) {
		FunctionModuleCompanyDTO dto = FunctionModuleCompanyConverter.toDTO(vo);
		Long rt = functionModuleCompanyManage.insertFunctionModuleCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateFunctionModuleCompanyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFunctionModuleCompanyByIdWithTx(FunctionModuleCompanyVO vo,HttpServletRequest req ) {
		FunctionModuleCompanyDTO dto = FunctionModuleCompanyConverter.toDTO(vo);
		int rt = functionModuleCompanyManage.updateFunctionModuleCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteFunctionModuleCompanyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteFunctionModuleCompanyWithTx(FunctionModuleCompanyVO vo,HttpServletRequest req ) {
		FunctionModuleCompanyDTO dto = FunctionModuleCompanyConverter.toDTO(vo);
		int rt = functionModuleCompanyManage.deleteFunctionModuleCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	