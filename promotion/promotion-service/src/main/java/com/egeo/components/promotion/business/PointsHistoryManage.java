package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.PointsHistoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PointsHistoryManage {

	public PointsHistoryDTO findPointsHistoryById(PointsHistoryDTO dto);	

	public PageResult<PointsHistoryDTO> findPointsHistoryOfPage(PointsHistoryDTO dto,Pagination page);

	public List<PointsHistoryDTO> findPointsHistoryAll(PointsHistoryDTO dto);

	Long insertPointsHistoryWithTx(PointsHistoryDTO dto);

	int updatePointsHistoryWithTx(PointsHistoryDTO dto);

	int deletePointsHistoryWithTx(PointsHistoryDTO dto);
}
	