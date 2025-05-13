package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.LimitRuleRecordManage;
import com.egeo.components.order.facade.LimitRuleRecordFacade;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("limitRuleRecord")
public class LimitRuleRecordManageImpl implements LimitRuleRecordManage{

	
	@Resource(name="limitRuleRecordFacade")
	private LimitRuleRecordFacade limitRuleRecordFacade;

	@Override
	public LimitRuleRecordDTO findLimitRuleRecordById(LimitRuleRecordDTO dto) {
		return limitRuleRecordFacade.findLimitRuleRecordById(dto);
	}

	@Override
	public PageResult<LimitRuleRecordDTO> findLimitRuleRecordOfPage(LimitRuleRecordDTO dto, Pagination page) {
		return limitRuleRecordFacade.findLimitRuleRecordOfPage(dto, page);
	}

	@Override
	public List<LimitRuleRecordDTO> findLimitRuleRecordAll(LimitRuleRecordDTO dto) {
		return limitRuleRecordFacade.findLimitRuleRecordAll(dto);
	}

	@Override
	public Long insertLimitRuleRecordWithTx(LimitRuleRecordDTO dto) {
		return limitRuleRecordFacade.insertLimitRuleRecordWithTx(dto);
	}

	@Override
	public int updateLimitRuleRecordWithTx(LimitRuleRecordDTO dto) {
		return limitRuleRecordFacade.updateLimitRuleRecordWithTx(dto);
	}

	@Override
	public int deleteLimitRuleRecordWithTx(LimitRuleRecordDTO dto) {
		return limitRuleRecordFacade.deleteLimitRuleRecordWithTx(dto);
	}


}
	