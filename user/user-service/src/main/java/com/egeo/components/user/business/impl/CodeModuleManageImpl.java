package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.CodeModuleManage;
import com.egeo.components.user.dto.CodeModuleDTO;
import com.egeo.components.user.service.read.CodeModuleReadService;
import com.egeo.components.user.service.write.CodeModuleWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("codeModule")
public class CodeModuleManageImpl implements CodeModuleManage{

	
	@Resource
	private CodeModuleReadService codeModuleReadService;
	
	@Resource
	private CodeModuleWriteService codeModuleWriteService;

	@Override
	public CodeModuleDTO findCodeModuleById(CodeModuleDTO dto) {
		return codeModuleReadService.findCodeModuleById(dto);
	}

	@Override
	public PageResult<CodeModuleDTO> findCodeModuleOfPage(CodeModuleDTO dto, Pagination page) {
		return codeModuleReadService.findCodeModuleOfPage(dto, page);
	}

	@Override
	public List<Map<String, Object>> findCodeModuleAll(CodeModuleDTO dto) {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<CodeModuleDTO> list = codeModuleReadService.findCodeModuleAll(dto);
		for (CodeModuleDTO codeModuleDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", codeModuleDTO.getId());
			map.put("codeModuleName", codeModuleDTO.getCodeModuleName());
			maps.add(map);
		}
		return maps;
	}

	@Override
	public Long insertCodeModuleWithTx(CodeModuleDTO dto) {
		return codeModuleWriteService.insertCodeModuleWithTx(dto);
	}

	@Override
	public int updateCodeModuleWithTx(CodeModuleDTO dto) {
		return codeModuleWriteService.updateCodeModuleWithTx(dto);
	}

	@Override
	public int deleteCodeModuleWithTx(CodeModuleDTO dto) {
		return codeModuleWriteService.deleteCodeModuleWithTx(dto);
	}


}
	