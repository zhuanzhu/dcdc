package com.egeo.components.finance.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserExtendPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
@Component
public class UserFacade {
	
	@Autowired
	private CompanyClient companyReadService;
	
	@Autowired
	private UserExtendClient uerService;


	/**
	 * 查询所有公司
	 * @return
	 */
	public List<CompanyDTO> queryCompanyList(Long platformId) {
		return companyReadService.queryCompanysByPlatformId(platformId);
	}

	/**
	 * 分页查询用户列表
	 * @param page
	 * @param condition
	 * @return
	 */
	public PageResult<UserExtendDTO> userPage(Pagination page, UserExtendDTO condition) {
		return uerService.findProductUser(new UserExtendPageDTO(page,condition));
	}

	/**
	 * 查询单个公司
	 * @param companyId
	 * @return
	 */
	public CompanyDTO queryCompanyById(Long companyId) {
		return companyReadService.findCompanyById(companyId);
	}

	/**
	 * 根据邮箱查询用户
	 * @param email
	 * @return
	 */
	public UserExtendDTO queryUserExtendByEmail(String email) {
		
		return uerService.queryUserExtendsByEmail(email);
	}

	/**
	 * 批量查询公司
	 * @param companyIds
	 * @return
	 */
	public List<CompanyDTO> queryCompaniesByIds(List<Long> companyIds) {
		
		return companyReadService.queryCompaniesByIds(com.egeo.utils.StringUtils.longsToStrings(companyIds));
	}

	/**
	 * 关联表查询用户信息
	 * @param page
	 * @param condition
	 * @return
	 */
	public PageResult<UserExtendDTO> queryFullUserExtendPage(Pagination page, UserExtendDTO condition,Long userCompanyId,Long platformId) {
		if (userCompanyId != null) {
			CompanyDTO companyById = companyReadService.findCompanyById(userCompanyId);
			Integer isManagementCompany = companyById.getIsManagementCompany();
			if (isManagementCompany == null) {
				condition.setCompanyId(userCompanyId);
			}else {
				if ((platformId == 7L && isManagementCompany != 1L) || (platformId == 2L && isManagementCompany != 2L)) {
					condition.setCompanyId(userCompanyId);
				}
			}
		}
		return uerService.queryFullUserExtendPage(new UserExtendPageDTO(page,condition));
	}

	/**
	 * 根据id查询userExtend
	 * @param userId
	 * @return
	 */
	public UserExtendDTO queryFullUserExtendById(Long userId) {
		return uerService.userByUserId(userId);
	}


	public List<UserExtendDTO> queryFullUserExtend(List<Long> userIdList) {
		return uerService.queryFullUserExtend(com.egeo.utils.StringUtils.longsToStrings(userIdList));
	}
	
}
