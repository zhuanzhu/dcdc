package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.TrialApplyPO;


public interface TrialApplyWriteManage {

	Long insertTrialApplyWithTx(TrialApplyPO po);

	int updateTrialApplyWithTx(TrialApplyPO po);

	int deleteTrialApplyWithTx(TrialApplyPO po);
}
	