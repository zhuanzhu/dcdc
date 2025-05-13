package com.egeo.components.pay.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.pay.business.ThirdpartyAwaitQueueManage;
import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.facade.ThirdpartyAwaitQueueFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("thirdpartyAwaitQueue")
public class ThirdpartyAwaitQueueManageImpl implements ThirdpartyAwaitQueueManage{

	
	@Resource(name="thirdpartyAwaitQueueFacade")
	private ThirdpartyAwaitQueueFacade thirdpartyAwaitQueueFacade;

	@Override
	public ThirdpartyAwaitQueueDTO findThirdpartyAwaitQueueById(ThirdpartyAwaitQueueDTO dto) {
		return thirdpartyAwaitQueueFacade.findThirdpartyAwaitQueueById(dto);
	}

	@Override
	public PageResult<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueOfPage(ThirdpartyAwaitQueueDTO dto, Pagination page) {
		return thirdpartyAwaitQueueFacade.findThirdpartyAwaitQueueOfPage(dto, page);
	}

	@Override
	public List<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueueDTO dto) {
		return thirdpartyAwaitQueueFacade.findThirdpartyAwaitQueueAll(dto);
	}

	@Override
	public Long insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto) {
		return thirdpartyAwaitQueueFacade.insertThirdpartyAwaitQueueWithTx(dto);
	}

	@Override
	public int updateThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto) {
		return thirdpartyAwaitQueueFacade.updateThirdpartyAwaitQueueWithTx(dto);
	}

	@Override
	public int deleteThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto) {
		return thirdpartyAwaitQueueFacade.deleteThirdpartyAwaitQueueWithTx(dto);
	}


}
	