package com.egeo.components.pay.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.ThirdpartyAwaitQueueConverter;
import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.manage.read.ThirdpartyAwaitQueueReadManage;
import com.egeo.components.pay.po.ThirdpartyAwaitQueuePO;
import com.egeo.components.pay.service.read.ThirdpartyAwaitQueueReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("thirdpartyAwaitQueueReadService")
public class ThirdpartyAwaitQueueReadServiceImpl  implements ThirdpartyAwaitQueueReadService {
	@Autowired
	private ThirdpartyAwaitQueueReadManage thirdpartyAwaitQueueReadManage;

	@Override
	public ThirdpartyAwaitQueueDTO findThirdpartyAwaitQueueById(ThirdpartyAwaitQueueDTO dto) {
		ThirdpartyAwaitQueuePO po = ThirdpartyAwaitQueueConverter.toPO(dto);
		ThirdpartyAwaitQueuePO list = thirdpartyAwaitQueueReadManage.findThirdpartyAwaitQueueById(po);		
		return ThirdpartyAwaitQueueConverter.toDTO(list);
	}

	@Override
	public PageResult<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueOfPage(ThirdpartyAwaitQueueDTO dto, Pagination page) {
		ThirdpartyAwaitQueuePO po = ThirdpartyAwaitQueueConverter.toPO(dto);
        PageResult<ThirdpartyAwaitQueuePO> pageResult = thirdpartyAwaitQueueReadManage.findThirdpartyAwaitQueueOfPage(po, page);
        
        List<ThirdpartyAwaitQueueDTO> list = ThirdpartyAwaitQueueConverter.toDTO(pageResult.getList());
        PageResult<ThirdpartyAwaitQueueDTO> result = new PageResult<ThirdpartyAwaitQueueDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueueDTO dto) {
		ThirdpartyAwaitQueuePO po = ThirdpartyAwaitQueueConverter.toPO(dto);
		List<ThirdpartyAwaitQueuePO> list = thirdpartyAwaitQueueReadManage.findThirdpartyAwaitQueueAll(po);		
		return ThirdpartyAwaitQueueConverter.toDTO(list);
	}
}
	