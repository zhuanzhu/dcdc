package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CardStampsAdministrationReadService;
import com.egeo.components.product.manage.read.CardStampsAdministrationReadManage;
import com.egeo.components.product.condition.CardStampsAdministrationCondition;
import com.egeo.components.product.converter.CardStampsAdministrationConverter;
import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.components.product.po.CardStampsAdministrationPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cardStampsAdministrationReadService")
public class CardStampsAdministrationReadServiceImpl  implements CardStampsAdministrationReadService {
	@Autowired
	private CardStampsAdministrationReadManage cardStampsAdministrationReadManage;

	@Override
	public CardStampsAdministrationDTO findCardStampsAdministrationById(CardStampsAdministrationDTO dto) {
		CardStampsAdministrationPO po = CardStampsAdministrationConverter.toPO(dto);
		CardStampsAdministrationPO list = cardStampsAdministrationReadManage.findCardStampsAdministrationById(po);		
		return CardStampsAdministrationConverter.toDTO(list);
	}

	@Override
	public PageResult<CardStampsAdministrationDTO> findCardStampsAdministrationOfPage(CardStampsAdministrationDTO dto, Pagination page) {
		CardStampsAdministrationPO po = CardStampsAdministrationConverter.toPO(dto);
        PageResult<CardStampsAdministrationPO> pageResult = cardStampsAdministrationReadManage.findCardStampsAdministrationOfPage(po, page);
        
        List<CardStampsAdministrationDTO> list = CardStampsAdministrationConverter.toDTO(pageResult.getList());
        PageResult<CardStampsAdministrationDTO> result = new PageResult<CardStampsAdministrationDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CardStampsAdministrationDTO> findCardStampsAdministrationAll(CardStampsAdministrationDTO dto) {
		CardStampsAdministrationPO po = CardStampsAdministrationConverter.toPO(dto);
		List<CardStampsAdministrationPO> list = cardStampsAdministrationReadManage.findCardStampsAdministrationAll(po);		
		return CardStampsAdministrationConverter.toDTO(list);
	}
	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<CardStampsAdministrationDTO> findPage(CardStampsAdministrationDTO dto, Pagination page) {
		List<CardStampsAdministrationDTO> list = new ArrayList<>();
		CardStampsAdministrationPO po = CardStampsAdministrationConverter.toPO(dto);
        PageResult<CardStampsAdministrationCondition> pageResult = cardStampsAdministrationReadManage.findPage(po, page);
        List<CardStampsAdministrationCondition> cardStampsAdministrationConditionList = pageResult.getList();
        for (CardStampsAdministrationCondition cardStampsAdministrationCondition : cardStampsAdministrationConditionList) {
        	CardStampsAdministrationDTO cardStampsAdministrationDTO = CardStampsAdministrationConverter.toDTO(cardStampsAdministrationCondition);
        	cardStampsAdministrationDTO.setStandardUnitName(cardStampsAdministrationCondition.getStandardUnitName());
        	cardStampsAdministrationDTO.setLeadingEndCategoryName(cardStampsAdministrationCondition.getLeadingEndCategoryName());
        	list.add(cardStampsAdministrationDTO);
		}
        
        PageResult<CardStampsAdministrationDTO> result = new PageResult<CardStampsAdministrationDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
}
	