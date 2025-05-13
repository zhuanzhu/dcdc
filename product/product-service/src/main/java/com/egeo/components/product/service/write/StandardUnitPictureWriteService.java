package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitPictureDTO;

import java.util.List;


public interface StandardUnitPictureWriteService {

	public Long insertStandardUnitPictureWithTx(StandardUnitPictureDTO dto);

	public int updateStandardUnitPictureWithTx(StandardUnitPictureDTO dto);

	public int deleteStandardUnitPictureWithTx(StandardUnitPictureDTO dto);
	/**
	 * 根据suid删除su图片关系表
	 * @param merchantProdId
	 * @return
	 */
	public int deleteByStandardUnitIdWithTx(Long merchantProdId);

    void saveStandardUnitPicture(List<Long> suIdList, List<Long> merchantPictureIdList);
}
	