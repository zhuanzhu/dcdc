package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitRecordMembershipManage;
import com.egeo.components.product.converter.StandardUnitRecordMembershipConverter;
import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;
import com.egeo.components.product.vo.StandardUnitRecordMembershipVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitRecordMembership")
public class StandardUnitRecordMembershipAction extends BaseSpringController {
	
	@Resource(name="standardUnitRecordMembership")
	private StandardUnitRecordMembershipManage standardUnitRecordMembershipManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordMembershipById")
	@ResponseBody
	public JsonResult<StandardUnitRecordMembershipVO> findStandardUnitRecordMembershipById(Long id ) {
		
		StandardUnitRecordMembershipDTO dto = new StandardUnitRecordMembershipDTO();
		dto.setId(id);
		StandardUnitRecordMembershipDTO rt = standardUnitRecordMembershipManage.findStandardUnitRecordMembershipById(dto);		
		return JsonResult.success(StandardUnitRecordMembershipConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordMembershipAll")
	@ResponseBody
	public JsonResult<List<StandardUnitRecordMembershipVO>> findStandardUnitRecordMembershipAll(StandardUnitRecordMembershipVO vo,HttpServletRequest req ) {
		StandardUnitRecordMembershipDTO dto = StandardUnitRecordMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardUnitRecordMembershipDTO> rt = standardUnitRecordMembershipManage.findStandardUnitRecordMembershipAll(dto);	
		return JsonResult.success(StandardUnitRecordMembershipConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitRecordMembershipOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitRecordMembershipVO>> findStandardUnitRecordMembershipOfPage(StandardUnitRecordMembershipVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitRecordMembershipDTO dto = StandardUnitRecordMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardUnitRecordMembershipDTO> rt = standardUnitRecordMembershipManage.findStandardUnitRecordMembershipOfPage(dto, page);
        List<StandardUnitRecordMembershipVO> list = StandardUnitRecordMembershipConverter.toVO(rt.getList());
        PageResult<StandardUnitRecordMembershipVO> result = new PageResult<StandardUnitRecordMembershipVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitRecordMembershipWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipVO vo,HttpServletRequest req ) {
		StandardUnitRecordMembershipDTO dto = StandardUnitRecordMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitRecordMembershipManage.insertStandardUnitRecordMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitRecordMembershipByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitRecordMembershipByIdWithTx(StandardUnitRecordMembershipVO vo,HttpServletRequest req ) {
		StandardUnitRecordMembershipDTO dto = StandardUnitRecordMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitRecordMembershipManage.updateStandardUnitRecordMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitRecordMembershipWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipVO vo,HttpServletRequest req ) {
		StandardUnitRecordMembershipDTO dto = StandardUnitRecordMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitRecordMembershipManage.deleteStandardUnitRecordMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	