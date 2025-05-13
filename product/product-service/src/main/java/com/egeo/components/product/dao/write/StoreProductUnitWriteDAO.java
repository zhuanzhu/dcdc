package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StoreProductUnitPO;
import com.egeo.orm.BaseWriteDAO;

public interface StoreProductUnitWriteDAO extends BaseWriteDAO<StoreProductUnitPO> {

	int insertAllWithTx(@Param("list")List<StoreProductUnitPO> list);
}
	