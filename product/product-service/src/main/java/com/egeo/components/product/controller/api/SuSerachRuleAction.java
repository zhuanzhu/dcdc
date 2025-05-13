package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SuSerachRuleManage;
import com.egeo.components.product.converter.SuSerachRuleConverter;
import com.egeo.components.product.dto.SuSerachRuleDTO;
import com.egeo.components.product.vo.SuSerachRuleVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/suSerachRule")
public class SuSerachRuleAction extends BaseSpringController {
	
	@Resource(name="suSerachRule")
	private SuSerachRuleManage suSerachRuleManage;


	// 业务方法：
	@RequestMapping(value = "/findSuSerachRuleById")
	@ResponseBody
	public JsonResult<SuSerachRuleVO> findSuSerachRuleById(Long id ) {
		
		SuSerachRuleDTO dto = new SuSerachRuleDTO();
		dto.setId(id);
		SuSerachRuleDTO rt = suSerachRuleManage.findSuSerachRuleById(dto);		
		return JsonResult.success(SuSerachRuleConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSuSerachRuleAll")
	@ResponseBody
	public JsonResult<List<SuSerachRuleVO>> findSuSerachRuleAll(SuSerachRuleVO vo,HttpServletRequest req ) {
		SuSerachRuleDTO dto = SuSerachRuleConverter.toDTO(vo);
		List<SuSerachRuleDTO> rt = suSerachRuleManage.findSuSerachRuleAll(dto);	
		return JsonResult.success(SuSerachRuleConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSuSerachRuleOfPage")
	@ResponseBody
	public JsonResult<PageResult<SuSerachRuleVO>> findSuSerachRuleOfPage(SuSerachRuleVO vo,Pagination page,HttpServletRequest req ) {
		SuSerachRuleDTO dto = SuSerachRuleConverter.toDTO(vo);
		PageResult<SuSerachRuleDTO> rt = suSerachRuleManage.findSuSerachRuleOfPage(dto, page);
        List<SuSerachRuleVO> list = SuSerachRuleConverter.toVO(rt.getList());
        PageResult<SuSerachRuleVO> result = new PageResult<SuSerachRuleVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSuSerachRuleWithTx")
	@ResponseBody
	public JsonResult<Long> insertSuSerachRuleWithTx(SuSerachRuleVO vo,HttpServletRequest req ) {
		SuSerachRuleDTO dto = SuSerachRuleConverter.toDTO(vo);
		Long rt = suSerachRuleManage.insertSuSerachRuleWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSuSerachRuleByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSuSerachRuleByIdWithTx(SuSerachRuleVO vo,HttpServletRequest req ) {
		SuSerachRuleDTO dto = SuSerachRuleConverter.toDTO(vo);
		int rt = suSerachRuleManage.updateSuSerachRuleWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSuSerachRuleWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSuSerachRuleWithTx(SuSerachRuleVO vo,HttpServletRequest req ) {
		SuSerachRuleDTO dto = SuSerachRuleConverter.toDTO(vo);
		int rt = suSerachRuleManage.deleteSuSerachRuleWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	