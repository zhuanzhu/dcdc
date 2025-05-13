package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitDescriptionRecordManage;
import com.egeo.components.product.converter.StandardProductUnitDescriptionRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitDescriptionRecordDTO;
import com.egeo.components.product.vo.StandardProductUnitDescriptionRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitDescriptionRecord")
public class StandardProductUnitDescriptionRecordAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitDescriptionRecord")
	private StandardProductUnitDescriptionRecordManage standardProductUnitDescriptionRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitDescriptionRecordById")
	@ResponseBody
	public JsonResult<StandardProductUnitDescriptionRecordVO> findStandardProductUnitDescriptionRecordById(Long id ) {
		
		StandardProductUnitDescriptionRecordDTO dto = new StandardProductUnitDescriptionRecordDTO();
		dto.setId(id);
		StandardProductUnitDescriptionRecordDTO rt = standardProductUnitDescriptionRecordManage.findStandardProductUnitDescriptionRecordById(dto);		
		return JsonResult.success(StandardProductUnitDescriptionRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitDescriptionRecordAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitDescriptionRecordVO>> findStandardProductUnitDescriptionRecordAll(StandardProductUnitDescriptionRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitDescriptionRecordDTO dto = StandardProductUnitDescriptionRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitDescriptionRecordDTO> rt = standardProductUnitDescriptionRecordManage.findStandardProductUnitDescriptionRecordAll(dto);	
		return JsonResult.success(StandardProductUnitDescriptionRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitDescriptionRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitDescriptionRecordVO>> findStandardProductUnitDescriptionRecordOfPage(StandardProductUnitDescriptionRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitDescriptionRecordDTO dto = StandardProductUnitDescriptionRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitDescriptionRecordDTO> rt = standardProductUnitDescriptionRecordManage.findStandardProductUnitDescriptionRecordOfPage(dto, page);
        List<StandardProductUnitDescriptionRecordVO> list = StandardProductUnitDescriptionRecordConverter.toVO(rt.getList());
        PageResult<StandardProductUnitDescriptionRecordVO> result = new PageResult<StandardProductUnitDescriptionRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitDescriptionRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitDescriptionRecordDTO dto = StandardProductUnitDescriptionRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitDescriptionRecordManage.insertStandardProductUnitDescriptionRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitDescriptionRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitDescriptionRecordByIdWithTx(StandardProductUnitDescriptionRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitDescriptionRecordDTO dto = StandardProductUnitDescriptionRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitDescriptionRecordManage.updateStandardProductUnitDescriptionRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitDescriptionRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitDescriptionRecordDTO dto = StandardProductUnitDescriptionRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitDescriptionRecordManage.deleteStandardProductUnitDescriptionRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	