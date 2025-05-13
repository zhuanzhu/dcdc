package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.CompanyCallCenterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface CompanyCallCenterManage {

	public PageResult<CompanyCallCenterDTO> findCompanyCallCenterOfPage(CompanyCallCenterDTO dto,Pagination page);

	public List<CompanyCallCenterDTO> findCompanyCallCenterAll(CompanyCallCenterDTO dto);

	Long insertCompanyCallCenterWithTx(CompanyCallCenterDTO dto);

	int updateCompanyCallCenterWithTx(CompanyCallCenterDTO dto);

	int deleteCompanyCallCenterWithTx(CompanyCallCenterDTO dto);

	/**
	 * 查询默认客服信息
	 * @param companyId
	 * @return
	 */
	public JsonResult<Map<String, Object>> defaultInfo(Long companyId);
}
	