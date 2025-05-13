package com.egeo.components.finance.service.write;

import com.egeo.components.finance.dto.CompanyAccountDTO;


public interface CompanyAccountWriteService {

	public Long insertCompanyAccountWithTx(CompanyAccountDTO dto);

	public int updateCompanyAccountWithTx(CompanyAccountDTO dto);

	public int deleteCompanyAccountWithTx(CompanyAccountDTO dto);

	/**
	 * 更改公司账户有效性
	 * @param id
	 * @param disabled
	 * @return
	 */
	public int updateAccountDisable(Long id, Integer disabled);

}
	