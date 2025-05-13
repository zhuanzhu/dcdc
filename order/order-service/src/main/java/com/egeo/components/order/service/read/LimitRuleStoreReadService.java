package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.LimitRuleStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LimitRuleStoreReadService {

	public LimitRuleStoreDTO findLimitRuleStoreById(LimitRuleStoreDTO dto);

	public PageResult<LimitRuleStoreDTO> findLimitRuleStoreOfPage(LimitRuleStoreDTO dto,Pagination page);

	public List<LimitRuleStoreDTO> findLimitRuleStoreAll(LimitRuleStoreDTO dto);

    Integer findLimitRuleStoreCount(LimitRuleStoreDTO limitRuleStoreDTO);
}
	