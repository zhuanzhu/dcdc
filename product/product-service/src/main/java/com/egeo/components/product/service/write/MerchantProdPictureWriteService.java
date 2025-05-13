package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.MerchantProdPictureDTO;


public interface MerchantProdPictureWriteService {

	public Long insertMerchantProdPictureWithTx(MerchantProdPictureDTO dto);

	public int updateMerchantProdPictureWithTx(MerchantProdPictureDTO dto);

	public int deleteMerchantProdPictureWithTx(MerchantProdPictureDTO dto);
	/**
	 * 根据su草稿图片关系id集合批量删除su草稿图片关系
	 * @param merchantProdPictureIds
	 * @return
	 */
	public int deleteBymerchantProdPictureIdsWithTx(List<Long> merchantProdPictureIds);
	/**
	 * 根据su商品id删除su商品图片信息
	 * @param merchantProductId
	 * @return
	 */
	public int delByMerchantProductId(Long merchantProductId);

    void saveMerchantProdPicture(List<Long> merchantProductIdList, List<Long> merchantPictureIdList);
}
	