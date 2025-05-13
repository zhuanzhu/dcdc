package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitPictureRecordManage;
import com.egeo.components.product.converter.StandardUnitPictureRecordConverter;
import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.components.product.vo.StandardUnitPictureRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitPictureRecord")
public class StandardUnitPictureRecordAction extends BaseSpringController {
	
	@Resource(name="standardUnitPictureRecord")
	private StandardUnitPictureRecordManage standardUnitPictureRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitPictureRecordById")
	@ResponseBody
	public JsonResult<StandardUnitPictureRecordVO> findStandardUnitPictureRecordById(Long id ) {
		
		StandardUnitPictureRecordDTO dto = new StandardUnitPictureRecordDTO();
		dto.setId(id);
		StandardUnitPictureRecordDTO rt = standardUnitPictureRecordManage.findStandardUnitPictureRecordById(dto);		
		return JsonResult.success(StandardUnitPictureRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitPictureRecordAll")
	@ResponseBody
	public JsonResult<List<StandardUnitPictureRecordVO>> findStandardUnitPictureRecordAll(StandardUnitPictureRecordVO vo,HttpServletRequest req ) {
		StandardUnitPictureRecordDTO dto = StandardUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardUnitPictureRecordDTO> rt = standardUnitPictureRecordManage.findStandardUnitPictureRecordAll(dto);	
		return JsonResult.success(StandardUnitPictureRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitPictureRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitPictureRecordVO>> findStandardUnitPictureRecordOfPage(StandardUnitPictureRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitPictureRecordDTO dto = StandardUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardUnitPictureRecordDTO> rt = standardUnitPictureRecordManage.findStandardUnitPictureRecordOfPage(dto, page);
        List<StandardUnitPictureRecordVO> list = StandardUnitPictureRecordConverter.toVO(rt.getList());
        PageResult<StandardUnitPictureRecordVO> result = new PageResult<StandardUnitPictureRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitPictureRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitPictureRecordWithTx(StandardUnitPictureRecordVO vo,HttpServletRequest req ) {
		StandardUnitPictureRecordDTO dto = StandardUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitPictureRecordManage.insertStandardUnitPictureRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitPictureRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitPictureRecordByIdWithTx(StandardUnitPictureRecordVO vo,HttpServletRequest req ) {
		StandardUnitPictureRecordDTO dto = StandardUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitPictureRecordManage.updateStandardUnitPictureRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitPictureRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitPictureRecordWithTx(StandardUnitPictureRecordVO vo,HttpServletRequest req ) {
		StandardUnitPictureRecordDTO dto = StandardUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitPictureRecordManage.deleteStandardUnitPictureRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	