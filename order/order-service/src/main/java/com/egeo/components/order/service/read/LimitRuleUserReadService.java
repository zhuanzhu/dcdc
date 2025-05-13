package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.LimitRuleUserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LimitRuleUserReadService {

	public LimitRuleUserDTO findLimitRuleUserById(LimitRuleUserDTO dto);

	public PageResult<LimitRuleUserDTO> findLimitRuleUserOfPage(LimitRuleUserDTO dto,Pagination page);

	public List<LimitRuleUserDTO> findLimitRuleUserAll(LimitRuleUserDTO dto);

    Integer findLimitRuleAllByParam(LimitRuleUserDTO limitRuleUserDTO);
}
	