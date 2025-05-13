package com.egeo.components.finance.service.write;

import java.util.List;

import com.egeo.components.finance.dto.AdjustReasonDTO;


public interface AdjustReasonWriteService {

	public Long insertAdjustReasonWithTx(AdjustReasonDTO dto);

	public int updateAdjustReasonWithTx(AdjustReasonDTO dto);

	public int deleteAdjustReasonWithTx(AdjustReasonDTO dto);

	/**
	 * 新建原因,并建立与公司的关系
	 * @param dto
	 * @param cIds 
	 * @param cIds
	 * @return
	 */
	public Long createAdjustReason(AdjustReasonDTO dto, List<Long> cIds);

	/**
	 * 更改原因,并更改与公司的关系
	 * @param dto
	 * @param cIds 
	 * @param cIds
	 * @return
	 */
	public int editAdjustReason(AdjustReasonDTO dto, List<Long> cIds);
}
	