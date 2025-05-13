package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CardSaltReadService {

	public CardSaltDTO findCardSaltById(CardSaltDTO dto);

	public PageResult<CardSaltDTO> findCardSaltOfPage(CardSaltDTO dto,Pagination page);

	public List<CardSaltDTO> findCardSaltAll(CardSaltDTO dto);

	/**
	 * 根据uuid查询卡券盐
	 * @param uuid
	 * @return
	 */
	public CardSaltDTO queryCardSaltByUUID(String uuid);
}
	