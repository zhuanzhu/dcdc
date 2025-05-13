package com.egeo.components.finance.service.read;


import java.util.List;

import com.egeo.components.finance.dto.AdjustReasonDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AdjustReasonReadService {

	public AdjustReasonDTO findAdjustReasonById(AdjustReasonDTO dto);

	public PageResult<AdjustReasonDTO> findAdjustReasonOfPage(AdjustReasonDTO dto,Pagination page);

	public List<AdjustReasonDTO> findAdjustReasonAll(AdjustReasonDTO dto);

	/**
	 * 查询所有调整原因
	 * @return
	 */
	public List<AdjustReasonDTO> queryAdjustReasons(Long companyId);

	/**
	 * 原因分页列表
	 * @param type
	 * @param companyId
	 * @param disabled
	 * @param page
	 * @return
	 */
	public PageResult<AdjustReasonDTO> queryAdjustReasonPage(Integer type, Long companyId, Integer disabled,
			Long platformId,Pagination page);

	/**
	 * 查询几个指定类型的原因
	 *
     * @param platformId
     * @param typeList
     * @return
	 */
	public List<AdjustReasonDTO> queryAdjustReasonsByTypes(Long platformId, List<Integer> typeList, Long accountId, Long companyId);

}
	