package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitPictureManage;
import com.egeo.components.product.converter.StandardUnitPictureConverter;
import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.components.product.vo.StandardUnitPictureVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitPicture")
public class StandardUnitPictureAction extends BaseSpringController {
	
	@Resource(name="standardUnitPicture")
	private StandardUnitPictureManage standardUnitPictureManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitPictureById")
	@ResponseBody
	public JsonResult<StandardUnitPictureVO> findStandardUnitPictureById(Long id ) {
		
		StandardUnitPictureDTO dto = new StandardUnitPictureDTO();
		dto.setId(id);
		StandardUnitPictureDTO rt = standardUnitPictureManage.findStandardUnitPictureById(dto);		
		return JsonResult.success(StandardUnitPictureConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitPictureAll")
	@ResponseBody
	public JsonResult<List<StandardUnitPictureVO>> findStandardUnitPictureAll(StandardUnitPictureVO vo,HttpServletRequest req ) {
		StandardUnitPictureDTO dto = StandardUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardUnitPictureDTO> rt = standardUnitPictureManage.findStandardUnitPictureAll(dto);	
		return JsonResult.success(StandardUnitPictureConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitPictureOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitPictureVO>> findStandardUnitPictureOfPage(StandardUnitPictureVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitPictureDTO dto = StandardUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardUnitPictureDTO> rt = standardUnitPictureManage.findStandardUnitPictureOfPage(dto, page);
        List<StandardUnitPictureVO> list = StandardUnitPictureConverter.toVO(rt.getList());
        PageResult<StandardUnitPictureVO> result = new PageResult<StandardUnitPictureVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitPictureWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitPictureWithTx(StandardUnitPictureVO vo,HttpServletRequest req ) {
		StandardUnitPictureDTO dto = StandardUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitPictureManage.insertStandardUnitPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitPictureByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitPictureByIdWithTx(StandardUnitPictureVO vo,HttpServletRequest req ) {
		StandardUnitPictureDTO dto = StandardUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitPictureManage.updateStandardUnitPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitPictureWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitPictureWithTx(StandardUnitPictureVO vo,HttpServletRequest req ) {
		StandardUnitPictureDTO dto = StandardUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitPictureManage.deleteStandardUnitPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	