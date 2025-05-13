package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.PointsHistoryDTO;


public interface PointsHistoryWriteService {

	public Long insertPointsHistoryWithTx(PointsHistoryDTO dto);

	public int updatePointsHistoryWithTx(PointsHistoryDTO dto);

	public int deletePointsHistoryWithTx(PointsHistoryDTO dto);
}
	