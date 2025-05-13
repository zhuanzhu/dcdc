package com.egeo.components.product.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Component
public class UserFacade {


	@Autowired
	private CompanyClient companyReadService;
	/**
	 *	根据公司id查询公司类型
	 * @param companyId
	 * @return
	 */
	public Integer findCompanyTypeById(Long companyId){
		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		if (EmptyUtil.isEmpty(companyDTO))
			throw new BusinessException("公司信息异常，公司编号:" + companyId);
		if (EmptyUtil.isEmpty(companyDTO.getIsTest())) {
			throw new BusinessException("公司类型异常，公司编号:" + companyId);
		}
		return companyDTO.getCompanyType();
	}

}
