package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.InsuranceLoginDTO;


public interface InsuranceLoginWriteService {

	public Long insertInsuranceLoginWithTx(InsuranceLoginDTO dto);

	public int updateInsuranceLoginWithTx(InsuranceLoginDTO dto);

	public int deleteInsuranceLoginWithTx(InsuranceLoginDTO dto);

	/**
	 * 保险登陆更新用户信息
	 * @param il
	 * @return
	 */
	public int insuranceLogin(InsuranceLoginDTO il);

}
	