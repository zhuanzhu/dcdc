package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitDescriptionManage;
import com.egeo.components.product.converter.StandardProductUnitDescriptionConverter;
import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.components.product.vo.StandardProductUnitDescriptionVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitDescription")
public class StandardProductUnitDescriptionAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitDescription")
	private StandardProductUnitDescriptionManage standardProductUnitDescriptionManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitDescriptionById")
	@ResponseBody
	public JsonResult<StandardProductUnitDescriptionVO> findStandardProductUnitDescriptionById(Long id ) {
		
		StandardProductUnitDescriptionDTO dto = new StandardProductUnitDescriptionDTO();
		dto.setId(id);
		StandardProductUnitDescriptionDTO rt = standardProductUnitDescriptionManage.findStandardProductUnitDescriptionById(dto);		
		return JsonResult.success(StandardProductUnitDescriptionConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitDescriptionAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitDescriptionVO>> findStandardProductUnitDescriptionAll(StandardProductUnitDescriptionVO vo,HttpServletRequest req ) {
		StandardProductUnitDescriptionDTO dto = StandardProductUnitDescriptionConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitDescriptionDTO> rt = standardProductUnitDescriptionManage.findStandardProductUnitDescriptionAll(dto);	
		return JsonResult.success(StandardProductUnitDescriptionConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitDescriptionOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitDescriptionVO>> findStandardProductUnitDescriptionOfPage(StandardProductUnitDescriptionVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitDescriptionDTO dto = StandardProductUnitDescriptionConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitDescriptionDTO> rt = standardProductUnitDescriptionManage.findStandardProductUnitDescriptionOfPage(dto, page);
        List<StandardProductUnitDescriptionVO> list = StandardProductUnitDescriptionConverter.toVO(rt.getList());
        PageResult<StandardProductUnitDescriptionVO> result = new PageResult<StandardProductUnitDescriptionVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitDescriptionWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionVO vo,HttpServletRequest req ) {
		StandardProductUnitDescriptionDTO dto = StandardProductUnitDescriptionConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitDescriptionManage.insertStandardProductUnitDescriptionWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitDescriptionByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitDescriptionByIdWithTx(StandardProductUnitDescriptionVO vo,HttpServletRequest req ) {
		StandardProductUnitDescriptionDTO dto = StandardProductUnitDescriptionConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitDescriptionManage.updateStandardProductUnitDescriptionWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitDescriptionWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionVO vo,HttpServletRequest req ) {
		StandardProductUnitDescriptionDTO dto = StandardProductUnitDescriptionConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitDescriptionManage.deleteStandardProductUnitDescriptionWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	