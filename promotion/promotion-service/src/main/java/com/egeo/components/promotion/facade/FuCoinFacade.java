package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.FuCoinReadService;
import com.egeo.components.promotion.service.write.FuCoinWriteService;
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FuCoinFacade {
	
	@Autowired
	private FuCoinReadService fuCoinReadService;
	
	@Autowired
	private FuCoinWriteService fuCoinWriteService;
	
	
	public FuCoinDTO findFuCoinById(FuCoinDTO dto){
		
		return fuCoinReadService.findFuCoinById(dto);
	}

	public PageResult<FuCoinDTO> findFuCoinOfPage(FuCoinDTO dto,Pagination page){
		
		return fuCoinReadService.findFuCoinOfPage(dto, page);
		
	}

	public List<FuCoinDTO> findFuCoinAll(FuCoinDTO dto){
		
		return fuCoinReadService.findFuCoinAll(dto);
		
	}

	public Long insertFuCoinWithTx(FuCoinDTO dto){
		
		return fuCoinWriteService.insertFuCoinWithTx(dto);
	}

	public int updateFuCoinWithTx(FuCoinDTO dto){
		
		return fuCoinWriteService.updateFuCoinWithTx(dto);
	}

	public int deleteFuCoinWithTx(FuCoinDTO dto){
		
		return fuCoinWriteService.deleteFuCoinWithTx(dto);
		
	}

}
	