package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitDescribeRecordManage;
import com.egeo.components.product.converter.StandardUnitDescribeRecordConverter;
import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.components.product.vo.StandardUnitDescribeRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitDescribeRecord")
public class StandardUnitDescribeRecordAction extends BaseSpringController {
	
	@Resource(name="standardUnitDescribeRecord")
	private StandardUnitDescribeRecordManage standardUnitDescribeRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitDescribeRecordById")
	@ResponseBody
	public JsonResult<StandardUnitDescribeRecordVO> findStandardUnitDescribeRecordById(Long id ) {
		
		StandardUnitDescribeRecordDTO dto = new StandardUnitDescribeRecordDTO();
		dto.setId(id);
		StandardUnitDescribeRecordDTO rt = standardUnitDescribeRecordManage.findStandardUnitDescribeRecordById(dto);		
		return JsonResult.success(StandardUnitDescribeRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitDescribeRecordAll")
	@ResponseBody
	public JsonResult<List<StandardUnitDescribeRecordVO>> findStandardUnitDescribeRecordAll(StandardUnitDescribeRecordVO vo,HttpServletRequest req ) {
		StandardUnitDescribeRecordDTO dto = StandardUnitDescribeRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardUnitDescribeRecordDTO> rt = standardUnitDescribeRecordManage.findStandardUnitDescribeRecordAll(dto);	
		return JsonResult.success(StandardUnitDescribeRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitDescribeRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitDescribeRecordVO>> findStandardUnitDescribeRecordOfPage(StandardUnitDescribeRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitDescribeRecordDTO dto = StandardUnitDescribeRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardUnitDescribeRecordDTO> rt = standardUnitDescribeRecordManage.findStandardUnitDescribeRecordOfPage(dto, page);
        List<StandardUnitDescribeRecordVO> list = StandardUnitDescribeRecordConverter.toVO(rt.getList());
        PageResult<StandardUnitDescribeRecordVO> result = new PageResult<StandardUnitDescribeRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitDescribeRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordVO vo,HttpServletRequest req ) {
		StandardUnitDescribeRecordDTO dto = StandardUnitDescribeRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitDescribeRecordManage.insertStandardUnitDescribeRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitDescribeRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitDescribeRecordByIdWithTx(StandardUnitDescribeRecordVO vo,HttpServletRequest req ) {
		StandardUnitDescribeRecordDTO dto = StandardUnitDescribeRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitDescribeRecordManage.updateStandardUnitDescribeRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitDescribeRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordVO vo,HttpServletRequest req ) {
		StandardUnitDescribeRecordDTO dto = StandardUnitDescribeRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitDescribeRecordManage.deleteStandardUnitDescribeRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	