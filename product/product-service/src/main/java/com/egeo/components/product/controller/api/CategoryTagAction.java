package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CategoryTagManage;
import com.egeo.components.product.converter.CategoryTagConverter;
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.components.product.vo.CategoryTagVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/categoryTag")
public class CategoryTagAction extends BaseSpringController {
	
	@Resource(name="categoryTag")
	private CategoryTagManage categoryTagManage;


	// 业务方法：
	@RequestMapping(value = "/findCategoryTagById")
	@ResponseBody
	public JsonResult<CategoryTagVO> findCategoryTagById(Long id ) {
		
		CategoryTagDTO dto = new CategoryTagDTO();
		dto.setId(id);
		CategoryTagDTO rt = categoryTagManage.findCategoryTagById(dto);		
		return JsonResult.success(CategoryTagConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCategoryTagAll")
	@ResponseBody
	public JsonResult<List<CategoryTagVO>> findCategoryTagAll(CategoryTagVO vo,HttpServletRequest req ) {
		CategoryTagDTO dto = CategoryTagConverter.toDTO(vo);
		List<CategoryTagDTO> rt = categoryTagManage.findCategoryTagAll(dto);	
		return JsonResult.success(CategoryTagConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCategoryTagOfPage")
	@ResponseBody
	public JsonResult<PageResult<CategoryTagVO>> findCategoryTagOfPage(CategoryTagVO vo,Pagination page,HttpServletRequest req ) {
		CategoryTagDTO dto = CategoryTagConverter.toDTO(vo);
		PageResult<CategoryTagDTO> rt = categoryTagManage.findCategoryTagOfPage(dto, page);
        List<CategoryTagVO> list = CategoryTagConverter.toVO(rt.getList());
        PageResult<CategoryTagVO> result = new PageResult<CategoryTagVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCategoryTagWithTx")
	@ResponseBody
	public JsonResult<Long> insertCategoryTagWithTx(CategoryTagVO vo,HttpServletRequest req ) {
		CategoryTagDTO dto = CategoryTagConverter.toDTO(vo);
		Long rt = categoryTagManage.insertCategoryTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCategoryTagByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCategoryTagByIdWithTx(CategoryTagVO vo,HttpServletRequest req ) {
		CategoryTagDTO dto = CategoryTagConverter.toDTO(vo);
		int rt = categoryTagManage.updateCategoryTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCategoryTagWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCategoryTagWithTx(CategoryTagVO vo,HttpServletRequest req ) {
		CategoryTagDTO dto = CategoryTagConverter.toDTO(vo);
		int rt = categoryTagManage.deleteCategoryTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	