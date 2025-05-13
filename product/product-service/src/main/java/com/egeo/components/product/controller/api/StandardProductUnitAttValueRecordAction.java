package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitAttValueRecordManage;
import com.egeo.components.product.converter.StandardProductUnitAttValueRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;
import com.egeo.components.product.vo.StandardProductUnitAttValueRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitAttValueRecord")
public class StandardProductUnitAttValueRecordAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitAttValueRecord")
	private StandardProductUnitAttValueRecordManage standardProductUnitAttValueRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttValueRecordById")
	@ResponseBody
	public JsonResult<StandardProductUnitAttValueRecordVO> findStandardProductUnitAttValueRecordById(Long id ) {
		
		StandardProductUnitAttValueRecordDTO dto = new StandardProductUnitAttValueRecordDTO();
		dto.setId(id);
		StandardProductUnitAttValueRecordDTO rt = standardProductUnitAttValueRecordManage.findStandardProductUnitAttValueRecordById(dto);		
		return JsonResult.success(StandardProductUnitAttValueRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttValueRecordAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitAttValueRecordVO>> findStandardProductUnitAttValueRecordAll(StandardProductUnitAttValueRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitAttValueRecordDTO dto = StandardProductUnitAttValueRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitAttValueRecordDTO> rt = standardProductUnitAttValueRecordManage.findStandardProductUnitAttValueRecordAll(dto);	
		return JsonResult.success(StandardProductUnitAttValueRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttValueRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitAttValueRecordVO>> findStandardProductUnitAttValueRecordOfPage(StandardProductUnitAttValueRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitAttValueRecordDTO dto = StandardProductUnitAttValueRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitAttValueRecordDTO> rt = standardProductUnitAttValueRecordManage.findStandardProductUnitAttValueRecordOfPage(dto, page);
        List<StandardProductUnitAttValueRecordVO> list = StandardProductUnitAttValueRecordConverter.toVO(rt.getList());
        PageResult<StandardProductUnitAttValueRecordVO> result = new PageResult<StandardProductUnitAttValueRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitAttValueRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitAttValueRecordDTO dto = StandardProductUnitAttValueRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitAttValueRecordManage.insertStandardProductUnitAttValueRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitAttValueRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitAttValueRecordByIdWithTx(StandardProductUnitAttValueRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitAttValueRecordDTO dto = StandardProductUnitAttValueRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitAttValueRecordManage.updateStandardProductUnitAttValueRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitAttValueRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitAttValueRecordDTO dto = StandardProductUnitAttValueRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitAttValueRecordManage.deleteStandardProductUnitAttValueRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	