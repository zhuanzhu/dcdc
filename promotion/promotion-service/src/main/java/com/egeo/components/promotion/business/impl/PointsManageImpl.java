package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PointsManage;
import com.egeo.components.promotion.facade.PointsFacade;
import com.egeo.components.promotion.dto.PointsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("points")
public class PointsManageImpl implements PointsManage{

	
	@Resource(name="pointsFacade")
	private PointsFacade pointsFacade;

	@Override
	public PointsDTO findPointsById(PointsDTO dto) {
		return pointsFacade.findPointsById(dto);
	}

	@Override
	public PageResult<PointsDTO> findPointsOfPage(PointsDTO dto, Pagination page) {
		return pointsFacade.findPointsOfPage(dto, page);
	}

	@Override
	public List<PointsDTO> findPointsAll(PointsDTO dto) {
		return pointsFacade.findPointsAll(dto);
	}

	@Override
	public Long insertPointsWithTx(PointsDTO dto) {
		return pointsFacade.insertPointsWithTx(dto);
	}

	@Override
	public int updatePointsWithTx(PointsDTO dto) {
		return pointsFacade.updatePointsWithTx(dto);
	}

	@Override
	public int deletePointsWithTx(PointsDTO dto) {
		return pointsFacade.deletePointsWithTx(dto);
	}


}
	