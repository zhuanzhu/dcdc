package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.RuleDescriptionReadService;
import com.egeo.components.promotion.manage.read.RuleDescriptionReadManage;
import com.egeo.components.promotion.converter.RuleDescriptionConverter;
import com.egeo.components.promotion.dto.RuleDescriptionDTO;
import com.egeo.components.promotion.po.RuleDescriptionPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("ruleDescriptionReadService")
public class RuleDescriptionReadServiceImpl implements RuleDescriptionReadService {
	@Autowired
	private RuleDescriptionReadManage ruleDescriptionReadManage;

	@Override
	public RuleDescriptionDTO findRuleDescriptionById(RuleDescriptionDTO dto) {
		RuleDescriptionPO po = RuleDescriptionConverter.toPO(dto);
		RuleDescriptionPO list = ruleDescriptionReadManage.findRuleDescriptionById(po);		
		return RuleDescriptionConverter.toDTO(list);
	}

	@Override
	public PageResult<RuleDescriptionDTO> findRuleDescriptionOfPage(RuleDescriptionDTO dto, Pagination page) {
		RuleDescriptionPO po = RuleDescriptionConverter.toPO(dto);
        PageResult<RuleDescriptionPO> pageResult = ruleDescriptionReadManage.findRuleDescriptionOfPage(po, page);
        
        List<RuleDescriptionDTO> list = RuleDescriptionConverter.toDTO(pageResult.getList());
        PageResult<RuleDescriptionDTO> result = new PageResult<RuleDescriptionDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<RuleDescriptionDTO> findRuleDescriptionAll(RuleDescriptionDTO dto) {
		RuleDescriptionPO po = RuleDescriptionConverter.toPO(dto);
		List<RuleDescriptionPO> list = ruleDescriptionReadManage.findRuleDescriptionAll(po);		
		return RuleDescriptionConverter.toDTO(list);
	}
}
	