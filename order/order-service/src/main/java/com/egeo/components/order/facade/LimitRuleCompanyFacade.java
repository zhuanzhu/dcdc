package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.LimitRuleCompanyReadService;
import com.egeo.components.order.service.write.LimitRuleCompanyWriteService;
import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class LimitRuleCompanyFacade {
	
	@Resource
	private LimitRuleCompanyReadService limitRuleCompanyReadService;
	
	@Resource
	private LimitRuleCompanyWriteService limitRuleCompanyWriteService;
	
	
	public LimitRuleCompanyDTO findLimitRuleCompanyById(LimitRuleCompanyDTO dto){
		
		return limitRuleCompanyReadService.findLimitRuleCompanyById(dto);
	}

	public PageResult<LimitRuleCompanyDTO> findLimitRuleCompanyOfPage(LimitRuleCompanyDTO dto,Pagination page){
		
		return limitRuleCompanyReadService.findLimitRuleCompanyOfPage(dto, page);
		
	}

	public List<LimitRuleCompanyDTO> findLimitRuleCompanyAll(LimitRuleCompanyDTO dto){
		
		return limitRuleCompanyReadService.findLimitRuleCompanyAll(dto);
		
	}

	public Long insertLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto){
		
		return limitRuleCompanyWriteService.insertLimitRuleCompanyWithTx(dto);
	}

	public int updateLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto){
		
		return limitRuleCompanyWriteService.updateLimitRuleCompanyWithTx(dto);
	}

	public int deleteLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto){
		
		return limitRuleCompanyWriteService.deleteLimitRuleCompanyWithTx(dto);
		
	}

}
	