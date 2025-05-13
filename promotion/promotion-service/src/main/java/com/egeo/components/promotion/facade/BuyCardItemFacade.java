package com.egeo.components.promotion.facade;

import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.components.promotion.service.read.BuyCardItemReadService;
import com.egeo.components.promotion.service.write.BuyCardItemWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BuyCardItemFacade {

	@Autowired
	private BuyCardItemReadService buyCardItemReadService;

	@Autowired
	private BuyCardItemWriteService buyCardItemWriteService;


	public BuyCardItemDTO findBuyCardItemById(BuyCardItemDTO dto){

		return buyCardItemReadService.findBuyCardItemById(dto);
	}

	public PageResult<BuyCardItemDTO> findBuyCardItemOfPage(BuyCardItemDTO dto,Pagination page){

		return buyCardItemReadService.findBuyCardItemOfPage(dto, page);

	}

	public List<BuyCardItemDTO> findBuyCardItemAll(BuyCardItemDTO dto){

		return buyCardItemReadService.findBuyCardItemAll(dto);

	}

	public Long insertBuyCardItemWithTx(BuyCardItemDTO dto){

		return buyCardItemWriteService.insertBuyCardItemWithTx(dto);
	}

	public int updateBuyCardItemWithTx(BuyCardItemDTO dto){

		return buyCardItemWriteService.updateBuyCardItemWithTx(dto);
	}

	public int deleteBuyCardItemWithTx(BuyCardItemDTO dto){

		return buyCardItemWriteService.deleteBuyCardItemWithTx(dto);

	}

	public List<BuyCardItemDTO> findBuyCardItemAll(Long soId){
		BuyCardItemDTO buyCardItemQueryDTO  = new BuyCardItemDTO();
		buyCardItemQueryDTO.setSoId(soId);
		return buyCardItemReadService.findBuyCardItemAll(buyCardItemQueryDTO);

	}
}
