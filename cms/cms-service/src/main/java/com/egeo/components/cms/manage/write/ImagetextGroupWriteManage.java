package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ImagetextGroupPO;


public interface ImagetextGroupWriteManage {

	Long insertImagetextGroupWithTx(ImagetextGroupPO po);

	int updateImagetextGroupWithTx(ImagetextGroupPO po);

	int deleteImagetextGroupWithTx(ImagetextGroupPO po);
}
	