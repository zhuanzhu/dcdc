package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SurSerachKeywordManage;
import com.egeo.components.product.converter.SurSerachKeywordConverter;
import com.egeo.components.product.dto.SurSerachKeywordDTO;
import com.egeo.components.product.vo.SurSerachKeywordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/surSerachKeyword")
public class SurSerachKeywordAction extends BaseSpringController {
	
	@Resource(name="surSerachKeyword")
	private SurSerachKeywordManage surSerachKeywordManage;


	// 业务方法：
	@RequestMapping(value = "/findSurSerachKeywordById")
	@ResponseBody
	public JsonResult<SurSerachKeywordVO> findSurSerachKeywordById(Long id ) {
		
		SurSerachKeywordDTO dto = new SurSerachKeywordDTO();
		dto.setId(id);
		SurSerachKeywordDTO rt = surSerachKeywordManage.findSurSerachKeywordById(dto);		
		return JsonResult.success(SurSerachKeywordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSurSerachKeywordAll")
	@ResponseBody
	public JsonResult<List<SurSerachKeywordVO>> findSurSerachKeywordAll(SurSerachKeywordVO vo,HttpServletRequest req ) {
		SurSerachKeywordDTO dto = SurSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SurSerachKeywordDTO> rt = surSerachKeywordManage.findSurSerachKeywordAll(dto);	
		return JsonResult.success(SurSerachKeywordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSurSerachKeywordOfPage")
	@ResponseBody
	public JsonResult<PageResult<SurSerachKeywordVO>> findSurSerachKeywordOfPage(SurSerachKeywordVO vo,Pagination page,HttpServletRequest req ) {
		SurSerachKeywordDTO dto = SurSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SurSerachKeywordDTO> rt = surSerachKeywordManage.findSurSerachKeywordOfPage(dto, page);
        List<SurSerachKeywordVO> list = SurSerachKeywordConverter.toVO(rt.getList());
        PageResult<SurSerachKeywordVO> result = new PageResult<SurSerachKeywordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSurSerachKeywordWithTx")
	@ResponseBody
	public JsonResult<Long> insertSurSerachKeywordWithTx(SurSerachKeywordVO vo,HttpServletRequest req ) {
		SurSerachKeywordDTO dto = SurSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = surSerachKeywordManage.insertSurSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSurSerachKeywordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSurSerachKeywordByIdWithTx(SurSerachKeywordVO vo,HttpServletRequest req ) {
		SurSerachKeywordDTO dto = SurSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = surSerachKeywordManage.updateSurSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSurSerachKeywordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSurSerachKeywordWithTx(SurSerachKeywordVO vo,HttpServletRequest req ) {
		SurSerachKeywordDTO dto = SurSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = surSerachKeywordManage.deleteSurSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	