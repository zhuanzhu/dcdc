package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.FunctionModuleCategoryReadService;
import com.egeo.components.cms.manage.read.FunctionModuleCategoryReadManage;
import com.egeo.components.cms.converter.FunctionModuleCategoryConverter;
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.components.cms.po.FunctionModuleCategoryPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("functionModuleCategoryReadService")
public class FunctionModuleCategoryReadServiceImpl  implements FunctionModuleCategoryReadService {
	@Autowired
	private FunctionModuleCategoryReadManage functionModuleCategoryReadManage;

	@Override
	public FunctionModuleCategoryDTO findFunctionModuleCategoryById(FunctionModuleCategoryDTO dto) {
		FunctionModuleCategoryPO po = FunctionModuleCategoryConverter.toPO(dto);
		FunctionModuleCategoryPO list = functionModuleCategoryReadManage.findFunctionModuleCategoryById(po);		
		return FunctionModuleCategoryConverter.toDTO(list);
	}

	@Override
	public PageResult<FunctionModuleCategoryDTO> findFunctionModuleCategoryOfPage(FunctionModuleCategoryDTO dto, Pagination page) {
		FunctionModuleCategoryPO po = FunctionModuleCategoryConverter.toPO(dto);
        PageResult<FunctionModuleCategoryPO> pageResult = functionModuleCategoryReadManage.findFunctionModuleCategoryOfPage(po, page);
        
        List<FunctionModuleCategoryDTO> list = FunctionModuleCategoryConverter.toDTO(pageResult.getList());
        PageResult<FunctionModuleCategoryDTO> result = new PageResult<FunctionModuleCategoryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FunctionModuleCategoryDTO> findFunctionModuleCategoryAll(FunctionModuleCategoryDTO dto) {
		FunctionModuleCategoryPO po = FunctionModuleCategoryConverter.toPO(dto);
		List<FunctionModuleCategoryPO> list = functionModuleCategoryReadManage.findFunctionModuleCategoryAll(po);		
		return FunctionModuleCategoryConverter.toDTO(list);
	}
}
	