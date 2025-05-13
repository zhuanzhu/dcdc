package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.CodeModuleReadService;
import com.egeo.components.user.converter.CodeModuleConverter;
import com.egeo.components.user.dao.read.CodeModuleReadDAO;
import com.egeo.components.user.dto.CodeModuleDTO;
import com.egeo.components.user.po.CodeModulePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("codeModuleReadService")
public class CodeModuleReadServiceImpl implements CodeModuleReadService {
	@Autowired
	private CodeModuleReadDAO codeModuleReadDAO;

	@Override
	public CodeModuleDTO findCodeModuleById(CodeModuleDTO dto) {
		CodeModulePO po = CodeModuleConverter.toPO(dto);
		CodeModulePO codeModulepo = new CodeModulePO();
		codeModulepo.setId(po.getId());
		CodeModulePO list = codeModuleReadDAO.findById(codeModulepo);
		
		return CodeModuleConverter.toDTO(list);
	}

	@Override
	public PageResult<CodeModuleDTO> findCodeModuleOfPage(CodeModuleDTO dto, Pagination page) {
		CodeModulePO po = CodeModuleConverter.toPO(dto);
		PageResult<CodeModulePO> pageResult = new PageResult<CodeModulePO>();
		List<CodeModulePO> listT = null;

		int cnt = codeModuleReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = codeModuleReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<CodeModulePO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<CodeModuleDTO> list = CodeModuleConverter.toDTO(pageResult.getList());
        PageResult<CodeModuleDTO> result = new PageResult<CodeModuleDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CodeModuleDTO> findCodeModuleAll(CodeModuleDTO dto) {
		CodeModulePO po = CodeModuleConverter.toPO(dto);
		List<CodeModulePO> list = codeModuleReadDAO.findAll(po,null);		
		return CodeModuleConverter.toDTO(list);
	}
}
	