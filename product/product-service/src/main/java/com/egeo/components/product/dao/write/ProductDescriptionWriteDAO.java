package com.egeo.components.product.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.ProductDescriptionPO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface ProductDescriptionWriteDAO extends BaseWriteDAO<ProductDescriptionPO> {

    int deleteByProductId(@Param("productId")Long productId);

    void saveProductDescriptionList(@Param("poList")List<ProductDescriptionPO> productDescriptionPOList);

    void updateProductDescriptionSWithTx(@Param("po")ProductDescriptionPO productDescriptionPO);
}
	