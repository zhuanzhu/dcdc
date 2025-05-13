package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitAttValueManage;
import com.egeo.components.product.converter.StandardProductUnitAttValueConverter;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.vo.StandardProductUnitAttValueVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitAttValue")
public class StandardProductUnitAttValueAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitAttValue")
	private StandardProductUnitAttValueManage standardProductUnitAttValueManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttValueById")
	@ResponseBody
	public JsonResult<StandardProductUnitAttValueVO> findStandardProductUnitAttValueById(Long id ) {
		
		StandardProductUnitAttValueDTO dto = new StandardProductUnitAttValueDTO();
		dto.setId(id);
		StandardProductUnitAttValueDTO rt = standardProductUnitAttValueManage.findStandardProductUnitAttValueById(dto);		
		return JsonResult.success(StandardProductUnitAttValueConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttValueAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitAttValueVO>> findStandardProductUnitAttValueAll(StandardProductUnitAttValueVO vo,HttpServletRequest req ) {
		StandardProductUnitAttValueDTO dto = StandardProductUnitAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitAttValueDTO> rt = standardProductUnitAttValueManage.findStandardProductUnitAttValueAll(dto);	
		return JsonResult.success(StandardProductUnitAttValueConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttValueOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitAttValueVO>> findStandardProductUnitAttValueOfPage(StandardProductUnitAttValueVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitAttValueDTO dto = StandardProductUnitAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitAttValueDTO> rt = standardProductUnitAttValueManage.findStandardProductUnitAttValueOfPage(dto, page);
        List<StandardProductUnitAttValueVO> list = StandardProductUnitAttValueConverter.toVO(rt.getList());
        PageResult<StandardProductUnitAttValueVO> result = new PageResult<StandardProductUnitAttValueVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitAttValueWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitAttValueWithTx(StandardProductUnitAttValueVO vo,HttpServletRequest req ) {
		StandardProductUnitAttValueDTO dto = StandardProductUnitAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitAttValueManage.insertStandardProductUnitAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitAttValueByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitAttValueByIdWithTx(StandardProductUnitAttValueVO vo,HttpServletRequest req ) {
		StandardProductUnitAttValueDTO dto = StandardProductUnitAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitAttValueManage.updateStandardProductUnitAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitAttValueWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitAttValueWithTx(StandardProductUnitAttValueVO vo,HttpServletRequest req ) {
		StandardProductUnitAttValueDTO dto = StandardProductUnitAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitAttValueManage.deleteStandardProductUnitAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	