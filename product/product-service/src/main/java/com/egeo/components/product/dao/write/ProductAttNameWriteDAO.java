package com.egeo.components.product.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.ProductAttNamePO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface ProductAttNameWriteDAO extends BaseWriteDAO<ProductAttNamePO> {

    int deleteByProductId(@Param("productId")Long productId);
    /**
     * 根据spuId和属性id设为启用spu图片
     * @param po
     * @return
     */
	int updateByProductIdAndAttNameId(ProductAttNamePO po);

    void saveProductAttName(@Param("poList")List<ProductAttNamePO> productAttNamePOList);
}
	