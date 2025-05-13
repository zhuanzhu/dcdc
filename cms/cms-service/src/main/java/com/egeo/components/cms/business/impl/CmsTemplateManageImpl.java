package com.egeo.components.cms.business.impl;
	


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.CmsTemplateManage;
import com.egeo.components.cms.dto.CmsTemplateDTO;
import com.egeo.components.cms.facade.CmsTemplateFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("cmsTemplate")
public class CmsTemplateManageImpl implements CmsTemplateManage{

	
	@Resource(name="cmsTemplateFacade")
	private CmsTemplateFacade cmsTemplateFacade;

	@Override
	public CmsTemplateDTO findCmsTemplateById(CmsTemplateDTO dto) {
		CmsTemplateDTO cmsTemplateDTO = cmsTemplateFacade.findCmsTemplateById(dto);
		if (EmptyUtil.isNotEmpty(cmsTemplateDTO.getClientVersionARemark()) 
				&& !cmsTemplateDTO.getClientVersionARemark().contains("安卓")) {
			cmsTemplateDTO.setClientVersionARemark("安卓" + cmsTemplateDTO.getClientVersionARemark());
		}
		if (EmptyUtil.isNotEmpty(cmsTemplateDTO.getClientVersionIRemark()) 
				&& !cmsTemplateDTO.getClientVersionIRemark().contains("IOS") 
				&& !cmsTemplateDTO.getClientVersionIRemark().contains("ios")) {
			cmsTemplateDTO.setClientVersionIRemark("IOS" + cmsTemplateDTO.getClientVersionIRemark());
		}
		return cmsTemplateDTO;
	}

	@Override
	public PageResult<CmsTemplateDTO> findCmsTemplateOfPage(CmsTemplateDTO dto, Pagination page,Integer searchType) {
		PageResult<CmsTemplateDTO> result = cmsTemplateFacade.findCmsTemplateOfPage(dto, page,searchType);
		for (CmsTemplateDTO cmsTemplateDTO : result.getList()) {
			if (EmptyUtil.isNotEmpty(cmsTemplateDTO.getClientVersionARemark()) 
					&& !cmsTemplateDTO.getClientVersionARemark().contains("安卓")) {
				cmsTemplateDTO.setClientVersionARemark("安卓" + cmsTemplateDTO.getClientVersionARemark());
			}
			if (EmptyUtil.isNotEmpty(cmsTemplateDTO.getClientVersionIRemark()) 
					&& !cmsTemplateDTO.getClientVersionIRemark().contains("IOS") 
					&& !cmsTemplateDTO.getClientVersionIRemark().contains("ios")) {
				cmsTemplateDTO.setClientVersionIRemark("IOS" + cmsTemplateDTO.getClientVersionIRemark());
			}
		}
		return result;
	}

	@Override
	public List<CmsTemplateDTO> findCmsTemplateAll(CmsTemplateDTO dto) {
		return cmsTemplateFacade.findCmsTemplateAll(dto);
	}

	@Override
	public JsonResult<Long> insertCmsTemplateWithTx(CmsTemplateDTO dto) {
		// 动态逻辑代码
		if (dto.getType() == null) {
			return JsonResult.fail("请指定模板类型");
		}
		if (dto.getClientType() == null) {
			return JsonResult.fail("请指定客户端类型");
		}
		String templateName = dto.getName();
		if(EmptyUtil.isBlank(templateName)) {
			return JsonResult.fail("请填写模板名称");
		}else {
			CmsTemplateDTO cmsTemplateDTO = new CmsTemplateDTO();
			cmsTemplateDTO.setName(templateName);
			List<CmsTemplateDTO> list = cmsTemplateFacade.findCmsTemplateAll(cmsTemplateDTO);
			if(list != null && list.size() > 0) {
				return JsonResult.fail("该模板名称已存在");
			}
		}
			
		Long i = cmsTemplateFacade.insertCmsTemplateWithTx(dto);
		
		return JsonResult.success(i);
	}

	@Override
	public int updateCmsTemplateWithTx(CmsTemplateDTO dto) {
		return cmsTemplateFacade.updateCmsTemplateWithTx(dto);
	}

	@Override
	public int deleteCmsTemplateWithTx(CmsTemplateDTO dto) {
		return cmsTemplateFacade.deleteCmsTemplateWithTx(dto);
	}

	@Override
	public List<Map<String, Object>> findCmsElement(Integer groupType) {
		return cmsTemplateFacade.findCmsElement(groupType);
	}

	@Override
	public Map<String, Object> findCmsElementById(Long id) {
		return cmsTemplateFacade.findCmsElementById(id);
	}

	@Override
	public int updateStatus(CmsTemplateDTO dto) {
		return cmsTemplateFacade.updateStatus(dto);
	}

	@Override
	public JsonResult<Long> copyCmsTemplateWithTx(Long templateId) {
		if (EmptyUtil.isEmpty(templateId)){
			return JsonResult.fail("模板ID为空,请刷新后再操作");
		}
		CmsTemplateDTO templateDTO=new CmsTemplateDTO();
		templateDTO.setId(templateId);
		templateDTO=cmsTemplateFacade.findCmsTemplateById(templateDTO);
		if (Objects.isNull(templateDTO)){
			return JsonResult.fail("模板ID:"+templateId+",对应的模板不存在,请刷新后再操作");
		}
		templateDTO.setId(null);
		templateDTO.setName(templateDTO.getName()+"-副本");
		List<Long> elementIdList = new ArrayList<>();
		templateDTO.getElementList().forEach(item->{
			if (item.containsKey("id") && Objects.nonNull(item.get("id"))){
				Long elementId=(Long) item.get("id");
				elementIdList.add(elementId);
			}
		});
		if (EmptyUtil.isNotEmpty(elementIdList)){
			templateDTO.setElementIdList("["+StringUtils.join(elementIdList,",")+"]");
		}
		templateDTO.setStatus(1);
		templateDTO.setCreateTime(null);
		templateDTO.setUpdateTime(null);
		Long i = cmsTemplateFacade.insertCmsTemplateWithTx(templateDTO);
		return JsonResult.success(i);
	}
}
	