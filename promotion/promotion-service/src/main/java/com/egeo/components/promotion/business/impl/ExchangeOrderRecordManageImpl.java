package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.ExchangeOrderRecordManage;
import com.egeo.components.promotion.facade.ExchangeOrderRecordFacade;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("exchangeOrderRecord")
public class ExchangeOrderRecordManageImpl implements ExchangeOrderRecordManage{

	
	@Resource(name="exchangeOrderRecordFacade")
	private ExchangeOrderRecordFacade exchangeOrderRecordFacade;

	@Override
	public ExchangeOrderRecordDTO findExchangeOrderRecordById(ExchangeOrderRecordDTO dto) {
		return exchangeOrderRecordFacade.findExchangeOrderRecordById(dto);
	}

	@Override
	public PageResult<ExchangeOrderRecordDTO> findExchangeOrderRecordOfPage(ExchangeOrderRecordDTO dto, Pagination page) {
	    return exchangeOrderRecordFacade.findExchangeOrderRecordOfPage(dto, page);
    }

	@Override
	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAll(ExchangeOrderRecordDTO dto) {
		return exchangeOrderRecordFacade.findExchangeOrderRecordAll(dto);
	}

	@Override
	public Long insertExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto) {
		return exchangeOrderRecordFacade.insertExchangeOrderRecordWithTx(dto);
	}

	@Override
	public int updateExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto) {
		return exchangeOrderRecordFacade.updateExchangeOrderRecordWithTx(dto);
	}

	@Override
	public int deleteExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto) {
		return exchangeOrderRecordFacade.deleteExchangeOrderRecordWithTx(dto);
	}

	@Override
	public int getCountOfCompletedOrderByExchangeId(Long exchangeId) {
		return exchangeOrderRecordFacade.getCountOfCompletedOrderByExchangeId(exchangeId);
	}

    /**
     * 模糊查询以旧换新活动兑换记录
     * @param dto
     * @param page
     * @return
     */
    @Override
    public PageResult<ExchangeOrderRecordDTO> fuzzyQueryExchangeOrderRecordOfPage(ExchangeOrderRecordDTO dto,String startTime,String endTime, Pagination page) {
        return exchangeOrderRecordFacade.fuzzyQueryExchangeOrderRecordOfPage(dto,startTime,endTime, page);
    }

}
	