package com.egeo.components.cms.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.components.cms.business.CmsPageTabManage;
import com.egeo.components.cms.converter.CmsPageTabConverter;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.components.cms.vo.CmsPageTabVO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/cmsPageTab")
public class CmsPageTabAction extends BaseSpringController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsPageTabAction.class);
	
	@Resource(name="cmsPageTab")
	private CmsPageTabManage cmsPageTabManage;


	// 业务方法：
	@RequestMapping(value = "/findCmsPageTabById")
	@ResponseBody
	public JsonResult<CmsPageTabVO> findCmsPageTabById(Long id ) {
		
		CmsPageTabDTO dto = new CmsPageTabDTO();
		dto.setId(id);
		CmsPageTabDTO rt = cmsPageTabManage.findCmsPageTabById(dto);		
		return JsonResult.success(CmsPageTabConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCmsPageTabAll")
	@ResponseBody
	public JsonResult<List<CmsPageTabVO>> findCmsPageTabAll(CmsPageTabVO vo,HttpServletRequest req ) {
		CmsPageTabDTO dto = CmsPageTabConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CmsPageTabDTO> rt = cmsPageTabManage.findCmsPageTabAll(dto);	
		return JsonResult.success(CmsPageTabConverter.toVO(rt));
					 
	}
	
	/**
	 * 前端查询pageTable
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCmsPageTabFront")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findCmsPageTabFront(CmsPageTabVO vo,HttpServletRequest req ) {
		
		CmsPageTabDTO dto = CmsPageTabConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		
		CacheUser user = this.getCacheUser(false);
		dto.setCompanyId(user.getCompanyId());
		List<Map<String, Object>> list = cmsPageTabManage.findCmsPageTabFront(dto);
		logger.info("CmsPageTabFront 返回数据 :",JSON.toJSONString(list));
		return JsonResult.success(list);
					 
	}

	// 业务方法：
	@RequestMapping(value = "/findCmsPageTabOfPage")
	@ResponseBody
	public JsonResult<PageResult<CmsPageTabVO>> findCmsPageTabOfPage(CmsPageTabVO vo,Pagination page,HttpServletRequest req ) {
		CmsPageTabDTO dto = CmsPageTabConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CmsPageTabDTO> rt = cmsPageTabManage.findCmsPageTabOfPage(dto, page);
        List<CmsPageTabVO> list = CmsPageTabConverter.toVO(rt.getList());
        PageResult<CmsPageTabVO> result = new PageResult<CmsPageTabVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCmsPageTabWithTx")
	@ResponseBody
	public JsonResult<Long> insertCmsPageTabWithTx(CmsPageTabVO vo,HttpServletRequest req ) {
		CmsPageTabDTO dto = CmsPageTabConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = cmsPageTabManage.insertCmsPageTabWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCmsPageTabByIdWithTx")
	@ResponseBody
	public JsonResult<String> updateCmsPageTabByIdWithTx(String tabList,HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(tabList)){
			return JsonResult.fail("tabList参数缺失");
		}
		List<CmsPageTabDTO> cmsPageTabDTOS = JsonUtils.jsonToList(tabList, CmsPageTabDTO.class);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(str)){
			return JsonResult.fail("platformId缺失");
		}
		Long platformId = Long.valueOf(str);

		cmsPageTabManage.updateCmsPageTabWithTx(cmsPageTabDTOS,platformId);
		return JsonResult.success("更新成功");
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCmsPageTabWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCmsPageTabWithTx(CmsPageTabVO vo,HttpServletRequest req ) {
		CmsPageTabDTO dto = CmsPageTabConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cmsPageTabManage.deleteCmsPageTabWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	