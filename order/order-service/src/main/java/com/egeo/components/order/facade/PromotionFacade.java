package com.egeo.components.order.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.client.LinkableButtonClient;
import com.egeo.components.cms.client.LinkableButtonPageClient;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.order.common.DateUtils;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.vo.OrderConfirmCouponUnitVO;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.client.StoreTreeNodeClient;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.promotion.client.CouponBatchClient;
import com.egeo.components.promotion.client.CouponStoreClient;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Component
public class PromotionFacade {

	@Autowired
	private ECardClient eCardReadService;

	@Autowired
	private CouponUnitClient couponUnitReadService;

	@Autowired
	private StandardUnitClient standardUnitReadService;
	@Autowired
	private CouponStoreClient couponStoreReadService;
	@Autowired
	private StoreClient storeReadService;
	@Autowired
	private StoreTreeNodeClient storeTreeNodeReadService;
	
	@Autowired
	private ProductFacade productFacade;
	
	@Autowired
	private CouponBatchClient couponBatchReadService;
	
	@Autowired
	private LinkableButtonClient linkableButtonReadService;
	
	@Autowired
	private LinkableButtonPageClient linkableButtonPageReadService;
	/**
	 * 根据订单号查询卡券列表
	 * 
	 * @param orderCode
	 * @return
	 */
	public List<ECardDTO> queryECardListByOrderCode(String orderCode) {
		ECardDTO cond = new ECardDTO();
		cond.setOrderCode(orderCode);
		return eCardReadService.findECardAll(cond);
	}

	public List<OrderConfirmCouponUnitVO> calculateAfterCouponDetail(BigDecimal orderAmountTotal, BigDecimal payByFuBiAmount, BigDecimal orderAmountByFuBi, BigDecimal orderAmountOnlyCashTotal, List<OrderResult> orderResultList, Long storeId, Long couponUnitId,
																	 CouponUnitDTO couponUnitDTO, Long platformId, Map<String, Object> data, Map<Long, BigDecimal> deliveryPriceMap, Long clientId) {
		List<OrderConfirmCouponUnitVO> list = new ArrayList<OrderConfirmCouponUnitVO>();
		
		//如果是兑换券需要设置兑换券id
		if (couponUnitDTO.getCouponType() == 1) {
			// 兑换卷设置id
			couponUnitDTO.setId(couponUnitId);
		}
		// 1.查询出该用户的所有可用(满减卷)优惠卷unit(优惠卷批次有效)/兑换卷
		List<CouponUnitDTO> couponUnitDTOLists = couponUnitReadService.findCouponUnitOfAllByUser(couponUnitDTO);
		List<CouponUnitDTO> couponUnitDTOList = new ArrayList<>();
		//原有基础上过滤门店优惠卷
		for(CouponUnitDTO dto:couponUnitDTOLists){
			CouponStoreDTO couponStoreDTO = new CouponStoreDTO();
			couponStoreDTO.setCouponId(dto.getCouponId());
			couponStoreDTO.setStoreId(storeId);
			List<CouponStoreDTO> couponStoreAll = couponStoreReadService.findCouponStoreAll(couponStoreDTO);
			if(couponStoreAll.size()>1){
				throw new BusinessException("门店配置优惠卷有误");
			}
			if(couponStoreAll.size()==1){
				couponUnitDTOList.add(dto);

			}

		}
		if(EmptyUtil.isEmpty(couponUnitDTOList)){
			return list;
		}
		
		BigDecimal fubiBalance = new BigDecimal(String.valueOf(data.get("fubiBalance")));//积分账户余额
		//BigDecimal cashPay = new BigDecimal(String.valueOf(data.get("cashPay")));//商品金额-积分金额
		if (couponUnitDTO.getCouponType() == 0) {
			// 满减卷
			// 2.遍历所有可用优惠卷unit,查看所选商品是否在优惠卷unit的优惠卷的商品集合里
			for (CouponUnitDTO couponUnitDTO_ : couponUnitDTOList) {
				OrderConfirmCouponUnitVO orderConfirmCouponUnitVO = calculateSingleCouponDetail(orderAmountTotal,payByFuBiAmount,orderAmountByFuBi,couponUnitDTO_, couponUnitId, orderResultList, deliveryPriceMap, storeId, platformId, fubiBalance, orderAmountOnlyCashTotal,clientId);
				if (orderConfirmCouponUnitVO != null) {
					list.add(orderConfirmCouponUnitVO);
				}
			}
		} else if (couponUnitDTO.getCouponType() == 1) {
			// 兑换卷

			if (EmptyUtil.isNotEmpty(couponUnitDTOList)) {
				OrderConfirmCouponUnitVO orderConfirmCouponUnitVO = calculateSingleCouponDetail(orderAmountTotal, payByFuBiAmount, orderAmountByFuBi, couponUnitDTOList.get(0), couponUnitId, orderResultList, deliveryPriceMap, storeId, platformId, fubiBalance, orderAmountOnlyCashTotal,clientId);
				if (orderConfirmCouponUnitVO != null) {
					list.add(orderConfirmCouponUnitVO);
				}
			}
		}
		return list;
	}
	
	private OrderConfirmCouponUnitVO calculateSingleCouponDetail(BigDecimal orderAmountTotal, BigDecimal payByFuBiAmount, BigDecimal orderAmountByFuBi, CouponUnitDTO couponUnit, Long couponUnitId, List<OrderResult> orderResultList,
																 Map<Long, BigDecimal> deliveryPriceMap, Long storeId, Long platformId, BigDecimal fubiBalance, BigDecimal orderAmountOnlyCashTotal,
																 Long clientId) {
		OrderConfirmCouponUnitVO orderConfirmCouponUnitVO = null;
		outer:for (OrderResult orderResult : orderResultList) {
			// 判断关联商品类型
			if (couponUnit.getGoodsType() == 0) {
				// 0: 单su
				// 订单中包含优惠券关联的商品同时关联商品的总额达到优惠券的最低消费金额
				for (OrderConfirmGoodsDTO orderConfirmGoodsDTO : orderResult.getGoodsList()) {
					//是兑换券或者符合满减金额
					if(Integer.valueOf(1).equals(couponUnit.getCouponType()) || (orderConfirmGoodsDTO.getStandardUnitId().equals(couponUnit.getGoodsId())
							&& couponUnit.getTriggerAmount().doubleValue() <= orderConfirmGoodsDTO.getPrice()
									* orderConfirmGoodsDTO.getNum().doubleValue())) {
						//单个商品促发满减优惠
						orderConfirmCouponUnitVO = calculateSingleOrderResult(platformId, storeId, orderResult, couponUnit, couponUnitId, deliveryPriceMap,clientId);
						orderResult.setUseCoupon(true);
						break outer;
					}
				}
			} else if (couponUnit.getGoodsType() == 1) {
				// 1:商品组
				List<StandardUnitDTO> standardUnitDTOList = standardUnitReadService
						.findByStandardUnitCombinationId(couponUnit.getGoodsId(), platformId);

				double totalAmount = 0D;
				for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
					if (Integer.valueOf(1).equals(couponUnit.getCouponType())) {
						//是兑换券
						for (OrderConfirmGoodsDTO orderConfirmGoodsDTO : orderResult.getGoodsList()) {
							if (orderConfirmGoodsDTO.getStandardUnitId().equals(standardUnitDTO.getId())) {
								orderConfirmCouponUnitVO = calculateSingleOrderResult(platformId, storeId, orderResult, couponUnit, couponUnitId, deliveryPriceMap,clientId);
								orderResult.setUseCoupon(true);
								break outer;
							}
						}
					} else {
						// 3.如果在就判断符合商品合计金额是否达到满减的触发金额
						for (OrderConfirmGoodsDTO orderConfirmGoodsDTO : orderResult.getGoodsList()) {
							if (orderConfirmGoodsDTO.getStandardUnitId().equals(standardUnitDTO.getId())) {
								totalAmount += orderConfirmGoodsDTO.getPrice()
										* orderConfirmGoodsDTO.getNum().doubleValue();
							}
						}
						if (couponUnit.getTriggerAmount().doubleValue() <= totalAmount) {
							//商品组促发满减优惠
							orderConfirmCouponUnitVO = calculateSingleOrderResult(platformId, storeId, orderResult, couponUnit, couponUnitId, deliveryPriceMap,clientId);
							orderResult.setUseCoupon(true);
							break outer;
						}
					}
				}
			}
		}
		if (orderConfirmCouponUnitVO != null) {
			for (OrderResult orderResult : orderResultList) {
				if (!orderResult.isUseCoupon()) {
					orderConfirmCouponUnitVO.setAfterDiscountAmount(orderConfirmCouponUnitVO.getAfterDiscountAmount().add(orderResult.getOrderAmount()));
				}
			}
			BigDecimal totalDeliveryPrice = new BigDecimal(0);
			List<Map<String, Object>> deliveryPriceList = new ArrayList<>();
			for (Entry<Long, BigDecimal> dp : deliveryPriceMap.entrySet()) {
				totalDeliveryPrice = totalDeliveryPrice.add(dp.getValue());
				Map<String, Object> deliveryMap = new HashMap<>();
				deliveryMap.put("merchantId", dp.getKey());
				deliveryMap.put("deliveryPrice", dp.getValue());
				deliveryMap.put("deliveryMethod", dp.getValue().doubleValue() > 0 ? 1 : 0);
				deliveryPriceList.add(deliveryMap);
			}
			orderConfirmCouponUnitVO.setDeliveryPriceList(deliveryPriceList);
			orderConfirmCouponUnitVO.setTotalDeliveryPrice(totalDeliveryPrice);
			//1.已结算完优惠卷
			//2.结算门店折扣
			BigDecimal dicount=BigDecimal.valueOf(this.getDiscount(storeId,platformId)).divide(BigDecimal.valueOf(100));//折扣率*100
			//门店优惠金额
			BigDecimal storeDiscount = (orderConfirmCouponUnitVO.getAfterDiscountAmount().subtract(orderConfirmCouponUnitVO.getAfterDiscountAmount()
					.multiply(dicount))).setScale(2,BigDecimal.ROUND_DOWN);
			orderConfirmCouponUnitVO.setAfterDiscountAmount(orderConfirmCouponUnitVO.getAfterDiscountAmount()
					.multiply(dicount));
			
			//设置门店优惠金额
			orderConfirmCouponUnitVO.setStoreDiscount(storeDiscount);
			//总优惠金额
			BigDecimal discount=orderAmountTotal.subtract(orderConfirmCouponUnitVO.getAfterDiscountAmount());
			BigDecimal payByFuBi = payByFuBiAmount.subtract(discount);
			Integer canPay=1;
			if(payByFuBi.compareTo(fubiBalance)>0){
				canPay = 0;
			}
			orderConfirmCouponUnitVO.setCanPay(canPay);




			//3.结算运费
			orderConfirmCouponUnitVO.setAfterDiscountAmount(orderConfirmCouponUnitVO.getAfterDiscountAmount().add(totalDeliveryPrice));


			//当可用积分余额≥max{0,实付款 -总运费 – 仅支持现金支付的商品总额} + 总运费 时，可抵扣金额 = max{0，实付款 - 总运费 – 仅支持现金支付的商品总额} + 总运费
			//当可用积分余额≤max{0，实付款 - 总运费 – 仅支持现金支付的商品总额} + 总运费时，可抵扣金额 = 当可用积分余额
			BigDecimal compareAmount=orderConfirmCouponUnitVO.getAfterDiscountAmount().subtract(orderConfirmCouponUnitVO.getTotalDeliveryPrice()).subtract(orderAmountOnlyCashTotal);
			if(compareAmount.compareTo(BigDecimal.valueOf(0))>=0){
				//实付款 -总运费 – 仅支持现金支付的商品总额 max
				if(fubiBalance.compareTo(compareAmount.add(totalDeliveryPrice))>=0){
					//当可用积分余额>=max
					orderConfirmCouponUnitVO.setOrderAmountPaidByFuBi(compareAmount.add(totalDeliveryPrice));
				}else{
					//当可用积分余额<=max
					orderConfirmCouponUnitVO.setOrderAmountPaidByFuBi(fubiBalance);
				}

			}else{
				//0 max
				if(fubiBalance.compareTo(totalDeliveryPrice)>=0){
					//当可用积分余额>=max
					orderConfirmCouponUnitVO.setOrderAmountPaidByFuBi(totalDeliveryPrice);
				}else{
					//当可用积分余额<=max
					orderConfirmCouponUnitVO.setOrderAmountPaidByFuBi(fubiBalance);
				}
			}
			orderConfirmCouponUnitVO.setAfterDiscountCashPay(orderConfirmCouponUnitVO.getAfterDiscountAmount().subtract(orderConfirmCouponUnitVO.getOrderAmountPaidByFuBi()));

/*

			if (orderConfirmCouponUnitVO.getDiscountAmount() != null) {
				// 设置优惠卷后现金支付金额 : 折后现金支付价格 = 折后订单总额 - 积分余额 (正值判断:负值则需混合支付)
				//先计算积分可抵扣金额(商品可积分支付金额+总运费-优惠券金额-门店优惠金额)
				BigDecimal amountByFuBi=orderAmountByFuBi.add(totalDeliveryPrice).subtract(orderConfirmCouponUnitVO.getCouponDiscount()).subtract(storeDiscount);
				if(amountByFuBi.compareTo(BigDecimal.valueOf(0))<=0){
					orderConfirmCouponUnitVO.setOrderAmountPaidByFuBi(BigDecimal.valueOf(0));
					orderConfirmCouponUnitVO.setAfterDiscountCashPay(orderAmountOnlyCashTotal.add(amountByFuBi));
				}else{
					//如果可抵扣金额大于0
					if (fubiBalance.compareTo(amountByFuBi)<0) {
						//账户积分不足
						orderConfirmCouponUnitVO.setOrderAmountPaidByFuBi(fubiBalance);
						orderConfirmCouponUnitVO.setAfterDiscountCashPay(orderAmountOnlyCashTotal.add(amountByFuBi.subtract(fubiBalance)));

					}else{
						//账户积分充足
						orderConfirmCouponUnitVO.setOrderAmountPaidByFuBi(amountByFuBi);
						orderConfirmCouponUnitVO.setAfterDiscountCashPay(orderAmountOnlyCashTotal);
					}
				}
			}


			*/

		}
		return orderConfirmCouponUnitVO;
	}
	
	private OrderConfirmCouponUnitVO calculateSingleOrderResult(Long platformId, Long storeId, OrderResult orderResult,
			CouponUnitDTO couponUnitDTO_, Long couponUnitId, Map<Long, BigDecimal> deliveryPriceMap,Long clientId) {
		OrderConfirmCouponUnitVO orderConfirmCouponUnitVO = new OrderConfirmCouponUnitVO();
		orderConfirmCouponUnitVO.setCouponType(couponUnitDTO_.getCouponType());
		orderConfirmCouponUnitVO.setJumpType(couponUnitDTO_.getJumpType());
		orderConfirmCouponUnitVO.setCouponUnitStatus(couponUnitDTO_.getCouponUnitStatus());
		orderConfirmCouponUnitVO.setDetail(couponUnitDTO_.getDetail());
		orderConfirmCouponUnitVO.setDiscountAmount(
				couponUnitDTO_.getDiscountAmount() != null ? couponUnitDTO_.getDiscountAmount().intValue() : null);
		orderConfirmCouponUnitVO.setEffectEndTime(
				couponUnitDTO_.getEffectEndTime() != null ? couponUnitDTO_.getEffectEndTime().getTime() : null);
		orderConfirmCouponUnitVO.setEffectStartTime(
				couponUnitDTO_.getEffectStartTime() != null ? couponUnitDTO_.getEffectStartTime().getTime() : null);
		orderConfirmCouponUnitVO.setGoodsId(couponUnitDTO_.getGoodsId());
		orderConfirmCouponUnitVO.setGoodsType(couponUnitDTO_.getGoodsType());
		orderConfirmCouponUnitVO.setIconUrl(couponUnitDTO_.getIconUrl());
		orderConfirmCouponUnitVO.setId(couponUnitDTO_.getId());
		orderConfirmCouponUnitVO.setTitle(couponUnitDTO_.getTitle());
		orderConfirmCouponUnitVO.setTriggerAmount(
				couponUnitDTO_.getTriggerAmount() != null ? couponUnitDTO_.getTriggerAmount().intValue() : null);
		orderConfirmCouponUnitVO
				.setSelected(couponUnitId == null || !couponUnitDTO_.getId().equals(couponUnitId) ? false : true);

		// 有效期处理问题:已领取优惠卷unit,以unit有效期为准
		if (couponUnitDTO_.getEffectStartTime() == null || couponUnitDTO_.getEffectEndTime() == null) {
			orderConfirmCouponUnitVO.setEffectTimeRange("有效期:不限时间");
		} else {
			orderConfirmCouponUnitVO.setEffectTimeRange(
					DateUtils.format(DateUtils.DATE_FORMAT_POINT, couponUnitDTO_.getEffectStartTime()) + "-"
							+ DateUtils.format(DateUtils.DATE_FORMAT_POINT, couponUnitDTO_.getEffectEndTime()));
		}

		// 设置使用优惠卷后的订单总额,现金支付金额
		if (Integer.valueOf(0).equals(couponUnitDTO_.getCouponType())) {
			// 满减卷
			orderConfirmCouponUnitVO.setAfterDiscountAmount(orderResult.getOrderAmount());
			//设置优惠卷金额
			orderConfirmCouponUnitVO.setCouponDiscount(BigDecimal.valueOf(orderConfirmCouponUnitVO.getDiscountAmount()));
			orderConfirmCouponUnitVO.setAfterDiscountAmount(orderConfirmCouponUnitVO.getAfterDiscountAmount()
					.subtract(orderConfirmCouponUnitVO.getDiscountAmount() != null ?
							new BigDecimal(orderConfirmCouponUnitVO.getDiscountAmount()) : BigDecimal.ZERO));

			//1.已结算完优惠卷
			//2.计算门店折扣后的金额，用来计算运营方的运费
			BigDecimal dicount=BigDecimal.valueOf(this.getDiscount(storeId,platformId)).divide(BigDecimal.valueOf(100));//折扣率*100
			//门店折扣后金额
			BigDecimal afterStoreDiscount = (orderConfirmCouponUnitVO.getAfterDiscountAmount().multiply(dicount)).setScale(2,BigDecimal.ROUND_DOWN);
	
			if (orderResult.isNeedCountDelivery()) {//需要计算运费时重新计算
				double deliveryPrice = productFacade.freightAmountByMerchantId(afterStoreDiscount.doubleValue(), storeId, platformId, orderResult.getMerchant().getId());
				deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.valueOf(deliveryPrice));
			}
		} else if (Integer.valueOf(1).equals(couponUnitDTO_.getCouponType())) {
			// 兑换卷
			orderConfirmCouponUnitVO.setAfterDiscountAmount(BigDecimal.ZERO);
			orderConfirmCouponUnitVO.setAfterDiscountCashPay(BigDecimal.ZERO);
		}

		// 设置订单总额(疑问)
		if (couponUnitId != null && couponUnitDTO_.getId().equals(couponUnitId)) {
			// 选中的优惠卷
			orderConfirmCouponUnitVO.setSelected(true);
//			data.put("orderAmount", orderConfirmCouponUnitVO.getAfterDiscountAmount());
		} else {
			orderConfirmCouponUnitVO.setSelected(false);
		}
		orderConfirmCouponUnitVO.setCmsPageId(findCmsPageByCouponUnitId(couponUnitDTO_,clientId,platformId));
		orderConfirmCouponUnitVO.setIsShowEffectTimeRange(dealEffectTimeRange(couponUnitDTO_));
		return orderConfirmCouponUnitVO;
	}
	
	
	/**
	 * 通过优惠卷unit的id和商品集合查询对应优惠卷信息
	 * 
	 * @param couponUnitId
	 * @param goodsList
	 */
	public List<OrderConfirmCouponUnitVO> queryCouponUnitListByIdAndGoodsList(Long storeId,Long couponUnitId,
			CouponUnitDTO couponUnitDTO, Long platformId, List<OrderConfirmGoodsDTO> goodsList,
			Map<String, Object> data) {
		//如果是兑换券需要设置兑换券id
		if (couponUnitDTO.getCouponType() == 1) {
			// 兑换卷设置id
			couponUnitDTO.setId(couponUnitId);
		}
		List<OrderConfirmCouponUnitVO> list = new ArrayList<OrderConfirmCouponUnitVO>();
		BigDecimal orderAmount = new BigDecimal(String.valueOf((data.get("orderAmount"))));
		BigDecimal fubiBalance = new BigDecimal(String.valueOf(data.get("fubiBalance")));
		BigDecimal cashPay = new BigDecimal(String.valueOf(data.get("cashPay")));//商品金额-积分金额
		BigDecimal deliveryPrice = new BigDecimal(String.valueOf(data.get("totalDeliveryPrice")));

		// 1.查询出该用户的所有可用(满减卷)优惠卷unit(优惠卷批次有效)/兑换卷
		List<CouponUnitDTO> couponUnitDTOLists = couponUnitReadService.findCouponUnitOfAllByUser(couponUnitDTO);
		List<CouponUnitDTO> couponUnitDTOList = new ArrayList<>();
		if(EmptyUtil.isEmpty(couponUnitDTOLists)){
			return list;
		}
		//原有基础上过滤门店优惠卷
		for(CouponUnitDTO dto:couponUnitDTOLists){
			CouponStoreDTO couponStoreDTO = new CouponStoreDTO();
			couponStoreDTO.setCouponId(dto.getCouponId());
			couponStoreDTO.setStoreId(storeId);
			List<CouponStoreDTO> couponStoreAll = couponStoreReadService.findCouponStoreAll(couponStoreDTO);
			if(couponStoreAll.size()>1){
				throw new BusinessException("门店配置优惠卷有误");
			}
			if(couponStoreAll.size()==1){
				couponUnitDTOList.add(dto);

			}

		}




		if (couponUnitDTO.getCouponType() == 0) {
			// 满减卷
			// 2.遍历所有可用优惠卷unit,查看所选商品是否在优惠卷unit的优惠卷的商品集合里
			for (CouponUnitDTO couponUnitDTO_ : couponUnitDTOList) {
				// 判断关联商品类型
				if (couponUnitDTO_.getGoodsType() == 0) {
					// 0: 单su
					// 订单中包含优惠券关联的商品同时关联商品的总额达到优惠券的最低消费金额
					for (OrderConfirmGoodsDTO orderConfirmGoodsDTO : goodsList) {
						if (orderConfirmGoodsDTO.getStandardUnitId().equals(couponUnitDTO_.getGoodsId())
								&& couponUnitDTO_.getTriggerAmount().doubleValue() <= orderConfirmGoodsDTO.getPrice()
										* orderConfirmGoodsDTO.getNum().doubleValue()) {

							//单个商品促发满减优惠
							addOrderConfirmCouponUnitVO(platformId,storeId,deliveryPrice,couponUnitDTO_, couponUnitId, list, data, orderAmount, fubiBalance,cashPay);
							break;
						}
					}

				} else if (couponUnitDTO_.getGoodsType() == 1) {
					// 1:商品组
					List<StandardUnitDTO> standardUnitDTOList = standardUnitReadService
							.findByStandardUnitCombinationId(couponUnitDTO_.getGoodsId(), platformId);

					double totalAmount = 0D;
					for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
						// 3.如果在就判断符合商品合计金额是否达到满减的触发金额
						for (OrderConfirmGoodsDTO orderConfirmGoodsDTO : goodsList) {
							if (orderConfirmGoodsDTO.getStandardUnitId().equals(standardUnitDTO.getId())) {
								totalAmount += orderConfirmGoodsDTO.getPrice()
										* orderConfirmGoodsDTO.getNum().doubleValue();
							}
						}
						if (couponUnitDTO_.getTriggerAmount().doubleValue() <= totalAmount) {

							//商品组促发满减优惠
							addOrderConfirmCouponUnitVO(platformId,storeId,deliveryPrice,couponUnitDTO_, couponUnitId, list, data, orderAmount,fubiBalance,cashPay);
							break;
						}
					}
				}
			}
		} else if (couponUnitDTO.getCouponType() == 1) {
			// 兑换卷

			if (EmptyUtil.isNotEmpty(couponUnitDTOList)) {
				CouponUnitDTO couponUnitDTO_ = couponUnitDTOList.get(0);
				// 判断关联商品类型
				OrderConfirmGoodsDTO orderConfirmGoodsDTO = goodsList.get(0);
				if (couponUnitDTO_.getGoodsType() == 0) {
					// 0: 单su
					if (orderConfirmGoodsDTO.getStandardUnitId().equals(couponUnitDTO_.getGoodsId())) {

						addOrderConfirmCouponUnitVO(platformId,storeId,deliveryPrice,couponUnitDTO_, couponUnitId, list, data, orderAmount,fubiBalance,cashPay);
					}
				} else if (couponUnitDTO_.getGoodsType() == 1) {
					// 1:商品组
					List<StandardUnitDTO> standardUnitDTOList = standardUnitReadService
							.findByStandardUnitCombinationId(couponUnitDTO_.getGoodsId(), platformId);

					for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
						if (orderConfirmGoodsDTO.getStandardUnitId().equals(standardUnitDTO.getId())) {
							addOrderConfirmCouponUnitVO(platformId,storeId,deliveryPrice,couponUnitDTO_, couponUnitId, list, data, orderAmount,fubiBalance,cashPay);
							break;
						}
					}
				}
			}
		}/*else{
			OrderConfirmCouponUnitVO orderConfirmCouponUnitVO = new OrderConfirmCouponUnitVO();
			orderConfirmCouponUnitVO.setCouponType(-1);
			orderConfirmCouponUnitVO.setCouponDiscount(BigDecimal.valueOf(0));
			Long dicount=this.getDiscount(storeId,platformId);//折扣率*100
			BigDecimal afterDiscount = orderAmount.multiply(BigDecimal.valueOf(dicount));
			orderConfirmCouponUnitVO.setStoreDiscount(orderAmount.subtract(afterDiscount));
			orderConfirmCouponUnitVO.setAfterDiscountAmount(afterDiscount.add(deliveryPrice));
		}*/






		return list;
	}

	private void addOrderConfirmCouponUnitVO(Long platformId,Long storeId,BigDecimal deliveryPrice,CouponUnitDTO couponUnitDTO_, Long couponUnitId,
			List<OrderConfirmCouponUnitVO> list, Map<String, Object> data, BigDecimal orderAmount, BigDecimal fubiBalance, BigDecimal cashPay) {

		OrderConfirmCouponUnitVO orderConfirmCouponUnitVO = new OrderConfirmCouponUnitVO();
		orderConfirmCouponUnitVO.setCouponType(couponUnitDTO_.getCouponType());
		orderConfirmCouponUnitVO.setJumpType(couponUnitDTO_.getJumpType());
		orderConfirmCouponUnitVO.setCouponUnitStatus(couponUnitDTO_.getCouponUnitStatus());
		orderConfirmCouponUnitVO.setDetail(couponUnitDTO_.getDetail());
		orderConfirmCouponUnitVO.setDiscountAmount(
				couponUnitDTO_.getDiscountAmount() != null ? couponUnitDTO_.getDiscountAmount().intValue() : null);
		orderConfirmCouponUnitVO.setEffectEndTime(
				couponUnitDTO_.getEffectEndTime() != null ? couponUnitDTO_.getEffectEndTime().getTime() : null);
		orderConfirmCouponUnitVO.setEffectStartTime(
				couponUnitDTO_.getEffectStartTime() != null ? couponUnitDTO_.getEffectStartTime().getTime() : null);
		orderConfirmCouponUnitVO.setGoodsId(couponUnitDTO_.getGoodsId());
		orderConfirmCouponUnitVO.setGoodsType(couponUnitDTO_.getGoodsType());
		orderConfirmCouponUnitVO.setIconUrl(couponUnitDTO_.getIconUrl());
		orderConfirmCouponUnitVO.setId(couponUnitDTO_.getId());
		orderConfirmCouponUnitVO.setTitle(couponUnitDTO_.getTitle());
		orderConfirmCouponUnitVO.setTriggerAmount(
				couponUnitDTO_.getTriggerAmount() != null ? couponUnitDTO_.getTriggerAmount().intValue() : null);
		orderConfirmCouponUnitVO
				.setSelected(couponUnitId == null || !couponUnitDTO_.getId().equals(couponUnitId) ? false : true);

		// 有效期处理问题:已领取优惠卷unit,以unit有效期为准
		if (couponUnitDTO_.getEffectStartTime() == null || couponUnitDTO_.getEffectEndTime() == null) {
			orderConfirmCouponUnitVO.setEffectTimeRange("有效期:不限时间");
		} else {
			orderConfirmCouponUnitVO.setEffectTimeRange(
					DateUtils.format(DateUtils.DATE_FORMAT_POINT, couponUnitDTO_.getEffectStartTime()) + "-"
							+ DateUtils.format(DateUtils.DATE_FORMAT_POINT, couponUnitDTO_.getEffectEndTime()));
		}

		// 设置使用优惠卷后的订单总额,现金支付金额
		if (Integer.valueOf(0).equals(couponUnitDTO_.getCouponType())) {
			// 满减卷
			orderConfirmCouponUnitVO.setAfterDiscountAmount(orderAmount);
			//设置优惠卷金额
			orderConfirmCouponUnitVO.setCouponDiscount(BigDecimal.valueOf(orderConfirmCouponUnitVO.getDiscountAmount()));
			orderConfirmCouponUnitVO.setAfterDiscountAmount(orderConfirmCouponUnitVO.getAfterDiscountAmount()
					.subtract(orderConfirmCouponUnitVO.getDiscountAmount() != null ?
							new BigDecimal(orderConfirmCouponUnitVO.getDiscountAmount()) : BigDecimal.ZERO));


		//1.已结算完优惠卷
			//2.结算门店折扣
			BigDecimal dicount=BigDecimal.valueOf(this.getDiscount(storeId,platformId)).divide(BigDecimal.valueOf(100));//折扣率*100
			//门店优惠金额
			BigDecimal storeDiscount = (orderConfirmCouponUnitVO.getAfterDiscountAmount().subtract(orderConfirmCouponUnitVO.getAfterDiscountAmount()
					.multiply(dicount))).setScale(2,BigDecimal.ROUND_DOWN);
			orderConfirmCouponUnitVO.setAfterDiscountAmount(orderConfirmCouponUnitVO.getAfterDiscountAmount()
					.multiply(dicount));

			//设置门店优惠金额

			orderConfirmCouponUnitVO.setStoreDiscount(storeDiscount);

			//3.结算运费
			orderConfirmCouponUnitVO.setAfterDiscountAmount(orderConfirmCouponUnitVO.getAfterDiscountAmount()
					.add(deliveryPrice));











			// 设置优惠卷后现金支付金额 : 折后现金支付价格 = 折后订单总额 - 积分余额 (正值判断:负值则需混合支付)
			orderConfirmCouponUnitVO.setAfterDiscountCashPay(cashPay);
			BigDecimal afterDiscountCashPay = null;
			if (orderConfirmCouponUnitVO.getDiscountAmount() != null) {
				afterDiscountCashPay = orderConfirmCouponUnitVO.getAfterDiscountAmount().subtract(fubiBalance);
				orderConfirmCouponUnitVO.setAfterDiscountCashPay(afterDiscountCashPay.compareTo(BigDecimal.ZERO) >= 0 ? 
						afterDiscountCashPay : BigDecimal.ZERO);
			}
			
		} else if (Integer.valueOf(1).equals(couponUnitDTO_.getCouponType())) {
			// 兑换卷
			orderConfirmCouponUnitVO.setAfterDiscountAmount(BigDecimal.ZERO);
			orderConfirmCouponUnitVO.setAfterDiscountCashPay(BigDecimal.ZERO);
		}

		// 设置订单总额(疑问)
		if (couponUnitId != null && couponUnitDTO_.getId().equals(couponUnitId)) {
			// 选中的优惠卷
			orderConfirmCouponUnitVO.setSelected(true);
			data.put("orderAmount", orderConfirmCouponUnitVO.getAfterDiscountAmount());
		} else {
			orderConfirmCouponUnitVO.setSelected(false);
		}

		list.add(orderConfirmCouponUnitVO);
	}




	public Long getDiscount(Long storeId,Long platformId){
		StoreDTO dto = new StoreDTO();
		dto.setId(storeId);
		StoreDTO storeById = storeReadService.findStoreById(dto);
		if(storeById.getDiscount()==null){
			StoreTreeNodeDTO treeNodeDTO = new StoreTreeNodeDTO();
			treeNodeDTO.setStoreId(storeId);
			treeNodeDTO.setPlatformId(platformId);
			List<StoreTreeNodeDTO> storeTreeNodeAll = storeTreeNodeReadService.findStoreTreeNodeAll(treeNodeDTO);
			if(storeTreeNodeAll==null){
				throw new BusinessException("总店折扣不能为空");
			}else if(storeTreeNodeAll.size()>1){
				throw new BusinessException("一个门店只能有一个上级门店");

			}
			this.getDiscount(storeTreeNodeAll.get(0).getParentId(), platformId);


		}

		return storeById.getDiscount();


	}
	
	/**
	 * cmsPageId
	 * @param dto
	 * @param clientId
	 * @param platformId
	 * @return
	 */
	public Long findCmsPageByCouponUnitId(CouponUnitDTO dto,Long clientId,Long platformId){
		Long cmsPageId = null;
		if(dto.getJumpType() != 2) {
			return null;
		}
		Integer clientType = clientId == 3 ? CmsConstant.CMS_CLINTE_TYPE_PC : CmsConstant.CMS_CLINTE_TYPE_MOBILE;
		CouponBatchDTO couponBatchDTO = couponBatchReadService.findCouponBatchById(new CouponBatchDTO(dto.getCouponBatchId()));
		
		if(couponBatchDTO != null ) {
			LinkableButtonPageDTO linkableButtonPageDTO = new LinkableButtonPageDTO(couponBatchDTO.getLinkableId());
			linkableButtonPageDTO.setClientType(clientType);
			List<LinkableButtonPageDTO> list = linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(couponBatchDTO.getLinkableId()));
			if(list != null && list.size() > 0) {
				linkableButtonPageDTO = list.get(0);
				cmsPageId = linkableButtonPageDTO.getCmsPageId();
			}
		}
		
		if(cmsPageId == null) {
			cmsPageId = getDefaultPageId(platformId,clientType ,dto.getCouponType());
		}
		
		return cmsPageId;
	}
	
	/**
	 * 获取默认初始化页面
	 * @param platformId
	 * @param clienType
	 * @param couponType
	 * @return
	 */
	public Long getDefaultPageId(Long platformId,Integer clientType ,Integer couponType) {
		
		Long cmsPageId = null;
		
		if(platformId == null || couponType == null) {
			return null;
		}
		if(platformId == CmsConstant.CMS_PLATFORM_FGJ) {
			
			if(clientType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
				
				if(couponType == CmsConstant.CMS_COUPON_TYPE_FULL_REDUCED) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_3;
				} else if(couponType == CmsConstant.CMS_COUPON_TYPE_EXCHANGE) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_4;
				}
				
			} else {
				
				if(couponType == CmsConstant.CMS_COUPON_TYPE_FULL_REDUCED) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_5;
				} else if(couponType == CmsConstant.CMS_COUPON_TYPE_EXCHANGE) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_6;
				}
			}
		} else if(platformId == CmsConstant.CMS_PLATFORM_MYY) {
			
				if(clientType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
				
				if(couponType == CmsConstant.CMS_COUPON_TYPE_FULL_REDUCED) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_11;
				} else if(couponType == CmsConstant.CMS_COUPON_TYPE_EXCHANGE) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_12;
				}
				
			} else {
				
				if(couponType == CmsConstant.CMS_COUPON_TYPE_FULL_REDUCED) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_13;
				} else if(couponType == CmsConstant.CMS_COUPON_TYPE_EXCHANGE) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_14;
				}
			}
		}
		
		return cmsPageId;
	}
	
	private boolean dealEffectTimeRange(CouponUnitDTO couponUnitDTO) {
		boolean isShowEffectTimeRange = false;
		if (couponUnitDTO.getId() != null) {
			// 已领取优惠卷unit,以unit有效期为准
			if (couponUnitDTO.getEffectStartTime() == null || couponUnitDTO.getEffectEndTime() == null) {
				isShowEffectTimeRange = true;
			} else {
				if (couponUnitDTO.getEffectStartTime().before(new Date()) 
						&& couponUnitDTO.getEffectEndTime().after(new Date())) {
					isShowEffectTimeRange = true;
				} 
			}
			
		} else {
			// 未领取,以批次有效期为准
			if (couponUnitDTO.getEffectDays() != null && !couponUnitDTO.getEffectDays().equals(Integer.valueOf(-1))){
				isShowEffectTimeRange = true;
			} else if (couponUnitDTO.getCouponBatchEffectStartTime() != null && couponUnitDTO.getCouponBatchEffectEndTime() != null) {
				
				if (couponUnitDTO.getCouponBatchEffectStartTime().before(new Date()) 
						&& couponUnitDTO.getCouponBatchEffectEndTime().after(new Date())) {
					isShowEffectTimeRange = true;
				} 
			} else {
				
				isShowEffectTimeRange = true;
			}
		}
		return isShowEffectTimeRange;
	}

}
