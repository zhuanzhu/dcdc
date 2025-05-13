package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ImagetextPO;


public interface ImagetextWriteManage {

	Long insertImagetextWithTx(ImagetextPO po);

	int updateImagetextWithTx(ImagetextPO po);

	int deleteImagetextWithTx(ImagetextPO po);
}
	