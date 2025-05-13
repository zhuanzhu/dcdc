package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SerachSortManage;
import com.egeo.components.product.converter.SerachSortConverter;
import com.egeo.components.product.dto.SerachSortDTO;
import com.egeo.components.product.vo.SerachSortVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/serachSort")
public class SerachSortAction extends BaseSpringController {
	
	@Resource(name="serachSort")
	private SerachSortManage serachSortManage;


	// 业务方法：
	@RequestMapping(value = "/findSerachSortById")
	@ResponseBody
	public JsonResult<SerachSortVO> findSerachSortById(Long id ) {
		
		SerachSortDTO dto = new SerachSortDTO();
		dto.setId(id);
		SerachSortDTO rt = serachSortManage.findSerachSortById(dto);		
		return JsonResult.success(SerachSortConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSerachSortAll")
	@ResponseBody
	public JsonResult<List<SerachSortVO>> findSerachSortAll(SerachSortVO vo,HttpServletRequest req ) {
		SerachSortDTO dto = SerachSortConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SerachSortDTO> rt = serachSortManage.findSerachSortAll(dto);	
		return JsonResult.success(SerachSortConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSerachSortOfPage")
	@ResponseBody
	public JsonResult<PageResult<SerachSortVO>> findSerachSortOfPage(SerachSortVO vo,Pagination page,HttpServletRequest req ) {
		SerachSortDTO dto = SerachSortConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SerachSortDTO> rt = serachSortManage.findSerachSortOfPage(dto, page);
        List<SerachSortVO> list = SerachSortConverter.toVO(rt.getList());
        PageResult<SerachSortVO> result = new PageResult<SerachSortVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSerachSortWithTx")
	@ResponseBody
	public JsonResult<Long> insertSerachSortWithTx(SerachSortVO vo,HttpServletRequest req ) {
		SerachSortDTO dto = SerachSortConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = serachSortManage.insertSerachSortWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSerachSortByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSerachSortByIdWithTx(SerachSortVO vo,HttpServletRequest req ) {
		SerachSortDTO dto = SerachSortConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = serachSortManage.updateSerachSortWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSerachSortWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSerachSortWithTx(SerachSortVO vo,HttpServletRequest req ) {
		SerachSortDTO dto = SerachSortConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = serachSortManage.deleteSerachSortWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	