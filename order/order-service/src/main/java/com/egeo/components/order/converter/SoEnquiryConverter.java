package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoEnquiryDTO;
import com.egeo.components.order.po.SoEnquiryPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoEnquiryConverter {
	
	public static SoEnquiryDTO toDTO(SoEnquiryPO src) {
		SoEnquiryDTO tar = new SoEnquiryDTO();
		tar.setId(src.getId());
		tar.setEnquiryCode(src.getEnquiryCode());
		tar.setEnquiryStatus(src.getEnquiryStatus());
		tar.setEnquiryAuditStatus(src.getEnquiryAuditStatus());
		tar.setAffirmStatus(src.getAffirmStatus());
		tar.setProductId(src.getProductId());
		tar.setProductName(src.getProductName());
		tar.setProductPrice(src.getProductPrice());
		tar.setProductNum(src.getProductNum());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setUserPhone(src.getUserPhone());
		tar.setCId(src.getCId());
		tar.setCName(src.getCName());
		tar.setAskTime(src.getAskTime());
		tar.setOfferTime(src.getOfferTime());
		tar.setEnquiryRemarkUser(src.getEnquiryRemarkUser());
		tar.setEnquiryRemarkC(src.getEnquiryRemarkC());
		tar.setPlatformId(src.getPlatformId());
		tar.setAuditReason(src.getAuditReason());
		tar.setLinkPhone(src.getLinkPhone());
		tar.setCancelReasonUser(src.getCancelReasonUser());
		tar.setCancelReasonC(src.getCancelReasonC());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setMerchantName(src.getMerchantName());
		tar.setEnquiryRemarkCUser(src.getEnquiryRemarkCUser());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static SoEnquiryPO toPO(SoEnquiryDTO src) {
		SoEnquiryPO tar = new SoEnquiryPO();
		tar.setId(src.getId());
		tar.setEnquiryCode(src.getEnquiryCode());
		tar.setEnquiryStatus(src.getEnquiryStatus());
		tar.setEnquiryAuditStatus(src.getEnquiryAuditStatus());
		tar.setAffirmStatus(src.getAffirmStatus());
		tar.setProductId(src.getProductId());
		tar.setProductName(src.getProductName());
		tar.setProductPrice(src.getProductPrice());
		tar.setProductNum(src.getProductNum());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setUserPhone(src.getUserPhone());
		tar.setCId(src.getCId());
		tar.setCName(src.getCName());
		tar.setAskTime(src.getAskTime());
		tar.setOfferTime(src.getOfferTime());
		tar.setEnquiryRemarkUser(src.getEnquiryRemarkUser());
		tar.setEnquiryRemarkC(src.getEnquiryRemarkC());
		tar.setPlatformId(src.getPlatformId());
		tar.setAuditReason(src.getAuditReason());
		tar.setLinkPhone(src.getLinkPhone());
		tar.setCancelReasonUser(src.getCancelReasonUser());
		tar.setCancelReasonC(src.getCancelReasonC());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setMerchantName(src.getMerchantName());
		tar.setEnquiryRemarkCUser(src.getEnquiryRemarkCUser());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<SoEnquiryDTO> toDTO(List<SoEnquiryPO> srcs) {
		if (srcs == null)
			return null;
		List<SoEnquiryDTO> list = new ArrayList<SoEnquiryDTO>();
		for (SoEnquiryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoEnquiryPO> toPO(List<SoEnquiryDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoEnquiryPO> list = new ArrayList<SoEnquiryPO>();
		for (SoEnquiryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	