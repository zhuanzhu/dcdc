package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ProductUnitReadService {

	public ProductUnitDTO findProductUnitById(ProductUnitDTO dto);

	public PageResult<ProductUnitDTO> findProductUnitOfPage(ProductUnitDTO dto,Pagination page);

	public List<ProductUnitDTO> findProductUnitAll(ProductUnitDTO dto);
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	public List<Long> attValueByProductUnitId(Long id);

    Long findLastId();
}
	