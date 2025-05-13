package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.LimitRuleCompanyManage;
import com.egeo.components.order.converter.LimitRuleCompanyConverter;
import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.components.order.vo.LimitRuleCompanyVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/limitRuleCompany")
public class LimitRuleCompanyAction extends BaseSpringController {
	
	@Resource(name="limitRuleCompany")
	private LimitRuleCompanyManage limitRuleCompanyManage;


	// 业务方法：
	@RequestMapping(value = "/findLimitRuleCompanyById")
	@ResponseBody
	public JsonResult<LimitRuleCompanyVO> findLimitRuleCompanyById(Long id ) {
		
		LimitRuleCompanyDTO dto = new LimitRuleCompanyDTO();
		dto.setId(id);
		LimitRuleCompanyDTO rt = limitRuleCompanyManage.findLimitRuleCompanyById(dto);		
		return JsonResult.success(LimitRuleCompanyConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findLimitRuleCompanyAll")
	@ResponseBody
	public JsonResult<List<LimitRuleCompanyVO>> findLimitRuleCompanyAll(LimitRuleCompanyVO vo,HttpServletRequest req ) {
		LimitRuleCompanyDTO dto = LimitRuleCompanyConverter.toDTO(vo);
		List<LimitRuleCompanyDTO> rt = limitRuleCompanyManage.findLimitRuleCompanyAll(dto);	
		return JsonResult.success(LimitRuleCompanyConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findLimitRuleCompanyOfPage")
	@ResponseBody
	public JsonResult<PageResult<LimitRuleCompanyVO>> findLimitRuleCompanyOfPage(LimitRuleCompanyVO vo,Pagination page,HttpServletRequest req ) {
		LimitRuleCompanyDTO dto = LimitRuleCompanyConverter.toDTO(vo);
		PageResult<LimitRuleCompanyDTO> rt = limitRuleCompanyManage.findLimitRuleCompanyOfPage(dto, page);
        List<LimitRuleCompanyVO> list = LimitRuleCompanyConverter.toVO(rt.getList());
        PageResult<LimitRuleCompanyVO> result = new PageResult<LimitRuleCompanyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertLimitRuleCompanyWithTx")
	@ResponseBody
	public JsonResult<Long> insertLimitRuleCompanyWithTx(LimitRuleCompanyVO vo,HttpServletRequest req ) {
		LimitRuleCompanyDTO dto = LimitRuleCompanyConverter.toDTO(vo);
		Long rt = limitRuleCompanyManage.insertLimitRuleCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateLimitRuleCompanyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateLimitRuleCompanyByIdWithTx(LimitRuleCompanyVO vo,HttpServletRequest req ) {
		LimitRuleCompanyDTO dto = LimitRuleCompanyConverter.toDTO(vo);
		int rt = limitRuleCompanyManage.updateLimitRuleCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteLimitRuleCompanyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteLimitRuleCompanyWithTx(LimitRuleCompanyVO vo,HttpServletRequest req ) {
		LimitRuleCompanyDTO dto = LimitRuleCompanyConverter.toDTO(vo);
		int rt = limitRuleCompanyManage.deleteLimitRuleCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	