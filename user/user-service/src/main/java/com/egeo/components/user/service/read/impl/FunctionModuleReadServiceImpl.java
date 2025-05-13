package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.FunctionModuleReadService;
import com.egeo.components.user.converter.FunctionModuleConverter;
import com.egeo.components.user.dao.read.FunctionModuleReadDAO;
import com.egeo.components.user.dto.FunctionModuleDTO;
import com.egeo.components.user.po.FunctionModulePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("functionModuleReadService")
public class FunctionModuleReadServiceImpl implements FunctionModuleReadService {
	@Autowired
	private FunctionModuleReadDAO functionModuleReadDAO;

	@Override
	public FunctionModuleDTO findFunctionModuleById(FunctionModuleDTO dto) {
		FunctionModulePO po = FunctionModuleConverter.toPO(dto);
		FunctionModulePO functionModulepo = new FunctionModulePO();
		functionModulepo.setId(po.getId());
		FunctionModulePO list = functionModuleReadDAO.findById(functionModulepo);		
		return FunctionModuleConverter.toDTO(list);
	}

	@Override
	public PageResult<FunctionModuleDTO> findFunctionModuleOfPage(FunctionModuleDTO dto, Pagination page) {
		FunctionModulePO po = FunctionModuleConverter.toPO(dto);
		
		PageResult<FunctionModulePO> pageResult = new PageResult<FunctionModulePO>();
		List<FunctionModulePO> listT = null;

		int cnt = functionModuleReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = functionModuleReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<FunctionModulePO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
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
		List<FunctionModulePO> list = functionModuleReadDAO.findAll(po,null);		
		return FunctionModuleConverter.toDTO(list);
	}
}
	