package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface StandardUnitCombinationManage {

	public Map<String, Object> findStandardUnitCombinationById(StandardUnitCombinationDTO dto);	

	public PageResult<Map<String, Object>> findStandardUnitCombinationOfPage(StandardUnitCombinationDTO dto,Pagination page,List<Long> standardUnitCombinationIdList);

	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAll(StandardUnitCombinationDTO dto);

	Long insertStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto);

	int updateStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto);

	int deleteStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto);
	/**
	 * 查询所有su分组信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> queryStandardUnitCombinationAll(StandardUnitCombinationDTO dto);

	/**
	 * 模糊查询所有商品组合
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> findStandardUnitCombinationAllByBlurry(StandardUnitCombinationDTO dto);

    PageResult<Map<String, Object>> findSucTitleOfPage(StandardUnitCombinationDTO dto, Pagination page);
}
	