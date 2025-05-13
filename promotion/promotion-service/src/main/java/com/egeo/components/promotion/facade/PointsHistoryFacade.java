package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PointsHistoryReadService;
import com.egeo.components.promotion.service.write.PointsHistoryWriteService;
import com.egeo.components.promotion.dto.PointsHistoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class PointsHistoryFacade {
	
	@Autowired
	private PointsHistoryReadService pointsHistoryReadService;
	
	@Autowired
	private PointsHistoryWriteService pointsHistoryWriteService;
	
	
	public PointsHistoryDTO findPointsHistoryById(PointsHistoryDTO dto){
		
		return pointsHistoryReadService.findPointsHistoryById(dto);
	}

	public PageResult<PointsHistoryDTO> findPointsHistoryOfPage(PointsHistoryDTO dto,Pagination page){
		
		return pointsHistoryReadService.findPointsHistoryOfPage(dto, page);
		
	}

	public List<PointsHistoryDTO> findPointsHistoryAll(PointsHistoryDTO dto){
		
		return pointsHistoryReadService.findPointsHistoryAll(dto);
		
	}

	public Long insertPointsHistoryWithTx(PointsHistoryDTO dto){
		
		return pointsHistoryWriteService.insertPointsHistoryWithTx(dto);
	}

	public int updatePointsHistoryWithTx(PointsHistoryDTO dto){
		
		return pointsHistoryWriteService.updatePointsHistoryWithTx(dto);
	}

	public int deletePointsHistoryWithTx(PointsHistoryDTO dto){
		
		return pointsHistoryWriteService.deletePointsHistoryWithTx(dto);
		
	}

}
	