package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.AttNameValuePO;
import com.egeo.components.product.po.BrandPO;
import com.egeo.components.product.po.PicturePO;
import com.egeo.components.product.po.ProductDescriptionPO;
import com.egeo.components.product.po.ProductPO;

public interface ProductWriteManage {

    Long saveProductWithTx(ProductPO po);

    String updateProductWithTx(ProductPO productPO2,ProductDescriptionPO productDescriptionPO, PicturePO picturePO ,BrandPO brandPO,List<AttNameValuePO> attNameValuePOList,Long showProductAttNameId);

    String deleteProductWithTx(ProductPO po);

    Long auditWithTx(ProductPO po);

    int updateWithTx(ProductPO po);
    /**
	 * 保存spu草稿信息
	 * @param id
	 * @return
	 */
	Long insertProductWithTx(ProductPO po, ProductDescriptionPO po2);
	/**
	 * 设置是否启用
	 * @param productVO
	 * @return
	 */
	int updateAvailableWithTx(ProductPO po);
	/**
	 * 删除所有点击添加规格属性没有点下一步所创建的空spu草稿信息
	 * @return
	 */
	int delByNullProductWithTx();

	int synchronizationStandardProductUnitWithTx(Long productId, Long userId, String userName, String ip,
			String mac);
	
	List<Long> assembleSku(Long productId,String standardProductUnitName,String productSerialNumber);

    void saveProductList(List<ProductPO> productPOList);

    void updateProductList(List<ProductPO> productPOList);

	void cleanLink(ProductPO po);

	void updateProductSkuWithTx(Long standardProductUnitId);
}
	