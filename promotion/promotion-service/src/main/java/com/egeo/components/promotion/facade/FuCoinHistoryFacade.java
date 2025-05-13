package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.FuCoinHistoryReadService;
import com.egeo.components.promotion.service.write.FuCoinHistoryWriteService;
import com.egeo.components.promotion.dto.FuCoinHistoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FuCoinHistoryFacade {
	
	@Autowired
	private FuCoinHistoryReadService fuCoinHistoryReadService;
	
	@Autowired
	private FuCoinHistoryWriteService fuCoinHistoryWriteService;
	
	
	public FuCoinHistoryDTO findFuCoinHistoryById(FuCoinHistoryDTO dto){
		
		return fuCoinHistoryReadService.findFuCoinHistoryById(dto);
	}

	public PageResult<FuCoinHistoryDTO> findFuCoinHistoryOfPage(FuCoinHistoryDTO dto,Pagination page){
		
		return fuCoinHistoryReadService.findFuCoinHistoryOfPage(dto, page);
		
	}

	public List<FuCoinHistoryDTO> findFuCoinHistoryAll(FuCoinHistoryDTO dto){
		
		return fuCoinHistoryReadService.findFuCoinHistoryAll(dto);
		
	}

	public Long insertFuCoinHistoryWithTx(FuCoinHistoryDTO dto){
		
		return fuCoinHistoryWriteService.insertFuCoinHistoryWithTx(dto);
	}

	public int updateFuCoinHistoryWithTx(FuCoinHistoryDTO dto){
		
		return fuCoinHistoryWriteService.updateFuCoinHistoryWithTx(dto);
	}

	public int deleteFuCoinHistoryWithTx(FuCoinHistoryDTO dto){
		
		return fuCoinHistoryWriteService.deleteFuCoinHistoryWithTx(dto);
		
	}

}
	