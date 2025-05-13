package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitManage {

	public Map<String, Object> findStandardProductUnitById(StandardProductUnitDTO dto,Boolean quick);	

	public PageResult<StandardProductUnitDTO> findStandardProductUnitOfPage(StandardProductUnitDTO dto,Pagination page);

	public List<StandardProductUnitDTO> findStandardProductUnitAll(StandardProductUnitDTO dto);

	Long insertStandardProductUnitWithTx(StandardProductUnitDTO dto);

	int updateStandardProductUnitWithTx(StandardProductUnitDTO dto);

	int deleteStandardProductUnitWithTx(StandardProductUnitDTO dto);
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	public Map<String, Object> queryStandardProductUnitById(Long standardProductUnitId);
	/**
	 * 根据suId查询spu模版id
	 * @param standardUnitId
	 * @return
	 */
	public Map<String, Object> findCommodityTemplateIdByStandardUnitId(Long standardUnitId);

    List<StandardProductUnitDTO> conditionStandardProductUnitAll(StandardProductUnitDTO dto);

}
	