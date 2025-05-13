package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.po.SoPO;
import com.egeo.components.order.vo.SoVO;

/**
 * DTO和PO相互转换工具类
 *
 * @author jiang
 * @date 2018-01-29 17:56:48
 */
public class SoConverter {


	public static SoDTO toDTO(SoVO src) {
		if (src == null)
		return null;
		SoDTO tar = new SoDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOrderAmountPay(src.getOrderAmountPay());
		tar.setProductAmount(src.getProductAmount());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderConfirmStatus(src.getOrderConfirmStatus());
		tar.setOrderPayStatus(src.getOrderPayStatus());
		tar.setOrderAutoCompleteDate(src.getOrderAutoCompleteDate());
		tar.setOrderPaymentConfirmDate(src.getOrderPaymentConfirmDate());
		tar.setOrderPaymentConfirmType(src.getOrderPaymentConfirmType());
		tar.setOrderDeliveryFee(src.getOrderDeliveryFee());
		tar.setOrderDeliveryFeeInsuranceType(src.getOrderDeliveryFeeInsuranceType());
		tar.setOrderDeliveryFeeInsuranceAmount(src.getOrderDeliveryFeeInsuranceAmount());
		tar.setCashPayType(src.getCashPayType());
		tar.setOrderPaidByCash(src.getOrderPaidByCash());
		tar.setOrderPaidByCard(src.getOrderPaidByCard());
		tar.setOrderPaidByRebate(src.getOrderPaidByRebate());
		tar.setOrderPaidByFubi(src.getOrderPaidByFubi());
		tar.setUseFubi(src.getUseFubi());
		tar.setOrderPromotionDiscount(src.getOrderPromotionDiscount());
		tar.setOrderGivePoints(src.getOrderGivePoints());
		tar.setCancelReason(src.getCancelReason());
		tar.setOrderCancelDate(src.getOrderCancelDate());
		tar.setOrderCanceOperateType(src.getOrderCanceOperateType());
		tar.setOrderChannel(src.getOrderChannel());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setOrderDeleteStatus(src.getOrderDeleteStatus());
		tar.setPaidOnlineThreshold(src.getPaidOnlineThreshold());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setIncrementTaxAmount(src.getIncrementTaxAmount());
		tar.setCustomsDutiesAmount(src.getCustomsDutiesAmount());
		tar.setDeliveryEndtime(src.getDeliveryEndtime());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setRefundTime(src.getRefundTime());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSaleWay(src.getSaleWay());
		tar.setStoreId(src.getStoreId());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setDeliveryFeeDiscount(src.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setLimitCashPayAmount(src.getLimitCashPayAmount());
		tar.setExt(src.getExt());
		return tar;
	}

	public static SoVO toVO(SoDTO src) {
		if (src == null)
		return null;
		SoVO tar = new SoVO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOrderAmountPay(src.getOrderAmountPay());
		tar.setProductAmount(src.getProductAmount());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderConfirmStatus(src.getOrderConfirmStatus());
		tar.setOrderPayStatus(src.getOrderPayStatus());
		tar.setOrderAutoCompleteDate(src.getOrderAutoCompleteDate());
		tar.setOrderPaymentConfirmDate(src.getOrderPaymentConfirmDate());
		tar.setOrderPaymentConfirmType(src.getOrderPaymentConfirmType());
		tar.setOrderDeliveryFee(src.getOrderDeliveryFee());
		tar.setOrderDeliveryFeeInsuranceType(src.getOrderDeliveryFeeInsuranceType());
		tar.setOrderDeliveryFeeInsuranceAmount(src.getOrderDeliveryFeeInsuranceAmount());
		tar.setCashPayType(src.getCashPayType());
		tar.setOrderPaidByCash(src.getOrderPaidByCash());
		tar.setOrderPaidByCard(src.getOrderPaidByCard());
		tar.setOrderPaidByRebate(src.getOrderPaidByRebate());
		tar.setOrderPaidByFubi(src.getOrderPaidByFubi());
		tar.setUseFubi(src.getUseFubi());
		tar.setOrderPromotionDiscount(src.getOrderPromotionDiscount());
		tar.setOrderGivePoints(src.getOrderGivePoints());
		tar.setCancelReason(src.getCancelReason());
		tar.setOrderCancelDate(src.getOrderCancelDate());
		tar.setOrderCanceOperateType(src.getOrderCanceOperateType());
		tar.setOrderChannel(src.getOrderChannel());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setOrderDeleteStatus(src.getOrderDeleteStatus());
		tar.setPaidOnlineThreshold(src.getPaidOnlineThreshold());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setIncrementTaxAmount(src.getIncrementTaxAmount());
		tar.setCustomsDutiesAmount(src.getCustomsDutiesAmount());
		tar.setDeliveryEndtime(src.getDeliveryEndtime());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setRefundTime(src.getRefundTime());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSaleWay(src.getSaleWay());
		tar.setStoreId(src.getStoreId());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setDeliveryFeeDiscount(src.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setLimitCashPayAmount(src.getLimitCashPayAmount());
		tar.setExt(src.getExt());
		tar.setOrderPaidByFubi(src.getOrderPaidByJidian());
		tar.setOrderCardPaid(src.getOrderCardPaid());
		tar.setRefundCard(src.getRefundCard());
		return tar;
	}

	public static List<SoDTO> toDTOs(List<SoVO> srcs) {
		if (srcs == null)
			return null;
		List<SoDTO> list = new ArrayList<SoDTO>();
		for (SoVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoVO> toVO(List<SoDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoVO> list = new ArrayList<SoVO>();
		for (SoDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoDTO toDTO(SoPO src) {
		if (src == null)
		return null;
		SoDTO tar = new SoDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOrderAmountPay(src.getOrderAmountPay());
		tar.setProductAmount(src.getProductAmount());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderConfirmStatus(src.getOrderConfirmStatus());
		tar.setOrderPayStatus(src.getOrderPayStatus());
		tar.setOrderAutoCompleteDate(src.getOrderAutoCompleteDate());
		tar.setOrderPaymentConfirmDate(src.getOrderPaymentConfirmDate());
		tar.setOrderPaymentConfirmType(src.getOrderPaymentConfirmType());
		tar.setOrderDeliveryFee(src.getOrderDeliveryFee());
		tar.setOrderDeliveryFeeInsuranceType(src.getOrderDeliveryFeeInsuranceType());
		tar.setOrderDeliveryFeeInsuranceAmount(src.getOrderDeliveryFeeInsuranceAmount());
		tar.setCashPayType(src.getCashPayType());
		tar.setOrderPaidByCash(src.getOrderPaidByCash());
		tar.setOrderPaidByCard(src.getOrderPaidByCard());
		tar.setOrderPaidByRebate(src.getOrderPaidByRebate());
		tar.setOrderPaidByFubi(src.getOrderPaidByFubi());
		tar.setUseFubi(src.getUseFubi());
		tar.setOrderPromotionDiscount(src.getOrderPromotionDiscount());
		tar.setOrderGivePoints(src.getOrderGivePoints());
		tar.setCancelReason(src.getCancelReason());
		tar.setOrderCancelDate(src.getOrderCancelDate());
		tar.setOrderCanceOperateType(src.getOrderCanceOperateType());
		tar.setOrderChannel(src.getOrderChannel());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setOrderDeleteStatus(src.getOrderDeleteStatus());
		tar.setPaidOnlineThreshold(src.getPaidOnlineThreshold());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setIncrementTaxAmount(src.getIncrementTaxAmount());
		tar.setCustomsDutiesAmount(src.getCustomsDutiesAmount());
		tar.setDeliveryEndtime(src.getDeliveryEndtime());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setRefundTime(src.getRefundTime());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setRefundCash(src.getRefundCash());
		tar.setRefundFubi(src.getRefundFubi());
		tar.setCompanyId(src.getCompanyId());
		tar.setSignPlatform(src.getSignPlatform());
		tar.setSaleWay(src.getSaleWay());
		tar.setStoreId(src.getStoreId());
		tar.setCouponUnitId(src.getCouponUnitId());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setDeliveryFeeDiscount(src.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setLimitCashPayAmount(src.getLimitCashPayAmount());
		tar.setLimitFuBiPayAmount(src.getLimitFuBiPayAmount());
		tar.setAuditStatus(src.getAuditStatus());
		tar.setExt(src.getExt());
		tar.setOrderPaidByJidian(src.getOrderPaidByJidian());
		tar.setRefundJidian(src.getRefundJidian());
		tar.setOrderCardPaid(src.getOrderCardPaid());
		tar.setRefundCard(src.getRefundCard());
		return tar;
	}

	public static SoPO toPO(SoDTO src) {
		if (src == null)
		return null;
		SoPO tar = new SoPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOrderAmountPay(src.getOrderAmountPay());
		tar.setProductAmount(src.getProductAmount());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderConfirmStatus(src.getOrderConfirmStatus());
		tar.setOrderPayStatus(src.getOrderPayStatus());
		tar.setOrderAutoCompleteDate(src.getOrderAutoCompleteDate());
		tar.setOrderPaymentConfirmDate(src.getOrderPaymentConfirmDate());
		tar.setOrderPaymentConfirmType(src.getOrderPaymentConfirmType());
		tar.setOrderDeliveryFee(src.getOrderDeliveryFee());
		tar.setOrderDeliveryFeeInsuranceType(src.getOrderDeliveryFeeInsuranceType());
		tar.setOrderDeliveryFeeInsuranceAmount(src.getOrderDeliveryFeeInsuranceAmount());
		tar.setCashPayType(src.getCashPayType());
		tar.setOrderPaidByCash(src.getOrderPaidByCash());
		tar.setOrderPaidByCard(src.getOrderPaidByCard());
		tar.setOrderPaidByRebate(src.getOrderPaidByRebate());
		tar.setOrderPaidByFubi(src.getOrderPaidByFubi());
		tar.setUseFubi(src.getUseFubi());
		tar.setOrderPromotionDiscount(src.getOrderPromotionDiscount());
		tar.setOrderGivePoints(src.getOrderGivePoints());
		tar.setCancelReason(src.getCancelReason());
		tar.setOrderCancelDate(src.getOrderCancelDate());
		tar.setOrderCanceOperateType(src.getOrderCanceOperateType());
		tar.setOrderChannel(src.getOrderChannel());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setOrderDeleteStatus(src.getOrderDeleteStatus());
		tar.setPaidOnlineThreshold(src.getPaidOnlineThreshold());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setIncrementTaxAmount(src.getIncrementTaxAmount());
		tar.setCustomsDutiesAmount(src.getCustomsDutiesAmount());
		tar.setDeliveryEndtime(src.getDeliveryEndtime());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setRefundTime(src.getRefundTime());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setRefundCash(src.getRefundCash());
		tar.setRefundFubi(src.getRefundFubi());
		tar.setCompanyId(src.getCompanyId());
		tar.setSignPlatform(src.getSignPlatform());
		tar.setSaleWay(src.getSaleWay());
		tar.setStoreId(src.getStoreId());
		tar.setCouponUnitId(src.getCouponUnitId());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setDeliveryFeeDiscount(src.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setLimitCashPayAmount(src.getLimitCashPayAmount());
		tar.setLimitFuBiPayAmount(src.getLimitFuBiPayAmount());
		tar.setAuditStatus(src.getAuditStatus());
		tar.setExt(src.getExt());
		tar.setOrderPaidByJidian(src.getOrderPaidByJidian());
		tar.setRefundJidian(src.getRefundJidian());
		tar.setOrderCardPaid(src.getOrderCardPaid());
		tar.setRefundCard(src.getRefundCard());
		return tar;
	}

	public static List<SoDTO> toDTO(List<SoPO> srcs) {
		if (srcs == null)
			return null;
		List<SoDTO> list = new ArrayList<SoDTO>();
		for (SoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoPO> toPO(List<SoDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoPO> list = new ArrayList<SoPO>();
		for (SoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
