package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoFlowReadService;
import com.egeo.components.order.manage.read.SoFlowReadManage;
import com.egeo.components.order.converter.SoFlowConverter;
import com.egeo.components.order.dto.SoFlowDTO;
import com.egeo.components.order.po.SoFlowPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soFlowReadService")
public class SoFlowReadServiceImpl  implements SoFlowReadService {
	@Autowired
	private SoFlowReadManage soFlowReadManage;

	@Override
	public SoFlowDTO findSoFlowById(SoFlowDTO dto) {
		SoFlowPO po = SoFlowConverter.toPO(dto);
		SoFlowPO list = soFlowReadManage.findSoFlowById(po);		
		return SoFlowConverter.toDTO(list);
	}

	@Override
	public PageResult<SoFlowDTO> findSoFlowOfPage(SoFlowDTO dto, Pagination page) {
		SoFlowPO po = SoFlowConverter.toPO(dto);
        PageResult<SoFlowPO> pageResult = soFlowReadManage.findSoFlowOfPage(po, page);
        
        List<SoFlowDTO> list = SoFlowConverter.toDTO(pageResult.getList());
        PageResult<SoFlowDTO> result = new PageResult<SoFlowDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoFlowDTO> findSoFlowAll(SoFlowDTO dto) {
		SoFlowPO po = SoFlowConverter.toPO(dto);
		List<SoFlowPO> list = soFlowReadManage.findSoFlowAll(po);		
		return SoFlowConverter.toDTO(list);
	}

	@Override
	public List<SoFlowDTO> queryFlowListBySoId(Long orderId) {
		return SoFlowConverter.toDTO(soFlowReadManage.queryFlowListBySoId(orderId));
	}
}
	