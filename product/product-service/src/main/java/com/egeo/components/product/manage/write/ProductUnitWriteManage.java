package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ProductUnitPO;

import java.util.List;


public interface ProductUnitWriteManage {

	Long insertProductUnitWithTx(ProductUnitPO po);

	int updateProductUnitWithTx(ProductUnitPO po);

	int deleteProductUnitWithTx(ProductUnitPO po);
	/**
	 * 根据suid修改草稿PU状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updateProductUnitStatusByStandardUnitIdWithTx(Long id, Integer status);
	/**
	 * 根据skuid同步下架pu草稿信息
	 * @param skuId
	 * @return
	 */
	int updateStatusBySkuId(Long skuId);
	/**
	 * 根据skuid同步失效sku下面的所有草稿pu
	 * @param id
	 * @return
	 */
	int updatePUIsVendibleBySkuIdWithTx(Long skuId);

    void saveProductUnit(List<ProductUnitPO> productUnitPOList);

    void updateProductUnitPrice(List<ProductUnitPO> productUnitPricePOList);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);

    void updateProductUnitList(List<ProductUnitPO> productUnitPOList);
}
	