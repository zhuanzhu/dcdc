package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CardStampsAdministrationReadService;
import com.egeo.components.product.service.write.CardStampsAdministrationWriteService;
import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CardStampsAdministrationFacade {
	
	@Resource
	private CardStampsAdministrationReadService cardStampsAdministrationReadService;
	
	@Resource
	private CardStampsAdministrationWriteService cardStampsAdministrationWriteService;
	
	
	public CardStampsAdministrationDTO findCardStampsAdministrationById(CardStampsAdministrationDTO dto){
		
		return cardStampsAdministrationReadService.findCardStampsAdministrationById(dto);
	}

	public PageResult<CardStampsAdministrationDTO> findCardStampsAdministrationOfPage(CardStampsAdministrationDTO dto,Pagination page){
		
		return cardStampsAdministrationReadService.findCardStampsAdministrationOfPage(dto, page);
		
	}

	public List<CardStampsAdministrationDTO> findCardStampsAdministrationAll(CardStampsAdministrationDTO dto){
		
		return cardStampsAdministrationReadService.findCardStampsAdministrationAll(dto);
		
	}

	public Long insertCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto){
		
		return cardStampsAdministrationWriteService.insertCardStampsAdministrationWithTx(dto);
	}

	public int updateCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto){
		
		return cardStampsAdministrationWriteService.updateCardStampsAdministrationWithTx(dto);
	}

	public int deleteCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto){
		
		return cardStampsAdministrationWriteService.deleteCardStampsAdministrationWithTx(dto);
		
	}
	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<CardStampsAdministrationDTO> findPage(CardStampsAdministrationDTO dto, Pagination page) {
		// TODO Auto-generated method stub
		return cardStampsAdministrationReadService.findPage(dto, page);
	}

}
	