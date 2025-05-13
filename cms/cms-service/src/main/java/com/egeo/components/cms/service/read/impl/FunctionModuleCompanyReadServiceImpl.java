package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.FunctionModuleCompanyReadService;
import com.egeo.components.cms.manage.read.FunctionModuleCompanyReadManage;
import com.egeo.components.cms.converter.FunctionModuleCompanyConverter;
import com.egeo.components.cms.dto.FunctionModuleCompanyDTO;
import com.egeo.components.cms.po.FunctionModuleCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("functionModuleCompanyReadService")
public class FunctionModuleCompanyReadServiceImpl  implements FunctionModuleCompanyReadService {
	@Autowired
	private FunctionModuleCompanyReadManage functionModuleCompanyReadManage;

	@Override
	public FunctionModuleCompanyDTO findFunctionModuleCompanyById(FunctionModuleCompanyDTO dto) {
		FunctionModuleCompanyPO po = FunctionModuleCompanyConverter.toPO(dto);
		FunctionModuleCompanyPO list = functionModuleCompanyReadManage.findFunctionModuleCompanyById(po);		
		return FunctionModuleCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<FunctionModuleCompanyDTO> findFunctionModuleCompanyOfPage(FunctionModuleCompanyDTO dto, Pagination page) {
		FunctionModuleCompanyPO po = FunctionModuleCompanyConverter.toPO(dto);
        PageResult<FunctionModuleCompanyPO> pageResult = functionModuleCompanyReadManage.findFunctionModuleCompanyOfPage(po, page);
        
        List<FunctionModuleCompanyDTO> list = FunctionModuleCompanyConverter.toDTO(pageResult.getList());
        PageResult<FunctionModuleCompanyDTO> result = new PageResult<FunctionModuleCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FunctionModuleCompanyDTO> findFunctionModuleCompanyAll(FunctionModuleCompanyDTO dto) {
		FunctionModuleCompanyPO po = FunctionModuleCompanyConverter.toPO(dto);
		List<FunctionModuleCompanyPO> list = functionModuleCompanyReadManage.findFunctionModuleCompanyAll(po);		
		return FunctionModuleCompanyConverter.toDTO(list);
	}
}
	