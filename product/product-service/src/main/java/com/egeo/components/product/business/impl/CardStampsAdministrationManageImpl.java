package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CardStampsAdministrationManage;
import com.egeo.components.product.facade.CardStampsAdministrationFacade;
import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cardStampsAdministration")
public class CardStampsAdministrationManageImpl implements CardStampsAdministrationManage{

	
	@Resource(name="cardStampsAdministrationFacade")
	private CardStampsAdministrationFacade cardStampsAdministrationFacade;

	@Override
	public CardStampsAdministrationDTO findCardStampsAdministrationById(CardStampsAdministrationDTO dto) {
		return cardStampsAdministrationFacade.findCardStampsAdministrationById(dto);
	}

	@Override
	public PageResult<CardStampsAdministrationDTO> findCardStampsAdministrationOfPage(CardStampsAdministrationDTO dto, Pagination page) {
		return cardStampsAdministrationFacade.findCardStampsAdministrationOfPage(dto, page);
	}

	@Override
	public List<CardStampsAdministrationDTO> findCardStampsAdministrationAll(CardStampsAdministrationDTO dto) {
		return cardStampsAdministrationFacade.findCardStampsAdministrationAll(dto);
	}

	@Override
	public Long insertCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto) {
		return cardStampsAdministrationFacade.insertCardStampsAdministrationWithTx(dto);
	}

	@Override
	public int updateCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto) {
		return cardStampsAdministrationFacade.updateCardStampsAdministrationWithTx(dto);
	}

	@Override
	public int deleteCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto) {
		return cardStampsAdministrationFacade.deleteCardStampsAdministrationWithTx(dto);
	}
	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> findPage(CardStampsAdministrationDTO dto, Pagination page) {
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		List<Map<String, Object>> maps = new ArrayList<>();
		PageResult<CardStampsAdministrationDTO> result = cardStampsAdministrationFacade.findPage(dto, page);
		List<CardStampsAdministrationDTO> list = result.getList();
		for (CardStampsAdministrationDTO cardStampsAdministrationDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", cardStampsAdministrationDTO.getId());
			map.put("name", cardStampsAdministrationDTO.getName());
			map.put("sortValue", cardStampsAdministrationDTO.getSortValue());
			map.put("isShow", cardStampsAdministrationDTO.getIsShow());
			map.put("standardUnitName", cardStampsAdministrationDTO.getStandardUnitName());
			map.put("leadingEndCategoryName", cardStampsAdministrationDTO.getLeadingEndCategoryName());
			maps.add(map);
		}
		pageResult.setList(maps);
		pageResult.setPageNo(result.getPageNo());
		pageResult.setPageSize(result.getPageSize());
		pageResult.setTotalSize(result.getTotalSize());
		return pageResult;
	}


}
	