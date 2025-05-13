package com.egeo.components.pay.service.read;


import java.util.List;

import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ThirdpartyAwaitQueueReadService {

	public ThirdpartyAwaitQueueDTO findThirdpartyAwaitQueueById(ThirdpartyAwaitQueueDTO dto);

	public PageResult<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueOfPage(ThirdpartyAwaitQueueDTO dto,Pagination page);

	public List<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueueDTO dto);
}
	