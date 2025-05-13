package com.egeo.components.cms.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.FunctionModuleManage;
import com.egeo.components.cms.converter.FunctionModuleConverter;
import com.egeo.components.cms.dto.FunctionModuleDTO;
import com.egeo.components.cms.vo.FunctionModuleVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/functionModule")
public class FunctionModuleAction extends BaseSpringController {
	
	@Resource(name="functionModule")
	private FunctionModuleManage functionModuleManage;


	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleById")
	@ResponseBody
	public JsonResult<FunctionModuleVO> findFunctionModuleById(Long id ) {
		
		FunctionModuleDTO dto = new FunctionModuleDTO();
		dto.setId(id);
		FunctionModuleDTO rt = functionModuleManage.findFunctionModuleById(dto);		
		return JsonResult.success(FunctionModuleConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleAll")
	@ResponseBody
	public JsonResult<List<FunctionModuleVO>> findFunctionModuleAll(FunctionModuleVO vo,HttpServletRequest req ) {
		FunctionModuleDTO dto = FunctionModuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<FunctionModuleDTO> rt = functionModuleManage.findFunctionModuleAll(dto);	
		return JsonResult.success(FunctionModuleConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleOfPage")
	@ResponseBody
	public JsonResult<PageResult<FunctionModuleVO>> findFunctionModuleOfPage(FunctionModuleVO vo,Pagination page,HttpServletRequest req ) {
		FunctionModuleDTO dto = FunctionModuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<FunctionModuleDTO> rt = functionModuleManage.findFunctionModuleOfPage(dto, page);
        List<FunctionModuleVO> list = FunctionModuleConverter.toVO(rt.getList());
        PageResult<FunctionModuleVO> result = new PageResult<FunctionModuleVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertFunctionModuleWithTx")
	@ResponseBody
	public JsonResult<Long> insertFunctionModuleWithTx(FunctionModuleVO vo,HttpServletRequest req ) {
		FunctionModuleDTO dto = FunctionModuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = functionModuleManage.insertFunctionModuleWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateFunctionModuleByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFunctionModuleByIdWithTx(FunctionModuleVO vo,HttpServletRequest req ) {
		FunctionModuleDTO dto = FunctionModuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = functionModuleManage.updateFunctionModuleWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteFunctionModuleWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteFunctionModuleWithTx(FunctionModuleVO vo,HttpServletRequest req ) {
		FunctionModuleDTO dto = FunctionModuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = functionModuleManage.deleteFunctionModuleWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	