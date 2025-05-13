package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CardStampsAdministrationManage {

	public CardStampsAdministrationDTO findCardStampsAdministrationById(CardStampsAdministrationDTO dto);	

	public PageResult<CardStampsAdministrationDTO> findCardStampsAdministrationOfPage(CardStampsAdministrationDTO dto,Pagination page);

	public List<CardStampsAdministrationDTO> findCardStampsAdministrationAll(CardStampsAdministrationDTO dto);

	Long insertCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto);

	int updateCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto);

	int deleteCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto);
	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> findPage(CardStampsAdministrationDTO dto, Pagination page);
}
	