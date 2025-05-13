package com.egeo.components.product.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.ProductAttValuePO;
import com.egeo.orm.BaseReadDAO;

public interface ProductAttValueReadDAO extends BaseReadDAO<ProductAttValuePO>{
	/**
	 * 根据spu属性id查询属性值信息
	 * @param id
	 * @return
	 */
	int findProductAttValueSum(@Param("productAttNameId")Long productAttNameId);
}
	