package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SuSerachRuleReadService;
import com.egeo.components.product.manage.read.SuSerachRuleReadManage;
import com.egeo.components.product.converter.SuSerachRuleConverter;
import com.egeo.components.product.dto.SuSerachRuleDTO;
import com.egeo.components.product.po.SuSerachRulePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("suSerachRuleReadService")
public class SuSerachRuleReadServiceImpl  implements SuSerachRuleReadService {
	@Autowired
	private SuSerachRuleReadManage suSerachRuleReadManage;
	
	@Override
	public SuSerachRuleDTO findSuSerachRuleById(SuSerachRuleDTO dto) {
		SuSerachRulePO po = SuSerachRuleConverter.toPO(dto);
		SuSerachRulePO list = suSerachRuleReadManage.findSuSerachRuleById(po);		
		return SuSerachRuleConverter.toDTO(list);
	}

	@Override
	public PageResult<SuSerachRuleDTO> findSuSerachRuleOfPage(SuSerachRuleDTO dto, Pagination page) {
		SuSerachRulePO po = SuSerachRuleConverter.toPO(dto);
        PageResult<SuSerachRulePO> pageResult = suSerachRuleReadManage.findSuSerachRuleOfPage(po, page);
        
        List<SuSerachRuleDTO> list = SuSerachRuleConverter.toDTO(pageResult.getList());
        PageResult<SuSerachRuleDTO> result = new PageResult<SuSerachRuleDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SuSerachRuleDTO> findSuSerachRuleAll(SuSerachRuleDTO dto) {
		SuSerachRulePO po = SuSerachRuleConverter.toPO(dto);
		List<SuSerachRulePO> list = suSerachRuleReadManage.findSuSerachRuleAll(po);		
		return SuSerachRuleConverter.toDTO(list);
	}
	
}
	