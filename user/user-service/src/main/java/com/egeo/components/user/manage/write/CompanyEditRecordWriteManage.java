package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.CompanyEditRecordPO;


public interface CompanyEditRecordWriteManage {

	Long insertCompanyEditRecordWithTx(CompanyEditRecordPO po);

	int updateCompanyEditRecordWithTx(CompanyEditRecordPO po);

	int deleteCompanyEditRecordWithTx(CompanyEditRecordPO po);
}
	