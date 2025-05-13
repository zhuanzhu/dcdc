package com.egeo.components.promotion.facade;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.service.read.*;
import com.egeo.components.promotion.dto.*;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.write.ExchangeActivityWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ExchangeActivityFacade {
	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private ExchangeActivityReadService exchangeActivityReadService;
	
	@Autowired
	private ExchangeActivityWriteService exchangeActivityWriteService;

	@Autowired
	private ExchangeBatchReadService exchangeBatchReadService;

	@Autowired
	private CouponReadService couponReadService;

	@Autowired
	private CouponBatchReadService couponBatchReadService;

	@Autowired
	private ExchangeCouponUnitStatusReadService exchangeCouponUnitStatusReadService;

	public ExchangeActivityDTO findExchangeActivityById(ExchangeActivityDTO dto){
		
		return exchangeActivityReadService.findExchangeActivityById(dto);
	}

	public PageResult<ExchangeActivityDTO> findExchangeActivityOfPage(ExchangeActivityDTO dto,Pagination page){
		
		return exchangeActivityReadService.findExchangeActivityOfPage(dto, page);
		
	}

	public List<ExchangeActivityDTO> findExchangeActivityAll(ExchangeActivityDTO dto){
		
		return exchangeActivityReadService.findExchangeActivityAll(dto);
		
	}

	public Long insertExchangeActivityWithTx(ExchangeActivityDTO dto){
		
		return exchangeActivityWriteService.insertExchangeActivityWithTx(dto);
	}

	public int updateExchangeActivityWithTx(ExchangeActivityDTO dto){
		
		return exchangeActivityWriteService.updateExchangeActivityWithTx(dto);
	}

	public int deleteExchangeActivityWithTx(ExchangeActivityDTO dto){
		
		return exchangeActivityWriteService.deleteExchangeActivityWithTx(dto);
		
	}

	/**
	 * 模糊查询以旧换新活动列表
	 */
	public PageResult<ExchangeActivityDTO> fuzzQueryExchangeActivityOfPage(ExchangeActivityDTO dto,Pagination page){

		return exchangeActivityReadService.fuzzQueryExchangeActivityOfPage(dto, page);

	}

    public int insertOrUpdateExchangeActivityWithTx(ExchangeActivityDTO exchangeActivityDTO) {
		return exchangeActivityWriteService.insertOrUpdateExchangeActivityWithTx(exchangeActivityDTO);
	}

	public JsonResult<Map<String, Object>> findExchangeActivity(ExchangeActivityDTO dto) {
		ExchangeActivityDTO exchangeActivityDTO = exchangeActivityReadService.findExchangeActivityById(dto);
		JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (EmptyUtil.isNotEmpty(exchangeActivityDTO)) {

			String name = exchangeActivityDTO.getExchangeName();
			map.put("exchangeName",name);
			//String endTime = DateUtils.getDefaultDate(exchangeActivityDTO.getEndTime());
			map.put("endTime",exchangeActivityDTO.getEndTime().getTime()-((24*60*60-1)*1000));

			//可兑换的旧批次
			List<Map<String, Object>> oldBatchInfo = getOldNewBatchInfo(dto.getId(), 0);
			map.put("oldBatchInfo",oldBatchInfo);

			//可兑换的新批次
			List<Map<String, Object>> newBatchInfo = getOldNewBatchInfo(dto.getId(), 1);
			map.put("newBatchInfo",newBatchInfo);

			//可兑换的优惠券Unit状态
			ExchangeCouponUnitStatusDTO unitStatusDTO = new ExchangeCouponUnitStatusDTO();
			unitStatusDTO.setExchangeId(dto.getId());
			List<Integer> status = exchangeCouponUnitStatusReadService.findUnitStatusAll(unitStatusDTO);
			map.put("status",status);
		} else {
			logger.info("dto为空");
		}
		result.setData(map);
		return result;
	}

	private List<Map<String,Object>> getOldNewBatchInfo(Long exchangeId, int type) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ExchangeBatchDTO exchangeBatchDTO = new ExchangeBatchDTO();
		exchangeBatchDTO.setExchangeId(exchangeId);
		exchangeBatchDTO.setType(type);
		List<ExchangeBatchDTO> exchangeBatchDTOList = exchangeBatchReadService.findExchangeBatchAll(exchangeBatchDTO);
		if (EmptyUtil.isNotEmpty(exchangeBatchDTOList)) {
			for (ExchangeBatchDTO batchDTO : exchangeBatchDTOList) {
				Map<String, Object> map = new HashMap<String, Object>();
				CouponBatchDTO couponBatchDTO1 = new CouponBatchDTO();
				couponBatchDTO1.setId(batchDTO.getBatchId());
				CouponBatchDTO couponBatchDTO = couponBatchReadService.findCouponBatchById(couponBatchDTO1);
				CouponDTO couponDTO = new CouponDTO();
				Long couponId = couponBatchDTO.getCouponRelId();
				couponDTO.setId(couponId);
				CouponDTO couponDTO2 = couponReadService.findCouponById(couponDTO);
				map.put("id",batchDTO.getBatchId());
				map.put("title",couponDTO2.getTitle());
				map.put("sort",batchDTO.getSort());
				BigDecimal addPrice = batchDTO.getAddPrice();
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(false);
				if (addPrice == null) {
					map.put("addPrice","0");
				}else {
					map.put("addPrice",nf.format(addPrice));
				}
				//判断优惠券类型 , 做字符拼接
				Integer couponType = couponDTO2.getCouponType();
				nf.setGroupingUsed(false);
				if (couponType == 0) {
					//满减券
					String discount = nf.format(couponDTO2.getDiscountAmount());
					String trigger = nf.format(couponDTO2.getTriggerAmount());
					String detail = "满"+trigger+"减"+discount;
					map.put("detail",detail);
				}else{
					String detail = "价值"+nf.format(couponDTO2.getDiscountAmount());
					map.put("detail",detail);
				}
				list.add(map);
			}
		}
		return list;
	}


}
	