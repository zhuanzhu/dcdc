package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.VideoPO;


public interface VideoWriteManage {

	Long insertVideoWithTx(VideoPO po);

	int updateVideoWithTx(VideoPO po);

	int deleteVideoWithTx(VideoPO po);
}
	