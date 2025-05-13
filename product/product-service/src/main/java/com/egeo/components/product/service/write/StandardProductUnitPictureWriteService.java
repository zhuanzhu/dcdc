package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitPictureDTO;

import java.util.List;


public interface StandardProductUnitPictureWriteService {

	public Long insertStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto);

	public int updateStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto);

	public int deleteStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto);
	/**
	 * 根据spuid删除spu图片关系
	 * @param id
	 * @return
	 */
	public int deleteByStandardProductUnitIdWithTx(Long id);

    void saveStandardProductUnitPicture(List<Long> spuIdList, List<Long> pictureIdList);
}
	