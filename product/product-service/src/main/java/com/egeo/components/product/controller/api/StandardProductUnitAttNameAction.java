package com.egeo.components.product.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitAttNameManage;
import com.egeo.components.product.converter.StandardProductUnitAttNameConverter;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.vo.StandardProductUnitAttNameVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnitAttName")
public class StandardProductUnitAttNameAction extends BaseSpringController {
	
	@Resource(name="standardProductUnitAttName")
	private StandardProductUnitAttNameManage standardProductUnitAttNameManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttNameById")
	@ResponseBody
	public JsonResult<StandardProductUnitAttNameVO> findStandardProductUnitAttNameById(Long id ) {
		
		StandardProductUnitAttNameDTO dto = new StandardProductUnitAttNameDTO();
		dto.setId(id);
		StandardProductUnitAttNameDTO rt = standardProductUnitAttNameManage.findStandardProductUnitAttNameById(dto);		
		return JsonResult.success(StandardProductUnitAttNameConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttNameAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitAttNameVO>> findStandardProductUnitAttNameAll(StandardProductUnitAttNameVO vo,HttpServletRequest req ) {
		StandardProductUnitAttNameDTO dto = StandardProductUnitAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitAttNameDTO> rt = standardProductUnitAttNameManage.findStandardProductUnitAttNameAll(dto);	
		return JsonResult.success(StandardProductUnitAttNameConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAttNameOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitAttNameVO>> findStandardProductUnitAttNameOfPage(StandardProductUnitAttNameVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitAttNameDTO dto = StandardProductUnitAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitAttNameDTO> rt = standardProductUnitAttNameManage.findStandardProductUnitAttNameOfPage(dto, page);
        List<StandardProductUnitAttNameVO> list = StandardProductUnitAttNameConverter.toVO(rt.getList());
        PageResult<StandardProductUnitAttNameVO> result = new PageResult<StandardProductUnitAttNameVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitAttNameWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitAttNameWithTx(StandardProductUnitAttNameVO vo,HttpServletRequest req ) {
		StandardProductUnitAttNameDTO dto = StandardProductUnitAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitAttNameManage.insertStandardProductUnitAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitAttNameByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitAttNameByIdWithTx(StandardProductUnitAttNameVO vo,HttpServletRequest req ) {
		StandardProductUnitAttNameDTO dto = StandardProductUnitAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitAttNameManage.updateStandardProductUnitAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitAttNameWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitAttNameWithTx(StandardProductUnitAttNameVO vo,HttpServletRequest req ) {
		StandardProductUnitAttNameDTO dto = StandardProductUnitAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitAttNameManage.deleteStandardProductUnitAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findByStandardUnitId")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findByStandardUnitId(Long standardUnitId,HttpServletRequest req ) {
		List<Map<String, Object>> rt = standardProductUnitAttNameManage.findByStandardUnitId(standardUnitId);	
		return JsonResult.success(rt);
					 
	}	
		
}
	