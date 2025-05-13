package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProductUnitManage {

	public ProductUnitDTO findProductUnitById(ProductUnitDTO dto);	

	public PageResult<ProductUnitDTO> findProductUnitOfPage(ProductUnitDTO dto,Pagination page);

	public List<ProductUnitDTO> findProductUnitAll(ProductUnitDTO dto);

	Long insertProductUnitWithTx(ProductUnitDTO dto);

	int updateProductUnitWithTx(ProductUnitDTO dto);

	int deleteProductUnitWithTx(ProductUnitDTO dto);
	/**
	 * 根据puid查询pu所有pu信息
	 * @return
	 */
	public Map<String, Object> findCommodityProductUnitAllByStandardUnitId(Long merchantProductId, Integer f);
}
	