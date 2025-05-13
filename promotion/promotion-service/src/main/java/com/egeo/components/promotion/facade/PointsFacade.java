package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PointsReadService;
import com.egeo.components.promotion.service.write.PointsWriteService;
import com.egeo.components.promotion.dto.PointsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class PointsFacade {
	
	@Autowired
	private PointsReadService pointsReadService;
	
	@Autowired
	private PointsWriteService pointsWriteService;
	
	
	public PointsDTO findPointsById(PointsDTO dto){
		
		return pointsReadService.findPointsById(dto);
	}

	public PageResult<PointsDTO> findPointsOfPage(PointsDTO dto,Pagination page){
		
		return pointsReadService.findPointsOfPage(dto, page);
		
	}

	public List<PointsDTO> findPointsAll(PointsDTO dto){
		
		return pointsReadService.findPointsAll(dto);
		
	}

	public Long insertPointsWithTx(PointsDTO dto){
		
		return pointsWriteService.insertPointsWithTx(dto);
	}

	public int updatePointsWithTx(PointsDTO dto){
		
		return pointsWriteService.updatePointsWithTx(dto);
	}

	public int deletePointsWithTx(PointsDTO dto){
		
		return pointsWriteService.deletePointsWithTx(dto);
		
	}

}
	