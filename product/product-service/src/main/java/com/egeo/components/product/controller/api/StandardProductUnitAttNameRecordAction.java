package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitAttNameRecordManage;
import com.egeo.components.product.converter.StandardProductUnitAttNameRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;
import com.egeo.components.product.vo.StandardProductUnitAttNameRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitAttNameRecord")
public class StandardProductUnitAttNameRecordAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitAttNameRecord")
	private StandardProductUnitAttNameRecordManage standardProductUnitAttNameRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttNameRecordById")
	@ResponseBody
	public JsonResult<StandardProductUnitAttNameRecordVO> findStandardProductUnitAttNameRecordById(Long id ) {
		
		StandardProductUnitAttNameRecordDTO dto = new StandardProductUnitAttNameRecordDTO();
		dto.setId(id);
		StandardProductUnitAttNameRecordDTO rt = standardProductUnitAttNameRecordManage.findStandardProductUnitAttNameRecordById(dto);		
		return JsonResult.success(StandardProductUnitAttNameRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttNameRecordAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitAttNameRecordVO>> findStandardProductUnitAttNameRecordAll(StandardProductUnitAttNameRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitAttNameRecordDTO dto = StandardProductUnitAttNameRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitAttNameRecordDTO> rt = standardProductUnitAttNameRecordManage.findStandardProductUnitAttNameRecordAll(dto);	
		return JsonResult.success(StandardProductUnitAttNameRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttNameRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitAttNameRecordVO>> findStandardProductUnitAttNameRecordOfPage(StandardProductUnitAttNameRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitAttNameRecordDTO dto = StandardProductUnitAttNameRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitAttNameRecordDTO> rt = standardProductUnitAttNameRecordManage.findStandardProductUnitAttNameRecordOfPage(dto, page);
        List<StandardProductUnitAttNameRecordVO> list = StandardProductUnitAttNameRecordConverter.toVO(rt.getList());
        PageResult<StandardProductUnitAttNameRecordVO> result = new PageResult<StandardProductUnitAttNameRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitAttNameRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitAttNameRecordDTO dto = StandardProductUnitAttNameRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitAttNameRecordManage.insertStandardProductUnitAttNameRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitAttNameRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitAttNameRecordByIdWithTx(StandardProductUnitAttNameRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitAttNameRecordDTO dto = StandardProductUnitAttNameRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitAttNameRecordManage.updateStandardProductUnitAttNameRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitAttNameRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitAttNameRecordDTO dto = StandardProductUnitAttNameRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitAttNameRecordManage.deleteStandardProductUnitAttNameRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	