package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.ProductPictureDTO;

import java.util.List;


public interface ProductPictureWriteService {

	public Long insertProductPictureWithTx(ProductPictureDTO dto);

	public int updateProductPictureWithTx(ProductPictureDTO dto);

	public int deleteProductPictureWithTx(ProductPictureDTO dto);
	/**
	 * 根据产品id删除产品与图片的关系
	 * @param id
	 * @return
	 */
	public int deleteByProductIdWithTx(Long id);

    void saveProductPicture(List<Long> productIdList, List<Long> pictureIdList);
}
	