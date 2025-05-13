package com.egeo.components.user.service.read.impl;

import com.egeo.config.RuntimeContext;
import com.egeo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.common.BusinessException;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.service.read.CompanyCoreReadService;
import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.utils.EmptyUtil;

import java.util.Objects;


@Service("companyCoreReadService")
public class CompanyCoreReadServiceImpl  implements CompanyCoreReadService {

	@Autowired
	private CompanyReadService companyReadService;

	@Override
	public Integer findCompanyTypeById(Long companyId) {
		// 根据公司id查询公司信息
		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		if (EmptyUtil.isEmpty(companyDTO))
			throw new BusinessException("公司信息异常，公司编号:" + companyId);
		if (EmptyUtil.isEmpty(companyDTO.getIsTest())) {
			throw new BusinessException("公司类型异常，公司编号:" + companyId);
		}
		// 公司类型 0:正式公司 1:测试公司 2:竞品公司
		return companyDTO.getCompanyType();
	}

	@Override
	public Integer findCompanyTypeByUserId(Long userId) {
		CompanyDTO companyDTO = companyReadService.queryCompanyByUserId(userId);
		if (EmptyUtil.isEmpty(companyDTO))
			throw new BusinessException("公司信息异常");
		// 公司类型 0:正式公司 1:测试公司 2:竞品公司
		return companyDTO.getCompanyType();
	}

	@Override
	public Long findCompanyAllIdByCompanyId(Long companyId) {
		Integer companyType = this.findCompanyTypeById(companyId);
		Long companyAllId = -1L;
		// 公司类型：0:正式公司(默认) 1:演示公司 2:竞品公司
		switch (companyType) {
			case 0:
				companyAllId = -1L;
				break;
			case 1:
				companyAllId = -3L;
				break;
			case 2:
				companyAllId = -2L;
				break;

			default:
				throw new BusinessException("未定义公司类型");
		}
		return companyAllId;
	}

	@Override
	public Long findEnterpriseCompanyAllId(Long companyId) {
		Integer companyType = this.findCompanyTypeById(companyId);
		return findCompanyAllIdByCompanyType(companyType);
	}

	@Override
	public Long findCompanyAllIdByCompanyType(Integer companyType){
		if(Objects.isNull(RuntimeContext.cacheUser())) {
			throw new BusinessException("用户未登录");
		}
		String enterpriseId=Objects.isNull(RuntimeContext.cacheUser().getEnterpriseId())?
				"":String.valueOf(RuntimeContext.cacheUser().getEnterpriseId());
		Long companyAllId = -1L;
		// 公司类型：0:正式公司(默认) 1:演示公司 2:竞品公司
		switch (companyType) {
			case 0:
				companyAllId = StringUtils.parseLong(-1+enterpriseId);
				break;
			case 1:
				companyAllId = StringUtils.parseLong(-3+enterpriseId);
				break;
			case 2:
				companyAllId = StringUtils.parseLong(-2+enterpriseId);
				break;

			default:
				throw new BusinessException("未定义公司类型");
		}
		return companyAllId;
	}

}

	