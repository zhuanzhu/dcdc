package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.client.CmsPageTabClient;
import com.egeo.components.cms.client.LinkableButtonClient;
import com.egeo.components.cms.client.LinkableParamClient;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.user.business.CompanyManage;
import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserPlatformDTO;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.facade.CompanyFacade;
import com.egeo.components.user.service.read.CompanyCoreReadService;
import com.egeo.components.user.service.read.CompanyPageReadService;
import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.components.user.service.read.CompanyUserDisabledReadService;
import com.egeo.components.user.service.read.UserReadService;
import com.egeo.components.user.service.write.CompanyEditRecordWriteService;
import com.egeo.components.user.service.write.CompanyUserDisabledWriteService;
import com.egeo.components.user.service.write.CompanyWriteService;
import com.egeo.components.user.service.write.UserExtendWriteService;
import com.egeo.components.user.service.write.UserPlatformWriteService;
import com.egeo.components.user.service.write.UserRoleWriteService;
import com.egeo.components.user.service.write.UserWriteService;
import com.egeo.components.user.vo.CompanySimpleVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("company")
public class CompanyManageImpl implements CompanyManage{

	private static final Logger logger = LoggerFactory.getLogger(CompanyManageImpl.class);

	@Autowired
	private CompanyReadService companyReadService;

	@Autowired
	private CompanyWriteService companyWriteService;
	@Autowired
	private SaltClient saltWriteService;
	@Autowired
	private CompanyAccountClient companyAccountWriteService;
	@Autowired
	private CompanyAccountClient companyAccountReadService;
	@Autowired
	private UserReadService userReadService;
	@Autowired
	private UserPlatformWriteService userPlatformWriteService;
	@Autowired
	CompanyFacade companyFacade;
	@Autowired
	UserRoleWriteService userRoleWriteService;
	@Autowired
	private UserAccountClient userAccountReadService;
	@Autowired
	private UserAccountClient userAccountWriteService;
	@Autowired
	private CompanyEditRecordWriteService companyEditRecordWriteService;
	
	@Autowired
	private CompanyPageReadService companyPageReadService;
	
	@Autowired
	private UserWriteService userWriteService;
	@Autowired
	UserExtendWriteService userExtendWriteService;
	@Autowired
	private CompanyCoreReadService companyCoreReadService;

	@Autowired
	private CompanyUserDisabledReadService companyUserDisabledReadService;

	@Autowired
	private CompanyUserDisabledWriteService companyUserDisabledWriteService;
	@Autowired
	private LinkableButtonClient linkableButtonWriteService;
	@Autowired
	private LinkableButtonClient linkableButtonReadService;
	@Autowired
	private LinkableButtonClient linkableButtonPageWriteService;
	@Autowired
	private LinkableButtonClient linkableButtonPageReadService;
	
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
	

	@Override
	public Map<String, Object> findCompanyById(Long id) {
		Map<String, Object> map = new HashMap<>();
		CompanyDTO companyDTO = companyReadService.findCompanyById(id);
		map.put("companyId", companyDTO.getId());
		map.put("companyName", companyDTO.getCompanyName());
		map.put("companyLogo", companyDTO.getCompanyLogo());
		map.put("backgrondImg", companyDTO.getBackgrondImg());
		map.put("companyContent", companyDTO.getCompanyContent());
		map.put("status", companyDTO.getStatus());
		map.put("registrationStatus", companyDTO.getRegistrationStatus());
		map.put("isTest", companyDTO.getIsTest());
		map.put("companyType", companyDTO.getCompanyType());
		UserExtendCondition user = userReadService.findCompanyAdmin(id);
		if(user!=null) {
			map.put("mail", user.getMail());
		}
		
		//logo跳转配置
		List<Map<String, Object>> linkableButtonList = findLinkableButtonByCompanyId(companyDTO);
		map.put("linkJson", linkableButtonList);
		//根据公司id查询公司页面配置信息
		List<CompanyPageDTO> companyPageList = companyFacade.findCompanyPageByCompanyId(id);
		map.put("companyPageList", companyPageList);
		//根据公司ID查询移动tab页配置
		List<Map<String, Object>> cmsPageTabList = companyFacade.findCmsPageTabByCompanyId(id);
		map.put("cmsPageTabList", cmsPageTabList);
		return map;
	}
	public List<Map<String, Object>> findLinkableButtonByCompanyId(CompanyDTO companyDTO) {
		List<Map<String, Object>> linkableButtonDTOList = new ArrayList<>();
		
		if(companyDTO.getCompanyLinkableId() != null) {
			Map<String, Object> map = new HashMap<>();
			LinkableButtonDTO lb = companyFacade.queryLinkableButtonByLinkableId(companyDTO.getCompanyLinkableId());
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
			LinkableButtonDTO lb = companyFacade.queryLinkableButtonByLinkableId(companyDTO.getPlatformLinkableId());
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
	@Override
	public PageResult<CompanyDTO> findCompanyOfPage(CompanyDTO dto, Pagination page,List<Long> companyIdList) {
		return companyFacade.findCompanyOfPage(dto, page,companyIdList);
	}

	@Override
	public List<CompanyDTO> findCompanyAll(CompanyDTO dto) {
		return companyFacade.findCompanyAll(dto);
	}

	@Override
	public Long insertCompanyWithTx(CompanyDTO dto,List<CompanyPageDTO> companyPageList,String linkJson,String cmsPageTabList) {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCompanyName(dto.getCompanyName());
		List<CompanyDTO> list = companyFacade.findCompanyAll(companyDTO);
		if(EmptyUtil.isNotEmpty(list)){
			throw new BusinessException("公司名称已经存在");
		}
		return companyFacade.insertCompanyWithTx(dto,companyPageList,linkJson,cmsPageTabList);
	}

	@Override
	public Long insertCompanyAndAdminWithTx(CompanyDTO dto,UserDTO userDto,List<CompanyPageDTO> companyPageList,String linkJson,String cmsPageTabList) {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCompanyName(dto.getCompanyName());
		List<CompanyDTO> list = companyFacade.findCompanyAll(companyDTO);
		if(EmptyUtil.isNotEmpty(list)){
			throw new BusinessException("公司名称已经存在");
		}
		Long companyId = companyFacade.insertCompanyWithTx(dto,companyPageList,linkJson,cmsPageTabList);
		userDto.setEnterpriseId(dto.getEnterpriseId());
		userDto.setCompanyId(companyId);
		userDto.setPlatformId(7l);
		Long userId = userWriteService.insertUserWithTx(userDto);
		UserRoleDTO userRolePo = new UserRoleDTO();
		userRolePo.setRoleId(10037l);
		userRolePo.setUserId(userId);
		userRolePo.setCreateTime(new Date());
		userRoleWriteService.saveWithTx(userRolePo);
		UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
		userPlatformDTO.setIsAvailable(1);
		userPlatformDTO.setUserId(userId);
		userPlatformDTO.setPlatformId(7l);
		userPlatformDTO.setCreateTime(new Date());
		userPlatformWriteService.saveWithTx(userPlatformDTO);
		UserExtendDTO userExtendDTO = new UserExtendDTO(); 
		userExtendDTO.setId(userId);
		userExtendDTO.setStatus(1);
		userExtendDTO.setAccountStatus(0);
		userExtendDTO.setIsAdministrator(1);
		userExtendDTO.setIsDeleted(0);
		userExtendDTO.setMemberCode("companyAdmin-"+companyId+"-"+userId);
		userExtendWriteService.saveWithTx(userExtendDTO);
		
		return companyId;
	}
	@Override
	public int updateCompanyWithTx(CompanyDTO dto,CacheUser userCache,List<CompanyPageDTO> companyPageList,String linkJson,String cmsPageTabList) {
		int i = 0;
		if(EmptyUtil.isEmpty(dto.getId())){
			insertCompanyWithTx(dto,companyPageList,linkJson,cmsPageTabList);
		}else{
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyName(dto.getCompanyName());
			List<CompanyDTO> list = companyFacade.findCompanyAll(companyDTO);
			if(EmptyUtil.isNotEmpty(list)){
				if(!dto.getId().equals(list.get(0).getId())){
					throw new BusinessException("公司名称已经存在");
				}
				
			}
			i = companyFacade.updateCompanyWithTx(dto,userCache,companyPageList,linkJson,cmsPageTabList);
		}
		return i;
	}

	@Override
	public int deleteCompanyWithTx(CompanyDTO dto) {
		return companyFacade.deleteCompanyWithTx(dto);
	}

	@Override
	public JsonResult<Map<String,Object>> simpleCompanyList(Long platformId) {
		List<CompanyDTO> cs=companyFacade.queryCompanysByPlatformId(platformId);
		List<CompanySimpleVO> vos=new ArrayList<>();
		for(CompanyDTO c:cs){
			CompanySimpleVO vo=new CompanySimpleVO();
			vo.setId(c.getId());
			vo.setName(c.getCompanyName());
			vos.add(vo);
		}
		Map<String,Object> data=new HashMap<>();
		data.put("companyList", vos);
		return JsonResult.success(data);
	}


	@Override
	public Integer companyInvalid(CompanyDTO companyDTO,Integer validType) {

		return companyFacade.companyInvalid(companyDTO,validType);
	}

	@Override
	public List<CompanyDTO> findCompanyIdOrName(CompanyDTO dto) {
		return companyFacade.findCompanyIdOrName( dto);
	}

	@Override
	public Integer companyValid(Integer recoverUser,CompanyDTO companyDTO) {
		
		return companyFacade.companyValid(recoverUser,companyDTO);
	}

	@Override
	public CompanyDTO queryCompanyByName(String companyName) {
		return companyFacade.queryCompanyByName(companyName);
	}


}
	