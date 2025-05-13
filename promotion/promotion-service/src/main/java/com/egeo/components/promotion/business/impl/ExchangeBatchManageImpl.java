package com.egeo.components.promotion.business.impl;
	

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.ExchangeBatchManage;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.facade.CouponUnitFacade;
import com.egeo.components.promotion.facade.ExchangeBatchFacade;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("exchangeBatch")
public class ExchangeBatchManageImpl implements ExchangeBatchManage{

	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public static Integer EXCHANGE_PU_ID_FGJ=-2;
	public static Integer EXCHANGE_PU_ID_MYY=-3;

	@Resource(name="exchangeBatchFacade")
	private ExchangeBatchFacade exchangeBatchFacade;

	@Resource(name = "couponUnitFacade")
	private CouponUnitFacade couponUnitFacade;

	@Override
	public ExchangeBatchDTO findExchangeBatchById(ExchangeBatchDTO dto) {
		return exchangeBatchFacade.findExchangeBatchById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findExchangeBatchOfPage(ExchangeBatchDTO dto, Pagination page) {
		return exchangeBatchFacade.findExchangeBatchOfPage(dto, page);
	}

	@Override
	public List<ExchangeBatchDTO> findExchangeBatchAll(ExchangeBatchDTO dto) {
		return exchangeBatchFacade.findExchangeBatchAll(dto);
	}

	@Override
	public Long insertExchangeBatchWithTx(ExchangeBatchDTO dto) {
		return exchangeBatchFacade.insertExchangeBatchWithTx(dto);
	}

	@Override
	public int updateExchangeBatchWithTx(ExchangeBatchDTO dto) {
		return exchangeBatchFacade.updateExchangeBatchWithTx(dto);
	}

	@Override
	public int deleteExchangeBatchWithTx(ExchangeBatchDTO dto) {
		return exchangeBatchFacade.deleteExchangeBatchWithTx(dto);
	}

	@Override
	public JsonResult checkExchangeValid(Long couponUnitId) {
		//1.校验当前优惠券couponUnit是否存在有效的活动
		//2.校验当前unit状态是否符合
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setId(couponUnitId);
		CouponUnitDTO unitDTO = couponUnitFacade.findCouponUnitById(couponUnitDTO);
		List<Long> res=exchangeBatchFacade.findExchangeActivityByOldCouponUnitId(unitDTO.getCouponBatchId(),unitDTO.getCouponUnitStatus());

		if(EmptyUtil.isNotEmpty(res)){
			return JsonResult.success("校验成功");
		}else{
			return JsonResult.fail("该券无法参加以旧换新活动",BusinessExceptionConstant.NO_EXCHANGE);
		}

	}

	@Override
	public JsonResult<Map<String, Object>> checkAndExchange(Long platformId, Integer type, Long userId, String userName, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId) {
		//1.校验有效性
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setId(exchangeCouponUnitId);
		CouponUnitDTO unitDTO = couponUnitFacade.findCouponUnitById(couponUnitDTO);
		Boolean flag=checkCouponUnitAndCouponBatchId(unitDTO.getCouponBatchId(),unitDTO.getCouponUnitStatus(),exchangeCouponBatchId);
		Boolean count = ckeckCouponBatchCount(exchangeCouponBatchId);
		if(!count){
			return JsonResult.fail("该券已领完",BusinessExceptionConstant.COUPON_ALL_USED);
		}
		if(!flag){
			logger.info("[兑换流程校验是否有效]错误:没有可用兑换的数量/无效的关系:flag="+flag+"**count="+count);
			return JsonResult.fail("兑换失败", BusinessExceptionConstant.EXCHANGE_FAIL);
		}
		//1.1校验新批次的;领取日期是否有效
		CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
		couponBatchDTO.setId(exchangeCouponBatchId);
		CouponBatchDTO couponBatchById = couponUnitFacade.findCouponBatchById(couponBatchDTO);
		if(EmptyUtil.isEmpty(couponBatchById)){
			logger.info("[兑换流程校验是否有效]错误:未查询到目标批次");
			return JsonResult.fail("兑换失败",BusinessExceptionConstant.EXCHANGE_FAIL);
		}else if(couponBatchById.getIsDisplay()==0){
			logger.info("[兑换流程校验是否有效]错误:目标批次前端不可显示");
			return JsonResult.fail("兑换失败",BusinessExceptionConstant.EXCHANGE_FAIL);
		}else if(couponBatchById.getReceiveEndTime().getTime()<new Date().getTime()){
			logger.info("[兑换流程校验是否有效]错误:目标批次可领取时间已过");
			return JsonResult.fail("兑换失败",BusinessExceptionConstant.EXCHANGE_FAIL);
		}else if(couponBatchById.getReceiveStartTime().getTime()>new Date().getTime()){
			logger.info("[兑换流程校验是否有效]错误:目标批次可领取时间还未到");
			return JsonResult.fail("兑换失败",BusinessExceptionConstant.EXCHANGE_FAIL);
		}else if(couponBatchById.getIsEffect()==1){
			logger.info("[兑换流程校验是否有效]错误:目标批次已失效");
			return JsonResult.fail("兑换失败",BusinessExceptionConstant.EXCHANGE_FAIL);
		}

		//1.2校验当前用户是否领取过该优惠券
		CouponUnitDTO couponUnitDTO1 = new CouponUnitDTO();
		couponUnitDTO1.setCouponBatchId(exchangeCouponBatchId);
		couponUnitDTO1.setUserId(userId);
		List<CouponUnitDTO> couponUnitAll = couponUnitFacade.findCouponUnitAll(couponUnitDTO1);
		if(EmptyUtil.isNotEmpty(couponUnitAll)){
			if(couponBatchById.getIsRepeat()==0){
				logger.info("[兑换流程校验是否有效]错误:目标批次不可重复领取");
				return JsonResult.fail("兑换失败",BusinessExceptionConstant.EXCHANGE_FAIL);
			}
		}


		//2.返回兑换方式
		ExchangeBatchDTO exchangeBatchDTO = new ExchangeBatchDTO();
		exchangeBatchDTO.setExchangeId(exchangeId);
		exchangeBatchDTO.setBatchId(exchangeCouponBatchId);
		exchangeBatchDTO.setType(1);
		List<ExchangeBatchDTO> exchangeBatchAll = exchangeBatchFacade.findExchangeBatchAll(exchangeBatchDTO);
		if(EmptyUtil.isEmpty(exchangeBatchAll)||exchangeBatchAll.size()>1){
			logger.info("[兑换流程校验是否有效]错误:查询新的批次出错,size="+exchangeBatchAll.size());
			return JsonResult.fail("兑换失败", BusinessExceptionConstant.EXCHANGE_FAIL);
		}
		Map<String, Object> map = new HashMap<>();
		ExchangeBatchDTO dto=exchangeBatchAll.get(0);
		//0.加价兑换,1.立即兑换
		int addPriceType = EmptyUtil.isNotEmpty(dto.getAddPrice()) && dto.getAddPrice().compareTo(BigDecimal.valueOf(0)) > 0 ? 0 : 1;
		int exchangeType=0;
		int puId;
		if(platformId==2){
			puId=EXCHANGE_PU_ID_MYY;
		}else{
			puId=EXCHANGE_PU_ID_FGJ;
		}
		if(addPriceType==0){
			//加价购
			//判断是否已拥有订单
			//查询新批次
			CouponBatchDTO batchDTO = new CouponBatchDTO();
			batchDTO.setId(exchangeCouponBatchId);
			CouponBatchDTO newBatch = couponUnitFacade.findCouponBatchById(batchDTO);
			//查询订单记录
			ExchangeOrderRecordDTO exchangeOrderRecordDTO = new ExchangeOrderRecordDTO();
			exchangeOrderRecordDTO.setOldUnitCode(unitDTO.getCouponUnitCode());
			exchangeOrderRecordDTO.setNewBatchCode(newBatch.getCouponBatchCode());

			List<ExchangeOrderRecordDTO> recordDTOList=exchangeBatchFacade.findExchangeOrderRecord(exchangeOrderRecordDTO);
			if(EmptyUtil.isEmpty(recordDTOList)){
				if(type==1){
					//发起订单确认页
					exchangeType=0;
					map.put("num",dto.getAddPrice().intValue());
					map.put("puId",puId);
					logger.info("puId="+puId);
					logger.info("num="+dto.getAddPrice().intValue());
				}else if(type==2){
					//发起订单创建
					exchangeType=4;
					map.put("num",dto.getAddPrice().intValue());
					map.put("puId",puId);
					logger.info("puId="+puId);
					logger.info("num="+dto.getAddPrice().intValue());
				}

			}else if(recordDTOList.size()>1){
				logger.info("同一个unit对应多个兑换记录");
				return JsonResult.fail("兑换失败", BusinessExceptionConstant.EXCHANGE_FAIL);
			}else if(recordDTOList.get(0).getOrderAmount().compareTo(dto.getAddPrice())==0){
				//加价金额与原订单金额一致直接进入订单详情
				exchangeType=2;
				map.put("orderCode",recordDTOList.get(0).getOrderCode());
			}else{
				//取消订单
				exchangeType=3;
				map.put("orderCode", recordDTOList.get(0).getOrderCode());
				map.put("num",dto.getAddPrice().intValue());
				map.put("puId",puId);
				logger.info("puId="+puId);
				logger.info("num="+dto.getAddPrice().intValue());
			}

		}else{
			//立即兑换
			exchangeType=1;
		}

		map.put("exchangeType",exchangeType);
		return JsonResult.success(map);


	}

	@Override
	public JsonResult exchangeRightNow(Long userId, String userName, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId) {
		//进行依旧换新
		Boolean res=exchangeBatchFacade.exchangeRightNow(userId,userName,exchangeCouponBatchId,exchangeCouponUnitId,exchangeId);
		if(!res){
			logger.info("[以旧换新立即兑换]错误");
			return JsonResult.fail("兑换失败", BusinessExceptionConstant.EXCHANGE_FAIL);
		}
		return JsonResult.success("兑换成功");
	}

	//校验旧批次unit和新批次
	private Boolean checkCouponUnitAndCouponBatchId(Long oldBatchId, Integer couponUnitStatus,Long batchId){
		//查询旧unit对应的活动id
		List<Long> list = couponUnitFacade.checkIsShowExchange(oldBatchId, couponUnitStatus);
		if(EmptyUtil.isEmpty(list)){
			throw new BusinessException("兑换失败");
		}
		//查询新的batch对应的活动id
		List<Long> activityIds=exchangeBatchFacade.findExchangeByBatchId(batchId);
		Boolean flag=false;
		for(Long id:list){
			for(Long newId:activityIds){
				if(id.equals(newId)){
					flag=true;
				}
			}
		}
		return flag;
	}
	//校验优惠券批次是否还有剩余的可领券
	private Boolean ckeckCouponBatchCount(Long batchId){
		CouponBatchDTO batchDTO = new CouponBatchDTO();
		batchDTO.setId(batchId);
		CouponBatchDTO couponBatchDTO=couponUnitFacade.findCouponBatchById(batchDTO);
		if(EmptyUtil.isEmpty(couponBatchDTO)){
			throw new BusinessException("优惠券批次不存在");
		}
		//查询已领取的优惠券数量
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setCouponBatchId(batchId);
		Long couponUnitAllCount = couponUnitFacade.findCouponUnitAllCount(couponUnitDTO);
		if(couponBatchDTO.getGrantCount()==-1||couponUnitAllCount.compareTo(Long.valueOf(couponBatchDTO.getGrantCount()))<0){
			return true;
		}else{
			return false;
		}


	}


}
	