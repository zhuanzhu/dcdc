package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitCompanyManage;
import com.egeo.components.product.converter.StandardUnitCompanyConverter;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.vo.StandardUnitCompanyVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitCompany")
public class StandardUnitCompanyAction extends BaseSpringController {
	
	@Resource(name="standardUnitCompany")
	private StandardUnitCompanyManage standardUnitCompanyManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCompanyById")
	@ResponseBody
	public JsonResult<StandardUnitCompanyVO> findStandardUnitCompanyById(Long id ) {
		
		StandardUnitCompanyDTO dto = new StandardUnitCompanyDTO();
		dto.setId(id);
		StandardUnitCompanyDTO rt = standardUnitCompanyManage.findStandardUnitCompanyById(dto);		
		return JsonResult.success(StandardUnitCompanyConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCompanyAll")
	@ResponseBody
	public JsonResult<List<StandardUnitCompanyVO>> findStandardUnitCompanyAll(StandardUnitCompanyVO vo,HttpServletRequest req ) {
		StandardUnitCompanyDTO dto = StandardUnitCompanyConverter.toDTO(vo);
		List<StandardUnitCompanyDTO> rt = standardUnitCompanyManage.findStandardUnitCompanyAll(dto);	
		return JsonResult.success(StandardUnitCompanyConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCompanyOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitCompanyVO>> findStandardUnitCompanyOfPage(StandardUnitCompanyVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitCompanyDTO dto = StandardUnitCompanyConverter.toDTO(vo);
		PageResult<StandardUnitCompanyDTO> rt = standardUnitCompanyManage.findStandardUnitCompanyOfPage(dto, page);
        List<StandardUnitCompanyVO> list = StandardUnitCompanyConverter.toVO(rt.getList());
        PageResult<StandardUnitCompanyVO> result = new PageResult<StandardUnitCompanyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitCompanyWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitCompanyWithTx(StandardUnitCompanyVO vo,HttpServletRequest req ) {
		StandardUnitCompanyDTO dto = StandardUnitCompanyConverter.toDTO(vo);
		Long rt = standardUnitCompanyManage.insertStandardUnitCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitCompanyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitCompanyByIdWithTx(StandardUnitCompanyVO vo,HttpServletRequest req ) {
		StandardUnitCompanyDTO dto = StandardUnitCompanyConverter.toDTO(vo);
		int rt = standardUnitCompanyManage.updateStandardUnitCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitCompanyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitCompanyWithTx(StandardUnitCompanyVO vo,HttpServletRequest req ) {
		StandardUnitCompanyDTO dto = StandardUnitCompanyConverter.toDTO(vo);
		int rt = standardUnitCompanyManage.deleteStandardUnitCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	