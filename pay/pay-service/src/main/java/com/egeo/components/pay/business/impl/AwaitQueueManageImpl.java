package com.egeo.components.pay.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.pay.business.AwaitQueueManage;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.facade.AwaitQueueFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("awaitQueue")
public class AwaitQueueManageImpl implements AwaitQueueManage{

	
	@Resource(name="awaitQueueFacade")
	private AwaitQueueFacade awaitQueueFacade;

	@Override
	public AwaitQueueDTO findAwaitQueueById(AwaitQueueDTO dto) {
		return awaitQueueFacade.findAwaitQueueById(dto);
	}

	@Override
	public PageResult<AwaitQueueDTO> findAwaitQueueOfPage(AwaitQueueDTO dto, Pagination page) {
		return awaitQueueFacade.findAwaitQueueOfPage(dto, page);
	}

	@Override
	public List<AwaitQueueDTO> findAwaitQueueAll(AwaitQueueDTO dto) {
		return awaitQueueFacade.findAwaitQueueAll(dto);
	}

	@Override
	public Long insertAwaitQueueWithTx(AwaitQueueDTO dto) {
		return awaitQueueFacade.insertAwaitQueueWithTx(dto);
	}

	@Override
	public int updateAwaitQueueWithTx(AwaitQueueDTO dto) {
		return awaitQueueFacade.updateAwaitQueueWithTx(dto);
	}

	@Override
	public int deleteAwaitQueueWithTx(AwaitQueueDTO dto) {
		return awaitQueueFacade.deleteAwaitQueueWithTx(dto);
	}


}
	