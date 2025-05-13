package com.egeo.components.product.business;

import java.util.Map;

import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitStoreManage {

	public StandardUnitStoreDTO findStandardUnitStoreById(StandardUnitStoreDTO dto);	

	public PageResult<Map<String, Object>> findStandardUnitStoreOfPage(StandardUnitStoreDTO dto,Pagination page);

	public Map<String, Object> findStandardUnitStoreAll(StandardUnitStoreDTO dto);

	Long insertStandardUnitStoreWithTx(StandardUnitStoreDTO dto);

	int updateStandardUnitStoreWithTx(StandardUnitStoreDTO dto);

	int deleteStandardUnitStoreWithTx(StandardUnitStoreDTO dto);
	/**
	 * 根据商品id查询所有门店（包含id和名称）
	 * @param dto
	 * @return
	 */
	public Map<String, Object> standardUnitStoreByStandardUnitId(StandardUnitStoreDTO dto);
}
	