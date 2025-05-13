package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.BuyCardItemRefundReadService;
import com.egeo.components.promotion.service.write.BuyCardItemRefundWriteService;
import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class BuyCardItemRefundFacade {

	@Autowired
	private BuyCardItemRefundReadService buyCardItemRefundReadService;

	@Autowired
	private BuyCardItemRefundWriteService buyCardItemRefundWriteService;


	public BuyCardItemRefundDTO findBuyCardItemRefundById(BuyCardItemRefundDTO dto){

		return buyCardItemRefundReadService.findBuyCardItemRefundById(dto);
	}

	public PageResult<BuyCardItemRefundDTO> findBuyCardItemRefundOfPage(BuyCardItemRefundDTO dto,Pagination page){

		return buyCardItemRefundReadService.findBuyCardItemRefundOfPage(dto, page);

	}

	public List<BuyCardItemRefundDTO> findBuyCardItemRefundAll(BuyCardItemRefundDTO dto){

		return buyCardItemRefundReadService.findBuyCardItemRefundAll(dto);

	}

	public Long insertBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto){

		return buyCardItemRefundWriteService.insertBuyCardItemRefundWithTx(dto);
	}

	public int updateBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto){

		return buyCardItemRefundWriteService.updateBuyCardItemRefundWithTx(dto);
	}

	public int deleteBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto){

		return buyCardItemRefundWriteService.deleteBuyCardItemRefundWithTx(dto);
	}

	public List<BuyCardItemRefundDTO> findBuyCardItemRefundAll(Long soId){
		BuyCardItemRefundDTO buyCardItemRefundQueryDTO = new BuyCardItemRefundDTO();
		buyCardItemRefundQueryDTO.setSoId(soId);
		return buyCardItemRefundReadService.findBuyCardItemRefundAll(buyCardItemRefundQueryDTO);
	}



}
