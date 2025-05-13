package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitCompanyReadService;
import com.egeo.components.product.manage.read.StandardUnitCompanyReadManage;
import com.egeo.components.product.converter.StandardUnitCompanyConverter;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.po.StandardUnitCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCompanyReadService")
public class StandardUnitCompanyReadServiceImpl  implements StandardUnitCompanyReadService {
	@Autowired
	private StandardUnitCompanyReadManage standardUnitCompanyReadManage;

	@Override
	public StandardUnitCompanyDTO findStandardUnitCompanyById(StandardUnitCompanyDTO dto) {
		StandardUnitCompanyPO po = StandardUnitCompanyConverter.toPO(dto);
		StandardUnitCompanyPO list = standardUnitCompanyReadManage.findStandardUnitCompanyById(po);		
		return StandardUnitCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitCompanyDTO> findStandardUnitCompanyOfPage(StandardUnitCompanyDTO dto, Pagination page) {
		StandardUnitCompanyPO po = StandardUnitCompanyConverter.toPO(dto);
        PageResult<StandardUnitCompanyPO> pageResult = standardUnitCompanyReadManage.findStandardUnitCompanyOfPage(po, page);
        
        List<StandardUnitCompanyDTO> list = StandardUnitCompanyConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitCompanyDTO> result = new PageResult<StandardUnitCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(StandardUnitCompanyDTO dto) {
		StandardUnitCompanyPO po = StandardUnitCompanyConverter.toPO(dto);
		List<StandardUnitCompanyPO> list = standardUnitCompanyReadManage.findStandardUnitCompanyAll(po);		
		return StandardUnitCompanyConverter.toDTO(list);
	}

	@Override
	public boolean querySuCompanyAvailable(Long suId, Long companyId,Long clientId, Integer companyType) {
		
		return standardUnitCompanyReadManage.querySuCompanyAvailable(suId,companyId,clientId, companyType);
	}

	@Override
	public List<StandardUnitCompanyDTO> findSuCompanyByCompanyIdAndTypeAndSuIds(StandardUnitCompanyDTO dto,List<Long> suIds) {
		StandardUnitCompanyPO po = StandardUnitCompanyConverter.toPO(dto);
		List<StandardUnitCompanyPO> list = standardUnitCompanyReadManage.findSuCompanyByCompanyIdAndTypeAndSuIds(po,suIds);		
		return StandardUnitCompanyConverter.toDTO(list);
	}
}
	