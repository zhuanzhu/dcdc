package com.egeo.components.cms.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.cms.dto.CmsCfgKeyDTO;
import com.egeo.components.cms.dto.CmsElementDTO;
import com.egeo.components.cms.dto.CmsTemplateCfgDTO;
import com.egeo.components.cms.dto.CmsTemplateDTO;
import com.egeo.components.cms.dto.CmsTemplateElementDTO;
import com.egeo.components.cms.service.read.CmsCfgKeyReadService;
import com.egeo.components.cms.service.read.CmsElementReadService;
import com.egeo.components.cms.service.read.CmsTemplateElementReadService;
import com.egeo.components.cms.service.read.CmsTemplateReadService;
import com.egeo.components.cms.service.write.CmsTemplateCfgWriteService;
import com.egeo.components.cms.service.write.CmsTemplateElementWriteService;
import com.egeo.components.cms.service.write.CmsTemplateWriteService;
import com.egeo.components.user.client.VersionsClient;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class CmsTemplateFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsTemplateFacade.class);
	
	@Resource
	private CmsTemplateReadService cmsTemplateReadService;
	
	@Resource
	private CmsTemplateWriteService cmsTemplateWriteService;
	
	
	@Autowired
	private VersionsClient versionsReadService;
	
	@Resource
	private CmsTemplateElementWriteService cmsTemplateElementWriteService;
	
	
	@Resource
	private CmsElementReadService cmsElementReadService;
	
	@Resource
	private CmsTemplateElementReadService cmsTemplateElementReadService;
	
	@Resource
	private CmsCfgKeyReadService cmsCfgKeyReadService;
	
	@Resource
	private CmsTemplateCfgWriteService cmsTemplateCfgWriteService;
	
	
	public CmsTemplateDTO findCmsTemplateById(CmsTemplateDTO dto){
		List<CmsElementDTO> eledtos = cmsElementReadService.findCmsElementByTemplateId(dto.getId());
		CmsTemplateDTO cmsTemplateDTO = cmsTemplateReadService.findCmsTemplateById(dto);
		List<Map<String, Object>> elementList = new ArrayList<>();
		if(eledtos != null && eledtos.size() > 0) {
			for (CmsElementDTO cmsElementDTO : eledtos) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", cmsElementDTO.getId());
				map.put("clientVersionARemark", cmsElementDTO.getClientVersionARemark());
				map.put("clientVersionIRemark", cmsElementDTO.getClientVersionIRemark());
				map.put("clientVersionAId", cmsElementDTO.getClientVersionAId());
				map.put("clientVersionIId", cmsElementDTO.getClientVersionIId());
				map.put("name", cmsElementDTO.getName());
				map.put("sampleImgUrl", cmsElementDTO.getSampleImgUrl());
				map.put("sort", cmsElementDTO.getSort());
				elementList.add(map);
			}
			
		}
		cmsTemplateDTO.setElementList(elementList);
		return cmsTemplateDTO;
	}

	public PageResult<CmsTemplateDTO> findCmsTemplateOfPage(CmsTemplateDTO dto,Pagination page,Integer searchType){
		
		return cmsTemplateReadService.findCmsTemplateOfPage(dto, page,searchType);
		
	}

	public List<CmsTemplateDTO> findCmsTemplateAll(CmsTemplateDTO dto){
		
		return cmsTemplateReadService.findCmsTemplateAll(dto);
		
	}

	public Long insertCmsTemplateWithTx(CmsTemplateDTO dto){
		
		initVersion(dto);
		
		Long i = cmsTemplateWriteService.insertCmsTemplateWithTx(dto);
		String elementIdList = dto.getElementIdList();
		if(EmptyUtil.isNotBlank(elementIdList)) {
			List<Long> elementIds = JSONArray.parseArray(elementIdList, Long.class);
			if(elementIds != null && elementIds.size() > 0) {
				List<CmsTemplateElementDTO> list = new ArrayList<>();
				CmsTemplateElementDTO templateElementDTO ;
				int sort = 0;
				for (Long long1 : elementIds) {
					templateElementDTO = new CmsTemplateElementDTO();
					templateElementDTO.setElementId(long1);
					templateElementDTO.setTemplateId(i);
					templateElementDTO.setSort(sort++);
					list.add(templateElementDTO);
					cmsTemplateElementWriteService.insertCmsTemplateElementWithTx(templateElementDTO);
				}
			}
		}

		CmsCfgKeyDTO cmsCfgKeyDTO = new CmsCfgKeyDTO();
		cmsCfgKeyDTO.setCfgKind(1);
		List<CmsCfgKeyDTO> cfgKeyDTOs = cmsCfgKeyReadService.findCmsCfgKeyAll(cmsCfgKeyDTO);
		List<CmsTemplateCfgDTO> cmsTemplateCfgDTOs = new ArrayList<>();
		for (CmsCfgKeyDTO cmsCfgKeyDTO2 : cfgKeyDTOs) {
			CmsTemplateCfgDTO cmsTemplateCfgDTO = new CmsTemplateCfgDTO();
			cmsTemplateCfgDTO.setCmsCfgKeyId(cmsCfgKeyDTO2.getId());
			cmsTemplateCfgDTO.setTemplateId(i);
			cmsTemplateCfgDTO.setSort(1);
			cmsTemplateCfgDTOs.add(cmsTemplateCfgDTO);
			
		}
		if(cmsTemplateCfgDTOs.size() > 0) {
			cmsTemplateCfgWriteService.insertBatchWithTx(cmsTemplateCfgDTOs);
		}
		
		return i;
	}

	public int updateCmsTemplateWithTx(CmsTemplateDTO dto){
		
		VersionsDTO versionsDTOA = new VersionsDTO();
		
		if(dto.getClientVersionAId() != null) {
			// -1 为全部版本
			if(dto.getClientVersionAId() == -1) {
				dto.setClientVersionACode(null);
				dto.setClientVersionARemark("全部");
			}else {
				versionsDTOA.setId(dto.getClientVersionAId());
				versionsDTOA = versionsReadService.findVersionsById(versionsDTOA);
				
				if(versionsDTOA != null) {
					dto.setClientVersionACode(versionsDTOA.getVersionCode());
					dto.setClientVersionARemark(versionsDTOA.getVersionsMark());
				}				
			}
		}
		
		VersionsDTO versionsDTOI = new VersionsDTO();
		
		if(dto.getClientVersionIId() != null) {
			
			if(dto.getClientVersionIId() == -1) {
				dto.setClientVersionICode(null);
				dto.setClientVersionIRemark("全部");
			}else {
				versionsDTOI.setId(dto.getClientVersionIId());
				versionsDTOI = versionsReadService.findVersionsById(versionsDTOI);
				
				if(versionsDTOI != null) {
					dto.setClientVersionICode(versionsDTOI.getVersionCode());
					dto.setClientVersionIRemark(versionsDTOI.getVersionsMark());
				}				
			}
		}
		
		//先删除所有关联组件，再添加
		Integer maxSort = 0;
		CmsTemplateElementDTO cmsTemplateElementDTO = new CmsTemplateElementDTO();
		cmsTemplateElementDTO.setTemplateId(dto.getId());
		List<CmsTemplateElementDTO> list = cmsTemplateElementReadService.findCmsTemplateElementAll(cmsTemplateElementDTO);
		if(list != null && list.size() > 0) {
			for (CmsTemplateElementDTO cmsTemplateElementDTO2 : list) {
				maxSort = maxSort > cmsTemplateElementDTO2.getSort() ? maxSort:cmsTemplateElementDTO2.getSort();
			}
			cmsTemplateElementWriteService.deleteCmsTemplateElementWithTx(cmsTemplateElementDTO);
		}
		
		//添加组件
		String elementIdList = dto.getElementIdList();
		logger.info("elementIdList : " + elementIdList);
		String templateElementList = dto.getTemplateElementList();
		logger.info("templateElementList : " + templateElementList);
		
		if(EmptyUtil.isNotBlank(templateElementList)) {
			List<CmsTemplateElementDTO> CmsTemplateElementDTOs = new ArrayList<>();
			JSONArray elementIds = JSONArray.parseArray(templateElementList);
			if(elementIds != null && elementIds.size() > 0) {
				for(int i = 0; i<elementIds.size(); i++ ) {
					JSONObject json = elementIds.getJSONObject(i);
					CmsTemplateElementDTO eleDto = new CmsTemplateElementDTO();
					eleDto.setElementId(json.getLong("id"));
					eleDto.setTemplateId(dto.getId());
					eleDto.setSort(json.getInteger("sort") == null?++maxSort:json.getInteger("sort"));
					CmsTemplateElementDTOs.add(eleDto);
				}
				cmsTemplateElementWriteService.insertBatchWithTx(CmsTemplateElementDTOs);
			}
			
		}
		
		return cmsTemplateWriteService.updateCmsTemplateWithTx(dto);
	}
	
	public void initVersion(CmsTemplateDTO dto){
		Long clientVersionAId = null;	
		Long clientVersionIId = null;	
		String clientVersionARemark = null;	
		String clientVersionIRemark = null;	
		int clientVersionACode = 0;	
		int clientVersionICode = 0;	
		String elementIdListStr = dto.getElementIdList();
		if(EmptyUtil.isBlank(elementIdListStr)) {
			return ;
		}
		List<Long> elementIdList = JSONArray.parseArray(elementIdListStr, Long.class);
		if(elementIdList != null && elementIdList.size() >0 ) {
			List<CmsElementDTO> cmsElementDTOs = cmsElementReadService.findMaxVersionByElementIdList(elementIdList);
			for (CmsElementDTO cmsElementDTO : cmsElementDTOs) {
				if(cmsElementDTO.getClientVersionACode() != null) {
					clientVersionACode = clientVersionACode > cmsElementDTO.getClientVersionACode() ? clientVersionACode:cmsElementDTO.getClientVersionACode();
					clientVersionAId = cmsElementDTO.getClientVersionAId();
					clientVersionARemark = cmsElementDTO.getClientVersionARemark();
				}
				if(cmsElementDTO.getClientVersionICode() != null) {
					clientVersionICode = clientVersionICode > cmsElementDTO.getClientVersionICode() ? clientVersionICode:cmsElementDTO.getClientVersionICode();
					clientVersionIId = cmsElementDTO.getClientVersionIId();
					clientVersionIRemark = cmsElementDTO.getClientVersionIRemark();
				}
			}
			dto.setClientVersionACode(clientVersionACode);
			dto.setClientVersionICode(clientVersionICode);
			dto.setClientVersionARemark(clientVersionARemark);
			dto.setClientVersionIRemark(clientVersionIRemark);
			dto.setClientVersionAId(clientVersionAId);
			dto.setClientVersionIId(clientVersionIId);
		}
		
	}

	public int deleteCmsTemplateWithTx(CmsTemplateDTO dto){
		
		return cmsTemplateWriteService.deleteCmsTemplateWithTx(dto);
		
	}

	public Map<Long, String> findTemplateMap() {
		List<CmsTemplateDTO> cmsTemplateDTOList = cmsTemplateReadService.findCmsTemplateAll(new CmsTemplateDTO());
		Map<Long, String> templateMap = new HashMap<>();
		for (CmsTemplateDTO cmsTemplateDTO : cmsTemplateDTOList) {
			templateMap.put(cmsTemplateDTO.getId(), cmsTemplateDTO.getName());
		}
		return templateMap;
	}

	public List<Map<String, Object>> findCmsElement(Integer groupType) {
		CmsElementDTO dto = new CmsElementDTO();
		dto.setGroupType(groupType);
		List<CmsElementDTO> listDto = cmsElementReadService.findCmsElementAll(dto);
		List<Map<String, Object>> list = new ArrayList<>();
		if(listDto != null && listDto.size() > 0) {
			for (CmsElementDTO cmsElementDTO : listDto) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", cmsElementDTO.getId());
				map.put("name", cmsElementDTO.getName());
				map.put("clientVersionARemark", cmsElementDTO.getClientVersionARemark());
				map.put("clientVersionIRemark", cmsElementDTO.getClientVersionIRemark());
				map.put("sampleImgUrl", cmsElementDTO.getSampleImgUrl());
				list.add(map);
			}
		}
		return list;
	}

	
	public Map<String, Object> findCmsElementById(Long id) {
		
		logger.info("组件Id:{}",id);
		CmsElementDTO dto = new CmsElementDTO();
		dto.setId(id);
		dto = cmsElementReadService.findCmsElementById(dto);
		Map<String, Object> map = new HashMap<>();
		map.put("id", dto.getId());
		map.put("name", dto.getName());
		map.put("clientVersionARemark", dto.getClientVersionARemark());
		map.put("clientVersionIRemark", dto.getClientVersionIRemark());
		map.put("sampleImgUrl", dto.getSampleImgUrl());
		return map;
	}

	public int updateStatus(CmsTemplateDTO dto) {
		
		return cmsTemplateWriteService.updateCmsTemplateWithTx(dto);
	}
	
}
	