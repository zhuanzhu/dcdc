package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.condition.SoChildCondition;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildDTOCondition;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.components.order.vo.SoChildVO;

/**
 * DTO和PO相互转换工具类
 *
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class SoChildConverter {


	public static SoChildDTO toDTO(SoChildVO src) {
		if (src == null)
		return null;
		SoChildDTO tar = new SoChildDTO();
		tar.setId(src.getId());
		tar.setChildCode(src.getChildCode());
		tar.setOrdinaryId(src.getOrdinaryId());
		tar.setSoId(src.getSoId());
		tar.setDeliveryId(src.getDeliveryId());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		tar.setAmount(src.getAmount());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setPerformingParty(src.getPerformingParty());
		tar.setSupplierId(src.getSupplierId());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setPlatformId(src.getPlatformId());
		tar.setThirdpartyType(src.getThirdpartyType());
		tar.setPreSell(src.getPreSell());
		tar.setDeliverEndTime(src.getDeliverEndTime());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setNeedCountDeliveryFee(src.getNeedCountDeliveryFee());
		tar.setRemark(src.getRemark());
		tar.setProductAmount(src.getProductAmount());
		tar.setDeliveryFeeDiscount(tar.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setCouponId(src.getCouponId());
		tar.setDeliveryCouponId(src.getDeliveryCouponId());
		tar.setOrdinaryDeliveryFee(src.getOrdinaryDeliveryFee());
		tar.setThirdpatyDiscountAmount(src.getThirdpatyDiscountAmount());
		tar.setExt(src.getExt());
		tar.setCancelStatus(src.getCancelStatus());
		return tar;
	}

	public static SoChildVO toVO(SoChildDTO src) {
		if (src == null)
		return null;
		SoChildVO tar = new SoChildVO();
		tar.setId(src.getId());
		tar.setChildCode(src.getChildCode());
		tar.setOrdinaryId(src.getOrdinaryId());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		tar.setSoId(src.getSoId());
		tar.setDeliveryId(src.getDeliveryId());
		tar.setAmount(src.getAmount());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setPerformingParty(src.getPerformingParty());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setSupplierId(src.getSupplierId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setPlatformId(src.getPlatformId());
		tar.setThirdpartyType(src.getThirdpartyType());
		tar.setPreSell(src.getPreSell());
		tar.setDeliverEndTime(src.getDeliverEndTime());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setNeedCountDeliveryFee(src.getNeedCountDeliveryFee());
		tar.setRemark(src.getRemark());
		tar.setProductAmount(src.getProductAmount());
		tar.setDeliveryFeeDiscount(tar.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setCouponId(src.getCouponId());
		tar.setDeliveryCouponId(src.getDeliveryCouponId());
		tar.setOrdinaryDeliveryFee(src.getOrdinaryDeliveryFee());
		tar.setSupplierId(src.getSupplierId());
		tar.setThirdpatyDiscountAmount(src.getThirdpatyDiscountAmount());
		tar.setExt(src.getExt());
		tar.setCancelStatus(src.getCancelStatus());
		tar.setChildDeleteStatus(src.getChildDeleteStatus());
		return tar;
	}

	public static List<SoChildDTO> toDTOs(List<SoChildVO> srcs) {
		if (srcs == null)
			return null;
		List<SoChildDTO> list = new ArrayList<SoChildDTO>();
		for (SoChildVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoChildVO> toVO(List<SoChildDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoChildVO> list = new ArrayList<SoChildVO>();
		for (SoChildDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoChildDTO toDTO(SoChildPO src) {
		if (src == null)
		return null;
		SoChildDTO tar = new SoChildDTO();
		tar.setId(src.getId());
		tar.setOrdinaryId(src.getOrdinaryId());
		tar.setDepotId(src.getDepotId());
		tar.setSoId(src.getSoId());
		tar.setAmount(src.getAmount());
		tar.setDeliveryId(src.getDeliveryId());
		tar.setPerformingParty(src.getPerformingParty());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundType(src.getRefundType());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		tar.setInvoiceId(src.getInvoiceId());
		tar.setInvoiceAmount(src.getInvoiceAmount());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setRemark(src.getRemark());
		tar.setSupplierId(src.getSupplierId());
		tar.setPlatformId(src.getPlatformId());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setChildCode(src.getChildCode());
		tar.setLastOperateTime(src.getLastOperateTime());
		tar.setThirdpartyType(src.getThirdpartyType());
		tar.setPreSell(src.getPreSell());
		tar.setDeliverEndTime(src.getDeliverEndTime());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setNeedCountDeliveryFee(src.getNeedCountDeliveryFee());
		tar.setProductAmount(src.getProductAmount());
		tar.setDeliveryFeeDiscount(tar.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setCouponId(src.getCouponId());
		tar.setDeliveryCouponId(src.getDeliveryCouponId());
		tar.setOrdinaryDeliveryFee(src.getOrdinaryDeliveryFee());
		tar.setThirdpartySoChildId(src.getThirdpartySoChildId());
		tar.setThirdpartySoChildStatus(src.getThirdpartySoChildStatus());
		tar.setThirdpartySoChildPayAmount(src.getThirdpartySoChildPayAmount());
		tar.setThirdpartySoChildCode(src.getThirdpartySoChildCode());
		tar.setThirdpatyDiscountAmount(src.getThirdpatyDiscountAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setExt(src.getExt());
		tar.setCancelStatus(src.getCancelStatus());
		tar.setChildDeleteStatus(src.getChildDeleteStatus());
		return tar;
	}

	public static SoChildDTOCondition toDTOCondition(SoChildPO src) {
		if (src == null)
		return null;
		SoChildDTOCondition tar = new SoChildDTOCondition();
		tar.setId(src.getId());
		tar.setChildCode(src.getChildCode());
		tar.setOrdinaryId(src.getOrdinaryId());
		tar.setDepotId(src.getDepotId());
		tar.setSoId(src.getSoId());
		tar.setAmount(src.getAmount());
		tar.setDeliveryId(src.getDeliveryId());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundType(src.getRefundType());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setPerformingParty(src.getPerformingParty());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setInvoiceAmount(src.getInvoiceAmount());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setRemark(src.getRemark());
		tar.setPlatformId(src.getPlatformId());
		tar.setLastOperateTime(src.getLastOperateTime());
		tar.setThirdpartyType(src.getThirdpartyType());
		tar.setPreSell(src.getPreSell());
		tar.setDeliverEndTime(src.getDeliverEndTime());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setNeedCountDeliveryFee(src.getNeedCountDeliveryFee());
		tar.setProductAmount(src.getProductAmount());
		tar.setDeliveryFeeDiscount(tar.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setCouponId(src.getCouponId());
		tar.setDeliveryCouponId(src.getDeliveryCouponId());
		tar.setOrdinaryDeliveryFee(src.getOrdinaryDeliveryFee());
		tar.setThirdpartySoChildId(src.getThirdpartySoChildId());
		tar.setThirdpartySoChildCode(src.getThirdpartySoChildCode());
		tar.setThirdpartySoChildStatus(src.getThirdpartySoChildStatus());
		tar.setThirdpartySoChildPayAmount(src.getThirdpartySoChildPayAmount());
		tar.setSupplierId(src.getSupplierId());
		tar.setThirdpatyDiscountAmount(src.getThirdpatyDiscountAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setExt(src.getExt());
		tar.setCancelStatus(src.getCancelStatus());
		tar.setChildDeleteStatus(src.getChildDeleteStatus());
		return tar;
	}

	public static SoChildPO toPO(SoChildDTO src) {
		if (src == null)
			return null;
		SoChildPO tar = new SoChildPO();
		tar.setId(src.getId());
		tar.setChildCode(src.getChildCode());
		tar.setOrdinaryId(src.getOrdinaryId());
		tar.setDepotId(src.getDepotId());
		tar.setSoId(src.getSoId());
		tar.setAmount(src.getAmount());
		tar.setDeliveryId(src.getDeliveryId());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundType(src.getRefundType());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setSupplierId(src.getSupplierId());
		tar.setSupplierId(src.getSupplierId());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setPerformingParty(src.getPerformingParty());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setInvoiceAmount(src.getInvoiceAmount());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setRemark(src.getRemark());
		tar.setPlatformId(src.getPlatformId());
		tar.setLastOperateTime(src.getLastOperateTime());
		tar.setThirdpartyType(src.getThirdpartyType());
		tar.setPreSell(src.getPreSell());
		tar.setDeliverEndTime(src.getDeliverEndTime());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setNeedCountDeliveryFee(src.getNeedCountDeliveryFee());
		tar.setProductAmount(src.getProductAmount());
		tar.setDeliveryFeeDiscount(src.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setCouponId(src.getCouponId());
		tar.setDeliveryCouponId(src.getDeliveryCouponId());
		tar.setOrdinaryDeliveryFee(src.getOrdinaryDeliveryFee());
		tar.setThirdpartySoChildId(src.getThirdpartySoChildId());
		tar.setThirdpartySoChildCode(src.getThirdpartySoChildCode());
		tar.setThirdpartySoChildStatus(src.getThirdpartySoChildStatus());
		tar.setThirdpartySoChildPayAmount(src.getThirdpartySoChildPayAmount());
		tar.setThirdpatyDiscountAmount(src.getThirdpatyDiscountAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setExt(src.getExt());
		tar.setCancelStatus(src.getCancelStatus());
		tar.setChildDeleteStatus(src.getChildDeleteStatus());
		return tar;
	}

	public static SoChildDTO conditionToDTO(SoChildCondition src) {
		if (src == null)
		return null;
		SoChildDTO tar = new SoChildDTO();
		tar.setId(src.getId());
		tar.setOrdinaryId(src.getOrdinaryId());
		tar.setDepotId(src.getDepotId());
		tar.setSoId(src.getSoId());
		tar.setAmount(src.getAmount());
		tar.setDeliveryId(src.getDeliveryId());
		tar.setPerformingParty(src.getPerformingParty());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundType(src.getRefundType());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setInvoiceAmount(src.getInvoiceAmount());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setLastOperatorId(src.getLastOperatorId());
		tar.setRemark(src.getRemark());
		tar.setPlatformId(src.getPlatformId());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setChildCode(src.getChildCode());
		tar.setLastOperateTime(src.getLastOperateTime());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		tar.setThirdpartyType(src.getThirdpartyType());
		tar.setPreSell(src.getPreSell());
		tar.setDeliverEndTime(src.getDeliverEndTime());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setNeedCountDeliveryFee(src.getNeedCountDeliveryFee());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderCode(src.getOrderCode());
		tar.setOrderPaymentConfirmDate(src.getOrderPaymentConfirmDate());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setSupplierId(src.getSupplierId());
		tar.setGoodReceiverMobile(tar.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setProductAmount(src.getProductAmount());
		tar.setDeliveryFeeDiscount(tar.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setCouponId(src.getCouponId());
		tar.setDeliveryCouponId(src.getDeliveryCouponId());
		tar.setOrdinaryDeliveryFee(src.getOrdinaryDeliveryFee());
		tar.setThirdpartySoChildId(src.getThirdpartySoChildId());
		tar.setThirdpartySoChildCode(src.getThirdpartySoChildCode());
		tar.setThirdpartySoChildStatus(src.getThirdpartySoChildStatus());
		tar.setThirdpartySoChildPayAmount(src.getThirdpartySoChildPayAmount());
		tar.setDiscount(src.getDiscount());
		tar.setUserId(src.getUserId());
		tar.setOrderConfirmStatus(src.getOrderConfirmStatus());
		tar.setOrderPayStatus(src.getOrderPayStatus());
		tar.setSupplierId(src.getSupplierId());
		tar.setThirdpatyDiscountAmount(src.getThirdpatyDiscountAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setExt(src.getExt());
		tar.setCancelStatus(src.getCancelStatus());
		return tar;
	}

	public static SoChildCondition toCondition(SoChildDTO src) {
		if (src == null)
			return null;
		SoChildCondition tar = new SoChildCondition();
		tar.setId(src.getId());
		tar.setChildCode(src.getChildCode());
		tar.setOrdinaryId(src.getOrdinaryId());
		tar.setDepotId(src.getDepotId());
		tar.setSoId(src.getSoId());
		tar.setAmount(src.getAmount());
		tar.setDeliveryId(src.getDeliveryId());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundType(src.getRefundType());
		tar.setModifyAddressTime(src.getModifyAddressTime());
		tar.setModifiedAddress(src.getModifiedAddress());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setSupplierId(src.getSupplierId());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setPerformingParty(src.getPerformingParty());
		tar.setInvoiceId(src.getInvoiceId());
		tar.setInvoiceAmount(src.getInvoiceAmount());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setLastOperatorId(src.getLastOperatorId());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		tar.setRemark(src.getRemark());
		tar.setPlatformId(src.getPlatformId());
		tar.setLastOperateTime(src.getLastOperateTime());
		tar.setThirdpartyType(src.getThirdpartyType());
		tar.setPreSell(src.getPreSell());
		tar.setDeliverEndTime(src.getDeliverEndTime());
		tar.setDeliveryFee(src.getDeliveryFee());
		tar.setNeedCountDeliveryFee(src.getNeedCountDeliveryFee());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderCode(src.getOrderCode());
		tar.setOrderPaymentConfirmDate(src.getOrderPaymentConfirmDate());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(tar.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setProductAmount(src.getProductAmount());
		tar.setDeliveryFeeDiscount(tar.getDeliveryFeeDiscount());
		tar.setCouponDiscount(src.getCouponDiscount());
		tar.setStoreDiscount(src.getStoreDiscount());
		tar.setCouponId(src.getCouponId());
		tar.setDeliveryCouponId(src.getDeliveryCouponId());
		tar.setOrdinaryDeliveryFee(src.getOrdinaryDeliveryFee());
		tar.setThirdpartySoChildId(src.getThirdpartySoChildId());
		tar.setThirdpartySoChildCode(src.getThirdpartySoChildCode());
		tar.setThirdpartySoChildStatus(src.getThirdpartySoChildStatus());
		tar.setThirdpartySoChildPayAmount(src.getThirdpartySoChildPayAmount());
		tar.setThirdpatyDiscountAmount(src.getThirdpatyDiscountAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setExt(src.getExt());
		tar.setCancelStatus(src.getCancelStatus());
		return tar;
	}

	public static List<SoChildDTO> conditionToDTO(List<SoChildCondition> srcs) {
		if (srcs == null)
			return null;
		List<SoChildDTO> list = new ArrayList<SoChildDTO>();
		for (SoChildCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}

	public static List<SoChildDTO> toDTO(List<SoChildPO> srcs) {
		if (srcs == null)
			return null;
		List<SoChildDTO> list = new ArrayList<SoChildDTO>();
		for (SoChildPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static List<SoChildDTOCondition> toDTOCondition(List<SoChildPO> srcs) {
		if (srcs == null)
			return null;
		List<SoChildDTOCondition> list = new ArrayList<SoChildDTOCondition>();
		for (SoChildPO src : srcs) {
			list.add(toDTOCondition(src));
		}
		return list;
	}

	public static List<SoChildPO> toPO(List<SoChildDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoChildPO> list = new ArrayList<SoChildPO>();
		for (SoChildDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}


}
