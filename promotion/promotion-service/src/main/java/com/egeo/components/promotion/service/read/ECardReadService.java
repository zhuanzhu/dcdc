package com.egeo.components.promotion.service.read;


import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ECardReadService {

	public ECardDTO findECardById(ECardDTO dto);

	public PageResult<ECardDTO> findECardOfPage(ECardDTO dto,Pagination page);

	public List<ECardDTO> findECardAll(ECardDTO dto);

	public List<ECardDTO> queryECardListByKey(Map<String, Object> keys);


}
	