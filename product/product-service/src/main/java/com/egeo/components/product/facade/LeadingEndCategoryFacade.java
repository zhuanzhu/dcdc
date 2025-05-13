package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.LeadingEndCategoryReadService;
import com.egeo.components.product.service.write.LeadingEndCategoryWriteService;
import com.egeo.components.product.dto.LeadingEndCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class LeadingEndCategoryFacade {
	
	@Resource
	private LeadingEndCategoryReadService leadingEndCategoryReadService;
	
	@Resource
	private LeadingEndCategoryWriteService leadingEndCategoryWriteService;
	
	
	public LeadingEndCategoryDTO findLeadingEndCategoryById(LeadingEndCategoryDTO dto){
		
		return leadingEndCategoryReadService.findLeadingEndCategoryById(dto);
	}

	public PageResult<LeadingEndCategoryDTO> findLeadingEndCategoryOfPage(LeadingEndCategoryDTO dto,Pagination page){
		
		return leadingEndCategoryReadService.findLeadingEndCategoryOfPage(dto, page);
		
	}

	public List<LeadingEndCategoryDTO> findLeadingEndCategoryAll(LeadingEndCategoryDTO dto){
		
		return leadingEndCategoryReadService.findLeadingEndCategoryAll(dto);
		
	}

	public Long insertLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto){
		
		return leadingEndCategoryWriteService.insertLeadingEndCategoryWithTx(dto);
	}

	public int updateLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto){
		
		return leadingEndCategoryWriteService.updateLeadingEndCategoryWithTx(dto);
	}

	public int deleteLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto){
		
		return leadingEndCategoryWriteService.deleteLeadingEndCategoryWithTx(dto);
		
	}

}
	