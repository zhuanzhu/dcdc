package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.LimitRuleCompanyReadService;
import com.egeo.components.order.manage.read.LimitRuleCompanyReadManage;
import com.egeo.components.order.converter.LimitRuleCompanyConverter;
import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.components.order.po.LimitRuleCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("limitRuleCompanyReadService")
public class LimitRuleCompanyReadServiceImpl  implements LimitRuleCompanyReadService {
	@Autowired
	private LimitRuleCompanyReadManage limitRuleCompanyReadManage;

	@Override
	public LimitRuleCompanyDTO findLimitRuleCompanyById(LimitRuleCompanyDTO dto) {
		LimitRuleCompanyPO po = LimitRuleCompanyConverter.toPO(dto);
		LimitRuleCompanyPO list = limitRuleCompanyReadManage.findLimitRuleCompanyById(po);		
		return LimitRuleCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<LimitRuleCompanyDTO> findLimitRuleCompanyOfPage(LimitRuleCompanyDTO dto, Pagination page) {
		LimitRuleCompanyPO po = LimitRuleCompanyConverter.toPO(dto);
        PageResult<LimitRuleCompanyPO> pageResult = limitRuleCompanyReadManage.findLimitRuleCompanyOfPage(po, page);
        
        List<LimitRuleCompanyDTO> list = LimitRuleCompanyConverter.toDTO(pageResult.getList());
        PageResult<LimitRuleCompanyDTO> result = new PageResult<LimitRuleCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LimitRuleCompanyDTO> findLimitRuleCompanyAll(LimitRuleCompanyDTO dto) {
		LimitRuleCompanyPO po = LimitRuleCompanyConverter.toPO(dto);
		List<LimitRuleCompanyPO> list = limitRuleCompanyReadManage.findLimitRuleCompanyAll(po);		
		return LimitRuleCompanyConverter.toDTO(list);
	}
	/**
	 * 根据限购规则id查询限购规则与公司关系
	 * @param limitRuleId
	 * @return
	 */
	@Override
	public List<Long> findCompanyByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleCompanyReadManage.findCompanyByLimitRuleId(limitRuleId);
	}

	@Override
	public Integer findLimitRuleCompanyCount(LimitRuleCompanyDTO limitRuleCompanyDTO) {
		return limitRuleCompanyReadManage.findLimitRuleCompanyCount(LimitRuleCompanyConverter.toPO(limitRuleCompanyDTO));
	}
}
	