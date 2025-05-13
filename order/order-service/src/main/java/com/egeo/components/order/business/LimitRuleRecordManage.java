package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LimitRuleRecordManage {

	public LimitRuleRecordDTO findLimitRuleRecordById(LimitRuleRecordDTO dto);	

	public PageResult<LimitRuleRecordDTO> findLimitRuleRecordOfPage(LimitRuleRecordDTO dto,Pagination page);

	public List<LimitRuleRecordDTO> findLimitRuleRecordAll(LimitRuleRecordDTO dto);

	Long insertLimitRuleRecordWithTx(LimitRuleRecordDTO dto);

	int updateLimitRuleRecordWithTx(LimitRuleRecordDTO dto);

	int deleteLimitRuleRecordWithTx(LimitRuleRecordDTO dto);
}
	