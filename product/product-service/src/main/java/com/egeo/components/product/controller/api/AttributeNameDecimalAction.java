package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.AttributeNameDecimalManage;
import com.egeo.components.product.converter.AttributeNameDecimalConverter;
import com.egeo.components.product.vo.AttributeNameDecimalVO;
import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/attributeNameDecimal")
public class AttributeNameDecimalAction extends BaseSpringController {
	
	@Resource(name="attributeNameDecimal")
	private AttributeNameDecimalManage attributeNameDecimalManage;


	// 业务方法：
	@RequestMapping(value = "/findAttributeNameDecimalById")
	@ResponseBody
	public JsonResult<AttributeNameDecimalVO> findAttributeNameDecimalById(Long id ) {
		
		AttributeNameDecimalDTO dto = new AttributeNameDecimalDTO();
		dto.setId(id);
		AttributeNameDecimalDTO rt = attributeNameDecimalManage.findAttributeNameDecimalById(dto);		
		return JsonResult.success(AttributeNameDecimalConverter.toVO(rt));
	}



	// 业务方法：
	@RequestMapping(value = "/findAttributeNameDecimalAll")
	@ResponseBody
	public JsonResult<List<AttributeNameDecimalVO>> findAttributeNameDecimalAll(AttributeNameDecimalVO vo,HttpServletRequest req ) {
		AttributeNameDecimalDTO dto = AttributeNameDecimalConverter.toDTO(vo);
		List<AttributeNameDecimalDTO> rt = attributeNameDecimalManage.findAttributeNameDecimalAll(dto);	
		return JsonResult.success(AttributeNameDecimalConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findAttributeNameDecimalOfPage")
	@ResponseBody
	public JsonResult<PageResult<AttributeNameDecimalVO>> findAttributeNameDecimalOfPage(AttributeNameDecimalVO vo,Pagination page,HttpServletRequest req ) {
		AttributeNameDecimalDTO dto = AttributeNameDecimalConverter.toDTO(vo);
		PageResult<AttributeNameDecimalDTO> rt = attributeNameDecimalManage.findAttributeNameDecimalOfPage(dto, page);
        List<AttributeNameDecimalVO> list = AttributeNameDecimalConverter.toVO(rt.getList());
        PageResult<AttributeNameDecimalVO> result = new PageResult<AttributeNameDecimalVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertAttributeNameDecimalWithTx")
	@ResponseBody
	public JsonResult<Long> insertAttributeNameDecimalWithTx(AttributeNameDecimalVO vo,HttpServletRequest req ) {
		AttributeNameDecimalDTO dto = AttributeNameDecimalConverter.toDTO(vo);
		Long rt = attributeNameDecimalManage.insertAttributeNameDecimalWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateAttributeNameDecimalByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateAttributeNameDecimalByIdWithTx(AttributeNameDecimalVO vo,HttpServletRequest req ) {
		AttributeNameDecimalDTO dto = AttributeNameDecimalConverter.toDTO(vo);
		int rt = attributeNameDecimalManage.updateAttributeNameDecimalWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteAttributeNameDecimalWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteAttributeNameDecimalWithTx(AttributeNameDecimalVO vo,HttpServletRequest req ) {
		AttributeNameDecimalDTO dto = AttributeNameDecimalConverter.toDTO(vo);
		int rt = attributeNameDecimalManage.deleteAttributeNameDecimalWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	