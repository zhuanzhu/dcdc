package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.LimitRuleCompanyManage;
import com.egeo.components.order.facade.LimitRuleCompanyFacade;
import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("limitRuleCompany")
public class LimitRuleCompanyManageImpl implements LimitRuleCompanyManage{

	
	@Resource(name="limitRuleCompanyFacade")
	private LimitRuleCompanyFacade limitRuleCompanyFacade;

	@Override
	public LimitRuleCompanyDTO findLimitRuleCompanyById(LimitRuleCompanyDTO dto) {
		return limitRuleCompanyFacade.findLimitRuleCompanyById(dto);
	}

	@Override
	public PageResult<LimitRuleCompanyDTO> findLimitRuleCompanyOfPage(LimitRuleCompanyDTO dto, Pagination page) {
		return limitRuleCompanyFacade.findLimitRuleCompanyOfPage(dto, page);
	}

	@Override
	public List<LimitRuleCompanyDTO> findLimitRuleCompanyAll(LimitRuleCompanyDTO dto) {
		return limitRuleCompanyFacade.findLimitRuleCompanyAll(dto);
	}

	@Override
	public Long insertLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto) {
		return limitRuleCompanyFacade.insertLimitRuleCompanyWithTx(dto);
	}

	@Override
	public int updateLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto) {
		return limitRuleCompanyFacade.updateLimitRuleCompanyWithTx(dto);
	}

	@Override
	public int deleteLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto) {
		return limitRuleCompanyFacade.deleteLimitRuleCompanyWithTx(dto);
	}


}
	