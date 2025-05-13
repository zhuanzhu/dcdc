package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CategoryBannerManage;
import com.egeo.components.product.converter.CategoryBannerConverter;
import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.components.product.vo.CategoryBannerVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/categoryBanner")
public class CategoryBannerAction extends BaseSpringController {
	
	@Resource(name="categoryBanner")
	private CategoryBannerManage categoryBannerManage;


	// 业务方法：
	@RequestMapping(value = "/findCategoryBannerById")
	@ResponseBody
	public JsonResult<CategoryBannerVO> findCategoryBannerById(Long id ) {
		
		CategoryBannerDTO dto = new CategoryBannerDTO();
		dto.setId(id);
		CategoryBannerDTO rt = categoryBannerManage.findCategoryBannerById(dto);		
		return JsonResult.success(CategoryBannerConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCategoryBannerAll")
	@ResponseBody
	public JsonResult<List<CategoryBannerVO>> findCategoryBannerAll(CategoryBannerVO vo,HttpServletRequest req ) {
		CategoryBannerDTO dto = CategoryBannerConverter.toDTO(vo);
		List<CategoryBannerDTO> rt = categoryBannerManage.findCategoryBannerAll(dto);	
		return JsonResult.success(CategoryBannerConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCategoryBannerOfPage")
	@ResponseBody
	public JsonResult<PageResult<CategoryBannerVO>> findCategoryBannerOfPage(CategoryBannerVO vo,Pagination page,HttpServletRequest req ) {
		CategoryBannerDTO dto = CategoryBannerConverter.toDTO(vo);
		PageResult<CategoryBannerDTO> rt = categoryBannerManage.findCategoryBannerOfPage(dto, page);
        List<CategoryBannerVO> list = CategoryBannerConverter.toVO(rt.getList());
        PageResult<CategoryBannerVO> result = new PageResult<CategoryBannerVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCategoryBannerWithTx")
	@ResponseBody
	public JsonResult<Long> insertCategoryBannerWithTx(CategoryBannerVO vo,HttpServletRequest req ) {
		CategoryBannerDTO dto = CategoryBannerConverter.toDTO(vo);
		Long rt = categoryBannerManage.insertCategoryBannerWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCategoryBannerByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCategoryBannerByIdWithTx(CategoryBannerVO vo,HttpServletRequest req ) {
		CategoryBannerDTO dto = CategoryBannerConverter.toDTO(vo);
		int rt = categoryBannerManage.updateCategoryBannerWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCategoryBannerWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCategoryBannerWithTx(CategoryBannerVO vo,HttpServletRequest req ) {
		CategoryBannerDTO dto = CategoryBannerConverter.toDTO(vo);
		int rt = categoryBannerManage.deleteCategoryBannerWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	