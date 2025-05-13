package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PointsHistoryManage;
import com.egeo.components.promotion.facade.PointsHistoryFacade;
import com.egeo.components.promotion.dto.PointsHistoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("pointsHistory")
public class PointsHistoryManageImpl implements PointsHistoryManage{

	
	@Resource(name="pointsHistoryFacade")
	private PointsHistoryFacade pointsHistoryFacade;

	@Override
	public PointsHistoryDTO findPointsHistoryById(PointsHistoryDTO dto) {
		return pointsHistoryFacade.findPointsHistoryById(dto);
	}

	@Override
	public PageResult<PointsHistoryDTO> findPointsHistoryOfPage(PointsHistoryDTO dto, Pagination page) {
		return pointsHistoryFacade.findPointsHistoryOfPage(dto, page);
	}

	@Override
	public List<PointsHistoryDTO> findPointsHistoryAll(PointsHistoryDTO dto) {
		return pointsHistoryFacade.findPointsHistoryAll(dto);
	}

	@Override
	public Long insertPointsHistoryWithTx(PointsHistoryDTO dto) {
		return pointsHistoryFacade.insertPointsHistoryWithTx(dto);
	}

	@Override
	public int updatePointsHistoryWithTx(PointsHistoryDTO dto) {
		return pointsHistoryFacade.updatePointsHistoryWithTx(dto);
	}

	@Override
	public int deletePointsHistoryWithTx(PointsHistoryDTO dto) {
		return pointsHistoryFacade.deletePointsHistoryWithTx(dto);
	}


}
	