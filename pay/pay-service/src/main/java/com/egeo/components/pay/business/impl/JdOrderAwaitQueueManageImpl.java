package com.egeo.components.pay.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.pay.business.JdOrderAwaitQueueManage;
import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.facade.JdOrderAwaitQueueFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdOrderAwaitQueue")
public class JdOrderAwaitQueueManageImpl implements JdOrderAwaitQueueManage{

	
	@Resource(name="jdOrderAwaitQueueFacade")
	private JdOrderAwaitQueueFacade jdOrderAwaitQueueFacade;

	@Override
	public JdOrderAwaitQueueDTO findJdOrderAwaitQueueById(JdOrderAwaitQueueDTO dto) {
		return jdOrderAwaitQueueFacade.findJdOrderAwaitQueueById(dto);
	}

	@Override
	public PageResult<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueOfPage(JdOrderAwaitQueueDTO dto, Pagination page) {
		return jdOrderAwaitQueueFacade.findJdOrderAwaitQueueOfPage(dto, page);
	}

	@Override
	public List<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueAll(JdOrderAwaitQueueDTO dto) {
		return jdOrderAwaitQueueFacade.findJdOrderAwaitQueueAll(dto);
	}

	@Override
	public Long insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto) {
		return jdOrderAwaitQueueFacade.insertJdOrderAwaitQueueWithTx(dto);
	}

	@Override
	public int updateJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto) {
		return jdOrderAwaitQueueFacade.updateJdOrderAwaitQueueWithTx(dto);
	}

	@Override
	public int deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto) {
		return jdOrderAwaitQueueFacade.deleteJdOrderAwaitQueueWithTx(dto);
	}


}
	