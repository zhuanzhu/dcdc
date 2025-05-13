package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.PicturePO;


public interface PictureWriteManage {

	Long insertPictureWithTx(PicturePO po);

	int updatePictureWithTx(PicturePO po);

	int deletePictureWithTx(PicturePO po);
}
	