package com.egeo.components.promotion.facade;

import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.service.read.CardUseRecordReadService;
import com.egeo.components.promotion.service.write.CardUseRecordWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public class CardUseRecordFacade {

	@Autowired
	private CardUseRecordReadService cardUseRecordReadService;

	@Autowired
	private CardUseRecordWriteService cardUseRecordWriteService;


	public CardUseRecordDTO findCardUseRecordById(CardUseRecordDTO dto){

		return cardUseRecordReadService.findCardUseRecordById(dto);
	}

	public PageResult<CardUseRecordDTO> findCardUseRecordOfPage(CardUseRecordDTO dto,Pagination page){

		return cardUseRecordReadService.findCardUseRecordOfPage(dto, page);

	}

	public List<CardUseRecordDTO> findCardUseRecordAll(CardUseRecordDTO dto){

		return cardUseRecordReadService.findCardUseRecordAll(dto);

	}

	public Long insertCardUseRecordWithTx(CardUseRecordDTO dto){

		return cardUseRecordWriteService.insertCardUseRecordWithTx(dto);
	}

	public int updateCardUseRecordWithTx(CardUseRecordDTO dto){

		return cardUseRecordWriteService.updateCardUseRecordWithTx(dto);
	}

	public int deleteCardUseRecordWithTx(CardUseRecordDTO dto){

		return cardUseRecordWriteService.deleteCardUseRecordWithTx(dto);

	}

	/**设置卡记录过期**/
	public Boolean cancelUserCard(){

		return cardUseRecordWriteService.cancelUserCard();
	}
}
