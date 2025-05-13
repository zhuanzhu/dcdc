package com.egeo.components.finance.manage.write;

import com.egeo.components.finance.po.ReasonCompanyPO;


public interface ReasonCompanyWriteManage {

	Long insertReasonCompanyWithTx(ReasonCompanyPO po);

	int updateReasonCompanyWithTx(ReasonCompanyPO po);

	int deleteReasonCompanyWithTx(ReasonCompanyPO po);
}
	