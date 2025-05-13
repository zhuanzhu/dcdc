package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.ExchangeCouponUnitStatusReadService;
import com.egeo.components.promotion.manage.read.ExchangeCouponUnitStatusReadManage;
import com.egeo.components.promotion.converter.ExchangeCouponUnitStatusConverter;
import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("exchangeCouponUnitStatusReadService")
public class ExchangeCouponUnitStatusReadServiceImpl implements ExchangeCouponUnitStatusReadService {
	@Autowired
	private ExchangeCouponUnitStatusReadManage exchangeCouponUnitStatusReadManage;

	@Override
	public ExchangeCouponUnitStatusDTO findExchangeCouponUnitStatusById(ExchangeCouponUnitStatusDTO dto) {
		ExchangeCouponUnitStatusPO po = ExchangeCouponUnitStatusConverter.toPO(dto);
		ExchangeCouponUnitStatusPO list = exchangeCouponUnitStatusReadManage.findExchangeCouponUnitStatusById(po);		
		return ExchangeCouponUnitStatusConverter.toDTO(list);
	}

	@Override
	public PageResult<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusOfPage(ExchangeCouponUnitStatusDTO dto, Pagination page) {
		ExchangeCouponUnitStatusPO po = ExchangeCouponUnitStatusConverter.toPO(dto);
        PageResult<ExchangeCouponUnitStatusPO> pageResult = exchangeCouponUnitStatusReadManage.findExchangeCouponUnitStatusOfPage(po, page);
        
        List<ExchangeCouponUnitStatusDTO> list = ExchangeCouponUnitStatusConverter.toDTO(pageResult.getList());
        PageResult<ExchangeCouponUnitStatusDTO> result = new PageResult<ExchangeCouponUnitStatusDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusAll(ExchangeCouponUnitStatusDTO dto) {
		ExchangeCouponUnitStatusPO po = ExchangeCouponUnitStatusConverter.toPO(dto);
		List<ExchangeCouponUnitStatusPO> list = exchangeCouponUnitStatusReadManage.findExchangeCouponUnitStatusAll(po);		
		return ExchangeCouponUnitStatusConverter.toDTO(list);
	}

	@Override
	public List<Integer> findUnitStatusAll(ExchangeCouponUnitStatusDTO dto) {
		ExchangeCouponUnitStatusPO po = ExchangeCouponUnitStatusConverter.toPO(dto);
		List<Integer> list = exchangeCouponUnitStatusReadManage.findUnitStatusAll(po);
		return list;
	}
}
	