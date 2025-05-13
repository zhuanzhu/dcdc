package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.VersionsClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.VersionsDTO;

@Component
public class UserFacade {

	@Autowired
	private VersionsClient versionsReadService;
	
	@Autowired
	private CompanyClient companyReadService;
	
	@Autowired
	private UserClient userReadService;

	/**
	 * 根据版本编号和客户端类型查询版本信息
	 * @param versionCode
	 * @param type 0:安卓 1:ios
	 * @return
	 */
	public VersionsDTO queryVersionByCodeAndType(Integer versionCode, int type,Long platformId) {
		return versionsReadService.queryVerisonByVersionCode(versionCode, type,null,platformId);
	}

	/**
	 * 根据companyId查询公司
	 * @param companyId
	 * @return
	 */
	public CompanyDTO queryCompanyById(Long companyId) {
		return companyReadService.findCompanyById(companyId);
	}
	
	/**
	 * 根据公司信息（名称模糊）查询符合的公司信息
	 * @return
	 */
	public List<CompanyDTO> queryCompanyListByName(CompanyDTO companyDTO){
		return companyReadService.findCompanyAll(companyDTO);
	}

	public List<CompanyDTO> queryCompanyListByFuzzyName(CompanyDTO companyDTO) {
		return companyReadService.findCompanyAllByFuzzyName(companyDTO);
	}

	public UserDTO findUserById(Long id) {
		if (id == null)
			return null;
		return userReadService.findUserByID(id);
	}
	
	
}
