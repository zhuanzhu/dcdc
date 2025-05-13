package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitAttNameManage {

	public StandardProductUnitAttNameDTO findStandardProductUnitAttNameById(StandardProductUnitAttNameDTO dto);	

	public PageResult<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameOfPage(StandardProductUnitAttNameDTO dto,Pagination page);

	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(StandardProductUnitAttNameDTO dto);

	Long insertStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto);

	int updateStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto);

	int deleteStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto);
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> findByStandardUnitId(Long standardUnitId);
}
	