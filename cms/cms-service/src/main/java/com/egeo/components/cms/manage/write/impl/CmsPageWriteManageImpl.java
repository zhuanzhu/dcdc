package com.egeo.components.cms.manage.write.impl;

import java.util.*;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.egeo.components.cms.manage.write.CmsPageWriteManage;
import com.egeo.components.cms.condition.CmsCfgKeyCondition;
import com.egeo.components.cms.dao.read.CmsCfgKeyReadDAO;
import com.egeo.components.cms.dao.read.CmsInstReadDAO;
import com.egeo.components.cms.dao.write.BannerCompanyWriteDAO;
import com.egeo.components.cms.dao.write.BannerWriteDAO;
import com.egeo.components.cms.dao.write.CmsInstCfgWriteDAO;
import com.egeo.components.cms.dao.write.CmsInstCompanyWriteDAO;
import com.egeo.components.cms.dao.write.CmsInstWriteDAO;
import com.egeo.components.cms.dao.write.CmsPageCfgWriteDAO;
import com.egeo.components.cms.dao.write.CmsPageCompanyWriteDAO;
import com.egeo.components.cms.dao.write.CmsPageWriteDAO;
import com.egeo.components.cms.dao.write.LinkableButtonPageWriteDAO;
import com.egeo.components.cms.dao.write.LinkableButtonWriteDAO;
import com.egeo.components.cms.dao.write.LinkableParamWriteDAO;
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.CmsCfgKeyPO;
import com.egeo.components.cms.po.CmsInstCfgPO;
import com.egeo.components.cms.po.CmsInstCompanyPO;
import com.egeo.components.cms.po.CmsInstPO;
import com.egeo.components.cms.po.CmsPageCfgPO;
import com.egeo.components.cms.po.CmsPageCompanyPO;
import com.egeo.components.cms.po.CmsPagePO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.components.cms.po.LinkableParamPO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CmsPageWriteManageImpl implements CmsPageWriteManage {
	private final static Logger logger = LoggerFactory.getLogger(CmsPageWriteManageImpl.class);
	@Autowired
	private CmsPageWriteDAO cmsPageWriteDAO;
	@Autowired
	private CmsInstWriteDAO cmsInstWriteDAO;
	@Autowired
	private CmsInstCfgWriteDAO cmsInstCfgWriteDAO;
	@Autowired
	private LinkableButtonWriteDAO linkableButtonWriteDAO;
	@Autowired
	private LinkableButtonPageWriteDAO linkableButtonPageWriteDAO;
	@Autowired
	private BannerWriteDAO bannerWriteDAO;
	@Autowired
	private BannerCompanyWriteDAO bannerCompanyWriteDAO;
	@Autowired
	private CmsInstReadDAO cmsInstReadDAO;
	@Autowired
	private CmsPageCfgWriteDAO cmsPageCfgWriteDAO;
	@Autowired
	private LinkableParamWriteDAO linkableParamWriteDAO;
	@Autowired
	private CmsPageCompanyWriteDAO cmsPageCompanyWriteDAO;
	@Autowired
	private CmsInstCompanyWriteDAO cmsInstCompanyWriteDAO;
	@Autowired
	private CmsCfgKeyReadDAO cmsCfgKeyReadDAO;

	@Override
	public Long insertCmsPageWithTx(CmsPagePO po, String configJson, Long companyId) {
		Map<String, Object> configObj = JSON.parseObject(configJson);
		List<Map<String, Object>> pageConfig = (List<Map<String, Object>>) configObj.get("pageConfig");
		List<Map<String, Object>> elementConfig = (List<Map<String, Object>>) configObj.get("elementConfig");
		List<Map<String, Object>> elementCompanyConfig = (List<Map<String, Object>>) configObj.get("elementCompanyConfig");
		Map<String, Integer> pageConfigStatusMap = verifyPageConfig(pageConfig,po.getTemplateId());
		Map<String, Integer> configStatusMap = verifyElementConfig(elementConfig, po.getTemplateId());
		verifyElementCompanyConfig(configStatusMap,elementCompanyConfig);
		int cfgStatus = 0;
		if(configStatusMap.containsValue(0) || pageConfigStatusMap.containsValue(0)) {
			cfgStatus = 1;
		}
		po.setCfgStatus(cfgStatus);
		cmsPageWriteDAO.insert(po);
		if (EmptyUtil.isNotEmpty(companyId)) {
			CmsPageCompanyPO pageCompany = new CmsPageCompanyPO();
			pageCompany.setCompanyId(companyId);
			pageCompany.setPageId(po.getId());
			cmsPageCompanyWriteDAO.insert(pageCompany);
		}
		savePageConfig(pageConfig, po.getId(), null);
		
		Map<String, Long> instIdMap = new HashMap<>();
		for (Map<String, Object> config : elementConfig) {
			Object cfgKeyIdObj = config.get("cfgKeyId");
			Object cfgKeyTypeObj = config.get("cfgKeyType");
			Object cfgKeyValueObj = config.get("cfgKeyValue");
			Object elementIdObj = config.get("elementId");
			Object elementSortObj = config.get("elementSort");
			if (EmptyUtil.isEmpty(cfgKeyIdObj) || EmptyUtil.isEmpty(cfgKeyTypeObj) 
					|| EmptyUtil.isEmpty(elementIdObj) || EmptyUtil.isEmpty(elementSortObj)) {
				throw new BusinessException("参数错误:" + cfgKeyIdObj);
			}
			Long cfgKeyId = Long.parseLong(cfgKeyIdObj.toString());
			Long elementId = Long.parseLong(elementIdObj.toString());
			Integer cfgKeyType = Integer.parseInt(cfgKeyTypeObj.toString());
			Integer elementSort = Integer.parseInt(elementSortObj.toString());
			String key = elementId + "_" + elementSort;
			if (!instIdMap.containsKey(key)) {
				Long instId = saveInst(po.getId(), elementId, elementSort, configStatusMap.get(key));
				instIdMap.put(key, instId);
				saveInstCompany(elementCompanyConfig, elementId, elementSort, instId,po);
			}
			saveInstCfg(cfgKeyId, instIdMap.get(key), cfgKeyType, cfgKeyValueObj, null);
		}
		return po.getId();
	}

	private void saveInstCompany(List<Map<String, Object>> elementCompanyConfig, Long elementId, Integer elementSort, Long instId,CmsPagePO po) {
		if (EmptyUtil.isEmpty(elementCompanyConfig)) {
			return;
		}
		for (Map<String, Object> ec : elementCompanyConfig) {
			Object ecElementIdObj = ec.get("elementId");
			Object ecElementSortObj = ec.get("elementSort");
			Object ecCompanyIdsObj = ec.get("companyIds");
			if (EmptyUtil.isNotEmpty(ecElementIdObj) && EmptyUtil.isNotEmpty(ecElementSortObj) && EmptyUtil.isNotEmpty(ecCompanyIdsObj)) {
				Long ecElementId = Long.parseLong(ecElementIdObj.toString());
				Integer ecElementSort = Integer.parseInt(ecElementSortObj.toString());
				List<Object> ecCompanyIds = (List<Object>) ecCompanyIdsObj;
				if (ecElementId.equals(elementId) && ecElementSort.equals(elementSort)) {
					for (Object ecCompanyIdObj : ecCompanyIds) {
						Long ecCompanyId = Long.parseLong(ecCompanyIdObj.toString());
						if (ecCompanyId<0 && Objects.nonNull(po.getCompanyId())){
							ecCompanyId=po.getCompanyId();
						}
						CmsInstCompanyPO instCpmoany = new CmsInstCompanyPO();
						instCpmoany.setCompanyId(ecCompanyId);
						instCpmoany.setInstId(instId);
						cmsInstCompanyWriteDAO.insert(instCpmoany);
					}
				}
			} 
		}
	}
	
	private void savePageConfig(List<Map<String, Object>> pageConfig, Long pageId, Long parentId) {
		for (Map<String, Object> cfg : pageConfig) {
			Object cfgKeyIdObj = cfg.get("cfgKeyId");
			Object cfgKeyTypeObj = cfg.get("cfgKeyType");
			Object cfgKeyValueObj = cfg.get("cfgKeyValue");
			if (EmptyUtil.isEmpty(cfgKeyIdObj) || EmptyUtil.isEmpty(cfgKeyTypeObj)) {
				throw new BusinessException("参数错误  :" + cfgKeyIdObj);
			}
			Long cfgKeyId = Long.parseLong(cfgKeyIdObj.toString());
			Integer cfgKeyType = Integer.parseInt(cfgKeyTypeObj.toString());
			savePageConfigItem(cfgKeyId, cfgKeyType, cfgKeyValueObj, pageId, parentId);;
		}
	}
	
	private void savePageConfigItem(Long cfgKeyId, Integer cfgKeyType, Object cfgKeyValueObj, Long pageId, Long parentId) {
		CmsPageCfgPO po = new CmsPageCfgPO();
		po.setCmsPageId(pageId);
		po.setCmsCfgKeyId(cfgKeyId);
		po.setParentId(parentId);
		logger.info("cfgKeyId:{},cfgKeyType:{},cfgKeyValueObj:{}",cfgKeyId,cfgKeyType,cfgKeyValueObj);
		if (EmptyUtil.isEmpty(cfgKeyValueObj)) {
			cmsPageCfgWriteDAO.insert(po);
		} else {
			if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_CHECKBOX
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
				po.setCmsCfgValueCode(cfgKeyValueObj.toString());
				cmsPageCfgWriteDAO.insert(po);
			} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT 
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_IMAGE
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_SU_COMBINATION
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_COLOR_SELECT) {
				String cfgKeyValue = cfgKeyValueObj.toString();
				po.setTextValue(cfgKeyValue);
				cmsPageCfgWriteDAO.insert(po);
			} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_BANNER || cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
				List<Map<String, Object>> cfgKeyValue = (List<Map<String, Object>>) cfgKeyValueObj;
				saveBannerInfo(cfgKeyValue, null, cfgKeyId, pageId);
			} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
				Map<String, Object> cfgKeyValue = (Map<String, Object>) cfgKeyValueObj;
				saveLinkableButton(cfgKeyValue, null, cfgKeyId, parentId, pageId);
			} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
				List<List<Map<String, Object>>> cfgKeyValue = (List<List<Map<String, Object>>>) cfgKeyValueObj;
				saveObjectCfg(cfgKeyValue, null, pageId, cfgKeyId);
			}
		}
	}
	
	private Long saveInst(Long pageId, Long elementId, Integer elementSort, Integer status) {
		CmsInstPO cmsInstPO = new CmsInstPO();
		cmsInstPO.setCmsElementId(elementId);
		cmsInstPO.setCmsPageId(pageId);
		cmsInstPO.setSort(elementSort);
		cmsInstPO.setStatus(status);
		cmsInstWriteDAO.insert(cmsInstPO);
		return cmsInstPO.getId();
	}
	
	private void saveInstCfg(Long cfgKeyId, Long instId, Integer cfgKeyType, Object cfgKeyValueObj, Long parentId) {
		CmsInstCfgPO po = new CmsInstCfgPO();
		po.setCmsInstId(instId);
		po.setCmsCfgKeyId(cfgKeyId);
		po.setParentId(parentId);
		logger.info("cfgKeyId:{},cfgKeyType:{},cfgKeyValueObj:{}",cfgKeyId,cfgKeyType,cfgKeyValueObj);
		if (EmptyUtil.isEmpty(cfgKeyValueObj)) {
			cmsInstCfgWriteDAO.insert(po);
		} else {
			if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_CHECKBOX
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
				po.setCmsCfgValueCode(cfgKeyValueObj.toString());
				cmsInstCfgWriteDAO.insert(po);
			} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT 
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_IMAGE
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_SU_COMBINATION
					|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_COLOR_SELECT) {
				String cfgKeyValue = cfgKeyValueObj.toString();
				po.setInstTextValue(cfgKeyValue);
				cmsInstCfgWriteDAO.insert(po);
			} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_BANNER || cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
				List<Map<String, Object>> cfgKeyValue = (List<Map<String, Object>>) cfgKeyValueObj;
				saveBannerInfo(cfgKeyValue, instId, cfgKeyId, null);
			} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
				Map<String, Object> cfgKeyValue = (Map<String, Object>) cfgKeyValueObj;
				saveLinkableButton(cfgKeyValue, instId, cfgKeyId, parentId, null);
			} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
				List<List<Map<String, Object>>> cfgKeyValue = (List<List<Map<String, Object>>>) cfgKeyValueObj;
				saveObjectCfg(cfgKeyValue, instId, null, cfgKeyId);
			}
		}
	}
	
	private void saveObjectCfg(List<List<Map<String, Object>>> cfgKeyValueObj, Long instId, Long pageId, Long cfgKeyId) {
		for (List<Map<String, Object>> objCfgList : cfgKeyValueObj) {
			Long parentId = null;
			if (instId == null) {
				CmsPageCfgPO cmsPageCfg = new CmsPageCfgPO();
				cmsPageCfg.setCmsCfgKeyId(cfgKeyId);
				cmsPageCfg.setCmsPageId(pageId);
				cmsPageCfgWriteDAO.insert(cmsPageCfg);
				parentId = cmsPageCfg.getId();
			} else {
				CmsInstCfgPO po = new CmsInstCfgPO();
				po.setCmsInstId(instId);
				po.setCmsCfgKeyId(cfgKeyId);
				cmsInstCfgWriteDAO.insert(po);
				parentId = po.getId();
			}
			for (Map<String, Object> objCfg : objCfgList) {
				Object cfgKeyIdObj = objCfg.get("cfgKeyId");
				Object cfgKeyTypeObj = objCfg.get("cfgKeyType");
				Object valueObj = objCfg.get("cfgKeyValue");
				if (EmptyUtil.isEmpty(cfgKeyIdObj) || EmptyUtil.isEmpty(cfgKeyTypeObj)) {
					throw new BusinessException("实例配置参数错误");
				}
				Long ckId = Long.parseLong(cfgKeyIdObj.toString());
				Integer ckType = Integer.parseInt(cfgKeyTypeObj.toString());
				if (instId == null) {
					savePageConfigItem(ckId, ckType, valueObj, pageId, parentId);
				} else {
					saveInstCfg(ckId, instId, ckType, valueObj, parentId);
				}
			}
		}
	}
	
	private void saveLinkableButton(Map<String, Object> cfgKeyValue, Long instId, Long cfgKeyId, Long parentId, Long pageId) {
		Object linkTypeObj = cfgKeyValue.get("linkType");
		Object linkIdObj = cfgKeyValue.get("linkId");
		Object linkParamObj = cfgKeyValue.get("linkParam");
		Object linkUrlObj = cfgKeyValue.get("linkUrl");
		Object linkableButtonPageListObj = cfgKeyValue.get("linkableButtonPageList");
		Object extParamObj = cfgKeyValue.get("extParam");
		if (EmptyUtil.isEmpty(linkTypeObj)) {
			throw new BusinessException("跳转参数配置错误");
		}
		Integer linkType = Integer.parseInt(linkTypeObj.toString());
		LinkableButtonPO linkBtn = new LinkableButtonPO();
		linkBtn.setLinkType(linkType);
		if (EmptyUtil.isNotEmpty(linkIdObj)) {
			Long linkId = Long.parseLong(linkIdObj.toString());
			linkBtn.setLinkId(linkId);
		}
		if (EmptyUtil.isNotEmpty(linkParamObj)) {
			String linkParam = linkParamObj.toString();
			linkBtn.setLinkParam(linkParam);
		}
		if (EmptyUtil.isNotEmpty(linkUrlObj)) {
			String linkUrl = linkUrlObj.toString();
			linkBtn.setLinkUrl(linkUrl);
		}
		linkableButtonWriteDAO.insert(linkBtn);
		if (linkType == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
			if (EmptyUtil.isEmpty(linkableButtonPageListObj) || !(linkableButtonPageListObj instanceof List)) {
				throw new BusinessException("跳转参数配置错误");
			} else {
				List<Map<String, Object>> linkableButtonPageList = (List<Map<String, Object>>) linkableButtonPageListObj;
				for (Map<String, Object> linkableButtonPage : linkableButtonPageList) {
					Object cmsPageIdObj = linkableButtonPage.get("cmsPageId");
					Object clientTypeObj = linkableButtonPage.get("clientType");
					if (EmptyUtil.isNotEmpty(cmsPageIdObj) && EmptyUtil.isNotEmpty(clientTypeObj)) {
						Long cmsPageId = Long.parseLong(cmsPageIdObj.toString());
						Integer clientType = Integer.parseInt(clientTypeObj.toString());
						LinkableButtonPagePO btnPage = new LinkableButtonPagePO();
						btnPage.setClientType(clientType);
						btnPage.setCmsPageId(cmsPageId);
						btnPage.setLinkableId(linkBtn.getId());
						linkableButtonPageWriteDAO.insert(btnPage);
					}
				}
			}
		} else if (linkType == CmsConstant.CMS_LINK_TYPE_OPERATE 
				|| linkType == CmsConstant.CMS_LINK_TYPE_LOCAL_PARAM) {
			if (EmptyUtil.isNotEmpty(extParamObj)) {
				List<Map<String, Object>> extParam = (List<Map<String, Object>>) extParamObj;
				for (Map<String, Object> ep : extParam) {
					LinkableParamPO linkableParam = new LinkableParamPO();
					Object name = ep.get("name");
					Object value = ep.get("value");
					linkableParam.setName(name == null ? null : name.toString());
					linkableParam.setValue(value == null ? null : value.toString());
					linkableParam.setLinkButtonId(linkBtn.getId());
					linkableParamWriteDAO.insert(linkableParam);
				}
			}
		}
		if (instId == null) {
			CmsPageCfgPO cmsPageCfg = new CmsPageCfgPO();
			cmsPageCfg.setCmsCfgKeyId(cfgKeyId);
			cmsPageCfg.setTextValue(String.valueOf(linkBtn.getId()));
			cmsPageCfg.setParentId(parentId);
			cmsPageCfg.setCmsPageId(pageId);
			cmsPageCfgWriteDAO.insert(cmsPageCfg);
		} else {
			CmsInstCfgPO po = new CmsInstCfgPO();
			po.setCmsInstId(instId);
			po.setCmsCfgKeyId(cfgKeyId);
			po.setInstTextValue(String.valueOf(linkBtn.getId()));
			po.setParentId(parentId);
			cmsInstCfgWriteDAO.insert(po);
		}
	}
	
	private void saveBannerInfo(List<Map<String, Object>> cfgKeyValue, Long instId, Long cfgKeyId, Long pageId) {
		for (Map<String, Object> cfgMap : cfgKeyValue) {
			Object bannerIdObj = cfgMap.get("bannerId");
			Object imgUrlObj = cfgMap.get("imgUrl");
			Object linkTypeObj = cfgMap.get("linkType");
			Object linkIdObj = cfgMap.get("linkId");
			Object linkParamObj = cfgMap.get("linkParam");
			Object linkUrlObj = cfgMap.get("linkUrl");
			Object linkableButtonPageListObj = cfgMap.get("linkableButtonPageList");
			Object extParamObj = cfgMap.get("extParam");
			Long bannerId = null;
			if (EmptyUtil.isNotEmpty(bannerIdObj)) {
				bannerId = Long.parseLong(bannerIdObj.toString());
			} else if (EmptyUtil.isEmpty(imgUrlObj) || EmptyUtil.isEmpty(linkTypeObj)) {
				throw new BusinessException("banner参数配置错误");
			} else {
				Integer linkType = Integer.parseInt(linkTypeObj.toString());
				LinkableButtonPO linkBtn = new LinkableButtonPO();
				linkBtn.setLinkType(linkType);
				if (EmptyUtil.isNotEmpty(linkIdObj)) {
					Long linkId = Long.parseLong(linkIdObj.toString());
					linkBtn.setLinkId(linkId);
				}
				if (EmptyUtil.isNotEmpty(linkParamObj)) {
					Long linkParam = Long.parseLong(linkParamObj.toString());
					linkBtn.setLinkId(linkParam);
				}
				if (EmptyUtil.isNotEmpty(linkUrlObj)) {
					Long linkUrl = Long.parseLong(linkUrlObj.toString());
					linkBtn.setLinkId(linkUrl);
				}
				linkableButtonWriteDAO.insert(linkBtn);
				if (linkType == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
					if (EmptyUtil.isEmpty(linkableButtonPageListObj) || !(linkableButtonPageListObj instanceof List)) {
						throw new BusinessException("banner参数配置错误");
					} else {
						List<Map<String, Object>> linkableButtonPageList = (List<Map<String, Object>>) linkableButtonPageListObj;
						for (Map<String, Object> linkableButtonPage : linkableButtonPageList) {
							Object cmsPageIdObj = linkableButtonPage.get("cmsPageId");
							Object clientTypeObj = linkableButtonPage.get("clientType");
							if (EmptyUtil.isNotEmpty(cmsPageIdObj) && EmptyUtil.isNotEmpty(clientTypeObj)) {
								Long cmsPageId = Long.parseLong(cmsPageIdObj.toString());
								Integer clientType = Integer.parseInt(clientTypeObj.toString());
								LinkableButtonPagePO btnPage = new LinkableButtonPagePO();
								btnPage.setClientType(clientType);
								btnPage.setCmsPageId(cmsPageId);
								btnPage.setLinkableId(linkBtn.getId());
								linkableButtonPageWriteDAO.insert(btnPage);
							}
						}
					}
				} else if (linkType == CmsConstant.CMS_LINK_TYPE_OPERATE 
						|| linkType == CmsConstant.CMS_LINK_TYPE_LOCAL_PARAM) {
					if (EmptyUtil.isNotEmpty(extParamObj)) {
						List<Map<String, Object>> extParam = (List<Map<String, Object>>) extParamObj;
						for (Map<String, Object> ep : extParam) {
							LinkableParamPO linkableParam = new LinkableParamPO();
							Object name = ep.get("name");
							Object value = ep.get("value");
							linkableParam.setName(name == null ? null : name.toString());
							linkableParam.setValue(value == null ? null : value.toString());
							linkableParam.setLinkButtonId(linkBtn.getId());
							linkableParamWriteDAO.insert(linkableParam);
						}
					}
				}
				BannerPO banner = new BannerPO();
				banner.setImgUrl(imgUrlObj.toString());
				banner.setLinkableId(linkBtn.getId());
				banner.setEnabled(1);
				//所属页面设置为-1，不允许在轮播图管理列表页面编辑
				banner.setBelongPage(-1);
				bannerWriteDAO.insert(banner);
				BannerCompanyPO bannerCompany1 = new BannerCompanyPO();
				bannerCompany1.setBannerId(banner.getId());
				bannerCompany1.setCompanyId(-1L);
				bannerCompanyWriteDAO.insert(bannerCompany1);
				
				BannerCompanyPO bannerCompany2 = new BannerCompanyPO();
				bannerCompany2.setBannerId(banner.getId());
				bannerCompany2.setCompanyId(-2L);
				bannerCompanyWriteDAO.insert(bannerCompany2);
				
				BannerCompanyPO bannerCompany3 = new BannerCompanyPO();
				bannerCompany3.setBannerId(banner.getId());
				bannerCompany3.setCompanyId(-3L);
				bannerCompanyWriteDAO.insert(bannerCompany3);
				bannerId = banner.getId();
			}
			if (instId == null) {
				CmsPageCfgPO cmsPageCfg = new CmsPageCfgPO();
				cmsPageCfg.setCmsCfgKeyId(cfgKeyId);
				cmsPageCfg.setBannerId(bannerId);
				cmsPageCfg.setCmsPageId(pageId);
				cmsPageCfgWriteDAO.insert(cmsPageCfg);
			} else {
				CmsInstCfgPO po = new CmsInstCfgPO();
				po.setCmsInstId(instId);
				po.setCmsCfgKeyId(cfgKeyId);
				po.setBannerId(bannerId);
				cmsInstCfgWriteDAO.insert(po);
			}
		}
	}
	
	@Override
	public int updateCmsPageWithTx(CmsPagePO po, String configJson, Long companyId) {
		Map<String, Object> configObj = JSON.parseObject(configJson);
		List<Map<String, Object>> pageConfig = (List<Map<String, Object>>) configObj.get("pageConfig");
		List<Map<String, Object>> elementConfig = (List<Map<String, Object>>) configObj.get("elementConfig");
		List<Map<String, Object>> elementCompanyConfig = (List<Map<String, Object>>) configObj.get("elementCompanyConfig");
		
		Map<String, Integer> pageConfigStatusMap = verifyPageConfig(pageConfig,po.getTemplateId());
		Map<String, Integer> configStatusMap = verifyElementConfig(elementConfig, po.getTemplateId());
		//校验公司是否选中
		verifyElementCompanyConfig(configStatusMap,elementCompanyConfig);
		
		int cfgStatus = 0;
		if(configStatusMap.containsValue(0) || pageConfigStatusMap.containsValue(0)) {
			cfgStatus = 1;
		}
		po.setCfgStatus(cfgStatus);
		
		int i = cmsPageWriteDAO.update(po);
		updatePageCompany(companyId, po.getId());
		
		deletePageCfg(po.getId());
		savePageConfig(pageConfig, po.getId(), null);
		
		Map<String, Long> instIdMap = new HashMap<>();
		for (Map<String, Object> config : elementConfig) {
			Object cfgKeyIdObj = config.get("cfgKeyId");
			Object cfgKeyTypeObj = config.get("cfgKeyType");
			Object cfgKeyValueObj = config.get("cfgKeyValue");
			Object elementIdObj = config.get("elementId");
			Object elementSortObj = config.get("elementSort");
			if (EmptyUtil.isEmpty(cfgKeyIdObj) || EmptyUtil.isEmpty(cfgKeyTypeObj) 
					|| EmptyUtil.isEmpty(elementIdObj) || EmptyUtil.isEmpty(elementSortObj)) {
				throw new BusinessException("参数错误");
			}
			Long cfgKeyId = Long.parseLong(cfgKeyIdObj.toString());
			Long elementId = Long.parseLong(elementIdObj.toString());
			Integer cfgKeyType = Integer.parseInt(cfgKeyTypeObj.toString());
			Integer elementSort = Integer.parseInt(elementSortObj.toString());
			String key = elementId + "_" + elementSort;
			if (!instIdMap.containsKey(key)) {
				Long instId = saveInst(po.getId(), elementId, elementSort, configStatusMap.get(key));
				instIdMap.put(key, instId);
				updateInstCompany(elementCompanyConfig, elementId, elementSort, instId,po);
			}
			saveInstCfg(cfgKeyId, instIdMap.get(key), cfgKeyType, cfgKeyValueObj, null);
		}
		return i;
	}
	
	private void updateInstCompany(List<Map<String, Object>> elementCompanyConfig, Long elementId, Integer elementSort, Long instId,CmsPagePO po) {
		CmsInstCompanyPO instCompany = new CmsInstCompanyPO();
		instCompany.setInstId(instId);
		cmsInstCompanyWriteDAO.delete(instCompany);
		saveInstCompany(elementCompanyConfig, elementId, elementSort, instId,po);
	}

	private void updatePageCompany(Long companyId, Long pageId) {
		CmsPageCompanyPO oldPageCompany = new CmsPageCompanyPO();
		oldPageCompany.setPageId(pageId);
		cmsPageCompanyWriteDAO.deleteByPara(oldPageCompany);
		
		if (EmptyUtil.isNotEmpty(companyId)) {
			CmsPageCompanyPO pageCompany = new CmsPageCompanyPO();
			pageCompany.setCompanyId(companyId);
			pageCompany.setPageId(pageId);
			cmsPageCompanyWriteDAO.insert(pageCompany);
		}
	}
	
	private void deletePageCfg(Long pageId) {
		if (pageId != null) {
			CmsInstPO cmsInst = new CmsInstPO();
			cmsInst.setCmsPageId(pageId);
			List<CmsInstPO> cmsInstList = cmsInstReadDAO.findAll(cmsInst,null);
			for (CmsInstPO ci : cmsInstList) {
				CmsInstCfgPO delInstCfg = new CmsInstCfgPO();
				delInstCfg.setCmsInstId(ci.getId());
				cmsInstCfgWriteDAO.deleteByPara(delInstCfg);
			}
			cmsInstWriteDAO.deleteByPara(cmsInst);
			CmsPageCfgPO cmsPageCfgPO = new CmsPageCfgPO();
			cmsPageCfgPO.setCmsPageId(pageId);
			cmsPageCfgWriteDAO.deleteByPara(cmsPageCfgPO);
		}
	}
	
	@Override
	public int deleteCmsPageWithTx(CmsPagePO po) {
		int i;
		i = cmsPageWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateStatusWithTx(CmsPagePO po) {
		
		return cmsPageWriteDAO.update(po);
	}	
	
	/**
	 * 校验各实例所有配置项是否已配置完成
	 * @param elementConfig
	 */
	private Map<String, Integer> verifyElementConfig(List<Map<String, Object>> elementConfig, Long templateId) {
		logger.info("实例规则校验：。。。。");
		Map<String, Integer> configStatusMap = new HashMap<>();
		Map<Long, Map<Long, Map<String, Object>>> ruleMap = new HashMap<>();

		//格式化各配置项的校验规则
		List<CmsCfgKeyCondition> elementCfgKeyList = cmsCfgKeyReadDAO.findElementCfgKeyByTemplateId(templateId);
		CmsCfgKeyPO po = new CmsCfgKeyPO();
		po.setCfgKind(2);
		List<CmsCfgKeyPO> commonCfgKeyList = cmsCfgKeyReadDAO.findAll(po,null);
		Map<Long, Map<String,Object>> map = new HashMap<>();
		
		for (CmsCfgKeyPO cmsCfgKeyPO : commonCfgKeyList) {
			if (cmsCfgKeyPO.getMandatory() == 1) {
				Long cfgKeyId =cmsCfgKeyPO.getId();
				Map<String, Object> cfgRule = new HashMap<>();
				cfgRule.put("mandatory", String.valueOf(cmsCfgKeyPO.getMandatory()));
				String condition = cmsCfgKeyPO.getMandatoryCondition();
				if (EmptyUtil.isNotEmpty(condition)) {
					List<Object> conditionArr = JSONArray.parseArray(condition);
					cfgRule.put("condition", conditionArr);
				}
				map.put(cfgKeyId, cfgRule);
			}
		}
		for (CmsCfgKeyCondition ck : elementCfgKeyList) {
			Long elementId = ck.getElementId();
			if (!ruleMap.containsKey(elementId)) {
				ruleMap.put(elementId, new HashMap<>(map));
			}
			if (ck.getMandatory() == 1) {
				Long cfgKeyId = ck.getId();
				Map<String, Object> cfgRule = new HashMap<>();
				cfgRule.put("mandatory", String.valueOf(ck.getMandatory()));
				String condition = ck.getMandatoryCondition();
				if (EmptyUtil.isNotEmpty(condition)) {
					List<Object> conditionArr = JSONArray.parseArray(condition);
					cfgRule.put("condition", conditionArr);
				}
				ruleMap.get(elementId).put(cfgKeyId, cfgRule);
			}
		}
		
		Map<String, Long> elementIdMap = new HashMap<>();
		Map<String, Map<Long, Object>> elementConfigMap = new HashMap<>();
		for (Map<String, Object> ec : elementConfig) {
			String key = ec.get("elementId") + "_" + ec.get("elementSort");
			if (!elementConfigMap.containsKey(key)) {
				elementConfigMap.put(key, new HashMap<>());
				elementIdMap.put(key, Long.parseLong(ec.get("elementId").toString()));
			}
			Integer type = Integer.parseInt(ec.get("cfgKeyType").toString());
			if (type == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
				List<List<Map<String, Object>>> objectValue = (List<List<Map<String, Object>>>) ec.get("cfgKeyValue");
				for (List<Map<String, Object>> o : objectValue) {
					for (Map<String, Object> m : o) {
						putCfgMap(elementConfigMap.get(key), Long.parseLong(m.get("cfgKeyId").toString()), m.get("cfgKeyValue"));
					}
				}
			} else {
				putCfgMap(elementConfigMap.get(key), Long.parseLong(ec.get("cfgKeyId").toString()), ec.get("cfgKeyValue"));
			}
		}
		
		for (Entry<String, Map<Long, Object>> ec : elementConfigMap.entrySet()) {
			String key = ec.getKey();
			boolean configStatus = verifyConfigStatus(ruleMap.get(elementIdMap.get(key)), ec.getValue());
			configStatusMap.put(key, configStatus ? 1 : 0);
		}
		logger.info("实例规则校验结束 configStatusMap ：{}",JSON.toJSONString(configStatusMap));
		return configStatusMap;
	}
	
	
	/**
	 * 校验可见公司
	 * @param map
	 * @param elementCompanyConfig
	 */
	private void verifyElementCompanyConfig(Map<String, Integer> map,List<Map<String, Object>> elementCompanyConfig){
		logger.info("组件可见公司属性校验：start:{} elementCompanyConfig:{}" , map,elementCompanyConfig);
		Map<String, Integer> companyMap = new HashMap<>();
		if(EmptyUtil.isNotEmpty(elementCompanyConfig)) {
			for (Map<String, Object> ec : elementCompanyConfig) {
				Object ecElementIdObj = ec.get("elementId");
				Object ecElementSortObj = ec.get("elementSort");
				Object ecCompanyIdsObj = ec.get("companyIds");
				if (EmptyUtil.isNotEmpty(ecElementIdObj) && EmptyUtil.isNotEmpty(ecElementSortObj) && EmptyUtil.isNotEmpty(ecCompanyIdsObj)) {
					String elementId = ecElementIdObj + "_" + ecElementSortObj;
					companyMap.put(elementId, 1);
				} 
			}
		}
		
		for (Entry<String, Integer> map3 : map.entrySet()) {
			if(!companyMap.containsKey(map3.getKey())) {
				map.put(map3.getKey(), 0);
			}
		}
		logger.info("组件可见公司属性校验：end: {} " , map);
	}
	
	/**
	 * 校验各页面所有配置项是否已配置完成
	 * @param pageConfig
	 */
	private Map<String, Integer> verifyPageConfig(List<Map<String, Object>> pageConfig, Long templateId) {
		logger.info("页面规则校验：。。。。");
		logger.info("页面规则校验：pageConfig:{}",JSON.toJSONString(pageConfig));
		Map<String, Integer> configStatusMap = new HashMap<>();
		Map<Long, Map<String, Object>> ruleMap = new HashMap<>();

		//格式化各配置项的校验规则
		List<CmsCfgKeyCondition> pageCfgKeyList = cmsCfgKeyReadDAO.findTemplateCfgKeyByTemplateId(templateId);
		
		for (CmsCfgKeyCondition ck : pageCfgKeyList) {
			
			if (ck.getMandatory() == 1) {
				Long cfgKeyId = ck.getId();
				Map<String, Object> cfgRule = new HashMap<>();
				cfgRule.put("mandatory", String.valueOf(ck.getMandatory()));
				String condition = ck.getMandatoryCondition();
				if (EmptyUtil.isNotEmpty(condition)) {
					List<Object> conditionArr = JSONArray.parseArray(condition);
					cfgRule.put("condition", conditionArr);
				}
				ruleMap.put(cfgKeyId, cfgRule);
			}
		}
		
		Map<Long, Object> pageConfigMap = new HashMap<>();
		for (Map<String, Object> ec : pageConfig) {
			putCfgMap(pageConfigMap, Long.parseLong(ec.get("cfgKeyId").toString()), ec.get("cfgKeyValue"));
		}
		
		boolean configStatus = verifyConfigStatus(ruleMap, pageConfigMap);
		configStatusMap.put("status", configStatus ? 1 : 0);
		logger.info("页面规则校验结束 configStatusMap ：{}",JSON.toJSONString(configStatusMap));
		return configStatusMap;
	}
	
	private boolean verifyConfigStatus(Map<Long, Map<String, Object>> elementRuleMap, Map<Long, Object> elementConfigMap) {
		logger.info("组件规则校验：elementConfigMap:{},elementRuleMap:{}",JSON.toJSONString(elementConfigMap),JSON.toJSONString(elementRuleMap));
		List<Long> sloganCfgKeyList = Arrays.asList(new Long[]{77L, 78L, 80L});
		boolean sloganObjectVerifyStatus = false;
		List<Long> iconCfgKeyList = Arrays.asList(new Long[]{92L, 93L, 94L});
		List<Long> linkKeyList = Arrays.asList(new Long[]{16L, 26L, 27L, 40L, 54L, 68L, 119L, 128L, 137L, 160L});
		boolean iconObjectVerifyStatus = false;
		for (Entry<Long, Map<String, Object>> rule : elementRuleMap.entrySet()) {
			boolean flag = false;
			List<Map<String, String>> conditionArr = (List<Map<String, String>>) rule.getValue().get("condition");
			if (EmptyUtil.isNotEmpty(conditionArr)) {
				for (Map<String, String> condition : conditionArr) {
					String leftVar = condition.get("leftVar");
					String operator = condition.get("operator");
					String rightVar = condition.get("rightVar");
					Object leftVarValue = leftVar;
					Object rightVarValue = rightVar;
					if (leftVar.startsWith("$")) {
						leftVarValue = elementConfigMap.get(Long.parseLong(leftVar.substring(1)));
					}
					if (rightVar.startsWith("$")) {
						rightVarValue = elementConfigMap.get(Long.parseLong(rightVar.substring(1)));
					}
					logger.info("cfgKeyId:{},leftVarValue:{},rightVarValue:{}",rule.getKey(),leftVarValue,rightVarValue);
					if ("eq".equals(operator)) {
						flag = (leftVarValue != null && (leftVarValue.toString()).equals(rightVarValue.toString()));
					} else if ("ne".equals(operator)) {
						flag = (leftVarValue != null && !(leftVarValue.toString()).equals(rightVarValue.toString()));
					}
					if (flag) {
						break;
					}
				}
			}else {
				flag = true;
			}
			if (flag) {
				if (EmptyUtil.isEmpty(elementConfigMap.get(rule.getKey()))) {
					logger.info("必填校验：cfgKeyId:{} 属性未输入",rule.getKey());
					return false;
					
				}else {
					if(!iconCfgKeyList.contains(rule.getKey())) {
						Object object = elementConfigMap.get(rule.getKey());
						logger.info("必填校验：cfgKeyId:{}  value:{}",rule.getKey() ,JSON.toJSONString(object));
						if(object instanceof List) {
							if(((List) object).contains("") || ((List) object).contains(null)) {
								return false;
							}
						}
					}
				}
				if (linkKeyList.contains(rule.getKey())) {
					if (!verifyLink(elementConfigMap.get(rule.getKey()))) {
						logger.info("必填校验：cfgKeyId:{} 属性未输入",rule.getKey());
						return false;
					}
				}
				if (sloganCfgKeyList.contains(rule.getKey()) && !sloganObjectVerifyStatus) {
					sloganObjectVerifyStatus = true;
					if (!verifySlogan(elementConfigMap)) {
						return false;
					}
				}
				if (iconCfgKeyList.contains(rule.getKey()) && !iconObjectVerifyStatus) {
					iconObjectVerifyStatus = true;
					if (!verifyIcon(elementConfigMap)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 * 校验标语组件是否配置完成
	 * @param elementConfigMap
	 * @return
	 */
	private boolean verifySlogan(Map<Long, Object> elementConfigMap) {
		Object countObj = elementConfigMap.get(74L);
		Integer count = countObj == null ? null : Integer.parseInt(countObj.toString());
		Object textObject =  elementConfigMap.get(77L);
		Object fontSizeObject =  elementConfigMap.get(78L);
		Object linkObject =  elementConfigMap.get(80L);
		
		if(textObject instanceof List && fontSizeObject instanceof List && linkObject instanceof List) {
			List text = (List) elementConfigMap.get(77L);
			List fontSize = (List) elementConfigMap.get(78L);
			List link = (List) elementConfigMap.get(80L);
			if (EmptyUtil.isEmpty(text) || EmptyUtil.isEmpty(fontSize) || EmptyUtil.isEmpty(link)) {
				return false;
			}
			if (text.size() != count || fontSize.size() != count || link.size() != count) {
				return false;
			}
			if (hasNull(text, text.size()) || hasNull(fontSize, fontSize.size()) || !verifyLinkList(link, link.size())) {
				return false;
			}
		}else {
			if (EmptyUtil.isEmpty(textObject) || EmptyUtil.isEmpty(fontSizeObject) || EmptyUtil.isEmpty(linkObject)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 校验icon组件是否配置完成
	 * @param elementConfigMap
	 * @return
	 */
	private boolean verifyIcon(Map<Long, Object> elementConfigMap) {
		Object rowsObj = elementConfigMap.get(81L);
		Integer rows = rowsObj == null ? null : Integer.parseInt(rowsObj.toString());
		Object colsObj = elementConfigMap.get(82L);
		Integer cols = colsObj == null ? null : Integer.parseInt(colsObj.toString());
		logger.info("校验icon组件是否配置完成 rows:{},cols:{}",rows,cols);
		if (rows == null || cols == null || rows <= 0 || cols <= 0) {
			return false;
		}
		List text = (List) elementConfigMap.get(92L);
		List imgUrl = (List) elementConfigMap.get(93L);
		List link = (List) elementConfigMap.get(94L);
		
		if (EmptyUtil.isEmpty(text) || EmptyUtil.isEmpty(imgUrl) || EmptyUtil.isEmpty(link)) {
			return false;
		}
		
		List realText = new ArrayList();
		List realImgUrl = new ArrayList();
		List realLink = new ArrayList();
		for (Object object : link) {
			if(EmptyUtil.isNotEmpty(object)) {
				realLink.add(object);
			}
		}
		
		for (Object object : text) {
			if(EmptyUtil.isNotEmpty(object)) {
				realText.add(object);
			}
		}
		
		for (Object object : imgUrl) {
			if(EmptyUtil.isNotEmpty(object)) {
				realImgUrl.add(object);
			}
		}
		
		logger.info("校验icon组件是否配置完成 text:{},imgUrl:{},link:{}",JSON.toJSONString(text),JSON.toJSONString(imgUrl),JSON.toJSONString(link));
		logger.info("校验icon组件是否配置完成 realText:{},realImgUrl:{},realLink:{}",JSON.toJSONString(realText),JSON.toJSONString(realImgUrl),JSON.toJSONString(realLink));
		
		Integer count = realText.size();
		if (realImgUrl.size() != count || realLink.size() != count) {
			return false;
		}
		Integer lastRowFirst = (cols * (rows - 1)) + 1;
		if (count < lastRowFirst) {
			return false;
		}
//		if (EmptyUtil.isEmpty(text.get(lastRowFirst)) || EmptyUtil.isEmpty(imgUrl.get(lastRowFirst)) || EmptyUtil.isEmpty(link.get(lastRowFirst))) {
//			return false;
//		}
		if (hasNull(text, count) || hasNull(imgUrl, count) || !verifyLinkList(link, count)) {
			
			return false;
		}
		return true;
	}
	
	private boolean hasNull(List list, int end) {
		for (int i = 0; i < end; i++) {
			if (EmptyUtil.isEmpty(list.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean verifyLinkList(List list, int end) {
		for (int i = 0; i < end; i++) {
			if (!verifyLink(list.get(i))) {
				logger.info("跳转链接必填校验失败！");
				return false;
			}
		}
		return true;
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
	
	
	private boolean verifyLink(Object linkMapObj) {
		if (EmptyUtil.isEmpty(linkMapObj) || !(linkMapObj instanceof Map)) {
			return false;
		}
		Map linkMap = (Map) linkMapObj;
		Object linkTypeObj = linkMap.get("linkType");
		Object linkIdObj = linkMap.get("linkId");
		Object linkUrl = linkMap.get("linkUrl");
		Object extParamObj = linkMap.get("extParam");
		Object linkableButtonPageListObj = linkMap.get("linkableButtonPageList");
		logger.info("linkTypeObj:{},linkIdObj:{},linkUrl:{},extParamObj:{},linkableButtonPageListObj:{}",
				linkTypeObj,linkIdObj,linkUrl,JSON.toJSONString(extParamObj),JSON.toJSONString(linkableButtonPageListObj));
		List<Long> list1 = Arrays.asList(new Long[]{11L, 12L, 13L, 14L, 57L, 58L, 59L, 
				60L, 16L, 62L, 26L, 27L, 28L, 32L, 33L, 35L, 38L, 40L, 46L, 74L, 79L, 48L, 52L,72L, 81L, 84L, 54L, 86L});
		List<Long> list2 = Arrays.asList(new Long[]{15L, 17L, 18L, 23L, 27L, 30L, 51L,53L, 68L, 70L, 71L, 76L, 85L,88L});
		List<Long> list3 = Arrays.asList(new Long[]{2L, 3L, 7L, 8L});
		List<Long> list4 = Arrays.asList(new Long[]{1L, 4L, 5L, 6L, 9L, 10L});
		if (EmptyUtil.isNotEmpty(linkTypeObj)) {
			Integer linkType = Integer.parseInt(linkTypeObj.toString());
			//1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品池组(商品列表)、5.商品、6.无效果、7.分页tab、8.操作配置
			if (linkType == 1) {
				if (EmptyUtil.isEmpty(linkIdObj)) {
					return false;
				} else {
					Long linkId = Long.parseLong(linkIdObj.toString());
					if (list1.contains(linkId)) {
						return true;
					} else if (list2.contains(linkId)) {
						return verifyExtParam1(extParamObj);
					} else {
						return true;
					}
				}
			} else if (linkType == 2) {
				return EmptyUtil.isNotEmpty(linkUrl);
			} else if (linkType == 4) {
				return verifylinkPageList(linkableButtonPageListObj);
			} else if (linkType == 5 || linkType == 3) {
				return EmptyUtil.isNotEmpty(linkIdObj);
			} else if (linkType == 6) {
				return true;
			} else if (linkType == 7) {
				return EmptyUtil.isNotEmpty(linkIdObj);
			} else if (linkType == 8) {
				if (EmptyUtil.isEmpty(linkIdObj)) {
					return false;
				} else {
					Long linkId = Long.parseLong(linkIdObj.toString());
					if (list3.contains(linkId)) {
						return verifyExtParam2(extParamObj);
					} else if (list4.contains(linkId)) {
						return verifyExtParam1(extParamObj);
					}
				}
			}
		} else {
			return false;
		}
		return false;
	}
	
	private boolean verifyExtParam1(Object extParamObj) {
		if (EmptyUtil.isEmpty(extParamObj)) {
			return false;
		} else if (!(extParamObj instanceof List)) {
			return false;
		} else {
			List<Map> extParam = (List<Map>) extParamObj;
			for (Map m : extParam) {
				if ("extParam1".equals(m.get("name")) && EmptyUtil.isNotEmpty(m.get("value"))) {
					return true;
				}
			}
		} 
		return false;
	}
	
	private boolean verifyExtParam2(Object extParamObj) {
		if (EmptyUtil.isEmpty(extParamObj)) {
			return false;
		} else if (!(extParamObj instanceof List)) {
			return false;
		} else {
			List<Map> extParam = (List<Map>) extParamObj;
			int i = 0;
			for (Map m : extParam) {
				if ("extParam1".equals(m.get("name")) && EmptyUtil.isNotEmpty(m.get("value"))) {
					i++;
				}
				if ("extParam2".equals(m.get("name")) && EmptyUtil.isNotEmpty(m.get("value"))) {
					i++;
				}
			}
			if (i == 2) {
				return true;
			}
		} 
		return false;
	}
	
	private boolean verifylinkPageList(Object linkableButtonPageListObj) {
		if (EmptyUtil.isEmpty(linkableButtonPageListObj)) {
			return false;
		} else if (!(linkableButtonPageListObj instanceof List)) {
			return false;
		} else {
			List<Map> linkableButtonPageList = (List<Map>) linkableButtonPageListObj;
			if (linkableButtonPageList.size() != 2) {
				return false;
			}
			for (Map m : linkableButtonPageList) {
				if (EmptyUtil.isEmpty(m.get("cmsPageId")) || EmptyUtil.isEmpty(m.get("clientType"))) {
					return false;
				}
			}
			return true;
		} 
	}
	
}
	