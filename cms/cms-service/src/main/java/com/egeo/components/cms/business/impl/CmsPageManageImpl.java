package com.egeo.components.cms.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.cms.business.CmsPageManage;
import com.egeo.components.cms.dto.CmsCfgValueDTO;
import com.egeo.components.cms.dto.CmsInstCfgDTO;
import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.components.cms.dto.CmsPageCfgDTO;
import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.components.cms.facade.CmsCfgKeyFacade;
import com.egeo.components.cms.facade.CmsPageFacade;
import com.egeo.components.cms.facade.CmsTemplateFacade;
import com.egeo.components.cms.facade.LinkableButtonFacade;
import com.egeo.components.cms.vo.BannerVO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("cmsPage")
public class CmsPageManageImpl implements CmsPageManage {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsPageManageImpl.class);
	
	@Resource(name="cmsPageFacade")
	private CmsPageFacade cmsPageFacade;
	@Resource(name="cmsCfgKeyFacade")
	private CmsCfgKeyFacade cmsCfgKeyFacade;
	@Resource(name="cmsTemplateFacade")
	private CmsTemplateFacade cmsTemplateFacade;
	@Resource(name="linkableButtonFacade")
	private LinkableButtonFacade linkableButtonFacade;

	@Override
	public CmsPageDTO findCmsPageById(CmsPageDTO dto) {
		return cmsPageFacade.findCmsPageById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findCmsPageOfPage(CmsPageDTO dto, Pagination page) {
		PageResult<CmsPageDTO> pageResult = cmsPageFacade.findCmsPageOfPage(dto, page);
		if(pageResult == null) {
			return new PageResult<Map<String, Object>>();
		}
		List<Long> companyIds = new ArrayList<>();
		List<Long> userIds = new ArrayList<>();
		for (CmsPageDTO  cmsPageDTO: pageResult.getList()) {
			userIds.add(cmsPageDTO.getUpdateUserId());
			companyIds.add(cmsPageDTO.getCompanyId());
		}
		
		List<Map<String, Object>> pageMapList = new ArrayList<>();
		Map<Long, String> companyMap = new HashMap<>();
		Map<Long, String> userMap = new HashMap<>();
		if (EmptyUtil.isNotEmpty(companyIds)) {
			companyMap = cmsPageFacade.findCompanyByIdList(companyIds);
		}
		if (EmptyUtil.isNotEmpty(userIds)) {
			userMap = cmsPageFacade.findUserByIdList(userIds);
		}

		logger.info("userMap:{},companyMap:{}",userMap,companyMap);
		
		for (CmsPageDTO cmsPageDTO : pageResult.getList()) {
			Map<String, Object> pageMap = new HashMap<>();
			pageMap.put("id", cmsPageDTO.getId());
			pageMap.put("pageName", cmsPageDTO.getPageName());
			pageMap.put("templateName", cmsPageDTO.getTemplateName());
			pageMap.put("templateType", cmsPageDTO.getTemplateType());
			//商品列表页时返回
			if(dto.getSearchType() != null && dto.getSearchType() != 0) {
				List<CmsPageCfgDTO> cmsPageCfgList = cmsPageFacade.findPageCfgByPageId(cmsPageDTO.getId());
				List<CmsInstCfgDTO> instCfgList = cmsPageFacade.findPageInstCfgByPageId(cmsPageDTO.getId(), null);
				for (CmsPageCfgDTO cmsPageCfgDTO : cmsPageCfgList) {
					if (cmsPageCfgDTO.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT) {
						pageMap.put(cmsPageCfgDTO.getCkgKeyCode(), getCfgText(cmsPageCfgDTO.getCmsCfgKeyId(), cmsPageCfgDTO.getCmsCfgValueCode()));
					} else if (cmsPageCfgDTO.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT) {
						pageMap.put(cmsPageCfgDTO.getCkgKeyCode(), cmsPageCfgDTO.getTextValue());
					}
				}
				for (CmsInstCfgDTO cmsInstCfgDTO : instCfgList) {
					if (cmsInstCfgDTO.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT) {
						pageMap.put(cmsInstCfgDTO.getCkgKeyCode(), getCfgText(cmsInstCfgDTO.getCmsCfgKeyId(), cmsInstCfgDTO.getCmsCfgValueCode()));
					} else if (cmsInstCfgDTO.getCkgKeyType() == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT) {
						pageMap.put(cmsInstCfgDTO.getCkgKeyCode(), cmsInstCfgDTO.getInstTextValue());
					}
				}
			}
			if (EmptyUtil.isNotEmpty(companyMap)) {
				pageMap.put("companyName", companyMap.get(cmsPageDTO.getCompanyId()));
			}else {
				pageMap.put("companyName", "");
			}
			pageMap.put("pageStatus", cmsPageDTO.getPageStatus());
			pageMap.put("clientType", cmsPageDTO.getClientType());
			pageMap.put("cfgStatus", cmsPageDTO.getCfgStatus());
			pageMap.put("updateTime", cmsPageDTO.getUpdateTime());
			if (EmptyUtil.isNotEmpty(userMap)) {
				pageMap.put("updateUserName", userMap.get(cmsPageDTO.getUpdateUserId()));
			}else {
				pageMap.put("updateUserName","");
			}

			if (EmptyUtil.isNotEmpty(cmsPageDTO.getClientVersionARemark()) 
					&& !cmsPageDTO.getClientVersionARemark().contains("安卓")) {
				pageMap.put("clientVersionARemark", cmsPageDTO.getClientVersionARemark());
			}else {
				pageMap.put("clientVersionARemark", "");
			}
			if (EmptyUtil.isNotEmpty(cmsPageDTO.getClientVersionIRemark()) 
					&& !cmsPageDTO.getClientVersionIRemark().contains("IOS") 
					&& !cmsPageDTO.getClientVersionIRemark().contains("ios")) {
				pageMap.put("clientVersionIRemark", cmsPageDTO.getClientVersionIRemark());
			}else {
				pageMap.put("clientVersionIRemark", "");
			}
			if(cmsPageDTO.getCompanyId() != null) {
				pageMap.put("isSingle", cmsPageDTO.getCompanyId() > 0 ? 1:0);
			}
			
			pageMapList.add(pageMap);
			
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(pageMapList);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}
	
	private String getCfgText(Long cfgKeyId, String code) {
		CmsCfgValueDTO cmsCfgValueDTO = cmsCfgKeyFacade.findCmsCfgValueByCode(cfgKeyId, code);
		return cmsCfgValueDTO == null ? "" : cmsCfgValueDTO.getName();
	}

	@Override
	public List<CmsPageDTO> findCmsPageAll(CmsPageDTO dto) {
		return cmsPageFacade.findCmsPageAll(dto);
	}

	@Override
	public Long insertCmsPageWithTx(CmsPageDTO dto, String configJson) {
		return cmsPageFacade.insertCmsPageWithTx(dto, configJson);
	}

	@Override
	public int updateCmsPageWithTx(CmsPageDTO dto, String configJson) {
		return cmsPageFacade.updateCmsPageWithTx(dto, configJson);
	}

	@Override
	public int deleteCmsPageWithTx(CmsPageDTO dto) {
		return cmsPageFacade.deleteCmsPageWithTx(dto);
	}

	@Override
	public Map<String, Object> findPageCfgById(Long pageId, Long clientId, Integer versionCode, Long f, Long companyId,
			Integer scope, Long platformId, Integer pageType, Pagination page, Long userId) {
		if (pageType != null) {
			pageId = findSupportPageByPageTypeAndVersionCode(pageType, f, versionCode, platformId, companyId);
		} else {
			pageId = findSupportPageByVersionCode(pageId, f, versionCode, platformId);
		}
		if (pageId == null ||  pageId.equals(0)) {
			throw new BusinessException("页面未找到");
		}
		Map<String, Object> result = new HashMap<>();
		//查询范围 0-默认（页面配置+页面组件实例） 1-仅页面配置  2-仅组件实例
		if (scope == 0) {
			result.put("pageConfig", constructPageConfig(pageId, clientId, companyId, platformId,pageType));
			PageResult<CmsInstDTO> instPage = cmsPageFacade.findByPageAndCompanyOfPage(pageId, companyId, page);
			result.put("pageInsts", constructPageInsts(pageId, clientId, companyId, platformId, page, userId, instPage.getList()));
			result.put("pageNo", instPage.getPageNo());
			result.put("pageSize", instPage.getPageSize());
			result.put("totalSize", instPage.getTotalSize());
			result.put("totalPage", instPage.getTotalPage());
		} else if (scope == 1) {
			result.put("pageConfig", constructPageConfig(pageId, clientId, companyId, platformId,pageType));
		} else if (scope == 2) {
			PageResult<CmsInstDTO> instPage = cmsPageFacade.findByPageAndCompanyOfPage(pageId, companyId, page);
			result.put("pageInsts", constructPageInsts(pageId, clientId, companyId, platformId, page, userId, instPage.getList()));
			result.put("pageNo", instPage.getPageNo());
			result.put("pageSize", instPage.getPageSize());
			result.put("totalSize", instPage.getTotalSize());
			result.put("totalPage", instPage.getTotalPage());
		}
		return result;
	}
	
	private Long findSupportPageByPageTypeAndVersionCode(Integer pageType, Long f, Integer versionCode, Long platformId, Long companyId) {
		Long pageId = null;
		if (f != null && f.equals(0L)) {
			pageId =  cmsPageFacade.findSupportPageByPageTypeAndVersionCode(pageType, platformId, versionCode, null, companyId);
		} else if (f != null && f.equals(1L)) {
			pageId =  cmsPageFacade.findSupportPageByPageTypeAndVersionCode(pageType, platformId, null, versionCode, companyId);
		} else {
			pageId =  cmsPageFacade.findSupportPageByPageTypeAndVersionCode(pageType, platformId, null, null, companyId);
		}
		return pageId;
	}

	/**
	 * 根据页面ID和客户端类型查询支持的最高版本
	 * @param pageId
	 * @param f
	 * @param versionCode
	 * @param platformId
	 * @return
	 */
	private Long findSupportPageByVersionCode(Long pageId, Long f, Integer versionCode, Long platformId) {
		//请求来源：0、安卓 1、ios 2、微信端 3、web端
		if (f != null && f.equals(0L)) {
			int cnt = cmsPageFacade.findSupportPageByVersionCode(pageId, platformId, versionCode, null);
			if (cnt > 0) {
				return pageId;
			} else {
				return getDefaultPageIdForApp(platformId, pageId);
			}
		} else if (f != null && f.equals(1L)) {
			int cnt = cmsPageFacade.findSupportPageByVersionCode(pageId, platformId, null, versionCode);
			if (cnt > 0) {
				return pageId;
			} else {
				return getDefaultPageIdForApp(platformId, pageId);
			}
		} else {
			return pageId;
		}
	}
	
	private Long getDefaultPageIdForApp(Long platformId, Long pageId) {
		if (PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)) {
			return CmsConstant.CMS_DEFAULT_PAGE_SU_1;
		} else if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
			return CmsConstant.CMS_DEFAULT_PAGE_SU_9;
		}
		return pageId;
	}
	
	private List<Map<String, Object>> constructPageInsts(Long pageId, Long clientId, Long companyId, Long platformId, Pagination page, Long userId, List<CmsInstDTO> instList) {
		List<Map<String, Object>> pageElements = new ArrayList<>();
		if (EmptyUtil.isNotEmpty(instList)) {
			List<Long> instIdList = new ArrayList<>();
			for (CmsInstDTO ci : instList) {
				instIdList.add(ci.getId());
			}
			List<CmsInstCfgDTO> instCfgList = cmsPageFacade.findPageInstCfgByPageId(pageId, instIdList);
			Map<Long, Map<String, Object>> instConfigMap = new LinkedHashMap<>();
			Map<Long, Long> elementIdMap = new HashMap<>();
			for (CmsInstCfgDTO cmsInstCfg : instCfgList) {
				Map<String, Object> cfg = null;
				if (!instConfigMap.containsKey(cmsInstCfg.getCmsInstId())) {
					cfg = new LinkedHashMap<String, Object>();
					instConfigMap.put(cmsInstCfg.getCmsInstId(), cfg);
					elementIdMap.put(cmsInstCfg.getCmsInstId(), cmsInstCfg.getCmsElementId());
				} else {
					cfg = instConfigMap.get(cmsInstCfg.getCmsInstId());
				}
				if (EmptyUtil.isEmpty(cmsInstCfg.getParentId())) {
					//parentId为空说明是第一级配置属性，直接加入map
					constructInstConfig(cfg, cmsInstCfg, instCfgList, clientId, companyId, platformId);
				}
			}
			
			for (Entry<Long, Map<String, Object>> entry : instConfigMap.entrySet()) {
				Map<String, Object> inst = new HashMap<>();
				inst.put("instId", entry.getKey());
				inst.put("elementId", elementIdMap.get(entry.getKey()));
				if (elementIdMap.get(entry.getKey()).equals(13L)) {
					//消息组件查询用户消息
					List<Map<String, Object>> userMsg = cmsPageFacade.findUserInfoForMsgBox(userId, entry.getValue());
					entry.getValue().put("msgList", userMsg);
				}
				if (elementIdMap.get(entry.getKey()) > 5) {
					inst.put("config" + elementIdMap.get(entry.getKey()), entry.getValue());
				} else {
					inst.put("config", entry.getValue());
				}
				pageElements.add(inst);
			}
		}
		return pageElements;
	}
	
	private void constructInstConfig(Map<String, Object> cfg, CmsInstCfgDTO cmsInstCfg, List<CmsInstCfgDTO> instCfgList, Long clientId, Long companyId, Long platformId) {
		int cfgKeyType = cmsInstCfg.getCkgKeyType();
		String keyCode = cmsInstCfg.getCkgKeyCode();
		if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_IMAGE
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_COLOR_SELECT) {
			putCfgMap(cfg, keyCode, cmsInstCfg.getInstTextValue());
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_CHECKBOX
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
			putCfgMap(cfg, keyCode, cmsInstCfg.getCmsCfgValueCode());
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_BANNER || cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
			if (!cfg.containsKey(keyCode)) {
				cfg.put(keyCode, new ArrayList<>());
			}
			if (cmsInstCfg.getBannerId() != null) {
				BannerVO banner = cmsPageFacade.findBannerByIdAndCompanyId(cmsInstCfg.getBannerId(), clientId, companyId, platformId);
				if (banner != null) {
					putCfgMap(cfg, keyCode, banner);
				}
			}
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
			if (cmsInstCfg.getInstTextValue() == null) {
				putCfgMap(cfg, keyCode, null);
			} else {
				Long linkableId = Long.parseLong(cmsInstCfg.getInstTextValue());
				Map<String, Object> linkMap = cmsPageFacade.findLinkableButtonConfig(linkableId, clientId, companyId, platformId);
				putCfgMap(cfg, keyCode, linkMap);
			}
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_SU_COMBINATION) {
			if (cmsInstCfg.getInstTextValue() == null) {
				putCfgMap(cfg, keyCode, null);
			} else {
				Long sucId = Long.parseLong(cmsInstCfg.getInstTextValue());
				List<Map<String, Object>> suList = cmsPageFacade.findSuList(sucId, platformId, companyId, cmsInstCfg);
				putCfgMap(cfg, keyCode, suList);
			}
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
			String description = cmsInstCfg.getCkgKeyDescription();
			if (EmptyUtil.isNotEmpty(description) && description.endsWith("list")) {
				//Object类属性的描述字段以list结尾，当做列表处理，默认放入列表
				if (!cfg.containsKey(keyCode)) {
					cfg.put(keyCode, new ArrayList<>());
				}
			}
			//构建Object类型的值map
			Map<String, Object> objCfgMap = new HashMap<>();
			for (CmsInstCfgDTO instCfg : instCfgList) {
				//找出当前列表中的下级属性，递归加入值map中
				if (cmsInstCfg.getId().equals(instCfg.getParentId())) {
					constructInstConfig(objCfgMap, instCfg, instCfgList, clientId, companyId, platformId);
				}
			}
			if (!verifyObjectEmpty(objCfgMap)) {
				putCfgMap(cfg, keyCode, objCfgMap);
			}
		}
	}
	
	private boolean verifyObjectEmpty(Map<String, Object> objCfgMap) {
		if (objCfgMap ==null || objCfgMap.isEmpty()) {
			return true;
		} else {
			for (Entry<String, Object> e : objCfgMap.entrySet()) {
				if (e != null && e.getValue() != null) {
					return false;
				}
			}
		}
		return true;
	}
	
	private Map<String, Object> constructPageConfig(Long pageId, Long clientId, Long companyId, Long platformId,Integer pageType) {
		List<CmsPageCfgDTO> cmsPageCfgList = cmsPageFacade.findPageCfgByPageId(pageId);
		
		if(pageType == null) {
			CmsPageDTO dto = new CmsPageDTO();
			dto.setId(pageId);
			dto = cmsPageFacade.findCmsPageConditionById(dto);
			pageType = dto.getTemplateType();
		}
		Map<String, Object> pageConfig = new LinkedHashMap<>();
		for (CmsPageCfgDTO pageCfg : cmsPageCfgList) {
			if (EmptyUtil.isEmpty(pageCfg.getParentId())) {
				//parentId为空，说明是一级属性，直接加入值map
				constructPageConfigDetail(pageConfig, pageCfg, cmsPageCfgList, clientId, companyId, platformId,pageType);
			}
		}
		return pageConfig;
	}
	
	private void constructPageConfigDetail(Map<String, Object> cfg, CmsPageCfgDTO pageCfg, List<CmsPageCfgDTO> cmsPageCfgList, Long clientId, Long companyId, Long platformId,
			Integer pageType) {
		int cfgKeyType = pageCfg.getCkgKeyType();
		String keyCode = pageCfg.getCkgKeyCode();
		if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_TEXT_INPUT
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_IMAGE
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_SU_COMBINATION
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_COLOR_SELECT) {
			putCfgMap(cfg, keyCode, pageCfg.getTextValue());
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_CHECKBOX
				|| cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
			if ("titleTypeRadio".equals(keyCode)) {
				//图片标题查询公司logo和平台logo
				Map<String, Object> logoTitleInfo = cmsPageFacade.findCompanyAndPlatformLogoInfo(companyId, platformId, clientId,pageType);
				cfg.putAll(logoTitleInfo);
				
			}
			putCfgMap(cfg, keyCode, pageCfg.getCmsCfgValueCode());
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_BANNER || cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_BANNER_LIST) {
			if (!cfg.containsKey(keyCode)) {
				cfg.put(keyCode, new ArrayList<>());
			}
			if (pageCfg.getBannerId() != null) {
				BannerVO banner = cmsPageFacade.findBannerByIdAndCompanyId(pageCfg.getBannerId(), clientId, companyId, platformId);
				if (banner != null) {
					putCfgMap(cfg, keyCode, banner);
				}
			}
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_JUMP) {
			Long linkableId = null;
			if (EmptyUtil.isNotEmpty(pageCfg.getTextValue())) {
				linkableId = Long.parseLong(pageCfg.getTextValue());
			}
			Map<String, Object> linkMap = cmsPageFacade.findLinkableButtonConfig(linkableId, clientId, companyId, platformId);
			putCfgMap(cfg, keyCode, linkMap);
		} else if (cfgKeyType == CmsConstant.CMS_CFG_KEY_TYPE_OBJECT) {
			String description = pageCfg.getCkgKeyDescription();
			if (EmptyUtil.isNotEmpty(description) && description.endsWith("list")) {
				if (!cfg.containsKey(keyCode)) {
					cfg.put(keyCode, new ArrayList<>());
				}
			}
			Map<String, Object> objCfgMap = new HashMap<>();
			putCfgMap(cfg, keyCode, objCfgMap);
			for (CmsPageCfgDTO cpc : cmsPageCfgList) {
				if (pageCfg.getId().equals(cpc.getParentId())) {
					//如果是下级属性，直接放入值map
					constructPageConfigDetail(objCfgMap, cpc, cmsPageCfgList, clientId, companyId, platformId,pageType);
				}
			}
		}
	}
	
	private void putCfgMap(Map<String, Object> cfg, String keyCode, Object value) {
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

	@Override
	public List<CmsPageDTO> findCmsPageAllByClientType(CmsPageDTO cmsPageDTO) {
		return cmsPageFacade.findCmsPageAllByClientType(cmsPageDTO);
	}

	@Override
	public int updateStatus(CmsPageDTO dto) {
		//启用时判断是否独立公司
		//启用时 属于独立公司的页面,同一客户端,相同客户端版本号,每个公司只能启用一个
		if(dto.getPageStatus() != null && dto.getPageStatus() == 0) {
			
			CmsPageDTO pageDTO = cmsPageFacade.findCmsPageCompanyByPageId(dto.getId());
			if(pageDTO != null) {
				CmsPageDTO paramDTO = new CmsPageDTO();
				paramDTO.setCompanyId(pageDTO.getCompanyId());
				paramDTO.setClientVersionACode(pageDTO.getClientVersionACode());
				paramDTO.setClientVersionICode(pageDTO.getClientVersionICode());
				paramDTO.setPageStatus(0);
				paramDTO.setTemplateType(pageDTO.getTemplateType());
				paramDTO.setPlatformId(dto.getPlatformId());
				paramDTO.setClientType(pageDTO.getClientType());
				List<CmsPageDTO> cmsPageDTOList = cmsPageFacade.findCmsPageByCompanyIdAndVersion(paramDTO);
				if(cmsPageDTOList != null && cmsPageDTOList.size() > 0) {
					throw new BusinessException("当前公司已存在启用状态的相同版本的独立页面");
				}
			}
			
		}
		return cmsPageFacade.updateStatus(dto);
	}
	
}
	