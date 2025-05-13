package com.egeo.components.pay.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.dto.CompanyDTO;

@Component
public class UserFacade {
	@Autowired
	private CompanyCoreClient companyCoreReadService;
	@Autowired
	private CompanyClient companyReadService;
	/**
	 * 根据用户id查询公司类型
	 * @param userId
	 * @return
	 */
	public Integer findCompanyTypeByUserId(Long userId){
		return companyCoreReadService.findCompanyTypeByUserId(userId);
		
	}

    public CompanyDTO findCompanyById(Long companyId) {
		return companyReadService.findCompanyById(companyId);
	}
}
