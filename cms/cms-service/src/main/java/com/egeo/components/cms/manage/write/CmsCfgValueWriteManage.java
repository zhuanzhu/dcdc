package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsCfgValuePO;


public interface CmsCfgValueWriteManage {

	Long insertCmsCfgValueWithTx(CmsCfgValuePO po);

	int updateCmsCfgValueWithTx(CmsCfgValuePO po);

	int deleteCmsCfgValueWithTx(CmsCfgValuePO po);
}
	