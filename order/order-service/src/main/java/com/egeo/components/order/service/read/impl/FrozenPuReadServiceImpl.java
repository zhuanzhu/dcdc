package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.FrozenPuReadService;
import com.egeo.components.order.manage.read.FrozenPuReadManage;
import com.egeo.components.order.converter.FrozenPuConverter;
import com.egeo.components.order.dto.FrozenPuDTO;
import com.egeo.components.order.po.FrozenPuPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("frozenPuReadService")
public class FrozenPuReadServiceImpl  implements FrozenPuReadService {
	@Autowired
	private FrozenPuReadManage frozenPuReadManage;

	@Override
	public FrozenPuDTO findFrozenPuById(FrozenPuDTO dto) {
		FrozenPuPO po = FrozenPuConverter.toPO(dto);
		FrozenPuPO list = frozenPuReadManage.findFrozenPuById(po);		
		return FrozenPuConverter.toDTO(list);
	}

	@Override
	public PageResult<FrozenPuDTO> findFrozenPuOfPage(FrozenPuDTO dto, Pagination page) {
		FrozenPuPO po = FrozenPuConverter.toPO(dto);
        PageResult<FrozenPuPO> pageResult = frozenPuReadManage.findFrozenPuOfPage(po, page);
        
        List<FrozenPuDTO> list = FrozenPuConverter.toDTO(pageResult.getList());
        PageResult<FrozenPuDTO> result = new PageResult<FrozenPuDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FrozenPuDTO> findFrozenPuAll(FrozenPuDTO dto) {
		FrozenPuPO po = FrozenPuConverter.toPO(dto);
		List<FrozenPuPO> list = frozenPuReadManage.findFrozenPuAll(po);		
		return FrozenPuConverter.toDTO(list);
	}
}
	