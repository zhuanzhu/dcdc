package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.PointsDTO;


public interface PointsWriteService {

	public Long insertPointsWithTx(PointsDTO dto);

	public int updatePointsWithTx(PointsDTO dto);

	public int deletePointsWithTx(PointsDTO dto);
}
	