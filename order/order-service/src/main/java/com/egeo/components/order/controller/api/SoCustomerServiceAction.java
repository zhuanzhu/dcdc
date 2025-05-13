package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.SoCustomerServiceManage;
import com.egeo.components.order.converter.SoCustomerServiceConverter;
import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.components.order.vo.SoCustomerServiceVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/soCustomerService")
public class SoCustomerServiceAction extends BaseSpringController {
	
	@Resource(name="soCustomerService")
	private SoCustomerServiceManage soCustomerServiceManage;


	// 业务方法：
	@RequestMapping(value = "/findSoCustomerServiceById")
	@ResponseBody
	public JsonResult<SoCustomerServiceVO> findSoCustomerServiceById(Long id ) {
		
		SoCustomerServiceDTO dto = new SoCustomerServiceDTO();
		dto.setId(id);
		SoCustomerServiceDTO rt = soCustomerServiceManage.findSoCustomerServiceById(dto);		
		return JsonResult.success(SoCustomerServiceConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSoCustomerServiceAll")
	@ResponseBody
	public JsonResult<List<SoCustomerServiceVO>> findSoCustomerServiceAll(SoCustomerServiceVO vo,HttpServletRequest req ) {
		SoCustomerServiceDTO dto = SoCustomerServiceConverter.toDTO(vo);
		List<SoCustomerServiceDTO> rt = soCustomerServiceManage.findSoCustomerServiceAll(dto);	
		return JsonResult.success(SoCustomerServiceConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSoCustomerServiceOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoCustomerServiceVO>> findSoCustomerServiceOfPage(SoCustomerServiceVO vo,Pagination page,HttpServletRequest req ) {
		SoCustomerServiceDTO dto = SoCustomerServiceConverter.toDTO(vo);
		PageResult<SoCustomerServiceDTO> rt = soCustomerServiceManage.findSoCustomerServiceOfPage(dto, page);
        List<SoCustomerServiceVO> list = SoCustomerServiceConverter.toVO(rt.getList());
        PageResult<SoCustomerServiceVO> result = new PageResult<SoCustomerServiceVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSoCustomerServiceWithTx")
	@ResponseBody
	public JsonResult<Long> insertSoCustomerServiceWithTx(SoCustomerServiceVO vo,HttpServletRequest req ) {
		SoCustomerServiceDTO dto = SoCustomerServiceConverter.toDTO(vo);
		Long rt = soCustomerServiceManage.insertSoCustomerServiceWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSoCustomerServiceByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoCustomerServiceByIdWithTx(SoCustomerServiceVO vo,HttpServletRequest req ) {
		SoCustomerServiceDTO dto = SoCustomerServiceConverter.toDTO(vo);
		int rt = soCustomerServiceManage.updateSoCustomerServiceWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSoCustomerServiceWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSoCustomerServiceWithTx(SoCustomerServiceVO vo,HttpServletRequest req ) {
		SoCustomerServiceDTO dto = SoCustomerServiceConverter.toDTO(vo);
		int rt = soCustomerServiceManage.deleteSoCustomerServiceWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	