package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProductUnitReadManage {

	public ProductUnitPO findProductUnitById(ProductUnitPO po);

	public PageResult<ProductUnitPO> findProductUnitOfPage(ProductUnitPO po,Pagination page);

	public List<ProductUnitPO> findProductUnitAll(ProductUnitPO po);
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	public List<Long> attValueByProductUnitId(Long productUnitId);

    Long findLastId();
}
	