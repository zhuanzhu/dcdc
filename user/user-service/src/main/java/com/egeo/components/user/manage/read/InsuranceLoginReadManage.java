package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.InsuranceLoginPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InsuranceLoginReadManage {

	public InsuranceLoginPO findInsuranceLoginById(InsuranceLoginPO po);

	public PageResult<InsuranceLoginPO> findInsuranceLoginOfPage(InsuranceLoginPO po,Pagination page);

	public List<InsuranceLoginPO> findInsuranceLoginAll(InsuranceLoginPO po);

	/**
	 * 查询用户的第三方保险登陆信息
	 * @param userId
	 * @return
	 */
	public InsuranceLoginPO queryInsuranceLoginByUserId(Long userId);
}
	