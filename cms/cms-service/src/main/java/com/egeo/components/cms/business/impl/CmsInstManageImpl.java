package com.egeo.components.cms.business.impl;
	

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.CmsInstManage;
import com.egeo.components.cms.facade.BannerFacade;
import com.egeo.components.cms.facade.CmsInstFacade;
import com.egeo.components.cms.facade.CmsPageFacade;
import com.egeo.components.cms.facade.LinkableButtonFacade;
import com.egeo.components.cms.vo.BannerVO;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.CmsInstCfgDTO;
import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.utils.EmptyUtil;

@Service("cmsInst")
public class CmsInstManageImpl implements CmsInstManage {

	@Resource(name="cmsInstFacade")
	private CmsInstFacade cmsInstFacade;
	
	@Resource(name="cmsPageFacade")
	private CmsPageFacade cmsPageFacade;
	
	@Resource(name="bannerFacade")
	private BannerFacade bannerFacade;
	
	@Resource(name="linkableButtonFacade")
	private LinkableButtonFacade linkableButtonFacade;

	@Override
	public Long insertCommonCmsInstWithTx(CmsInstDTO dto, List<Long> companyIdList, String configJson) {
		return cmsInstFacade.insertCommonCmsInstWithTx(dto, companyIdList, configJson);
	}
	
	@Override
	public Map<String, Object> findCommonInstConfig(Long instId) {
		Map<String, Object> instConfig = new HashMap<>();
		List<Map<String, Object>> cfgKeyList = new ArrayList<>();
		List<CmsInstCfgDTO> instCfgList = cmsPageFacade.findPageInstCfgByPageId(-1L, Arrays.asList(new Long[]{instId}));
		Map<Long, Object> flatValueMap = constructFlatValue(instCfgList);
		Map<Long, List<Object>> objectValueMap = constructObjectValue(instCfgList);
		for (CmsInstCfgDTO instCfg : instCfgList) {
			Long parentId = instCfg.getParentId();
			if (EmptyUtil.isEmpty(parentId)) {
				//parentId为空说明是顶级属性
				Map<String, Object> cfgMap = new HashMap<>();
				cfgMap.put("id", instCfg.getCmsCfgKeyId());
				cfgMap.put("type", instCfg.getCkgKeyType());
				if (instCfg.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
					cfgMap.put("value", objectValueMap.get(instCfg.getId()));
				} else {
					cfgMap.put("value", flatValueMap.get(instCfg.getCmsCfgKeyId()));
				}
				cfgKeyList.add(cfgMap);
			}
		}
		instConfig.put("config", cfgKeyList);
		instConfig.put("companyIds", cmsInstFacade.findInstCompany(instId));
		return instConfig;
	}
	
	private Map<Long, List<Object>> constructObjectValue(List<CmsInstCfgDTO> instCfgList) {
		Map<Long, List<Map<String, Object>>> objectValueMap = new HashMap<>();
		for (CmsInstCfgDTO instCfg : instCfgList) {
			Map<String, Object> cfgMap = new HashMap<>();
			Long parentId = instCfg.getParentId();
			if (EmptyUtil.isNotEmpty(parentId)) {
				cfgMap.put("id", instCfg.getCmsCfgKeyId());
				cfgMap.put("type", instCfg.getCkgKeyType());
				if (!objectValueMap.containsKey(parentId)) {
					objectValueMap.put(parentId, new ArrayList<>());
				}
				Object value = getValue(instCfg);
				cfgMap.put("value", value);
				objectValueMap.get(parentId).add(cfgMap);
			}
		}
		
		Map<Long, List<Object>> objectCfgKeyMap = new HashMap<>();
		for (CmsInstCfgDTO instCfg : instCfgList) {
			if (instCfg.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
				Long key = instCfg.getCmsCfgKeyId();
				if (!objectCfgKeyMap.containsKey(key)) {
					objectCfgKeyMap.put(key, new ArrayList<>());
				}
				if (objectValueMap.containsKey(instCfg.getId())) {
					objectCfgKeyMap.get(key).add(objectValueMap.get(instCfg.getId()));
				}
			}
		}
		return objectCfgKeyMap;
	} 
	
	private Map<Long, Object> constructFlatValue(List<CmsInstCfgDTO> instCfgList) {
		Map<Long, Object> flatMap = new HashMap<>();
		for (CmsInstCfgDTO instCfg : instCfgList) {
			Long parentId = instCfg.getParentId();
			if (EmptyUtil.isEmpty(parentId)) {
				putCfgMap(flatMap, instCfg.getCmsCfgKeyId(), getValue(instCfg));
			}
		}
		return flatMap;
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
			if (banner != null && banner.getLinkableId() != null) {
				linkBtn = linkableButtonFacade.queryLinkableButtonById(banner.getLinkableId());
				linkBtnPageList = linkableButtonFacade.queryLinkableButtonPageByLinkableId(banner.getLinkableId());
			}
			return convertBannerToMap(banner, linkBtn, linkBtnPageList);
		} else if (dto.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
			BannerDTO banner = bannerFacade.queryBannerById(dto.getBannerId());
			return convertBannerToMap(banner, null, null);
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
	
	private Map<String, Object> convertBannerToMap(BannerDTO banner, LinkableButtonDTO linkBtn, List<LinkableButtonPageDTO> linkBtnPageList) {
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
			bannerMap.put("linkableButton", linkBtnMap);
		}
		if(linkBtnPageList != null) {
			List<Map<String, Object>> linkBtnPageMapList = new ArrayList<>();
			for (LinkableButtonPageDTO lbp : linkBtnPageList) {
				Map<String, Object> linkBtnPageMap = new HashMap<>();
				linkBtnPageMap.put("cmsPageId", lbp.getCmsPageId());
				linkBtnPageMap.put("clientType", lbp.getClientType());
				linkBtnPageMapList.add(linkBtnPageMap);
			}
			bannerMap.put("linkableButtonPage", linkBtnPageMapList);
		}
		return bannerMap;
	}
	
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
			linkMap.put("linkableButtonPage", linkBtnPageMapList);
		}
		if (linkParamList != null) {
			List<Map<String, Object>> linkParamMapList = new ArrayList<>();
			for (LinkableParamDTO linkParam : linkParamList) {
				Map<String, Object> linkParamMap = new HashMap<>();
				linkParamMap.put("name", linkParam.getName());
				linkParamMap.put("value", linkParam.getValue());
				linkParamMapList.add(linkParamMap);
			}
			linkMap.put("linkableParam", linkParamMapList);
		}
		return linkMap;
	}
	
}
	