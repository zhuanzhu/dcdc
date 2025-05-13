package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitPictureRecordManage;
import com.egeo.components.product.converter.StandardProductUnitPictureRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;
import com.egeo.components.product.vo.StandardProductUnitPictureRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitPictureRecord")
public class StandardProductUnitPictureRecordAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitPictureRecord")
	private StandardProductUnitPictureRecordManage standardProductUnitPictureRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitPictureRecordById")
	@ResponseBody
	public JsonResult<StandardProductUnitPictureRecordVO> findStandardProductUnitPictureRecordById(Long id ) {
		
		StandardProductUnitPictureRecordDTO dto = new StandardProductUnitPictureRecordDTO();
		dto.setId(id);
		StandardProductUnitPictureRecordDTO rt = standardProductUnitPictureRecordManage.findStandardProductUnitPictureRecordById(dto);		
		return JsonResult.success(StandardProductUnitPictureRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitPictureRecordAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitPictureRecordVO>> findStandardProductUnitPictureRecordAll(StandardProductUnitPictureRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitPictureRecordDTO dto = StandardProductUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitPictureRecordDTO> rt = standardProductUnitPictureRecordManage.findStandardProductUnitPictureRecordAll(dto);	
		return JsonResult.success(StandardProductUnitPictureRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitPictureRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitPictureRecordVO>> findStandardProductUnitPictureRecordOfPage(StandardProductUnitPictureRecordVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitPictureRecordDTO dto = StandardProductUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitPictureRecordDTO> rt = standardProductUnitPictureRecordManage.findStandardProductUnitPictureRecordOfPage(dto, page);
        List<StandardProductUnitPictureRecordVO> list = StandardProductUnitPictureRecordConverter.toVO(rt.getList());
        PageResult<StandardProductUnitPictureRecordVO> result = new PageResult<StandardProductUnitPictureRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitPictureRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitPictureRecordDTO dto = StandardProductUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitPictureRecordManage.insertStandardProductUnitPictureRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitPictureRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitPictureRecordByIdWithTx(StandardProductUnitPictureRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitPictureRecordDTO dto = StandardProductUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitPictureRecordManage.updateStandardProductUnitPictureRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitPictureRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordVO vo,HttpServletRequest req ) {
		StandardProductUnitPictureRecordDTO dto = StandardProductUnitPictureRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitPictureRecordManage.deleteStandardProductUnitPictureRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	