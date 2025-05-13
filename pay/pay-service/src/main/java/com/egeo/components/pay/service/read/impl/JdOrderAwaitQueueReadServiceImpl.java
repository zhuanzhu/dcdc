package com.egeo.components.pay.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.JdOrderAwaitQueueConverter;
import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.manage.read.JdOrderAwaitQueueReadManage;
import com.egeo.components.pay.po.JdOrderAwaitQueuePO;
import com.egeo.components.pay.service.read.JdOrderAwaitQueueReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdOrderAwaitQueueReadService")
public class JdOrderAwaitQueueReadServiceImpl  implements JdOrderAwaitQueueReadService {
	@Autowired
	private JdOrderAwaitQueueReadManage jdOrderAwaitQueueReadManage;

	@Override
	public JdOrderAwaitQueueDTO findJdOrderAwaitQueueById(JdOrderAwaitQueueDTO dto) {
		JdOrderAwaitQueuePO po = JdOrderAwaitQueueConverter.toPO(dto);
		JdOrderAwaitQueuePO list = jdOrderAwaitQueueReadManage.findJdOrderAwaitQueueById(po);		
		return JdOrderAwaitQueueConverter.toDTO(list);
	}

	@Override
	public PageResult<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueOfPage(JdOrderAwaitQueueDTO dto, Pagination page) {
		JdOrderAwaitQueuePO po = JdOrderAwaitQueueConverter.toPO(dto);
        PageResult<JdOrderAwaitQueuePO> pageResult = jdOrderAwaitQueueReadManage.findJdOrderAwaitQueueOfPage(po, page);
        
        List<JdOrderAwaitQueueDTO> list = JdOrderAwaitQueueConverter.toDTO(pageResult.getList());
        PageResult<JdOrderAwaitQueueDTO> result = new PageResult<JdOrderAwaitQueueDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueAll(JdOrderAwaitQueueDTO dto) {
		JdOrderAwaitQueuePO po = JdOrderAwaitQueueConverter.toPO(dto);
		List<JdOrderAwaitQueuePO> list = jdOrderAwaitQueueReadManage.findJdOrderAwaitQueueAll(po);		
		return JdOrderAwaitQueueConverter.toDTO(list);
	}
}
	