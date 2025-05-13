package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitMembershipManage;
import com.egeo.components.product.converter.StandardUnitMembershipConverter;
import com.egeo.components.product.dto.StandardUnitMembershipDTO;
import com.egeo.components.product.vo.StandardUnitMembershipVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitMembership")
public class StandardUnitMembershipAction extends BaseSpringController {
	
	@Resource(name="standardUnitMembership")
	private StandardUnitMembershipManage standardUnitMembershipManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitMembershipById")
	@ResponseBody
	public JsonResult<StandardUnitMembershipVO> findStandardUnitMembershipById(Long id ) {
		
		StandardUnitMembershipDTO dto = new StandardUnitMembershipDTO();
		dto.setId(id);
		StandardUnitMembershipDTO rt = standardUnitMembershipManage.findStandardUnitMembershipById(dto);		
		return JsonResult.success(StandardUnitMembershipConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitMembershipAll")
	@ResponseBody
	public JsonResult<List<StandardUnitMembershipVO>> findStandardUnitMembershipAll(StandardUnitMembershipVO vo,HttpServletRequest req ) {
		StandardUnitMembershipDTO dto = StandardUnitMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardUnitMembershipDTO> rt = standardUnitMembershipManage.findStandardUnitMembershipAll(dto);	
		return JsonResult.success(StandardUnitMembershipConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitMembershipOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitMembershipVO>> findStandardUnitMembershipOfPage(StandardUnitMembershipVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitMembershipDTO dto = StandardUnitMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardUnitMembershipDTO> rt = standardUnitMembershipManage.findStandardUnitMembershipOfPage(dto, page);
        List<StandardUnitMembershipVO> list = StandardUnitMembershipConverter.toVO(rt.getList());
        PageResult<StandardUnitMembershipVO> result = new PageResult<StandardUnitMembershipVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitMembershipWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitMembershipWithTx(StandardUnitMembershipVO vo,HttpServletRequest req ) {
		StandardUnitMembershipDTO dto = StandardUnitMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitMembershipManage.insertStandardUnitMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitMembershipByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitMembershipByIdWithTx(StandardUnitMembershipVO vo,HttpServletRequest req ) {
		StandardUnitMembershipDTO dto = StandardUnitMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitMembershipManage.updateStandardUnitMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitMembershipWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitMembershipWithTx(StandardUnitMembershipVO vo,HttpServletRequest req ) {
		StandardUnitMembershipDTO dto = StandardUnitMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitMembershipManage.deleteStandardUnitMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	