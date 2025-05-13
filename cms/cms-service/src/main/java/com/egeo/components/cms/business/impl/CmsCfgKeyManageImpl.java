package com.egeo.components.cms.business.impl;
	

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.cms.business.CmsCfgKeyManage;
import com.egeo.components.cms.facade.BannerFacade;
import com.egeo.components.cms.facade.CmsCfgKeyFacade;
import com.egeo.components.cms.facade.CmsInstFacade;
import com.egeo.components.cms.facade.CmsPageFacade;
import com.egeo.components.cms.facade.LinkableButtonFacade;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.CmsCfgKeyDTO;
import com.egeo.components.cms.dto.CmsElementDTO;
import com.egeo.components.cms.dto.CmsInstCfgDTO;
import com.egeo.components.cms.dto.CmsPageCfgDTO;
import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.utils.EmptyUtil;

@Service("cmsCfgKey")
public class CmsCfgKeyManageImpl implements CmsCfgKeyManage{
	
	public Logger logger = LoggerFactory.getLogger(CmsCfgKeyManageImpl.class);

	@Resource(name="cmsCfgKeyFacade")
	private CmsCfgKeyFacade cmsCfgKeyFacade;
	@Resource(name="bannerFacade")
	private BannerFacade bannerFacade;
	@Resource(name="cmsPageFacade")
	private CmsPageFacade cmsPageFacade;
	@Resource(name="linkableButtonFacade")
	private LinkableButtonFacade linkableButtonFacade;
	@Resource(name="cmsInstFacade")
	private CmsInstFacade cmsInstFacade;

	@Override
	public Map<String, Object> findCfgKeyByTemplateId(Long templateId, Long pageId) {
		String pageName = null;
		Integer companyType = null;
		if (templateId == null) {
			CmsPageDTO cmsPageDTO = new CmsPageDTO();
			cmsPageDTO.setId(pageId);
			CmsPageDTO findCmsPageDTO = cmsPageFacade.findCmsPageById(cmsPageDTO);
			templateId = findCmsPageDTO.getTemplateId();
			pageName = findCmsPageDTO.getPageName();
			companyType = findCmsPageDTO.getCompanyType();
		}
		//查找所有模板自有的配置项列表
		List<CmsCfgKeyDTO> templateCfgKeyList = cmsCfgKeyFacade.findTemplateCfgKeyByTemplateId(templateId);
		//查找所有关联的组件配置项列表
		List<CmsCfgKeyDTO> elementCfgKeyList = cmsCfgKeyFacade.findElementCfgKeyByTemplateId(templateId);
		List<CmsPageCfgDTO> cmsPageCfgList = null;
		List<CmsInstCfgDTO> instCfgList = null;
		if (pageId != null) {
			//查找所有页面自有的配置项列表
			cmsPageCfgList = cmsPageFacade.findPageCfgByPageId(pageId);
			//查找所有关联的组件实例的配置项值列表
			instCfgList = cmsPageFacade.findPageInstCfgByPageId(pageId, null);
		}
		Map<String, Integer> instStatusMap = instStatusMap(instCfgList);
		Map<String, Object> result = new HashMap<>();
		//构建模板自有配置项结构
		result.put("templateConfig", constructTemplateConfig(templateCfgKeyList, cmsPageCfgList));
		//构建页面实例配置项结构
		result.put("elementConfig", constructElementConfig(elementCfgKeyList, instCfgList, instStatusMap));
		result.put("templateId", templateId);
		result.put("pageName", pageName);
		result.put("companyType", companyType);
		result.put("companyId", cmsPageFacade.findPageCompanyId(pageId));
		return result;
	}
	
	private Map<String, Integer> instStatusMap(List<CmsInstCfgDTO> instCfgList) {
		Map<String, Integer> configStatusMap = new HashMap<>();
		if (EmptyUtil.isNotEmpty(instCfgList)) {
			for (CmsInstCfgDTO cmsInst : instCfgList) {
				configStatusMap.put(cmsInst.getCmsElementId() + "_" + cmsInst.getSort(), cmsInst.getInstStatus());
			}
		}
		return configStatusMap;
	}
	
	/**
	 * 构建模板自有配置项结构
	 * @param templateCfgKeyList
	 * @param cmsPageCfgList
	 * @return
	 */
	private List<Map<String, Object>> constructTemplateConfig(List<CmsCfgKeyDTO> templateCfgKeyList, List<CmsPageCfgDTO> cmsPageCfgList) {
		Map<Long, Object> pageCfgMap = new HashMap<>(); 
		Map<Long, List<Object>> objectValueMap = constructPageObjectValue(templateCfgKeyList, cmsPageCfgList);
		Map<String, List<Map<String, Object>>> objectCfgKeyMap = constructObjectCfgKeyMap(templateCfgKeyList);
		if (cmsPageCfgList != null) {
			for (CmsPageCfgDTO dto : cmsPageCfgList) {
				//考虑在外层抽象getValue，做无限级递归
				Long key = dto.getCmsCfgKeyId();
				if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_CHECKBOX
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
					putCfgMap(pageCfgMap, key, dto.getCmsCfgValueCode());
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT 
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_IMAGE
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SU_COMBINATION
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_COLOR_SELECT) {
					putCfgMap(pageCfgMap, key, dto.getTextValue());
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER) {
					BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
					LinkableButtonDTO linkBtn = null;
					List<LinkableButtonPageDTO> linkBtnPageList = null;
					List<LinkableParamDTO> linkParamList = null;
					if (banner != null && banner.getLinkableId() != null) {
						linkBtn = linkableButtonFacade.queryLinkableButtonById(banner.getLinkableId());
						linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(banner.getLinkableId());
						linkParamList = linkableButtonFacade.queryLinkableParamByLinkableId(banner.getLinkableId());
					}
					putCfgMap(pageCfgMap, key, convertBannerToMap(banner, linkBtn, linkBtnPageList, linkParamList));
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
					BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
					if (!pageCfgMap.containsKey(key)) {
						pageCfgMap.put(key, new ArrayList<>());
					}
					putCfgMap(pageCfgMap, key, convertBannerToMap(banner, null, null, null));
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
					if (EmptyUtil.isEmpty(dto.getTextValue())) {
						putCfgMap(pageCfgMap, key, null);
					} else {
						Long linkableId = Long.parseLong(dto.getTextValue());
						LinkableButtonDTO linkBtn = linkableButtonFacade.queryLinkableButtonById(linkableId);
						List<LinkableButtonPageDTO> linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(linkableId);
						List<LinkableParamDTO> linkParamList = linkableButtonFacade.queryLinkableParamByLinkableId(linkableId);
						putCfgMap(pageCfgMap, key, constructLinkMap(linkBtn, linkBtnPageList, linkParamList));
					}
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
					//Object类型在前面已经构建好，直接添加即可
					pageCfgMap.put(key, objectValueMap.get(key));
					/*if (!pageCfgMap.containsKey(key)) {
						pageCfgMap.put(key, new ArrayList<>());
					}
					if (objectValueMap.containsKey(key)) {
						putCfgMap(pageCfgMap, key, objectValueMap.get(key));
					} else {
						putCfgMap(pageCfgMap, key, objectCfgKeyMap.get(key));
					}*/
				}
			}
		}
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for (CmsCfgKeyDTO templateCfgKey : templateCfgKeyList) {
			if (EmptyUtil.isNotEmpty(templateCfgKey.getParentId())) {
				continue;
			}
			Map<String, Object> templateCfgMap = convertCmsCfgKeyToMap(templateCfgKey);
			Object value = pageCfgMap.get(templateCfgKey.getId());
			templateCfgMap.put("value", value);
			result.add(templateCfgMap);
		}
		return result;
	}
	
	/**
	 * 构建页面配置项值的map，结构为<配置项key的Id,配置项的值>
	 * 配置项值若有多个为列表类型，Object类型的配置项统一为列表类型
	 */
	/*private void constructPageConfigValue(List<CmsCfgKeyDTO> templateCfgKeyList, List<CmsPageCfgDTO> cmsPageCfgList) {
		
	}
	
	private void constructPageConfigValueMap(Map<String, Object> cfgValueMap, Map<Long, CmsCfgKeyDTO> templateCfgKeyMap, Map<Long, CmsPageCfgDTO> cmsPageCfgMap) {
		for (Entry<Long, CmsCfgKeyDTO> entry : templateCfgKeyMap.entrySet()) {
			
		}
	}*/
	
	private Map<String, Object> constructLinkMap(LinkableButtonDTO linkBtn, List<LinkableButtonPageDTO> linkBtnPageList, List<LinkableParamDTO> linkParamList) {
		Map<String, Object> linkMap = new HashMap<>();
		linkMap.put("id", linkBtn.getId());
		linkMap.put("linkType", linkBtn.getLinkType());
		linkMap.put("linkId", linkBtn.getLinkId());
		linkMap.put("linkParam", linkBtn.getLinkParam());
		linkMap.put("linkUrl", linkBtn.getLinkUrl());
		
		if(linkBtnPageList != null) {
			List<Map<String, Object>> linkBtnPageMapList = new ArrayList<>();
			for (LinkableButtonPageDTO lbp : linkBtnPageList) {
				Map<String, Object> linkBtnPageMap = new HashMap<>();
				linkBtnPageMap.put("cmsPageId", lbp.getCmsPageId());
				linkBtnPageMap.put("clientType", lbp.getClientType());
				linkBtnPageMapList.add(linkBtnPageMap);
			}
			linkMap.put("linkableButtonPageList", linkBtnPageMapList);
		}
		if (linkParamList != null) {
			List<Map<String, Object>> linkParamMapList = new ArrayList<>();
			for (LinkableParamDTO linkParam : linkParamList) {
				Map<String, Object> linkParamMap = new HashMap<>();
				linkParamMap.put("name", linkParam.getName());
				linkParamMap.put("value", linkParam.getValue());
				linkParamMapList.add(linkParamMap);
			}
			linkMap.put("extParam", linkParamMapList);
		}
		return linkMap;
	}
	
	private Map<String, List<Map<String, Object>>> constructObjectCfgKeyMap(List<CmsCfgKeyDTO> templateCfgKeyList) {
		Map<String, List<Map<String, Object>>> objectCfgKeyMap = new HashMap<>();
		for (CmsCfgKeyDTO ck : templateCfgKeyList) {
			Long parentId = ck.getParentId();
			String key = parentId + "_" + ck.getElementSort();
			if (EmptyUtil.isNotEmpty(parentId)) {
				if (!objectCfgKeyMap.containsKey(key)) {
					objectCfgKeyMap.put(key, new ArrayList<>());
				}
				Map<String, Object> ckItem = convertCmsCfgKeyToMap(ck);
				ckItem.put("value", null);
				objectCfgKeyMap.get(key).add(ckItem);
			}
		}
		return objectCfgKeyMap;
	} 
	
	private Map<Long, List<Object>> constructPageObjectValue(List<CmsCfgKeyDTO> templateCfgKeyList, List<CmsPageCfgDTO> cmsPageCfgList) {
		Map<Long, List<Object>> objectValueMap = new HashMap<>();
		if (EmptyUtil.isNotEmpty(cmsPageCfgList)) {
			Map<Long, CmsCfgKeyDTO> ckMap = new LinkedHashMap<>();
			for (CmsCfgKeyDTO ck : templateCfgKeyList) {
				if (EmptyUtil.isNotEmpty(ck.getParentId())) {
					ckMap.put(ck.getId(), ck);
				}
			}
			Map<Long, List<Map<String, Object>>> valueMap = new LinkedHashMap<>();
			Map<Long, List<Long>> idMap = new HashMap<>();
			for (CmsPageCfgDTO pc : cmsPageCfgList) {
				if (EmptyUtil.isNotEmpty(pc.getParentId())) {
					CmsCfgKeyDTO ck = ckMap.get(pc.getCmsCfgKeyId());
					Map<String, Object> objValue = convertCmsCfgKeyToMap(ck);
					objValue.put("value", getValue(pc));
					Long pcParentId = pc.getParentId();
					Long ckParentId = ck.getParentId();
					if (!valueMap.containsKey(pcParentId)) {
						valueMap.put(pcParentId, new ArrayList<>());
					}
					valueMap.get(pcParentId).add(objValue);
					if (!idMap.containsKey(ckParentId)) {
						idMap.put(ckParentId, new ArrayList<>());
					}
					if (!idMap.get(ckParentId).contains(pcParentId)) {
						idMap.get(ckParentId).add(pcParentId);
					}
				}
			}
			for (Entry<Long, List<Long>> entry : idMap.entrySet()) {
				for (Long pcId : entry.getValue()) {
					if (!objectValueMap.containsKey(entry.getKey())) {
						objectValueMap.put(entry.getKey(), new ArrayList<>());
					}
					objectValueMap.get(entry.getKey()).add(valueMap.get(pcId));
				}
			}
		}
		return objectValueMap;
	}
	
	private Object getValue(CmsPageCfgDTO dto) {
		if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_CHECKBOX
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
			return dto.getCmsCfgValueCode();
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT 
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_IMAGE
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SU_COMBINATION
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_COLOR_SELECT) {
			return dto.getTextValue();
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER) {
			BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
			LinkableButtonDTO linkBtn = null;
			List<LinkableButtonPageDTO> linkBtnPageList = null;
			List<LinkableParamDTO> linkParamList = null;
			if (banner != null && banner.getLinkableId() != null) {
				linkBtn = linkableButtonFacade.queryLinkableButtonById(banner.getLinkableId());
				linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(banner.getLinkableId());
				linkParamList = linkableButtonFacade.queryLinkableParamByLinkableId(banner.getLinkableId());
			}
			return  convertBannerToMap(banner, linkBtn, linkBtnPageList, linkParamList);
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
			BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
			return convertBannerToMap(banner, null, null, null);
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
			if (EmptyUtil.isEmpty(dto.getTextValue())) {
				return null;
			} else {
				Long linkableId = Long.parseLong(dto.getTextValue());
				LinkableButtonDTO linkBtn = linkableButtonFacade.queryLinkableButtonById(linkableId);
				List<LinkableButtonPageDTO> linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(linkableId);
				List<LinkableParamDTO> linkParamList = linkableButtonFacade.queryLinkableParamByLinkableId(linkableId);
				return constructLinkMap(linkBtn, linkBtnPageList, linkParamList);
			}
		}/* else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
			
		}*/
		return null;
	}
	
	private List<Map<String, Object>> constructElementConfig(List<CmsCfgKeyDTO> elementCfgKeyList, List<CmsInstCfgDTO> instCfgList, Map<String, Integer> instStatusMap) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		List<CmsCfgKeyDTO> basicCfgKeyList = cmsCfgKeyFacade.findElementBasicConfig();;
		Map<String, Object> instCfgMap = formatInstConfig(elementCfgKeyList, instCfgList);
		Map<String, List<Map<String, Object>>> elementCfgMap = new HashMap<>();
		Map<String, Long> elementIdMap = new HashMap<>();
		Map<String, Integer> elementSortMap = new HashMap<>();
		List<String> keyList = new ArrayList<>(); 
		for (CmsCfgKeyDTO dto : elementCfgKeyList) {
			if (EmptyUtil.isNotEmpty(dto.getParentId()) || (dto.getCfgKind() != null && dto.getCfgKind() == 2)) {
				continue;
			}
			List<Map<String, Object>> elementCfg = null;
			String elementKey = dto.getElementId() + "_" + dto.getElementSort();
			elementIdMap.put(elementKey, dto.getElementId());
			elementSortMap.put(elementKey, dto.getElementSort());
			if (elementCfgMap.containsKey(elementKey)) {
				elementCfg = elementCfgMap.get(elementKey);
			} else {
				elementCfg = new ArrayList<>();
				elementCfgMap.put(elementKey, elementCfg);
				keyList.add(elementKey);
			}
			Map<String, Object> cfgKey = convertCmsCfgKeyToMap(dto);
			Object value = instCfgMap.get(dto.getId() + "_" + dto.getElementSort());
			cfgKey.put("value", value);
			elementCfg.add(cfgKey);
		}
		Map<Long, CmsElementDTO> elementMap = new HashMap<>();
		for (String key : keyList) {
			CmsElementDTO elementDTO = findCmsElementDTO(elementIdMap.get(key), elementMap);
			Map<String, Object> element = new LinkedHashMap<>();
			element.put("elementId", elementIdMap.get(key));
			element.put("elementSort", elementSortMap.get(key));
			element.put("config", elementCfgMap.get(key));
			if (elementIdMap.get(key) > 5) {
				//是通用组件，加入基础配置属性
				element.put("basicConfig", formatBasicConfig(basicCfgKeyList, instCfgMap, elementSortMap.get(key), elementIdMap.get(key)));
			}
			element.put("name", elementDTO.getName());
			element.put("sampleImgUrl", elementDTO.getSampleImgUrl());
			element.put("androidVersion", elementDTO.getClientVersionARemark());
			element.put("iosVersion", elementDTO.getClientVersionIRemark());
			Integer configStatus = instStatusMap.get(key);
			element.put("configStatus", configStatus == null ? 0 : configStatus);
			
			List<Map<String, Object>> commonInstList = cmsInstFacade.findCommonInstByElementId(elementIdMap.get(key));
			element.put("commonInstList", commonInstList);
			element.put("companyIds", findInstCompany(instCfgList, elementIdMap.get(key), elementSortMap.get(key)));
			result.add(element);
		}
		return result;
	}
	
	private List<Long> findInstCompany(List<CmsInstCfgDTO> instCfgList, Long elementId, Integer elementSort) {
		if (EmptyUtil.isNotEmpty(instCfgList)) {
			for (CmsInstCfgDTO instCfg : instCfgList) {
				if (elementId.equals(instCfg.getCmsElementId()) && elementSort.equals(instCfg.getSort())) {
					return cmsInstFacade.findInstCompany(instCfg.getInstId());
				}
			}
		}
		return new ArrayList<>();
	}

	private CmsElementDTO findCmsElementDTO(Long elementId, Map<Long, CmsElementDTO> elementMap) {
		if (!elementMap.containsKey(elementId)) {
			CmsElementDTO dto = cmsPageFacade.findCmsElementById(elementId);
			elementMap.put(elementId, dto);
			return dto;
		}
		return elementMap.get(elementId);
	}
	
	/**
	 * 构建组件基本属性
	 * @param basicCfgKeyList
	 * @param instCfgMap
	 * @param elementSort
	 * @return
	 */
	private List<Map<String, Object>> formatBasicConfig(List<CmsCfgKeyDTO> basicCfgKeyList, Map<String, Object> instCfgMap, Integer elementSort, Long elementId) {
		List<Map<String, Object>> basicConfigList = new ArrayList<>();
		for (CmsCfgKeyDTO bc : basicCfgKeyList) {
			Map<String, Object> basicConfig = convertCmsCfgKeyToMap(bc);
			String key = bc.getId() + "_" + elementSort;
			basicConfig.put("value", instCfgMap.get(key));
			basicConfig.put("elementGroup", Arrays.asList(new Object[]{elementId, elementSort}));
			basicConfigList.add(basicConfig);
		}
		return basicConfigList;
	}
	
	private Map<String, Object> convertCmsCfgKeyToMap(CmsCfgKeyDTO dto) {
		Map<String, Object> cfgKeyMap = new HashMap<>();
		cfgKeyMap.put("id", dto.getId());
		cfgKeyMap.put("name", dto.getName());
		cfgKeyMap.put("type", dto.getType());
		cfgKeyMap.put("cfgValueList", dto.getCfgValueList());
		cfgKeyMap.put("units", dto.getUnits());
		cfgKeyMap.put("defaultValue", dto.getDefaultValue());
		cfgKeyMap.put("verifyRegex", dto.getVerifyRegex());
		cfgKeyMap.put("parentId", dto.getParentId());
		cfgKeyMap.put("label", dto.getLabel());
		cfgKeyMap.put("group", dto.getCfgGroup());
		return cfgKeyMap;
	}
	
	private Map<String, Object> formatInstConfig(List<CmsCfgKeyDTO> elementCfgKeyList, List<CmsInstCfgDTO> instCfgList) {
		Map<String, Object> instCfgMap = new HashMap<>();
		Map<String, List<Map<String, Object>>> objectCfgKeyMap = constructObjectCfgKeyMap(elementCfgKeyList);
		if (instCfgList != null) {
			Map<String, List<Object>> objectValueMap = constructElementObjectValue(elementCfgKeyList, instCfgList);
			for (CmsInstCfgDTO dto : instCfgList) {
				String key = dto.getCmsCfgKeyId() + "_" + dto.getSort();
				if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_CHECKBOX
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
					putCfgMap(instCfgMap, key, dto.getCmsCfgValueCode());
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT 
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_IMAGE
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SU_COMBINATION
						|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_COLOR_SELECT) {
					putCfgMap(instCfgMap, key, dto.getInstTextValue());
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER) {
					BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
					LinkableButtonDTO linkBtn = null;
					List<LinkableButtonPageDTO> linkBtnPageList = null;
					List<LinkableParamDTO> linkParamList = null;
					if (banner != null && banner.getLinkableId() != null) {
						linkBtn = linkableButtonFacade.queryLinkableButtonById(banner.getLinkableId());
						linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(banner.getLinkableId());
						linkParamList = linkableButtonFacade.queryLinkableParamByLinkableId(banner.getLinkableId());
					}
					putCfgMap(instCfgMap, key, convertBannerToMap(banner, linkBtn, linkBtnPageList, linkParamList));
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
					BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
					if (!instCfgMap.containsKey(key)) {
						instCfgMap.put(key, new ArrayList<>());
					}
					putCfgMap(instCfgMap, key, convertBannerToMap(banner, null, null, null));
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
					if (EmptyUtil.isEmpty(dto.getInstTextValue())) {
						putCfgMap(instCfgMap, key, null);
					} else {
						Long linkableId = Long.parseLong(dto.getInstTextValue());
						LinkableButtonDTO linkBtn = linkableButtonFacade.queryLinkableButtonById(linkableId);
						List<LinkableButtonPageDTO> linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(linkableId);
						List<LinkableParamDTO> linkParamList = linkableButtonFacade.queryLinkableParamByLinkableId(linkableId);
						putCfgMap(instCfgMap, key, constructLinkMap(linkBtn, linkBtnPageList, linkParamList));
					}
				} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
					//Object类型在前面已经构建好，直接添加即可
					instCfgMap.put(key, objectValueMap.get(key));
				}
			}
		}
		for (Entry<String, List<Map<String, Object>>> objectCfgKeyEntry : objectCfgKeyMap.entrySet()) {
			String key = objectCfgKeyEntry.getKey();
			if (!instCfgMap.containsKey(key)) {
				instCfgMap.put(key, new ArrayList<>());
				putCfgMap(instCfgMap, key, objectCfgKeyMap.get(key));
			}
		}
		return instCfgMap;
	}
	
	private Map<String, Object> convertBannerToMap(BannerDTO banner, LinkableButtonDTO linkBtn, List<LinkableButtonPageDTO> linkBtnPageList, List<LinkableParamDTO> linkParamList) {
		if (banner == null) {
			return null;
		}
		Map<String, Object> bannerMap = new HashMap<>();
		bannerMap.put("imgUrl", banner.getImgUrl());
		bannerMap.put("bannerId", banner.getId());
		bannerMap.put("linkableId", banner.getLinkableId());
		if (linkBtn != null) {
			Map<String, Object> linkBtnMap = new HashMap<>();
			linkBtnMap.put("id", linkBtn.getId());
			linkBtnMap.put("linkType", linkBtn.getLinkType());
			linkBtnMap.put("linkId", linkBtn.getLinkId());
			linkBtnMap.put("linkParam", linkBtn.getLinkParam());
			linkBtnMap.put("linkUrl", linkBtn.getLinkUrl());
			if(linkBtnPageList != null) {
				List<Map<String, Object>> linkBtnPageMapList = new ArrayList<>();
				for (LinkableButtonPageDTO lbp : linkBtnPageList) {
					Map<String, Object> linkBtnPageMap = new HashMap<>();
					linkBtnPageMap.put("cmsPageId", lbp.getCmsPageId());
					linkBtnPageMap.put("clientType", lbp.getClientType());
					linkBtnPageMapList.add(linkBtnPageMap);
				}
				linkBtnMap.put("linkableButtonPage", linkBtnPageMapList);
			}
			if (linkParamList != null) {
				List<Map<String, Object>> linkParamMapList = new ArrayList<>();
				for (LinkableParamDTO linkParam : linkParamList) {
					Map<String, Object> linkParamMap = new HashMap<>();
					linkParamMap.put("name", linkParam.getName());
					linkParamMap.put("value", linkParam.getValue());
					linkParamMapList.add(linkParamMap);
				}
				linkBtnMap.put("extParam", linkParamMapList);
			}
			bannerMap.put("linkableButton", linkBtnMap);
		}
		
		return bannerMap;
	}
	
	private Map<String, List<Object>> constructElementObjectValue(List<CmsCfgKeyDTO> templateCfgKeyList, List<CmsInstCfgDTO> cmsInstCfgList) {
		Map<Long, CmsCfgKeyDTO> ckMap = new LinkedHashMap<>();
		for (CmsCfgKeyDTO ck : templateCfgKeyList) {
			if (EmptyUtil.isNotEmpty(ck.getParentId())) {
				ckMap.put(ck.getId(), ck);
			}
		}
		Map<Long, List<Map<String, Object>>> valueMap = new LinkedHashMap<>();
		Map<String, List<Long>> idMap = new HashMap<>();
		for (CmsInstCfgDTO pc : cmsInstCfgList) {
			if (EmptyUtil.isNotEmpty(pc.getParentId())) {
				CmsCfgKeyDTO ck = ckMap.get(pc.getCmsCfgKeyId());
				Map<String, Object> objValue = convertCmsCfgKeyToMap(ck);
				objValue.put("value", getValue(pc));
				Long pcParentId = pc.getParentId();
				String ckParentId = ck.getParentId() + "_" + pc.getSort();
				if (!valueMap.containsKey(pcParentId)) {
					valueMap.put(pcParentId, new ArrayList<>());
				}
				valueMap.get(pcParentId).add(objValue);
				if (!idMap.containsKey(ckParentId)) {
					idMap.put(ckParentId, new ArrayList<>());
				}
				if (!idMap.get(ckParentId).contains(pcParentId)) {
					idMap.get(ckParentId).add(pcParentId);
				}
			}
		}
		Map<String, List<Object>> objectValueMap = new HashMap<>();
		for (Entry<String, List<Long>> entry : idMap.entrySet()) {
			for (Long pcId : entry.getValue()) {
				if (!objectValueMap.containsKey(entry.getKey())) {
					objectValueMap.put(entry.getKey(), new ArrayList<>());
				}
				objectValueMap.get(entry.getKey()).add(valueMap.get(pcId));
			}
		}
		return objectValueMap;
	}
	
	private Object getValue(CmsInstCfgDTO dto) {
		if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_CHECKBOX
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
			return dto.getCmsCfgValueCode();
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT 
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_IMAGE
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SU_COMBINATION
				|| dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_COLOR_SELECT) {
			return dto.getInstTextValue();
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER) {
			BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
			LinkableButtonDTO linkBtn = null;
			List<LinkableButtonPageDTO> linkBtnPageList = null;
			List<LinkableParamDTO> linkParamList = null;
			if (banner != null && banner.getLinkableId() != null) {
				linkBtn = linkableButtonFacade.queryLinkableButtonById(banner.getLinkableId());
				linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(banner.getLinkableId());
				linkParamList = linkableButtonFacade.queryLinkableParamByLinkableId(banner.getLinkableId());
			}
			return  convertBannerToMap(banner, linkBtn, linkBtnPageList, linkParamList);
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
			BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
			return convertBannerToMap(banner, null, null, null);
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
			if (EmptyUtil.isEmpty(dto.getInstTextValue())) {
				return null;
			} else {
				Long linkableId = Long.parseLong(dto.getInstTextValue());
				LinkableButtonDTO linkBtn = linkableButtonFacade.queryLinkableButtonById(linkableId);
				List<LinkableButtonPageDTO> linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(linkableId);
				List<LinkableParamDTO> linkParamList = linkableButtonFacade.queryLinkableParamByLinkableId(linkableId);
				return constructLinkMap(linkBtn, linkBtnPageList, linkParamList);
			}
		}/* else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
			
		}*/
		return null;
	}
	
	private <T> void putCfgMap(Map<T, Object> cfg, T keyCode, Object value) {
		if (!cfg.containsKey(keyCode)) {
			cfg.put(keyCode, value);
		} else {
			Object originValue = cfg.get(keyCode);
			if (originValue instanceof List) {
				((List)originValue).add(value);
			} else {
				List<Object> valList = new ArrayList<>();
				valList.add(originValue);
				valList.add(value);
				cfg.put(keyCode, valList);
			}
		}
	}
	
}
	