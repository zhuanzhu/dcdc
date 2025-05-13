package com.egeo.components.product.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface ProductUnitWriteDAO extends BaseWriteDAO<ProductUnitPO> {
	/**
	 * 根据suid修改草稿PU状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updateProductUnitStatusByStandardUnitIdWithTx(@Param("standardUnitId")Long standardUnitId, @Param("status")Integer status);
	/**
	 * 根据skuid同步下架pu草稿信息
	 * @param skuId
	 * @return
	 */
	int updateStatusBySkuId(@Param("skuId")Long skuId);
	/**
	 * 根据skuid同步失效sku下面的所有草稿pu
	 * @param id
	 * @return
	 */
	int updatePUIsVendibleBySkuIdWithTx(@Param("skuId")Long skuId);

    void saveProductUnit(@Param("poList")List<ProductUnitPO> productUnitPOList);

    void updateProductUnitPrice(@Param("poList") List<ProductUnitPO> productUnitPricePOList);

    void updateJdProductPriceByRate(@Param("competingCompanyRate")Integer competingCompanyRate,
									@Param("democompanysCompanyRate")Integer democompanysCompanyRate,
									@Param("standardCompanyRate")Integer standardCompanyRate);

    void updateProductUnitList(@Param("poList")List<ProductUnitPO> productUnitPOList);
}
	