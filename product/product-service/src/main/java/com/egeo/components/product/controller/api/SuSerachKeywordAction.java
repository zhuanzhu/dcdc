package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SuSerachKeywordManage;
import com.egeo.components.product.converter.SuSerachKeywordConverter;
import com.egeo.components.product.dto.SuSerachKeywordDTO;
import com.egeo.components.product.vo.SuSerachKeywordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/suSerachKeyword")
public class SuSerachKeywordAction extends BaseSpringController {
	
	@Resource(name="suSerachKeyword")
	private SuSerachKeywordManage suSerachKeywordManage;


	// 业务方法：
	@RequestMapping(value = "/findSuSerachKeywordById")
	@ResponseBody
	public JsonResult<SuSerachKeywordVO> findSuSerachKeywordById(Long id ) {
		
		SuSerachKeywordDTO dto = new SuSerachKeywordDTO();
		dto.setId(id);
		SuSerachKeywordDTO rt = suSerachKeywordManage.findSuSerachKeywordById(dto);		
		return JsonResult.success(SuSerachKeywordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSuSerachKeywordAll")
	@ResponseBody
	public JsonResult<List<SuSerachKeywordVO>> findSuSerachKeywordAll(SuSerachKeywordVO vo,HttpServletRequest req ) {
		SuSerachKeywordDTO dto = SuSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SuSerachKeywordDTO> rt = suSerachKeywordManage.findSuSerachKeywordAll(dto);	
		return JsonResult.success(SuSerachKeywordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSuSerachKeywordOfPage")
	@ResponseBody
	public JsonResult<PageResult<SuSerachKeywordVO>> findSuSerachKeywordOfPage(SuSerachKeywordVO vo,Pagination page,HttpServletRequest req ) {
		SuSerachKeywordDTO dto = SuSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SuSerachKeywordDTO> rt = suSerachKeywordManage.findSuSerachKeywordOfPage(dto, page);
        List<SuSerachKeywordVO> list = SuSerachKeywordConverter.toVO(rt.getList());
        PageResult<SuSerachKeywordVO> result = new PageResult<SuSerachKeywordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSuSerachKeywordWithTx")
	@ResponseBody
	public JsonResult<Long> insertSuSerachKeywordWithTx(SuSerachKeywordVO vo,HttpServletRequest req ) {
		SuSerachKeywordDTO dto = SuSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = suSerachKeywordManage.insertSuSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSuSerachKeywordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSuSerachKeywordByIdWithTx(SuSerachKeywordVO vo,HttpServletRequest req ) {
		SuSerachKeywordDTO dto = SuSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = suSerachKeywordManage.updateSuSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSuSerachKeywordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSuSerachKeywordWithTx(SuSerachKeywordVO vo,HttpServletRequest req ) {
		SuSerachKeywordDTO dto = SuSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = suSerachKeywordManage.deleteSuSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	