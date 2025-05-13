package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.LeadingEndCategoryReadService;
import com.egeo.components.product.manage.read.LeadingEndCategoryReadManage;
import com.egeo.components.product.converter.LeadingEndCategoryConverter;
import com.egeo.components.product.dto.LeadingEndCategoryDTO;
import com.egeo.components.product.po.LeadingEndCategoryPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("leadingEndCategoryReadService")
public class LeadingEndCategoryReadServiceImpl  implements LeadingEndCategoryReadService {
	@Autowired
	private LeadingEndCategoryReadManage leadingEndCategoryReadManage;

	@Override
	public LeadingEndCategoryDTO findLeadingEndCategoryById(LeadingEndCategoryDTO dto) {
		LeadingEndCategoryPO po = LeadingEndCategoryConverter.toPO(dto);
		LeadingEndCategoryPO list = leadingEndCategoryReadManage.findLeadingEndCategoryById(po);		
		return LeadingEndCategoryConverter.toDTO(list);
	}

	@Override
	public PageResult<LeadingEndCategoryDTO> findLeadingEndCategoryOfPage(LeadingEndCategoryDTO dto, Pagination page) {
		LeadingEndCategoryPO po = LeadingEndCategoryConverter.toPO(dto);
        PageResult<LeadingEndCategoryPO> pageResult = leadingEndCategoryReadManage.findLeadingEndCategoryOfPage(po, page);
        
        List<LeadingEndCategoryDTO> list = LeadingEndCategoryConverter.toDTO(pageResult.getList());
        PageResult<LeadingEndCategoryDTO> result = new PageResult<LeadingEndCategoryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LeadingEndCategoryDTO> findLeadingEndCategoryAll(LeadingEndCategoryDTO dto) {
		LeadingEndCategoryPO po = LeadingEndCategoryConverter.toPO(dto);
		List<LeadingEndCategoryPO> list = leadingEndCategoryReadManage.findLeadingEndCategoryAll(po);		
		return LeadingEndCategoryConverter.toDTO(list);
	}
}
	