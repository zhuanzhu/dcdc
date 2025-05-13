package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.FunctionModuleReadService;
import com.egeo.components.cms.manage.read.FunctionModuleReadManage;
import com.egeo.components.cms.converter.FunctionModuleConverter;
import com.egeo.components.cms.dto.FunctionModuleDTO;
import com.egeo.components.cms.po.FunctionModulePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("functionModuleReadService")
public class FunctionModuleReadServiceImpl  implements FunctionModuleReadService {
	@Autowired
	private FunctionModuleReadManage functionModuleReadManage;

	@Override
	public FunctionModuleDTO findFunctionModuleById(FunctionModuleDTO dto) {
		FunctionModulePO po = FunctionModuleConverter.toPO(dto);
		FunctionModulePO list = functionModuleReadManage.findFunctionModuleById(po);		
		return FunctionModuleConverter.toDTO(list);
	}

	@Override
	public PageResult<FunctionModuleDTO> findFunctionModuleOfPage(FunctionModuleDTO dto, Pagination page) {
		FunctionModulePO po = FunctionModuleConverter.toPO(dto);
        PageResult<FunctionModulePO> pageResult = functionModuleReadManage.findFunctionModuleOfPage(po, page);
        
        List<FunctionModuleDTO> list = FunctionModuleConverter.toDTO(pageResult.getList());
        PageResult<FunctionModuleDTO> result = new PageResult<FunctionModuleDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FunctionModuleDTO> findFunctionModuleAll(FunctionModuleDTO dto) {
		FunctionModulePO po = FunctionModuleConverter.toPO(dto);
		List<FunctionModulePO> list = functionModuleReadManage.findFunctionModuleAll(po);		
		return FunctionModuleConverter.toDTO(list);
	}
}
	