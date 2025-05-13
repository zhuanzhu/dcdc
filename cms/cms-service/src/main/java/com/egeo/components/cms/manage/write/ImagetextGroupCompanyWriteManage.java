package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ImagetextGroupCompanyPO;


public interface ImagetextGroupCompanyWriteManage {

	Long insertImagetextGroupCompanyWithTx(ImagetextGroupCompanyPO po);

	int updateImagetextGroupCompanyWithTx(ImagetextGroupCompanyPO po);

	int deleteImagetextGroupCompanyWithTx(ImagetextGroupCompanyPO po);
}
	