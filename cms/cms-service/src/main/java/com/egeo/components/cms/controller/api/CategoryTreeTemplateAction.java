package com.egeo.components.cms.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.CategoryTreeTemplateManage;
import com.egeo.components.cms.converter.CategoryTreeTemplateConverter;
import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.components.cms.vo.CategoryTreeTemplateVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/categoryTreeTemplate")
public class CategoryTreeTemplateAction extends BaseSpringController {
	
	@Resource(name="categoryTreeTemplate")
	private CategoryTreeTemplateManage categoryTreeTemplateManage;


	// 业务方法：
	@RequestMapping(value = "/findCategoryTreeTemplateById")
	@ResponseBody
	public JsonResult<CategoryTreeTemplateVO> findCategoryTreeTemplateById(Long id ) {
		
		CategoryTreeTemplateDTO dto = new CategoryTreeTemplateDTO();
		dto.setId(id);
		CategoryTreeTemplateDTO rt = categoryTreeTemplateManage.findCategoryTreeTemplateById(dto);		
		return JsonResult.success(CategoryTreeTemplateConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCategoryTreeTemplateAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findCategoryTreeTemplateAll(CategoryTreeTemplateVO vo,HttpServletRequest req ) {
		CategoryTreeTemplateDTO dto = CategoryTreeTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Map<String, Object>> rt = categoryTreeTemplateManage.findCategoryTreeTemplateAll(dto);	
		return JsonResult.success(rt);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCategoryTreeTemplateOfPage")
	@ResponseBody
	public JsonResult<PageResult<CategoryTreeTemplateVO>> findCategoryTreeTemplateOfPage(CategoryTreeTemplateVO vo,Pagination page,HttpServletRequest req ) {
		CategoryTreeTemplateDTO dto = CategoryTreeTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CategoryTreeTemplateDTO> rt = categoryTreeTemplateManage.findCategoryTreeTemplateOfPage(dto, page);
        List<CategoryTreeTemplateVO> list = CategoryTreeTemplateConverter.toVO(rt.getList());
        PageResult<CategoryTreeTemplateVO> result = new PageResult<CategoryTreeTemplateVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCategoryTreeTemplateWithTx")
	@ResponseBody
	public JsonResult<Long> insertCategoryTreeTemplateWithTx(CategoryTreeTemplateVO vo,HttpServletRequest req ) {
		CategoryTreeTemplateDTO dto = CategoryTreeTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = categoryTreeTemplateManage.insertCategoryTreeTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCategoryTreeTemplateByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCategoryTreeTemplateByIdWithTx(CategoryTreeTemplateVO vo,HttpServletRequest req ) {
		CategoryTreeTemplateDTO dto = CategoryTreeTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = categoryTreeTemplateManage.updateCategoryTreeTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCategoryTreeTemplateWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCategoryTreeTemplateWithTx(CategoryTreeTemplateVO vo,HttpServletRequest req ) {
		CategoryTreeTemplateDTO dto = CategoryTreeTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = categoryTreeTemplateManage.deleteCategoryTreeTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	