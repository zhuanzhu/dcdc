package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitPicturePO;

import java.util.List;


public interface StandardUnitPictureWriteManage {

	Long insertStandardUnitPictureWithTx(StandardUnitPicturePO po);

	int updateStandardUnitPictureWithTx(StandardUnitPicturePO po);

	int deleteStandardUnitPictureWithTx(StandardUnitPicturePO po);
	/**
	 * 根据suid删除su图片关系表
	 * @param merchantProdId
	 * @return
	 */
	int deleteByStandardUnitIdWithTx(Long merchantProdId);
	/**
	 * 根据suid全部删除
	 * @param merchantProductId
	 * @return
	 */
	int delByStandardUnitId(Long merchantProductId);

    void saveStandardUnitPicture(List<StandardUnitPicturePO> standardUnitPicturePOList);
}
	