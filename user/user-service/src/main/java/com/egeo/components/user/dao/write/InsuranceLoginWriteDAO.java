package com.egeo.components.user.dao.write;

import com.egeo.components.user.po.InsuranceLoginPO;
import com.egeo.orm.BaseWriteDAO;

public interface InsuranceLoginWriteDAO extends BaseWriteDAO<InsuranceLoginPO> {

	/**
	 * 保险登陆更新用户信息
	 * @param po
	 * @return
	 */
	int insuranceLogin(InsuranceLoginPO po);
}
	