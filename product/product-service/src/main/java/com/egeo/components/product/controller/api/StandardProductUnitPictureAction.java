package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitPictureManage;
import com.egeo.components.product.converter.StandardProductUnitPictureConverter;
import com.egeo.components.product.dto.StandardProductUnitPictureDTO;
import com.egeo.components.product.vo.StandardProductUnitPictureVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitPicture")
public class StandardProductUnitPictureAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitPicture")
	private StandardProductUnitPictureManage standardProductUnitPictureManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitPictureById")
	@ResponseBody
	public JsonResult<StandardProductUnitPictureVO> findStandardProductUnitPictureById(Long id ) {
		
		StandardProductUnitPictureDTO dto = new StandardProductUnitPictureDTO();
		dto.setId(id);
		StandardProductUnitPictureDTO rt = standardProductUnitPictureManage.findStandardProductUnitPictureById(dto);		
		return JsonResult.success(StandardProductUnitPictureConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitPictureAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitPictureVO>> findStandardProductUnitPictureAll(StandardProductUnitPictureVO vo,HttpServletRequest req ) {
		StandardProductUnitPictureDTO dto = StandardProductUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitPictureDTO> rt = standardProductUnitPictureManage.findStandardProductUnitPictureAll(dto);	
		return JsonResult.success(StandardProductUnitPictureConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitPictureOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitPictureVO>> findStandardProductUnitPictureOfPage(StandardProductUnitPictureVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitPictureDTO dto = StandardProductUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitPictureDTO> rt = standardProductUnitPictureManage.findStandardProductUnitPictureOfPage(dto, page);
        List<StandardProductUnitPictureVO> list = StandardProductUnitPictureConverter.toVO(rt.getList());
        PageResult<StandardProductUnitPictureVO> result = new PageResult<StandardProductUnitPictureVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitPictureWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitPictureWithTx(StandardProductUnitPictureVO vo,HttpServletRequest req ) {
		StandardProductUnitPictureDTO dto = StandardProductUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitPictureManage.insertStandardProductUnitPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitPictureByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitPictureByIdWithTx(StandardProductUnitPictureVO vo,HttpServletRequest req ) {
		StandardProductUnitPictureDTO dto = StandardProductUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitPictureManage.updateStandardProductUnitPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitPictureWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitPictureWithTx(StandardProductUnitPictureVO vo,HttpServletRequest req ) {
		StandardProductUnitPictureDTO dto = StandardProductUnitPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitPictureManage.deleteStandardProductUnitPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	