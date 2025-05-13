package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantPicturePO;

import java.util.List;


public interface MerchantPictureWriteManage {

	Long insertMerchantPictureWithTx(MerchantPicturePO po);

	int updateMerchantPictureWithTx(MerchantPicturePO po);

	int deleteMerchantPictureWithTx(MerchantPicturePO po);

    void saveMerchantPicture(List<MerchantPicturePO> merchantPicturePOList);
}
	