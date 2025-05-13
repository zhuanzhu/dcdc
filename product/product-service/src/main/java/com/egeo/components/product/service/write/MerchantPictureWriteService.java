package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantPictureDTO;

import java.util.List;


public interface MerchantPictureWriteService {

	public Long insertMerchantPictureWithTx(MerchantPictureDTO dto);

	public int updateMerchantPictureWithTx(MerchantPictureDTO dto);

	public int deleteMerchantPictureWithTx(MerchantPictureDTO dto);

    void saveMerchantPicture(List<Long> merchantPictureIdList, List<Long> pictureIdList);
}
	