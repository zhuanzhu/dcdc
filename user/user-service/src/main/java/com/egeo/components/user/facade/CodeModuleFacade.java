package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.CodeModuleDTO;
import com.egeo.components.user.service.read.CodeModuleReadService;
import com.egeo.components.user.service.write.CodeModuleWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CodeModuleFacade {
	
	@Resource
	private CodeModuleReadService codeModuleReadService;
	
	@Resource
	private CodeModuleWriteService codeModuleWriteService;
	
	
	public CodeModuleDTO findCodeModuleById(CodeModuleDTO dto){
		
		return codeModuleReadService.findCodeModuleById(dto);
	}

	public PageResult<CodeModuleDTO> findCodeModuleOfPage(CodeModuleDTO dto,Pagination page){
		
		return codeModuleReadService.findCodeModuleOfPage(dto, page);
		
	}

	public List<CodeModuleDTO> findCodeModuleAll(CodeModuleDTO dto){
		
		return codeModuleReadService.findCodeModuleAll(dto);
		
	}

	public Long insertCodeModuleWithTx(CodeModuleDTO dto){
		
		return codeModuleWriteService.insertCodeModuleWithTx(dto);
	}

	public int updateCodeModuleWithTx(CodeModuleDTO dto){
		
		return codeModuleWriteService.updateCodeModuleWithTx(dto);
	}

	public int deleteCodeModuleWithTx(CodeModuleDTO dto){
		
		return codeModuleWriteService.deleteCodeModuleWithTx(dto);
		
	}

}
	