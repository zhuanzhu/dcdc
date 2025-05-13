package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.orm.BaseReadDAO;

public interface ProductUnitReadDAO extends BaseReadDAO<ProductUnitPO>{
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	List<Long> attValueByProductUnitId(@Param("productUnitId")Long productUnitId);

    Long findLastId();
}
	