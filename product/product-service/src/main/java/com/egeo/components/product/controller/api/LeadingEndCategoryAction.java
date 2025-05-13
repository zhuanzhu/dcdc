package com.egeo.components.product.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.LeadingEndCategoryManage;
import com.egeo.components.product.converter.LeadingEndCategoryConverter;
import com.egeo.components.product.vo.LeadingEndCategoryVO;
import com.egeo.components.product.dto.LeadingEndCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/leadingEndCategory")
public class LeadingEndCategoryAction extends BaseSpringController {
	
	@Resource(name="leadingEndCategory")
	private LeadingEndCategoryManage leadingEndCategoryManage;


	// 业务方法：
	@RequestMapping(value = "/findLeadingEndCategoryById")
	@ResponseBody
	public JsonResult<LeadingEndCategoryVO> findLeadingEndCategoryById(Long id ) {
		
		LeadingEndCategoryDTO dto = new LeadingEndCategoryDTO();
		dto.setId(id);
		LeadingEndCategoryDTO rt = leadingEndCategoryManage.findLeadingEndCategoryById(dto);		
		return JsonResult.success(LeadingEndCategoryConverter.toVO(rt));
					 
	}



	/**
	 * 查询所有前端类目
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findLeadingEndCategoryAll")
	@ResponseBody
	public JsonResult<List<LeadingEndCategoryVO>> findLeadingEndCategoryAll(LeadingEndCategoryVO vo,HttpServletRequest req ) {
		logger.info("查询所有前端类目");
		LeadingEndCategoryDTO dto = LeadingEndCategoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<LeadingEndCategoryDTO> rt = leadingEndCategoryManage.findLeadingEndCategoryAll(dto);	
		return JsonResult.success(LeadingEndCategoryConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findLeadingEndCategoryOfPage")
	@ResponseBody
	public JsonResult<PageResult<LeadingEndCategoryVO>> findLeadingEndCategoryOfPage(LeadingEndCategoryVO vo,Pagination page,HttpServletRequest req ) {
		LeadingEndCategoryDTO dto = LeadingEndCategoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<LeadingEndCategoryDTO> rt = leadingEndCategoryManage.findLeadingEndCategoryOfPage(dto, page);
        List<LeadingEndCategoryVO> list = LeadingEndCategoryConverter.toVO(rt.getList());
        PageResult<LeadingEndCategoryVO> result = new PageResult<LeadingEndCategoryVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertLeadingEndCategoryWithTx")
	@ResponseBody
	public JsonResult<Long> insertLeadingEndCategoryWithTx(LeadingEndCategoryVO vo,HttpServletRequest req ) {
		LeadingEndCategoryDTO dto = LeadingEndCategoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = leadingEndCategoryManage.insertLeadingEndCategoryWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateLeadingEndCategoryByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateLeadingEndCategoryByIdWithTx(LeadingEndCategoryVO vo,HttpServletRequest req ) {
		LeadingEndCategoryDTO dto = LeadingEndCategoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = leadingEndCategoryManage.updateLeadingEndCategoryWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteLeadingEndCategoryWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteLeadingEndCategoryWithTx(LeadingEndCategoryVO vo,HttpServletRequest req ) {
		LeadingEndCategoryDTO dto = LeadingEndCategoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = leadingEndCategoryManage.deleteLeadingEndCategoryWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 查询所有只返回id和名称
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findLeadingEndCategoryIdAndName")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findLeadingEndCategoryIdAndName(LeadingEndCategoryVO vo,HttpServletRequest req ) {
		LeadingEndCategoryDTO dto = LeadingEndCategoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Map<String, Object>> rt = leadingEndCategoryManage.findLeadingEndCategoryIdAndName(dto);	
		return JsonResult.success(rt);
					 
	}	
	
	/**
	 * 客户端分页显示类目及子模块
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/leadingEndCategoryOfPageApp")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> leadingEndCategoryOfPageApp(LeadingEndCategoryVO vo,Pagination page,HttpServletRequest req ) {
		LeadingEndCategoryDTO dto = LeadingEndCategoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = leadingEndCategoryManage.leadingEndCategoryOfPageApp(dto, page);
		
		return JsonResult.success(rt);
					 
	}
		
}
	