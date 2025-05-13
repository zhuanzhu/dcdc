package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.FreightRegulationManage;
import com.egeo.components.product.converter.FreightRegulationConverter;
import com.egeo.components.product.vo.FreightRegulationVO;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/freightRegulation")
public class FreightRegulationAction extends BaseSpringController {
	
	@Resource(name="freightRegulation")
	private FreightRegulationManage freightRegulationManage;


	// 业务方法：
	@RequestMapping(value = "/findFreightRegulationById")
	@ResponseBody
	public JsonResult<FreightRegulationVO> findFreightRegulationById(Long id ) {
		
		FreightRegulationDTO dto = new FreightRegulationDTO();
		dto.setId(id);
		FreightRegulationDTO rt = freightRegulationManage.findFreightRegulationById(dto);		
		return JsonResult.success(FreightRegulationConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findFreightRegulationAll")
	@ResponseBody
	public JsonResult<List<FreightRegulationVO>> findFreightRegulationAll(FreightRegulationVO vo,HttpServletRequest req ) {
		FreightRegulationDTO dto = FreightRegulationConverter.toDTO(vo);
		List<FreightRegulationDTO> rt = freightRegulationManage.findFreightRegulationAll(dto);	
		return JsonResult.success(FreightRegulationConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findFreightRegulationOfPage")
	@ResponseBody
	public JsonResult<PageResult<FreightRegulationVO>> findFreightRegulationOfPage(FreightRegulationVO vo,Pagination page,HttpServletRequest req ) {
		FreightRegulationDTO dto = FreightRegulationConverter.toDTO(vo);
		PageResult<FreightRegulationDTO> rt = freightRegulationManage.findFreightRegulationOfPage(dto, page);
        List<FreightRegulationVO> list = FreightRegulationConverter.toVO(rt.getList());
        PageResult<FreightRegulationVO> result = new PageResult<FreightRegulationVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertFreightRegulationWithTx")
	@ResponseBody
	public JsonResult<Long> insertFreightRegulationWithTx(FreightRegulationVO vo,HttpServletRequest req ) {
		FreightRegulationDTO dto = FreightRegulationConverter.toDTO(vo);
		Long rt = freightRegulationManage.insertFreightRegulationWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateFreightRegulationByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFreightRegulationByIdWithTx(FreightRegulationVO vo,HttpServletRequest req ) {
		FreightRegulationDTO dto = FreightRegulationConverter.toDTO(vo);
		int rt = freightRegulationManage.updateFreightRegulationWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteFreightRegulationWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteFreightRegulationWithTx(FreightRegulationVO vo,HttpServletRequest req ) {
		FreightRegulationDTO dto = FreightRegulationConverter.toDTO(vo);
		int rt = freightRegulationManage.deleteFreightRegulationWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	