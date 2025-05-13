package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import com.egeo.components.promotion.condition.ExchangeActivityCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.ExchangeActivityReadService;
import com.egeo.components.promotion.manage.read.ExchangeActivityReadManage;
import com.egeo.components.promotion.converter.ExchangeActivityConverter;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.po.ExchangeActivityPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("exchangeActivityReadService")
public class ExchangeActivityReadServiceImpl implements ExchangeActivityReadService {
	@Autowired
	private ExchangeActivityReadManage exchangeActivityReadManage;

	@Override
	public ExchangeActivityDTO findExchangeActivityById(ExchangeActivityDTO dto) {
		ExchangeActivityPO po = ExchangeActivityConverter.toPO(dto);
		ExchangeActivityPO list = exchangeActivityReadManage.findExchangeActivityById(po);		
		return ExchangeActivityConverter.toDTO(list);
	}

	@Override
	public PageResult<ExchangeActivityDTO> findExchangeActivityOfPage(ExchangeActivityDTO dto, Pagination page) {
		ExchangeActivityPO po = ExchangeActivityConverter.toPO(dto);
        PageResult<ExchangeActivityPO> pageResult = exchangeActivityReadManage.findExchangeActivityOfPage(po, page);
        
        List<ExchangeActivityDTO> list = ExchangeActivityConverter.toDTO(pageResult.getList());
        PageResult<ExchangeActivityDTO> result = new PageResult<ExchangeActivityDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ExchangeActivityDTO> findExchangeActivityAll(ExchangeActivityDTO dto) {
		ExchangeActivityPO po = ExchangeActivityConverter.toPO(dto);
		List<ExchangeActivityPO> list = exchangeActivityReadManage.findExchangeActivityAll(po);		
		return ExchangeActivityConverter.toDTO(list);
	}

	/**
	 * 模糊查询以旧换新活动列表
	 * @param dto
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<ExchangeActivityDTO> fuzzQueryExchangeActivityOfPage(ExchangeActivityDTO dto, Pagination page) {
		ExchangeActivityPO po = ExchangeActivityConverter.toPO(dto);
		PageResult<ExchangeActivityPO> pageResult = exchangeActivityReadManage.fuzzQueryExchangeActivityOfPage(po, page);

		List<ExchangeActivityDTO> list = ExchangeActivityConverter.toDTO(pageResult.getList());
		PageResult<ExchangeActivityDTO> result = new PageResult<ExchangeActivityDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<ExchangeActivityDTO> findExchangeActivityByIds(List<Long> exchangeList) {
		List<ExchangeActivityPO> poList=exchangeActivityReadManage.findExchangeActivityByIds(exchangeList);
		return ExchangeActivityConverter.toDTO(poList);

	}

}
	