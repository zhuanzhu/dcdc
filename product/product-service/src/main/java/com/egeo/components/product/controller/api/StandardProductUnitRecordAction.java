package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitRecordManage;
import com.egeo.components.product.converter.StandardProductUnitRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitRecordDTO;
import com.egeo.components.product.vo.StandardProductUnitRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitRecord")
public class StandardProductUnitRecordAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitRecord")
	private StandardProductUnitRecordManage standardProductUnitRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitRecordById")
	@ResponseBody
	public JsonResult<StandardProductUnitRecordVO> findStandardProductUnitRecordById(Long id ) {
		
		StandardProductUnitRecordDTO dto = new StandardProductUnitRecordDTO();
		dto.setId(id);
		StandardProductUnitRecordDTO rt = standardProductUnitRecordManage.findStandardProductUnitRecordById(dto);		
		return JsonResult.success(StandardProductUnitRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitRecordAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitRecordVO>> findStandardProductUnitRecordAll(StandardProductUnitRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitRecordDTO dto = StandardProductUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitRecordDTO> rt = standardProductUnitRecordManage.findStandardProductUnitRecordAll(dto);	
		return JsonResult.success(StandardProductUnitRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitRecordVO>> findStandardProductUnitRecordOfPage(StandardProductUnitRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitRecordDTO dto = StandardProductUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitRecordDTO> rt = standardProductUnitRecordManage.findStandardProductUnitRecordOfPage(dto, page);
        List<StandardProductUnitRecordVO> list = StandardProductUnitRecordConverter.toVO(rt.getList());
        PageResult<StandardProductUnitRecordVO> result = new PageResult<StandardProductUnitRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitRecordWithTx(StandardProductUnitRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitRecordDTO dto = StandardProductUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitRecordManage.insertStandardProductUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitRecordByIdWithTx(StandardProductUnitRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitRecordDTO dto = StandardProductUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitRecordManage.updateStandardProductUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitRecordWithTx(StandardProductUnitRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitRecordDTO dto = StandardProductUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitRecordManage.deleteStandardProductUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	