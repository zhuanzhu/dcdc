package com.egeo.components.cms.controller.api;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.cms.business.FunctionModuleCategoryManage;
import com.egeo.components.cms.converter.FunctionModuleCategoryConverter;
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.components.cms.vo.FunctionModuleCategoryVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/functionModuleCategory")
public class FunctionModuleCategoryAction extends BaseSpringController {
	
	@Resource(name="functionModuleCategory")
	private FunctionModuleCategoryManage functionModuleCategoryManage;


	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleCategoryById")
	@ResponseBody
	public JsonResult<FunctionModuleCategoryVO> findFunctionModuleCategoryById(Long id ) {
		
		FunctionModuleCategoryDTO dto = new FunctionModuleCategoryDTO();
		dto.setId(id);
		FunctionModuleCategoryDTO rt = functionModuleCategoryManage.findFunctionModuleCategoryById(dto);		
		return JsonResult.success(FunctionModuleCategoryConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleCategoryAll")
	@ResponseBody
	public JsonResult<List<FunctionModuleCategoryVO>> findFunctionModuleCategoryAll(FunctionModuleCategoryVO vo,HttpServletRequest req ) {
		FunctionModuleCategoryDTO dto = FunctionModuleCategoryConverter.toDTO(vo);
		List<FunctionModuleCategoryDTO> rt = functionModuleCategoryManage.findFunctionModuleCategoryAll(dto);	
		return JsonResult.success(FunctionModuleCategoryConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findFunctionModuleCategoryOfPage")
	@ResponseBody
	public JsonResult<PageResult<FunctionModuleCategoryVO>> findFunctionModuleCategoryOfPage(FunctionModuleCategoryVO vo,Pagination page,HttpServletRequest req ) {
		FunctionModuleCategoryDTO dto = FunctionModuleCategoryConverter.toDTO(vo);
		PageResult<FunctionModuleCategoryDTO> rt = functionModuleCategoryManage.findFunctionModuleCategoryOfPage(dto, page);
        List<FunctionModuleCategoryVO> list = FunctionModuleCategoryConverter.toVO(rt.getList());
        PageResult<FunctionModuleCategoryVO> result = new PageResult<FunctionModuleCategoryVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertFunctionModuleCategoryWithTx")
	@ResponseBody
	public JsonResult<Long> insertFunctionModuleCategoryWithTx(FunctionModuleCategoryVO vo,HttpServletRequest req ) {
		FunctionModuleCategoryDTO dto = FunctionModuleCategoryConverter.toDTO(vo);
		Long rt = functionModuleCategoryManage.insertFunctionModuleCategoryWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 批量更新类目节点信息
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateFunctionModuleCategoryAllWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFunctionModuleCategoryAllWithTx(Long functionModuleId,String categoryTreeNodeIdList,HttpServletRequest req ) {
		logger.info("批量更新类目节点信息");
		int s = 0;
		List<Long> categoryTreeNodeIds = new ArrayList<Long>(JSONArray.parseArray(categoryTreeNodeIdList, Long.class));
		if(EmptyUtil.isNotEmpty(categoryTreeNodeIds)){
			s = functionModuleCategoryManage.updateFunctionModuleCategoryAllWithTx(functionModuleId,categoryTreeNodeIds);	
		}
		return JsonResult.success(s);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateFunctionModuleCategoryByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFunctionModuleCategoryByIdWithTx(FunctionModuleCategoryVO vo,HttpServletRequest req ) {
		FunctionModuleCategoryDTO dto = FunctionModuleCategoryConverter.toDTO(vo);
		int rt = functionModuleCategoryManage.updateFunctionModuleCategoryWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteFunctionModuleCategoryWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteFunctionModuleCategoryWithTx(FunctionModuleCategoryVO vo,HttpServletRequest req ) {
		FunctionModuleCategoryDTO dto = FunctionModuleCategoryConverter.toDTO(vo);
		int rt = functionModuleCategoryManage.deleteFunctionModuleCategoryWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据功能模版id查询所有选中的类目节点id
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/categoryTreeNodeIdsByFunctionModuleId")
	@ResponseBody
	public JsonResult<String> categoryTreeNodeIdsByFunctionModuleId(Long functionModuleId) {
		String rt = functionModuleCategoryManage.categoryTreeNodeIdsByFunctionModuleId(functionModuleId);	
		return JsonResult.success(rt);
					 
	}	
		
}
	