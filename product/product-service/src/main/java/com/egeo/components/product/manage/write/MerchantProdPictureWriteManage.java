package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.MerchantProdPicturePO;


public interface MerchantProdPictureWriteManage {

	Long insertMerchantProdPictureWithTx(MerchantProdPicturePO po);

	int updateMerchantProdPictureWithTx(MerchantProdPicturePO po);

	int deleteMerchantProdPictureWithTx(MerchantProdPicturePO po);
	/**
	 * 根据su草稿图片关系id集合批量删除su草稿图片关系
	 * @param merchantProdPictureIds
	 * @return
	 */
	int deleteBymerchantProdPictureIds(List<Long> merchantProdPictureIds);
	/**
	 * 根据su商品id删除su商品图片信息
	 */
	int delByMerchantProductId(Long merchantProductId);

    void saveMerchantProdPicture(List<MerchantProdPicturePO> merchantProdPicturePOList);
}
	