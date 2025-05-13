package com.egeo.components.user.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.cms.client.CmsPageTabClient;
import com.egeo.components.cms.client.LinkableButtonClient;
import com.egeo.components.cms.client.LinkableParamClient;
import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyEditRecordDTO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.service.read.CompanyCoreReadService;
import com.egeo.components.user.service.read.CompanyPageReadService;
import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.components.user.service.read.CompanyUserDisabledReadService;
import com.egeo.components.user.service.read.UserReadService;
import com.egeo.components.user.service.write.CompanyEditRecordWriteService;
import com.egeo.components.user.service.write.CompanyUserDisabledWriteService;
import com.egeo.components.user.service.write.CompanyWriteService;
import com.egeo.components.user.service.write.UserWriteService;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Util;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.UUID;

@Component
public class CompanyFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyFacade.class);

	@Resource
	private CompanyReadService companyReadService;

	@Resource
	private CompanyWriteService companyWriteService;
	@Autowired
	private SaltClient saltWriteService;
	@Autowired
	private CompanyAccountClient companyAccountWriteService;
	@Autowired
	private CompanyAccountClient companyAccountReadService;
	@Resource
	private UserReadService userReadService;
	@Autowired
	private UserAccountClient userAccountReadService;
	@Autowired
	private UserAccountClient userAccountWriteService;
	@Resource
	private CompanyEditRecordWriteService companyEditRecordWriteService;
	
	@Resource
	private CompanyPageReadService companyPageReadService;
	
	@Resource
	private UserWriteService userWriteService;
	
	@Resource
	private CompanyCoreReadService companyCoreReadService;

	@Resource
	private CompanyUserDisabledReadService companyUserDisabledReadService;

	@Resource
	private CompanyUserDisabledWriteService companyUserDisabledWriteService;
	@Autowired
	private LinkableButtonClient linkableButtonWriteService;
	@Autowired
	private LinkableButtonClient linkableButtonPageWriteService;
	@Autowired
	private LinkableButtonClient linkableButtonPageReadService;
	@Autowired
	private LinkableButtonClient linkableButtonReadService;
	
	@Autowired
	private CmsPageTabClient cmsPageTabReadService;
	
	@Autowired
	private CmsPageTabClient cmsPageTabCompanyReadService;
	
	@Autowired
	private CmsPageTabClient cmsPageTabCompanyWriteService;
	
	@Autowired
	private LinkableParamClient linkableParamReadService;
	
	@Autowired
	private LinkableParamClient linkableParamWriteService;
	
	public CompanyDTO findCompanyById(Long id) {

		return companyReadService.findCompanyById(id);
	}

	public PageResult<CompanyDTO> findCompanyOfPage(CompanyDTO dto, Pagination page,List<Long> companyIdList) {

		return companyReadService.findCompanyOfPage(dto, page,companyIdList);

	}

	public List<CompanyDTO> findCompanyAll(CompanyDTO dto) {

		return companyReadService.findCompanyAll(dto);

	}

	public Long insertCompanyWithTx(CompanyDTO dto,List<CompanyPageDTO> companyPageList,String linkJson,String cmsPageTabList) {
		//保存跳转配置信息
		saveLinkableButton(dto, linkJson);
		logger.info("(保存后)dto:{}",JSON.toJSONString(dto));
		Long companyId = companyWriteService.insertCompanyWithTx(dto,companyPageList);
		
		//添加
		savePageTabCompany(companyId,cmsPageTabList,dto.getPlatformId());
		// 1.插入公司记录
		// 2.生成uuid
		String uuid = UUID.uuid();
		// 3.生成密文
		String randomSalt = SaltUtils.getRandomSalt();
		BigDecimal defaultValue=new BigDecimal("0.00");
		String ciphertext = MD5Util.MD5Salt(defaultValue.toString(), randomSalt);

		// 4.盐表插入数据
		SaltDTO saltDTO = new SaltDTO();
		saltDTO.setUuid(uuid);
		saltDTO.setSaltValue(randomSalt);
		saltWriteService.insertSaltWithTx(saltDTO);

		// 5.企业账户表插入数据
		CompanyAccountDTO companyAccountDTO = new CompanyAccountDTO();

		companyAccountDTO.setCompanyId(companyId);
		companyAccountDTO.setName(dto.getCompanyName() + "企业账户");
		companyAccountDTO.setDisabled(0);
		companyAccountDTO.setType(20);
		companyAccountDTO.setUuid(uuid);
		companyAccountDTO.setBalance(defaultValue);
		companyAccountDTO.setCiphertext(ciphertext);
		companyAccountDTO.setPlatformId(dto.getPlatformId());
		companyAccountWriteService.insertCompanyAccountWithTx(companyAccountDTO);
		
		return companyId;
	}
	
	public void savePageTabCompany(Long companyId,String cmsPageTabList,Long platformId) {
		CmsPageTabDTO dto = new CmsPageTabDTO();
		dto.setPlatformId(platformId);
		List<CmsPageTabDTO> pageTabList = cmsPageTabReadService.findCmsPageTabAll(dto);
		Map<String, Long> pageTabMap = new HashMap<>();
		for (CmsPageTabDTO cmsPageTabDTO : pageTabList) {
			pageTabMap.put(cmsPageTabDTO.getType(), cmsPageTabDTO.getId());
		}
		if(StringUtils.isNotBlank(cmsPageTabList)) {
			//编辑企业
			JSONArray array = JSON.parseArray(cmsPageTabList);
			for (int i = 0; i<array.size(); i++) {
				CmsPageTabCompanyDTO cmsPageTabCompanyDTO = new CmsPageTabCompanyDTO();
				JSONObject object = array.getJSONObject(i);
				cmsPageTabCompanyDTO.setId(object.getLong("id"));
				cmsPageTabCompanyDTO.setShowClientLogo(object.getInteger("showClientLogo"));
				cmsPageTabCompanyDTO.setShowPlatformLogo(object.getInteger("showPlatformLogo"));
				if(object.getInteger("status") != null) {
					cmsPageTabCompanyDTO.setStatus(object.getInteger("status"));
				}
				cmsPageTabCompanyWriteService.updateCmsPageTabCompanyWithTx(cmsPageTabCompanyDTO);
//				cmsPageTabCompanyWriteService.insertCmsPageTabCompanyWithTx(cmsPageTabCompanyDTO);
			}
		}else {
			//新增企业
			for (int i = 1; i<5; i++) {
				CmsPageTabCompanyDTO cmsPageTabCompanyDTO = new CmsPageTabCompanyDTO();
				cmsPageTabCompanyDTO.setClientType(6);
				cmsPageTabCompanyDTO.setCompanyId(companyId);
				cmsPageTabCompanyDTO.setPlatformId(Integer.valueOf(platformId+""));
				cmsPageTabCompanyDTO.setPageTabId(pageTabMap.get(""+i));
				cmsPageTabCompanyDTO.setShowClientLogo(1);
				cmsPageTabCompanyDTO.setShowPlatformLogo(1);
				cmsPageTabCompanyDTO.setStatus(1);
				cmsPageTabCompanyWriteService.insertCmsPageTabCompanyWithTx(cmsPageTabCompanyDTO);
			}
		}
		
	}
	
	public void saveLinkableButton(CompanyDTO dto,String linkJson){
		//保存
		if(StringUtils.isNotBlank(linkJson)) {
			JSONArray jsonArray = JSON.parseArray(linkJson);
			if(jsonArray != null && jsonArray.size() > 0) {
				for(int i = 0;i< jsonArray.size();i++) {
					JSONObject linkableButtonJson = jsonArray.getJSONObject(i);
					LinkableButtonDTO linkableButtonDTO = JSON.toJavaObject(linkableButtonJson, LinkableButtonDTO.class);
					
					if(EmptyUtil.isNotEmpty(linkableButtonDTO.getLinkType())) {
						Long linkableId = linkableButtonWriteService.insertLinkableButtonWithTx(linkableButtonDTO);
						JSONArray linkPageArray = linkableButtonJson.getJSONArray("linkableButtonPageList");
						if(linkPageArray != null && CmsConstant.CMS_LINK_TYPE_SU_LIST == linkableButtonDTO.getLinkType()) {
							List<LinkableButtonPageDTO> linkableButtonPageDTOList = JSON.parseArray(linkPageArray.toJSONString(), LinkableButtonPageDTO.class);
							if(linkableButtonPageDTOList != null && linkableButtonPageDTOList.size() > 0) {
								for(int j = 0; j < linkableButtonPageDTOList.size();j ++) {
									linkableButtonPageDTOList.get(j).setLinkableId(linkableId);
								}
								linkableButtonPageWriteService.insertBatchLinkableButtonPageWithTx(linkableButtonPageDTOList);
							}
						}
						logger.info("(保存中)logoType:{} ,linkableId:{}",linkableButtonJson.getString("logoType"),linkableId);
						String logoType = linkableButtonJson.getString("logoType");
						if("platform".equals(logoType)) {
							dto.setPlatformLinkableId(linkableId);
						}else if("company".equals(logoType)) {
							dto.setCompanyLinkableId(linkableId);
						}
						
						//保存extParam
						JSONArray extParam = linkableButtonJson.getJSONArray("extParam");
						if(extParam != null && extParam.size() > 0) {
							for (int j = 0; j < extParam.size(); j++) {
								LinkableParamDTO paramDTO = new LinkableParamDTO();
								paramDTO.setName(extParam.getJSONObject(j).getString("name"));
								paramDTO.setValue(extParam.getJSONObject(j).getString("value"));
								paramDTO.setLinkButtonId(linkableId);
								linkableParamWriteService.insertLinkableParamWithTx(paramDTO);
							}
							
						}
					}
					
				}
			}
			
		}
		
	}
	
	public void updateLinkableButton(CompanyDTO dto,String linkJson){
		
		CompanyDTO companyDTO = companyReadService.findCompanyById(dto.getId());
		
		if(companyDTO.getPlatformLinkableId() != null) {
			LinkableButtonDTO LinkableButtonDTO = new LinkableButtonDTO();
			LinkableButtonDTO.setId(companyDTO.getPlatformLinkableId());
			LinkableButtonDTO linkableButton = linkableButtonReadService.findLinkableButtonById(companyDTO.getPlatformLinkableId());
			if(linkableButton != null ) {
				linkableButtonWriteService.deleteLinkableButtonWithTx(LinkableButtonDTO);
				linkableButtonPageWriteService.deleteLinkableButtonPageByLinkableIdWithTx(new LinkableButtonPageDTO(companyDTO.getPlatformLinkableId()));
			}
			LinkableParamDTO paramDTO = new LinkableParamDTO();
			paramDTO.setLinkButtonId(companyDTO.getPlatformLinkableId());
			List<LinkableParamDTO> paramList = linkableParamReadService.findLinkableParamAll(paramDTO);
			if(paramList != null && paramList.size() > 0) {
				linkableParamWriteService.deleteByPara(paramDTO);
			}
		}
		if(companyDTO.getCompanyLinkableId() != null) {
			LinkableButtonDTO LinkableButtonDTO = new LinkableButtonDTO();
			LinkableButtonDTO.setId(companyDTO.getCompanyLinkableId());
			LinkableButtonDTO linkableButton = linkableButtonReadService.findLinkableButtonById(companyDTO.getCompanyLinkableId());
			if(linkableButton != null ) {
				linkableButtonWriteService.deleteLinkableButtonWithTx(LinkableButtonDTO);
				linkableButtonPageWriteService.deleteLinkableButtonPageByLinkableIdWithTx(new LinkableButtonPageDTO(companyDTO.getCompanyLinkableId()));
			}
			LinkableParamDTO paramDTO = new LinkableParamDTO();
			paramDTO.setLinkButtonId(companyDTO.getCompanyLinkableId());
			List<LinkableParamDTO> paramList = linkableParamReadService.findLinkableParamAll(paramDTO);
			if(paramList != null && paramList.size() > 0) {
				linkableParamWriteService.deleteByPara(paramDTO);
			}
		}
		//保存
		saveLinkableButton(dto,linkJson);
			
	}

	public int updateCompanyWithTx(CompanyDTO dto, CacheUser userCache,List<CompanyPageDTO> companyPageList,String linkJson,String cmsPageTabList) {
		
		//更新link
		updateLinkableButton(dto,linkJson);
		//更新pagetab
		updatePageTabCompany(dto.getId(),cmsPageTabList,dto.getPlatformId());
		
		int updateCompanyCount = companyWriteService.updateCompanyWithTx(dto,companyPageList);

		// 插入编辑记录
		CompanyEditRecordDTO companyEditRecordDTO = new CompanyEditRecordDTO();
		companyEditRecordDTO.setCompanyId(dto.getId());
		companyEditRecordDTO.setCompanyName(dto.getCompanyName());
		companyEditRecordDTO.setPlatformId(dto.getPlatformId());
		companyEditRecordDTO.setUserId(userCache.getId());
		companyEditRecordDTO.setUserName(userCache.getName());
		companyEditRecordWriteService.insertCompanyEditRecordWithTx(companyEditRecordDTO);

		return updateCompanyCount;
	}

	private void updatePageTabCompany(Long companyId, String cmsPageTabList, Long platformId) {
		CmsPageTabCompanyDTO dto = new CmsPageTabCompanyDTO();
		dto.setCompanyId(companyId);
//		//查询现有companyId有没有
//		List<CmsPageTabCompanyDTO> list = cmsPageTabCompanyReadService.findCmsPageTabCompanyAll(dto);
//		
//		if(!EmptyUtil.isEmpty(list)) {
//			//删除所有
//			cmsPageTabCompanyWriteService.deleteCmsPageTabCompanyWithTx(dto);
//		}
		
		savePageTabCompany(companyId, cmsPageTabList, platformId);
		
	}

	public int deleteCompanyWithTx(CompanyDTO dto) {

		return companyWriteService.deleteCompanyWithTx(dto);

	}

	/**
	 * 根据平台id查询公司列表
	 * 
	 * @param platformId
	 * @return
	 */
	public List<CompanyDTO> queryCompanysByPlatformId(Long platformId) {
		return companyReadService.queryCompanysByPlatformId(platformId);
	}

	/**
	 * 公司失效
	 * 
	 * @param companyDTO
	 * @param validType
	 * @return
	 */
	public Integer companyInvalid(CompanyDTO companyDTO, Integer validType) {
		CompanyAccountDTO companyAccountDTO = new CompanyAccountDTO();
		companyAccountDTO.setCompanyId(companyDTO.getId());
		companyAccountDTO.setType(20);
		List<CompanyAccountDTO> companyAccountList = companyAccountReadService.findCompanyAccountAll(companyAccountDTO);
		if (EmptyUtil.isNotEmpty(companyAccountList)) {
			CompanyAccountDTO dto = companyAccountList.get(0);
			// 判断企业账户是否失效
			if (dto.getDisabled() == 0) {
				// 账户现在还有效
				return 0;
			}
			if (dto.getDisabled() == 1) {
				// 公司无效操作
				CompanyDTO companyDTO2 = new CompanyDTO();
				companyDTO2.setId(companyDTO.getId());
				companyDTO2.setStatus(1);
				companyWriteService.updateCompanyWithTx(companyDTO2,new ArrayList<CompanyPageDTO>());

				// 公司员工账户失效

				// 根据公司id查询user
				List<UserDTO> userDTOList = userReadService.findUsersByCompanyId(companyDTO.getId());

				if (EmptyUtil.isNotEmpty(userDTOList)) {
					for (UserDTO userDTO : userDTOList) {
						List<UserAccountDTO> userAccountList = userAccountReadService
								.queryUserAccountByUserId(userDTO.getId());
						if (EmptyUtil.isNotEmpty(userAccountList)) {
							for (UserAccountDTO userAccountDTO : userAccountList) {
								userAccountDTO.setDisabled(1);
								userAccountWriteService.updateUserAccountWithTx(userAccountDTO);
							}
						}
						//员工失效

						// 设置当前在职员工状态为离职 , 更新离职时间
						if (userDTO.getIsAvailable() == 1) {
							// 将当前失效企业id及 从在职变为离职的员工存储起来
							CompanyUserDisabledDTO companyUserDisabledDTO = new CompanyUserDisabledDTO();
							companyUserDisabledDTO.setCompanyId(companyDTO.getId());
							companyUserDisabledDTO.setUserId(userDTO.getId());
							companyUserDisabledWriteService.insertCompanyUserDisabledWithTx(companyUserDisabledDTO);

							userWriteService.updateIsAvailableAccountStatus(userDTO.getId(),0);
						}
					}
					// 标记当前失效公司
					CompanyUserDisabledDTO companyUserDisabledDTO = new CompanyUserDisabledDTO();
					companyUserDisabledDTO.setCompanyId(companyDTO.getId());
					companyUserDisabledDTO.setUserId(-1L);
					companyUserDisabledWriteService.insertCompanyUserDisabledWithTx(companyUserDisabledDTO);
				}
				// 账户已经失效
				return 1;
			}

		}
		return 2;
	}

	public List<CompanyDTO> findCompanyIdOrName(CompanyDTO dto) {
		return companyReadService.findCompanyAll(dto);
	}

	/**
	 * 公司有效
	 * 
	 * @param companyDTO
	 * @return
	 */
	public Integer companyValid(Integer recoverUser,CompanyDTO companyDTO) {
		//企业账号设为有效
		CompanyDTO companyDTO2 = new CompanyDTO();
		companyDTO2.setId(companyDTO.getId());
		companyDTO2.setStatus(0);
		companyWriteService.updateCompanyWithTx(companyDTO2,new ArrayList<CompanyPageDTO>());

		//判断是否恢复员工
		if (recoverUser == 1) {
			//恢复员工账号
			//查询失效时状态从在职变为离职的员工 , 将状态恢复
			CompanyUserDisabledDTO companyUserDisabledDTO = new CompanyUserDisabledDTO();
			companyUserDisabledDTO.setCompanyId(companyDTO.getId());
			List<CompanyUserDisabledDTO> companyUserDisabledAll = companyUserDisabledReadService.findCompanyUserDisabledAll(companyUserDisabledDTO);
			if (EmptyUtil.isNotEmpty(companyUserDisabledAll)) {
				companyUserDisabledWriteService.updateRevalidationByCompanyId(1,companyDTO.getId());
				for (CompanyUserDisabledDTO dto : companyUserDisabledAll) {
					if (dto.getUserId() != -1) {
						userWriteService.updateIsAvailableAccountStatus(dto.getUserId(),1);
					}
				}
			}
		}
		return 0;
	}
	/**
	 * 根据公司id查询公司页面配置信息
	 * @param companyId
	 * @return
	 */
	public List<CompanyPageDTO> findCompanyPageByCompanyId(Long companyId) {
		CompanyPageDTO companyPageDTO = new CompanyPageDTO();
		companyPageDTO.setCompanyId(companyId);
		return companyPageReadService.findCompanyPageAll(companyPageDTO);
	}

	/**
	 * 根据名称精确查询公司
	 * @param companyName
	 * @return
	 */
	public CompanyDTO queryCompanyByName(String companyName) {
		
		return companyReadService.queryCompanyByName(companyName);
	}
	/**
	 * 根据用户id查询公司类型
	 *
	 * @return
	 */
	public Integer findCompanyTypeByUserId(Long userId){
		CompanyDTO companyDTO = companyReadService.queryCompanyByUserId(userId);
		if (EmptyUtil.isEmpty(companyDTO))
			throw new BusinessException("公司信息异常");
		// 公司类型 0:正式公司 1:测试公司 2:竞品公司
		return companyDTO.getCompanyType();
	}

	public List<Map<String, Object>> findLinkableButtonByCompanyId(CompanyDTO companyDTO) {
		List<Map<String, Object>> linkableButtonDTOList = new ArrayList<>();
		
		if(companyDTO.getCompanyLinkableId() != null) {
			Map<String, Object> map = new HashMap<>();
			LinkableButtonDTO lb = queryLinkableButtonByLinkableId(companyDTO.getCompanyLinkableId());
			if(lb != null) {
				map.put("linkId", lb.getLinkId());
				map.put("linkType", lb.getLinkType());
				map.put("linkParam", lb.getLinkParam());
				map.put("linkUrl", lb.getLinkUrl());
				map.put("logoType", "company");
				map.put("linkableButtonPageList", lb.getLinkableButtonPageDTOList());
				LinkableParamDTO paramDTO = new LinkableParamDTO();
				paramDTO.setLinkButtonId(lb.getId());
				List<LinkableParamDTO> list = linkableParamReadService.findLinkableParamAll(paramDTO);
				map.put("extParam", list);
				linkableButtonDTOList.add(map);
			}
		}
		if(companyDTO.getPlatformLinkableId()!= null) {
			Map<String, Object> map = new HashMap<>();
			LinkableButtonDTO lb = queryLinkableButtonByLinkableId(companyDTO.getPlatformLinkableId());
			if(lb != null) {
				map.put("linkId", lb.getLinkId());
				map.put("linkType", lb.getLinkType());
				map.put("linkParam", lb.getLinkParam());
				map.put("linkUrl", lb.getLinkUrl());
				map.put("logoType", "platform");
				map.put("linkableButtonPageList", lb.getLinkableButtonPageDTOList());
				LinkableParamDTO paramDTO = new LinkableParamDTO();
				paramDTO.setLinkButtonId(lb.getId());
				List<LinkableParamDTO> list = linkableParamReadService.findLinkableParamAll(paramDTO);
				map.put("extParam", list);
				linkableButtonDTOList.add(map);
			}
		}
		return linkableButtonDTOList;
	}

	public LinkableButtonDTO queryLinkableButtonByLinkableId(Long linkableId){
		LinkableButtonDTO lb = linkableButtonReadService.findLinkableButtonById(linkableId);
		if(lb != null) {
			//设置 su组跳转页面属性
			if(lb.getLinkType() == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
				List<LinkableButtonPageDTO> linkableButtonPageList = linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(linkableId));
				lb.setLinkableButtonPageDTOList(linkableButtonPageList);
			}
		}
		return lb;
	}

	public List<Map<String, Object>> findCmsPageTabByCompanyId(Long id) {
		List<Map<String, Object>> listMap = new ArrayList<>();
		CmsPageTabDTO dto = new CmsPageTabDTO();
		dto.setCompanyId(id);
		List<CmsPageTabDTO> pageDTOs = cmsPageTabReadService.findCmsPageTabCondition(dto);
		if(pageDTOs != null && pageDTOs.size() > 0) {
			for (CmsPageTabDTO cmsPageTabDTO : pageDTOs) {
				if(CmsConstant.CMS_PAGE_TAB_TYPE_MINE.equals(cmsPageTabDTO.getType())) {
					continue;
				}
				Map<String, Object> map = new HashMap<>();
				map.put("id", cmsPageTabDTO.getPageTabCompanyId());
				map.put("value", cmsPageTabDTO.getValue());
				map.put("status", cmsPageTabDTO.getStatus());
				map.put("showClientLogo", cmsPageTabDTO.getShowClientLogo());
				map.put("showPlatformLogo", cmsPageTabDTO.getShowPlatformLogo());
				map.put("type", cmsPageTabDTO.getType());
				listMap.add(map);
			}
		}
		
		return listMap;
	}


    public void saveCompany(CompanyDTO dto) {
		companyWriteService.updateCompanyParamWithTX(dto);
	}
}
