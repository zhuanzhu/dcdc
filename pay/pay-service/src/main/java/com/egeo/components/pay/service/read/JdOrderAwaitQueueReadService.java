package com.egeo.components.pay.service.read;


import java.util.List;

import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface JdOrderAwaitQueueReadService {

	public JdOrderAwaitQueueDTO findJdOrderAwaitQueueById(JdOrderAwaitQueueDTO dto);

	public PageResult<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueOfPage(JdOrderAwaitQueueDTO dto,Pagination page);

	public List<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueAll(JdOrderAwaitQueueDTO dto);
}
	