package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.StandardProductUnitPO;
import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.ProductAttNamePO;
import com.egeo.orm.BaseReadDAO;

public interface ProductAttNameReadDAO extends BaseReadDAO<ProductAttNamePO>{
	/**
	 * 根据spuid查询spu属性数量
	 * @param id
	 * @return
	 */
	int findSumByProductId(@Param("productId")Long productId);

    ProductAttNamePO queryIsElectronicBySpuId(@Param("po") StandardProductUnitPO po);

	ProductAttNamePO queryIsUnitBySpuId(@Param("po") StandardProductUnitPO po);

    Long findLastId();
}
	