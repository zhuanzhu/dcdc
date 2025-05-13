package com.egeo.components.cms.manage.write.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.cms.manage.write.CmsInstWriteManage;
import com.egeo.components.cms.dao.read.CmsInstReadDAO;
import com.egeo.components.cms.dao.write.BannerCompanyWriteDAO;
import com.egeo.components.cms.dao.write.BannerWriteDAO;
import com.egeo.components.cms.dao.write.CmsInstCfgWriteDAO;
import com.egeo.components.cms.dao.write.CmsInstCompanyWriteDAO;
import com.egeo.components.cms.dao.write.CmsInstWriteDAO;
import com.egeo.components.cms.dao.write.LinkableButtonPageWriteDAO;
import com.egeo.components.cms.dao.write.LinkableButtonWriteDAO;
import com.egeo.components.cms.dao.write.LinkableParamWriteDAO;
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.CmsInstCfgPO;
import com.egeo.components.cms.po.CmsInstCompanyPO;
import com.egeo.components.cms.po.CmsInstPO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.components.cms.po.LinkableParamPO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CmsInstWriteManageImpl implements CmsInstWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsInstWriteDAO cmsInstWriteDAO;
	@Autowired
	private CmsInstReadDAO cmsInstReadDAO;
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
	private LinkableParamWriteDAO linkableParamWriteDAO;
	@Autowired
	private CmsInstCompanyWriteDAO cmsInstCompanyWriteDAO;

	@Override
	public Long insertCmsInstWithTx(CmsInstPO po) {
		
		int i ;
		try {
				i = cmsInstWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsInstWithTx(CmsInstPO po) {
		int i;
		i = cmsInstWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsInstWithTx(CmsInstPO po) {
		int i;
		i = cmsInstWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Long insertCommonCmsInstWithTx(CmsInstPO po, List<Long> companyIdList, String configJson) {
		List<Object> configArr = JSONArray.parseArray(configJson);
		deleteOldCommonCmsInst(po.getName(), po.getCmsElementId());		
		cmsInstWriteDAO.insert(po);
		Long instId = po.getId();
		for (Object config : configArr) {
			Map<String, Object> configMap = (Map<String, Object>) config;
			Object cfgKeyIdObj = configMap.get("cfgKeyId");
			Object cfgKeyTypeObj = configMap.get("cfgKeyType");
			Object cfgKeyValueObj = configMap.get("cfgKeyValue");
			if (EmptyUtil.isEmpty(cfgKeyIdObj) || EmptyUtil.isEmpty(cfgKeyTypeObj) 
					|| EmptyUtil.isEmpty(cfgKeyValueObj)) {
				throw new BusinessException("参数错误:" + cfgKeyIdObj);
			}
			Long cfgKeyId = Long.parseLong(cfgKeyIdObj.toString());
			Integer cfgKeyType = Integer.parseInt(cfgKeyTypeObj.toString());
			saveInstCfg(cfgKeyId, instId, cfgKeyType, cfgKeyValueObj, null);
		}
		saveInstCompany(companyIdList, instId);
		return instId;
	}	
	
	private void deleteOldCommonCmsInst(String instName, Long cmsElementId) {
		CmsInstPO po = new CmsInstPO();
		po.setCmsPageId(-1L);
		po.setName(instName);
		po.setCmsElementId(cmsElementId);
		List<CmsInstPO> commonInstList = cmsInstReadDAO.findAll(po,null);
		if (EmptyUtil.isNotEmpty(commonInstList)) {
			Long oldInstId = commonInstList.get(0).getId();
			CmsInstPO delPO = new CmsInstPO();
			delPO.setId(oldInstId);
			cmsInstWriteDAO.delete(delPO);
			CmsInstCfgPO delCfgPO = new CmsInstCfgPO();
			delCfgPO.setCmsInstId(oldInstId);
			cmsInstCfgWriteDAO.deleteByPara(delCfgPO);
		}
	}
	
	private void saveInstCompany(List<Long> companyIdList, Long instId) {
		for (Long companyId : companyIdList) {
			CmsInstCompanyPO instCpmoany = new CmsInstCompanyPO();
			instCpmoany.setCompanyId(companyId);
			instCpmoany.setInstId(instId);
			cmsInstCompanyWriteDAO.insert(instCpmoany);
		}
	}
	
	private void saveInstCfg(Long cfgKeyId, Long instId, Integer cfgKeyType, Object cfgKeyValueObj, Long parentId) {
		CmsInstCfgPO po = new CmsInstCfgPO();
		po.setCmsInstId(instId);
		po.setCmsCfgKeyId(cfgKeyId);
		po.setParentId(parentId);
		logger.info("cfgKeyId:{},cfgKeyType:{},cfgKeyValueObj:{}",cfgKeyId,cfgKeyType,cfgKeyValueObj);
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
			saveBannerInfo(cfgKeyValue, instId, cfgKeyId);
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
			Map<String, Object> cfgKeyValue = (Map<String, Object>) cfgKeyValueObj;
			saveLinkableButton(cfgKeyValue, instId, cfgKeyId);
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
			List<List<Map<String, Object>>> cfgKeyValue = (List<List<Map<String, Object>>>) cfgKeyValueObj;
			saveObjectCfg(cfgKeyValue, instId, null, cfgKeyId);
		}
	}
	
	private void saveObjectCfg(List<List<Map<String, Object>>> cfgKeyValueObj, Long instId, Long pageId, Long cfgKeyId) {
		if (cfgKeyValueObj == null) {
			return;
		}
		for (List<Map<String, Object>> objCfgList : cfgKeyValueObj) {
			CmsInstCfgPO po = new CmsInstCfgPO();
			po.setCmsInstId(instId);
			po.setCmsCfgKeyId(cfgKeyId);
			cmsInstCfgWriteDAO.insert(po);
			Long parentId = po.getId();
			
			for (Map<String, Object> objCfg : objCfgList) {
				Object cfgKeyIdObj = objCfg.get("cfgKeyId");
				Object cfgKeyTypeObj = objCfg.get("cfgKeyType");
				Object valueObj = objCfg.get("cfgKeyValue");
				if (EmptyUtil.isEmpty(cfgKeyIdObj) || EmptyUtil.isEmpty(cfgKeyTypeObj)) {
					throw new BusinessException("实例配置参数错误");
				}
				Long ckId = Long.parseLong(cfgKeyIdObj.toString());
				Integer ckType = Integer.parseInt(cfgKeyTypeObj.toString());
				saveInstCfg(ckId, instId, ckType, valueObj, parentId);
			}
		}
	}
	
	private void saveLinkableButton(Map<String, Object> cfgKeyValue, Long instId, Long cfgKeyId) {
		if (cfgKeyValue == null) {
			return;
		}
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
					linkableParam.setName(name == null ? name.toString() : null);
					linkableParam.setName(value == null ? value.toString() : null);
					linkableParam.setLinkButtonId(linkBtn.getId());
					linkableParamWriteDAO.insert(linkableParam);
				}
			}
		}
		CmsInstCfgPO po = new CmsInstCfgPO();
		po.setCmsInstId(instId);
		po.setCmsCfgKeyId(cfgKeyId);
		po.setInstTextValue(String.valueOf(linkBtn.getId()));
		cmsInstCfgWriteDAO.insert(po);
		
	}
	
	private void saveBannerInfo(List<Map<String, Object>> cfgKeyValue, Long instId, Long cfgKeyId) {
		if (cfgKeyValue == null) {
			return;
		}
		for (Map<String, Object> cfgMap : cfgKeyValue) {
			Object bannerIdObj = cfgMap.get("bannerId");
			Object imgUrlObj = cfgMap.get("imgUrl");
			Object linkTypeObj = cfgMap.get("linkType");
			Object linkIdObj = cfgMap.get("linkId");
			Object linkParamObj = cfgMap.get("linkParam");
			Object linkUrlObj = cfgMap.get("linkUrl");
			Object linkableButtonPageListObj = cfgMap.get("linkableButtonPageList");
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
			CmsInstCfgPO po = new CmsInstCfgPO();
			po.setCmsInstId(instId);
			po.setCmsCfgKeyId(cfgKeyId);
			po.setBannerId(bannerId);
			cmsInstCfgWriteDAO.insert(po);
		}
	}
	
}
	