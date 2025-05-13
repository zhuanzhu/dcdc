package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.ProductCausePO;
import com.egeo.orm.BaseReadDAO;

public interface ProductCauseReadDAO extends BaseReadDAO<ProductCausePO>{

    List<ProductCausePO> findByProductId(@Param("productId")Long productId);
}
	