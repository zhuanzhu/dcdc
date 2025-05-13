package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.PointsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PointsManage {

	public PointsDTO findPointsById(PointsDTO dto);	

	public PageResult<PointsDTO> findPointsOfPage(PointsDTO dto,Pagination page);

	public List<PointsDTO> findPointsAll(PointsDTO dto);

	Long insertPointsWithTx(PointsDTO dto);

	int updatePointsWithTx(PointsDTO dto);

	int deletePointsWithTx(PointsDTO dto);
}
	