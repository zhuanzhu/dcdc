package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoChildFlowReadService;
import com.egeo.components.order.manage.read.SoChildFlowReadManage;
import com.egeo.components.order.converter.SoChildFlowConverter;
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.components.order.po.SoChildFlowPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soChildFlowReadService")
public class SoChildFlowReadServiceImpl  implements SoChildFlowReadService {
	@Autowired
	private SoChildFlowReadManage soChildFlowReadManage;

	@Override
	public SoChildFlowDTO findSoChildFlowById(SoChildFlowDTO dto) {
		SoChildFlowPO po = SoChildFlowConverter.toPO(dto);
		SoChildFlowPO list = soChildFlowReadManage.findSoChildFlowById(po);		
		return SoChildFlowConverter.toDTO(list);
	}

	@Override
	public PageResult<SoChildFlowDTO> findSoChildFlowOfPage(SoChildFlowDTO dto, Pagination page) {
		SoChildFlowPO po = SoChildFlowConverter.toPO(dto);
        PageResult<SoChildFlowPO> pageResult = soChildFlowReadManage.findSoChildFlowOfPage(po, page);
        
        List<SoChildFlowDTO> list = SoChildFlowConverter.toDTO(pageResult.getList());
        PageResult<SoChildFlowDTO> result = new PageResult<SoChildFlowDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoChildFlowDTO> findSoChildFlowAll(SoChildFlowDTO dto) {
		SoChildFlowPO po = SoChildFlowConverter.toPO(dto);
		List<SoChildFlowPO> list = soChildFlowReadManage.findSoChildFlowAll(po);		
		return SoChildFlowConverter.toDTO(list);
	}

	@Override
	public List<SoChildFlowDTO> queryFlowListBySoId(Long orderId) {
		return SoChildFlowConverter.toDTO(soChildFlowReadManage.queryFlowListBySoId(orderId));
	}

	@Override
	public List<SoChildFlowDTO> queryFlowListBySoChildId(Long soChildId) {
		return SoChildFlowConverter.toDTO(soChildFlowReadManage.queryFlowListBySoChildId(soChildId));
	}
}
	