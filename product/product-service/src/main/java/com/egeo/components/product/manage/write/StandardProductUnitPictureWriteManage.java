package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitPicturePO;

import java.util.List;


public interface StandardProductUnitPictureWriteManage {

	Long insertStandardProductUnitPictureWithTx(StandardProductUnitPicturePO po);

	int updateStandardProductUnitPictureWithTx(StandardProductUnitPicturePO po);

	int deleteStandardProductUnitPictureWithTx(StandardProductUnitPicturePO po);
	/**
	 * 根据spuid删除spu图片关系
	 * @param id
	 * @return
	 */
	int deleteByStandardProductUnitId(Long id);

    void saveStandardProductUnitPicture(List<StandardProductUnitPicturePO> standardProductUnitPicturePOList);
}
	