package com.egeo.components.pay.business;

import java.util.List;

import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdOrderAwaitQueueManage {

	public JdOrderAwaitQueueDTO findJdOrderAwaitQueueById(JdOrderAwaitQueueDTO dto);	

	public PageResult<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueOfPage(JdOrderAwaitQueueDTO dto,Pagination page);

	public List<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueAll(JdOrderAwaitQueueDTO dto);

	Long insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto);

	int updateJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto);

	int deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto);
}
	