package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoCustomerServiceReadService;
import com.egeo.components.order.manage.read.SoCustomerServiceReadManage;
import com.egeo.components.order.converter.SoCustomerServiceConverter;
import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.components.order.po.SoCustomerServicePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soCustomerServiceReadService")
public class SoCustomerServiceReadServiceImpl  implements SoCustomerServiceReadService {
	@Autowired
	private SoCustomerServiceReadManage soCustomerServiceReadManage;

	@Override
	public SoCustomerServiceDTO findSoCustomerServiceById(SoCustomerServiceDTO dto) {
		SoCustomerServicePO po = SoCustomerServiceConverter.toPO(dto);
		SoCustomerServicePO list = soCustomerServiceReadManage.findSoCustomerServiceById(po);		
		return SoCustomerServiceConverter.toDTO(list);
	}

	@Override
	public PageResult<SoCustomerServiceDTO> findSoCustomerServiceOfPage(SoCustomerServiceDTO dto, Pagination page) {
		SoCustomerServicePO po = SoCustomerServiceConverter.toPO(dto);
        PageResult<SoCustomerServicePO> pageResult = soCustomerServiceReadManage.findSoCustomerServiceOfPage(po, page);
        
        List<SoCustomerServiceDTO> list = SoCustomerServiceConverter.toDTO(pageResult.getList());
        PageResult<SoCustomerServiceDTO> result = new PageResult<SoCustomerServiceDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoCustomerServiceDTO> findSoCustomerServiceAll(SoCustomerServiceDTO dto) {
		SoCustomerServicePO po = SoCustomerServiceConverter.toPO(dto);
		List<SoCustomerServicePO> list = soCustomerServiceReadManage.findSoCustomerServiceAll(po);		
		return SoCustomerServiceConverter.toDTO(list);
	}

	@Override
	public SoCustomerServiceDTO queryCustomerServiceBySoChildId(Long scId) {
		
		return SoCustomerServiceConverter.toDTO(soCustomerServiceReadManage.queryCustomerServiceBySoChildId(scId));
	}
}
	