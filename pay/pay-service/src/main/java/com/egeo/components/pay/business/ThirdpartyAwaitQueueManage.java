package com.egeo.components.pay.business;

import java.util.List;

import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ThirdpartyAwaitQueueManage {

	public ThirdpartyAwaitQueueDTO findThirdpartyAwaitQueueById(ThirdpartyAwaitQueueDTO dto);	

	public PageResult<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueOfPage(ThirdpartyAwaitQueueDTO dto,Pagination page);

	public List<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueueDTO dto);

	Long insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto);

	int updateThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto);

	int deleteThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto);
}
	