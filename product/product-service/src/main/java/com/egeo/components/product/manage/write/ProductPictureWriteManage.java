package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ProductPicturePO;

import java.util.List;


public interface ProductPictureWriteManage {

	Long insertProductPictureWithTx(ProductPicturePO po);

	int updateProductPictureWithTx(ProductPicturePO po);

	int deleteProductPictureWithTx(ProductPicturePO po);
	/**
	 * 根据产品id删除产品与图片的关系
	 */
	int deleteByProductIdWithTx(Long id);

    void saveProductPicture(List<ProductPicturePO> productPicturePOList);
}
	