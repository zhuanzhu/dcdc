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

import com.egeo.components.cms.business.CmsTemplateManage;
import com.egeo.components.cms.converter.CmsTemplateConverter;
import com.egeo.components.cms.dto.CmsTemplateDTO;
import com.egeo.components.cms.vo.CmsTemplateVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/cmsTemplate")
public class CmsTemplateAction extends BaseSpringController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsTemplateAction.class);
	
	@Resource(name="cmsTemplate")
	private CmsTemplateManage cmsTemplateManage;


	// 业务方法：
	@RequestMapping(value = "/findCmsTemplateById")
	@ResponseBody
	public JsonResult<CmsTemplateVO> findCmsTemplateById(Long id ) {
		
		CmsTemplateDTO dto = new CmsTemplateDTO();
		dto.setId(id);
		CmsTemplateDTO rt = cmsTemplateManage.findCmsTemplateById(dto);
		return JsonResult.success(CmsTemplateConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCmsTemplateAll")
	@ResponseBody
	public JsonResult<List<CmsTemplateVO>> findCmsTemplateAll(CmsTemplateVO vo,HttpServletRequest req ) {
		CmsTemplateDTO dto = CmsTemplateConverter.toDTO(vo);
		List<CmsTemplateDTO> rt = cmsTemplateManage.findCmsTemplateAll(dto);	
		return JsonResult.success(CmsTemplateConverter.toVO(rt));
					 
	}	

	// 业务方法：分页展示模板列表
	@RequestMapping(value = "/findCmsTemplateOfPage")
	@ResponseBody
	public JsonResult<PageResult<CmsTemplateVO>> findCmsTemplateOfPage(CmsTemplateVO vo,Pagination page,HttpServletRequest req ) {
		
		logger.info("模板列表页请求参数：{}", vo.toString());
		Integer searchType = vo.getSearchType();
		
		CmsTemplateDTO dto = CmsTemplateConverter.toDTO(vo);
		PageResult<CmsTemplateDTO> rt = cmsTemplateManage.findCmsTemplateOfPage(dto, page,searchType);
        List<CmsTemplateVO> list = CmsTemplateConverter.toVO(rt.getList());
        PageResult<CmsTemplateVO> result = new PageResult<CmsTemplateVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
        
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCmsTemplateWithTx")
	@ResponseBody
	public JsonResult<Long> insertCmsTemplateWithTx(CmsTemplateVO vo,HttpServletRequest req ) {
		CmsTemplateDTO dto = CmsTemplateConverter.toDTO(vo);
		//默认启用状态
		dto.setStatus(0);
		
		JsonResult<Long> rt = cmsTemplateManage.insertCmsTemplateWithTx(dto);	
		return rt;					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCmsTemplateByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCmsTemplateByIdWithTx(CmsTemplateVO vo,HttpServletRequest req ) {
		logger.info(" 编辑模板Id:",vo.getId());
		CmsTemplateDTO dto = CmsTemplateConverter.toDTO(vo);
		int rt = cmsTemplateManage.updateCmsTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCmsTemplateWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCmsTemplateWithTx(CmsTemplateVO vo,HttpServletRequest req ) {
		CmsTemplateDTO dto = CmsTemplateConverter.toDTO(vo);
		int rt = cmsTemplateManage.deleteCmsTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 查询组件列表
	@RequestMapping(value = "/findCmsElement")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findCmsElement(Integer groupType,HttpServletRequest req ) {
		List<Map<String, Object>> list = cmsTemplateManage.findCmsElement(groupType);	
		return JsonResult.success(list);					 
	}
	
	@RequestMapping(value = "/findCmsElementById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCmsElementById(Long id,HttpServletRequest req) {
		
		Map<String, Object> map = cmsTemplateManage.findCmsElementById(id);	
		
		return JsonResult.success(map);					 
	}
	
	/**
	 * 修改模板状态
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public JsonResult<Integer> updateStatus(CmsTemplateVO vo,HttpServletRequest req ) {
		logger.info("更改模板状态 模板Id:{},状态：{}",vo.getId(),vo.getStatus());
		CmsTemplateDTO dto = CmsTemplateConverter.toDTO(vo);
		int rt = cmsTemplateManage.updateStatus(dto);	
		return JsonResult.success(rt);					 
	}

	/**
	 * cms模板复制
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value = "/copyCmsTemplateWithTx")
	@ResponseBody
	public JsonResult<Long> copyCmsTemplateWithTx(Long templateId) {
		return cmsTemplateManage.copyCmsTemplateWithTx(templateId);
	}
}
	