package com.egeo.components.order.facade;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.LimitRuleRecordReadService;
import com.egeo.components.order.service.write.LimitRuleRecordWriteService;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class LimitRuleRecordFacade {
	
	@Resource
	private LimitRuleRecordReadService limitRuleRecordReadService;
	
	@Resource
	private LimitRuleRecordWriteService limitRuleRecordWriteService;
	
	
	public LimitRuleRecordDTO findLimitRuleRecordById(LimitRuleRecordDTO dto){
		
		return limitRuleRecordReadService.findLimitRuleRecordById(dto);
	}

	public PageResult<LimitRuleRecordDTO> findLimitRuleRecordOfPage(LimitRuleRecordDTO dto,Pagination page){
		
		return limitRuleRecordReadService.findLimitRuleRecordOfPage(dto, page);
		
	}

	public List<LimitRuleRecordDTO> findLimitRuleRecordAll(LimitRuleRecordDTO dto){
		
		return limitRuleRecordReadService.findLimitRuleRecordAll(dto);
		
	}

	public Long insertLimitRuleRecordWithTx(LimitRuleRecordDTO dto){
		
		return limitRuleRecordWriteService.insertLimitRuleRecordWithTx(dto);
	}

	public int updateLimitRuleRecordWithTx(LimitRuleRecordDTO dto){
		
		return limitRuleRecordWriteService.updateLimitRuleRecordWithTx(dto);
	}

	public int deleteLimitRuleRecordWithTx(LimitRuleRecordDTO dto){
		
		return limitRuleRecordWriteService.deleteLimitRuleRecordWithTx(dto);
		
	}
	/**
	 * 根据限购规则id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	public Long findBuySumByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadService.findBuySumByLimitRuleId(limitRuleId);
	}
	/**
	 * 根据限购规则id查询限购规则购买商品总金额
	 * @param id
	 * @return
	 */
	public BigDecimal findBuyMoneySumByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadService.findBuyMoneySumByLimitRuleId(limitRuleId);
	}

}
	