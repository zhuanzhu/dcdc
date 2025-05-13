package com.egeo.components.product.service.read;


import java.util.Date;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitCombinationSuReadService {

	public StandardUnitCombinationSuDTO findStandardUnitCombinationSuById(StandardUnitCombinationSuDTO dto);

	public PageResult<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuOfPage(StandardUnitCombinationSuDTO dto,Pagination page);

	public List<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuAll(StandardUnitCombinationSuDTO dto);
	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	public PageResult<StandardUnitCombinationSuDTO> findByStandardUnitCombinationIdOfPage(Long standardUnitCombinationId,Pagination page);
	/**
	 * 根据su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSize(Long standardUnitCombinationId);

	List<StandardUnitCombinationSuDTO> syncJdSellState(Integer source, Date endCheckTime, int size);

}
	