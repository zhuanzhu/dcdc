package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.FreightRegulationReadService;
import com.egeo.components.product.manage.read.FreightRegulationReadManage;
import com.egeo.components.product.converter.FreightRegulationConverter;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.po.FreightRegulationPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("freightRegulationReadService")
public class FreightRegulationReadServiceImpl  implements FreightRegulationReadService {
	@Autowired
	private FreightRegulationReadManage freightRegulationReadManage;

	@Override
	public FreightRegulationDTO findFreightRegulationById(FreightRegulationDTO dto) {
		FreightRegulationPO po = FreightRegulationConverter.toPO(dto);
		FreightRegulationPO list = freightRegulationReadManage.findFreightRegulationById(po);		
		return FreightRegulationConverter.toDTO(list);
	}

	@Override
	public PageResult<FreightRegulationDTO> findFreightRegulationOfPage(FreightRegulationDTO dto, Pagination page) {
		FreightRegulationPO po = FreightRegulationConverter.toPO(dto);
        PageResult<FreightRegulationPO> pageResult = freightRegulationReadManage.findFreightRegulationOfPage(po, page);
        
        List<FreightRegulationDTO> list = FreightRegulationConverter.toDTO(pageResult.getList());
        PageResult<FreightRegulationDTO> result = new PageResult<FreightRegulationDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FreightRegulationDTO> findFreightRegulationAll(FreightRegulationDTO dto) {
		FreightRegulationPO po = FreightRegulationConverter.toPO(dto);
		List<FreightRegulationPO> list = freightRegulationReadManage.findFreightRegulationAll(po);		
		return FreightRegulationConverter.toDTO(list);
	}
}
	