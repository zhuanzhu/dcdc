package com.egeo.components.promotion.service.read.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.converter.ExchangeOrderRecordConverter;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.manage.read.ExchangeOrderRecordReadManage;
import com.egeo.components.promotion.po.ExchangeOrderRecordPO;
import com.egeo.components.promotion.service.read.ExchangeOrderRecordReadService;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("exchangeOrderRecordReadService")
public class ExchangeOrderRecordReadServiceImpl implements ExchangeOrderRecordReadService {
	@Autowired
	private ExchangeOrderRecordReadManage exchangeOrderRecordReadManage;

	@Override
	public ExchangeOrderRecordDTO findExchangeOrderRecordById(ExchangeOrderRecordDTO dto) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(dto);
		ExchangeOrderRecordPO list = exchangeOrderRecordReadManage.findExchangeOrderRecordById(po);		
		return ExchangeOrderRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<ExchangeOrderRecordDTO> findExchangeOrderRecordOfPage(ExchangeOrderRecordDTO dto, Pagination page) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(dto);
        PageResult<ExchangeOrderRecordPO> pageResult = exchangeOrderRecordReadManage.findExchangeOrderRecordOfPage(po, page);
        
        List<ExchangeOrderRecordDTO> list = ExchangeOrderRecordConverter.toDTO(pageResult.getList());
        PageResult<ExchangeOrderRecordDTO> result = new PageResult<ExchangeOrderRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAll(ExchangeOrderRecordDTO dto) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(dto);
		List<ExchangeOrderRecordPO> list = exchangeOrderRecordReadManage.findExchangeOrderRecordAll(po);		
		return ExchangeOrderRecordConverter.toDTO(list);
	}

	@Override
	public int getCountOfCompletedOrderByExchangeId(Long exchangeId) {
		int count = exchangeOrderRecordReadManage.getCountOfCompletedOrderByExchangeId(exchangeId);
		return count;
	}

	/**
	 * 模糊查询以旧换新活动兑换记录
	 * @param dto
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<ExchangeOrderRecordDTO> fuzzyQueryExchangeOrderRecordOfPage(ExchangeOrderRecordDTO dto,String startTime,String endTime, Pagination page) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(dto);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		SimpleDateFormat sdf2= new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(startTime != null){
				Date beginTime = format.parse(startTime.replace("Z", " UTC"));
				String format2 = sdf2.format(beginTime);
                startTime = format2.substring(0, 10);
            }
			if(endTime != null){
				Date finishTime = format.parse(endTime.replace("Z", " UTC"));
				String string = sdf2.format(finishTime);
                endTime = string.substring(0, 10);
			}
		} catch (ParseException e) {
			throw new BusinessException(BusinessExceptionConstant.STRING_NO_DATE, "类型转换错误");
		}
		PageResult<ExchangeOrderRecordPO> pageResult = exchangeOrderRecordReadManage.fuzzyQueryExchangeOrderRecordOfPage(po,startTime,endTime, page);

		List<ExchangeOrderRecordDTO> list = ExchangeOrderRecordConverter.toDTO(pageResult.getList());
		PageResult<ExchangeOrderRecordDTO> result = new PageResult<ExchangeOrderRecordDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAllByOrderCode(String orderCode) {
		ExchangeOrderRecordPO exchangeOrderRecordPO = new ExchangeOrderRecordPO();
		exchangeOrderRecordPO.setOrderCode(orderCode);
		return ExchangeOrderRecordConverter.toDTO(exchangeOrderRecordReadManage.findExchangeOrderRecordAll(exchangeOrderRecordPO));
	}

	//查询所有未取消的记录
	@Override
	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordLived(ExchangeOrderRecordDTO exchangeOrderRecordDTO) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(exchangeOrderRecordDTO);
		List<ExchangeOrderRecordPO> list = exchangeOrderRecordReadManage.findExchangeOrderRecordLived(po);
		return ExchangeOrderRecordConverter.toDTO(list);
	}
}
	