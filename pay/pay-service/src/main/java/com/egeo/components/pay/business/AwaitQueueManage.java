package com.egeo.components.pay.business;

import java.util.List;

import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AwaitQueueManage {

	public AwaitQueueDTO findAwaitQueueById(AwaitQueueDTO dto);	

	public PageResult<AwaitQueueDTO> findAwaitQueueOfPage(AwaitQueueDTO dto,Pagination page);

	public List<AwaitQueueDTO> findAwaitQueueAll(AwaitQueueDTO dto);

	Long insertAwaitQueueWithTx(AwaitQueueDTO dto);

	int updateAwaitQueueWithTx(AwaitQueueDTO dto);

	int deleteAwaitQueueWithTx(AwaitQueueDTO dto);
}
	