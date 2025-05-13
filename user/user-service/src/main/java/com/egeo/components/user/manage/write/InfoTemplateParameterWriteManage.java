package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.InfoTemplateParameterPO;


public interface InfoTemplateParameterWriteManage {

	Long insertInfoTemplateParameterWithTx(InfoTemplateParameterPO po);

	int updateInfoTemplateParameterWithTx(InfoTemplateParameterPO po);

	int deleteInfoTemplateParameterWithTx(InfoTemplateParameterPO po);
}
	