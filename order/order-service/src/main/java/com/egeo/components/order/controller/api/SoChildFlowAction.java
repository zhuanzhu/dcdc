package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.SoChildFlowManage;
import com.egeo.components.order.converter.SoChildFlowConverter;
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.components.order.vo.SoOPFlowVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/soChildFlow")
public class SoChildFlowAction extends BaseSpringController {
	
	@Resource(name="soChildFlow")
	private SoChildFlowManage soChildFlowManage;


	// 业务方法：
	@RequestMapping(value = "/findSoChildFlowById")
	@ResponseBody
	public JsonResult<SoOPFlowVO> findSoChildFlowById(Long id ) {
		
		SoChildFlowDTO dto = new SoChildFlowDTO();
		dto.setId(id);
		SoChildFlowDTO rt = soChildFlowManage.findSoChildFlowById(dto);		
		return JsonResult.success(SoChildFlowConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSoChildFlowAll")
	@ResponseBody
	public JsonResult<List<SoOPFlowVO>> findSoChildFlowAll(SoOPFlowVO vo,HttpServletRequest req ) {
		SoChildFlowDTO dto = SoChildFlowConverter.toDTO(vo);
		List<SoChildFlowDTO> rt = soChildFlowManage.findSoChildFlowAll(dto);	
		return JsonResult.success(SoChildFlowConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSoChildFlowOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoOPFlowVO>> findSoChildFlowOfPage(SoOPFlowVO vo,Pagination page,HttpServletRequest req ) {
		SoChildFlowDTO dto = SoChildFlowConverter.toDTO(vo);
		PageResult<SoChildFlowDTO> rt = soChildFlowManage.findSoChildFlowOfPage(dto, page);
        List<SoOPFlowVO> list = SoChildFlowConverter.toVO(rt.getList());
        PageResult<SoOPFlowVO> result = new PageResult<SoOPFlowVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSoChildFlowWithTx")
	@ResponseBody
	public JsonResult<Long> insertSoChildFlowWithTx(SoOPFlowVO vo,HttpServletRequest req ) {
		SoChildFlowDTO dto = SoChildFlowConverter.toDTO(vo);
		Long rt = soChildFlowManage.insertSoChildFlowWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSoChildFlowByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoChildFlowByIdWithTx(SoOPFlowVO vo,HttpServletRequest req ) {
		SoChildFlowDTO dto = SoChildFlowConverter.toDTO(vo);
		int rt = soChildFlowManage.updateSoChildFlowWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSoChildFlowWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSoChildFlowWithTx(SoOPFlowVO vo,HttpServletRequest req ) {
		SoChildFlowDTO dto = SoChildFlowConverter.toDTO(vo);
		int rt = soChildFlowManage.deleteSoChildFlowWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	