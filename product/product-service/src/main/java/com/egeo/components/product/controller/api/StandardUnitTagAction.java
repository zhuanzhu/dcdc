package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitTagManage;
import com.egeo.components.product.converter.StandardUnitTagConverter;
import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.components.product.vo.StandardUnitTagVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitTag")
public class StandardUnitTagAction extends BaseSpringController {
	
	@Resource(name="standardUnitTag")
	private StandardUnitTagManage standardUnitTagManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitTagById")
	@ResponseBody
	public JsonResult<StandardUnitTagVO> findStandardUnitTagById(Long id ) {
		
		StandardUnitTagDTO dto = new StandardUnitTagDTO();
		dto.setId(id);
		StandardUnitTagDTO rt = standardUnitTagManage.findStandardUnitTagById(dto);		
		return JsonResult.success(StandardUnitTagConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitTagAll")
	@ResponseBody
	public JsonResult<List<StandardUnitTagVO>> findStandardUnitTagAll(StandardUnitTagVO vo,HttpServletRequest req ) {
		StandardUnitTagDTO dto = StandardUnitTagConverter.toDTO(vo);
		List<StandardUnitTagDTO> rt = standardUnitTagManage.findStandardUnitTagAll(dto);	
		return JsonResult.success(StandardUnitTagConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitTagOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitTagVO>> findStandardUnitTagOfPage(StandardUnitTagVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitTagDTO dto = StandardUnitTagConverter.toDTO(vo);
		PageResult<StandardUnitTagDTO> rt = standardUnitTagManage.findStandardUnitTagOfPage(dto, page);
        List<StandardUnitTagVO> list = StandardUnitTagConverter.toVO(rt.getList());
        PageResult<StandardUnitTagVO> result = new PageResult<StandardUnitTagVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitTagWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitTagWithTx(StandardUnitTagVO vo,HttpServletRequest req ) {
		StandardUnitTagDTO dto = StandardUnitTagConverter.toDTO(vo);
		Long rt = standardUnitTagManage.insertStandardUnitTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitTagByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitTagByIdWithTx(StandardUnitTagVO vo,HttpServletRequest req ) {
		StandardUnitTagDTO dto = StandardUnitTagConverter.toDTO(vo);
		int rt = standardUnitTagManage.updateStandardUnitTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitTagWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitTagWithTx(StandardUnitTagVO vo,HttpServletRequest req ) {
		StandardUnitTagDTO dto = StandardUnitTagConverter.toDTO(vo);
		int rt = standardUnitTagManage.deleteStandardUnitTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	