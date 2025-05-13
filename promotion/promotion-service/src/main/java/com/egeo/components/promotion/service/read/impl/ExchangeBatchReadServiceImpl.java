package com.egeo.components.promotion.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.ExchangeBatchReadService;
import com.egeo.components.promotion.manage.read.ExchangeBatchReadManage;
import com.egeo.components.promotion.converter.ExchangeBatchConverter;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.po.ExchangeBatchPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("exchangeBatchReadService")
public class ExchangeBatchReadServiceImpl implements ExchangeBatchReadService {
	@Autowired
	private ExchangeBatchReadManage exchangeBatchReadManage;

	@Override
	public ExchangeBatchDTO findExchangeBatchById(ExchangeBatchDTO dto) {
		ExchangeBatchPO po = ExchangeBatchConverter.toPO(dto);
		ExchangeBatchPO list = exchangeBatchReadManage.findExchangeBatchById(po);		
		return ExchangeBatchConverter.toDTO(list);
	}

	@Override
	public PageResult<ExchangeBatchDTO> findExchangeBatchOfPage(ExchangeBatchDTO dto, Pagination page) {
		ExchangeBatchPO po = ExchangeBatchConverter.toPO(dto);
        PageResult<ExchangeBatchPO> pageResult = exchangeBatchReadManage.findExchangeBatchOfPage(po, page);
        
        List<ExchangeBatchDTO> list = ExchangeBatchConverter.toDTO(pageResult.getList());
        PageResult<ExchangeBatchDTO> result = new PageResult<ExchangeBatchDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ExchangeBatchDTO> findExchangeBatchAll(ExchangeBatchDTO dto) {
		ExchangeBatchPO po = ExchangeBatchConverter.toPO(dto);
		List<ExchangeBatchPO> list = exchangeBatchReadManage.findExchangeBatchAll(po);		
		return ExchangeBatchConverter.toDTO(list);
	}

	@Override
	public List<Long> checkIsShowExchange(Long batchId, Integer unitStatus) {
		List<Long> list=exchangeBatchReadManage.checkIsShowExchange(batchId,unitStatus);
		if(EmptyUtil.isEmpty(list)){
			return null;
		}
		return list;
	}

	@Override
	public List<Long> findExchangeIdsByBatch(ExchangeBatchDTO dto) {
		return exchangeBatchReadManage.findExchangeIdsByBatch(ExchangeBatchConverter.toPO(dto));
	}

	@Override
	public List<Long> findExchangeActivityByOldCouponUnitId(Long batchId, Integer couponUnitStatus) {
		return exchangeBatchReadManage.findExchangeActivityByOldCouponUnitId(batchId, couponUnitStatus);
	}
}
	