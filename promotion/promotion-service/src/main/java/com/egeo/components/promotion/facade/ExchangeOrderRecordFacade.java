package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.service.read.CouponBatchReadService;
import com.egeo.components.promotion.service.read.ExchangeOrderRecordReadService;
import com.egeo.components.promotion.service.write.ExchangeOrderRecordWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;


@Component
public class ExchangeOrderRecordFacade {
	
	@Autowired
	private ExchangeOrderRecordReadService exchangeOrderRecordReadService;
	
	@Autowired
	private ExchangeOrderRecordWriteService exchangeOrderRecordWriteService;

	@Autowired
	private SoClient soReadService;

	@Autowired
	private CouponBatchReadService couponBatchReadService;
	
	
	public ExchangeOrderRecordDTO findExchangeOrderRecordById(ExchangeOrderRecordDTO dto){
		
		return exchangeOrderRecordReadService.findExchangeOrderRecordById(dto);
	}

	public PageResult<ExchangeOrderRecordDTO> findExchangeOrderRecordOfPage(ExchangeOrderRecordDTO dto,Pagination page){
		PageResult<ExchangeOrderRecordDTO> exchangeOrderRecordOfPage = exchangeOrderRecordReadService.findExchangeOrderRecordOfPage(dto, page);
		if (EmptyUtil.isNotEmpty(exchangeOrderRecordOfPage)) {
      	    List<ExchangeOrderRecordDTO> dtoList = exchangeOrderRecordOfPage.getList();
            for (ExchangeOrderRecordDTO dto2 : dtoList) {
                //组装订单
				if (dto2.getOrderCode() != null) {
					List<SoDTO> soList = soReadService.findSoByCode(dto2.getOrderCode());
					if (EmptyUtil.isNotEmpty(soList)) {
						int orderStatus = soList.get(0).getOrderStatus();
						dto2.setOrderStatus(orderStatus);
					}
				}
                //组装时间 转化成String
				if (dto2.getExchangeTime() != null){
					String exchangeTime = DateUtils.getDefaultTime(dto2.getExchangeTime());
					dto2.setExchangeTimeStr(exchangeTime);
				}
				if (dto2.getCreateTime() != null) {
					String createTime = DateUtils.getDefaultDate(dto2.getCreateTime());
					dto2.setCreateTimeStr(createTime);
				}
				if (dto2.getUpdateTime() !=null) {
					String updateTime = DateUtils.getDefaultDateTime(dto2.getUpdateTime());
					dto2.setUpdateTimeStr(updateTime);
				}

				String oldBatchCode = dto2.getOldBatchCode();
				String newBatchCode = dto2.getNewBatchCode();
				if(oldBatchCode != null) {
					CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
					couponBatchDTO.setCouponBatchCode(oldBatchCode);
					List<CouponBatchDTO> couponBatchAll = couponBatchReadService.findCouponBatchAll(couponBatchDTO);
					if (EmptyUtil.isNotEmpty(couponBatchAll)) {
						String name = couponBatchAll.get(0).getCouponBatchName();
						if (name == null) {
							dto2.setOldCouponBatchName("");
						} else {
							dto2.setOldCouponBatchName(name);
						}
					}
				}
				if(newBatchCode != null) {
					CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
					couponBatchDTO.setCouponBatchCode(newBatchCode);
					List<CouponBatchDTO> couponBatchAll = couponBatchReadService.findCouponBatchAll(couponBatchDTO);
					if (EmptyUtil.isNotEmpty(couponBatchAll)) {
						String name = couponBatchAll.get(0).getCouponBatchName();
						if (name == null) {
							dto2.setNewCouponBatchName("");
						}else {
							dto2.setNewCouponBatchName(name);
						}
					}
				}
			}
        }
        return exchangeOrderRecordOfPage;
	}

	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAll(ExchangeOrderRecordDTO dto){
		
		return exchangeOrderRecordReadService.findExchangeOrderRecordAll(dto);
		
	}

	public Long insertExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto){
		
		return exchangeOrderRecordWriteService.insertExchangeOrderRecordWithTx(dto);
	}

	public int updateExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto){
		
		return exchangeOrderRecordWriteService.updateExchangeOrderRecordWithTx(dto);
	}

	public int deleteExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto){
		
		return exchangeOrderRecordWriteService.deleteExchangeOrderRecordWithTx(dto);
		
	}

	public int getCountOfCompletedOrderByExchangeId(Long exchangeId){

		return exchangeOrderRecordReadService.getCountOfCompletedOrderByExchangeId(exchangeId);

	}

	/**
	 * 模糊查询以旧换新活动兑换记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<ExchangeOrderRecordDTO> fuzzyQueryExchangeOrderRecordOfPage(ExchangeOrderRecordDTO dto,String startTime,String endTime,Pagination page){
		PageResult<ExchangeOrderRecordDTO> exchangeOrderRecordOfPage = exchangeOrderRecordReadService.fuzzyQueryExchangeOrderRecordOfPage(dto,startTime,endTime, page);
		if (EmptyUtil.isNotEmpty(exchangeOrderRecordOfPage)) {
			List<ExchangeOrderRecordDTO> dtoList = exchangeOrderRecordOfPage.getList();
			for (ExchangeOrderRecordDTO dto2 : dtoList) {
				//组装订单
				if(EmptyUtil.isNotEmpty(dto2.getOrderCode())){
					List<SoDTO> soList = soReadService.findSoByCode(dto2.getOrderCode());
					if (EmptyUtil.isNotEmpty(soList)) {
						int orderStatus = soList.get(0).getOrderStatus();
						dto2.setOrderStatus(orderStatus);
					}
				}
				//组装时间 转化成String
				String createTime = DateUtils.getDefaultDate(dto2.getCreateTime());
				String exchangeTime = DateUtils.getDefaultTime(dto2.getExchangeTime());
				String updateTime = DateUtils.getDefaultDateTime(dto2.getUpdateTime());
				dto2.setCreateTimeStr(createTime);
				dto2.setExchangeTimeStr(exchangeTime);
				dto2.setUpdateTimeStr(updateTime);
			}
		}
		return exchangeOrderRecordOfPage;
	}
}
	