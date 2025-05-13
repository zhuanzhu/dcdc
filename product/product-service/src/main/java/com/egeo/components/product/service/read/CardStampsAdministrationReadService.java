package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CardStampsAdministrationReadService {

	public CardStampsAdministrationDTO findCardStampsAdministrationById(CardStampsAdministrationDTO dto);

	public PageResult<CardStampsAdministrationDTO> findCardStampsAdministrationOfPage(CardStampsAdministrationDTO dto,Pagination page);

	public List<CardStampsAdministrationDTO> findCardStampsAdministrationAll(CardStampsAdministrationDTO dto);
	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<CardStampsAdministrationDTO> findPage(CardStampsAdministrationDTO dto, Pagination page);
}
	