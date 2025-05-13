package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.CompanyPageDTO;


@FeignClient(name = "service-user-fgj",contextId="CompanyCoreClient")
public interface CompanyCoreClient {

	/**
	 * 根据公司id查询公司类型
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = { "/client/user/companyCore/findCompanyTypeById" }, method = { RequestMethod.POST }) 
	public Integer findCompanyTypeById(Long companyId);
	
	/**
	 * 根据用户id查询公司类型
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = { "/client/user/companyCore/findCompanyTypeByUserId" }, method = { RequestMethod.POST }) 
	public Integer findCompanyTypeByUserId(Long userId);
	
	/**
	 * 根据公司id查询公司类型对应的所有公司id
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = { "/client/user/companyCore/findCompanyAllIdByCompanyId" }, method = { RequestMethod.POST }) 
	public Long findCompanyAllIdByCompanyId(Long companyId);


	@RequestMapping(value = { "/client/user/companyCore/findEnterpriseCompanyAllId" }, method = { RequestMethod.POST })
	public Long findEnterpriseCompanyAllId(Long companyId);
	/**
	 * 根据公司类型查询公司类型对应的所有公司id
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = { "/client/user/companyCore/findCompanyAllIdByCompanyType" }, method = { RequestMethod.POST })
	public Long findCompanyAllIdByCompanyType(Integer companyType);
} 
 
