package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoModifyPriceDTO;
import com.egeo.components.order.po.SoModifyPricePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoModifyPriceConverter {
	
	public static SoModifyPriceDTO toDTO(SoModifyPricePO src) {
		SoModifyPriceDTO tar = new SoModifyPriceDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOrderBeforeAmount(src.getOrderBeforeAmount());
		tar.setOrderDeliveryFee(src.getOrderDeliveryFee());
		tar.setOrderBeforeDeliveryFee(src.getOrderBeforeDeliveryFee());
		tar.setAuditStatus(src.getAuditStatus());
		tar.setCommitUserId(src.getCommitUserId());
		tar.setCommitUserName(src.getCommitUserName());
		tar.setCommitTime(src.getCommitTime());
		tar.setAuditUserId(src.getAuditUserId());
		tar.setAuditUserName(src.getAuditUserName());
		tar.setAuditTime(src.getAuditTime());
		tar.setAuditReason(src.getAuditReason());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantId(src.getMerchantId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoModifyPricePO toPO(SoModifyPriceDTO src) {
		SoModifyPricePO tar = new SoModifyPricePO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOrderBeforeAmount(src.getOrderBeforeAmount());
		tar.setOrderDeliveryFee(src.getOrderDeliveryFee());
		tar.setOrderBeforeDeliveryFee(src.getOrderBeforeDeliveryFee());
		tar.setAuditStatus(src.getAuditStatus());
		tar.setCommitUserId(src.getCommitUserId());
		tar.setCommitUserName(src.getCommitUserName());
		tar.setCommitTime(src.getCommitTime());
		tar.setAuditUserId(src.getAuditUserId());
		tar.setAuditUserName(src.getAuditUserName());
		tar.setAuditTime(src.getAuditTime());
		tar.setAuditReason(src.getAuditReason());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantId(src.getMerchantId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoModifyPriceDTO> toDTO(List<SoModifyPricePO> srcs) {
		if (srcs == null)
			return null;
		List<SoModifyPriceDTO> list = new ArrayList<SoModifyPriceDTO>();
		for (SoModifyPricePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoModifyPricePO> toPO(List<SoModifyPriceDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoModifyPricePO> list = new ArrayList<SoModifyPricePO>();
		for (SoModifyPriceDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	