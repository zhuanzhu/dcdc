package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.ECardTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ECardTempReadService {

	public ECardTempDTO findECardTempById(ECardTempDTO dto);

	public PageResult<ECardTempDTO> findECardTempOfPage(ECardTempDTO dto,Pagination page);

	public List<ECardTempDTO> findECardTempAll(ECardTempDTO dto);
}
	