package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.FrozenPuDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FrozenPuReadService {

	public FrozenPuDTO findFrozenPuById(FrozenPuDTO dto);

	public PageResult<FrozenPuDTO> findFrozenPuOfPage(FrozenPuDTO dto,Pagination page);

	public List<FrozenPuDTO> findFrozenPuAll(FrozenPuDTO dto);
}
	