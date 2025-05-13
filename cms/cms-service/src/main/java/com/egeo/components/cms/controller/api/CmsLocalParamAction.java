package com.egeo.components.cms.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.CmsLocalParamManage;
import com.egeo.components.cms.converter.CmsLocalParamConverter;
import com.egeo.components.cms.dto.CmsLocalParamDTO;
import com.egeo.components.cms.vo.CmsLocalParamVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/cmsLocalParam")
public class CmsLocalParamAction extends BaseSpringController {
	
	@Resource(name="cmsLocalParam")
	private CmsLocalParamManage cmsLocalParamManage;


	// 业务方法：
	@RequestMapping(value = "/findCmsLocalParamById")
	@ResponseBody
	public JsonResult<CmsLocalParamVO> findCmsLocalParamById(Long id ) {
		
		CmsLocalParamDTO dto = new CmsLocalParamDTO();
		dto.setId(id);
		CmsLocalParamDTO rt = cmsLocalParamManage.findCmsLocalParamById(dto);		
		return JsonResult.success(CmsLocalParamConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCmsLocalParamAll")
	@ResponseBody
	public JsonResult<List<CmsLocalParamVO>> findCmsLocalParamAll(CmsLocalParamVO vo,HttpServletRequest req ) {
		CmsLocalParamDTO dto = CmsLocalParamConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CmsLocalParamDTO> rt = cmsLocalParamManage.findCmsLocalParamAll(dto);	
		return JsonResult.success(CmsLocalParamConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCmsLocalParamOfPage")
	@ResponseBody
	public JsonResult<PageResult<CmsLocalParamVO>> findCmsLocalParamOfPage(CmsLocalParamVO vo,Pagination page,HttpServletRequest req ) {
		CmsLocalParamDTO dto = CmsLocalParamConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CmsLocalParamDTO> rt = cmsLocalParamManage.findCmsLocalParamOfPage(dto, page);
        List<CmsLocalParamVO> list = CmsLocalParamConverter.toVO(rt.getList());
        PageResult<CmsLocalParamVO> result = new PageResult<CmsLocalParamVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCmsLocalParamWithTx")
	@ResponseBody
	public JsonResult<Long> insertCmsLocalParamWithTx(CmsLocalParamVO vo,HttpServletRequest req ) {
		CmsLocalParamDTO dto = CmsLocalParamConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = cmsLocalParamManage.insertCmsLocalParamWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCmsLocalParamByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCmsLocalParamByIdWithTx(CmsLocalParamVO vo,HttpServletRequest req ) {
		CmsLocalParamDTO dto = CmsLocalParamConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cmsLocalParamManage.updateCmsLocalParamWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCmsLocalParamWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCmsLocalParamWithTx(CmsLocalParamVO vo,HttpServletRequest req ) {
		CmsLocalParamDTO dto = CmsLocalParamConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cmsLocalParamManage.deleteCmsLocalParamWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	