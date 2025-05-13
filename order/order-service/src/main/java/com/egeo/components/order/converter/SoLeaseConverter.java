package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoLeaseDTO;
import com.egeo.components.order.po.SoLeasePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoLeaseConverter {
	
	public static SoLeaseDTO toDTO(SoLeasePO src) {
		SoLeaseDTO tar = new SoLeaseDTO();
		tar.setId(src.getId());
		tar.setLeaseCode(src.getLeaseCode());
		tar.setLeaseStatus(src.getLeaseStatus());
		tar.setLeaseAuditStatus(src.getLeaseAuditStatus());
		tar.setAffirmStatus(src.getAffirmStatus());
		tar.setProductId(src.getProductId());
		tar.setProductName(src.getProductName());
		tar.setRentalPrice(src.getRentalPrice());
		tar.setDepositPrice(src.getDepositPrice());
		tar.setProductPrice(src.getProductPrice());
		tar.setProductNum(src.getProductNum());
		tar.setCode(src.getCode());
		tar.setLeaseStartTime(src.getLeaseStartTime());
		tar.setLeaseEndTime(src.getLeaseEndTime());
		tar.setLeaseRemarkUser(src.getLeaseRemarkUser());
		tar.setLeaseRemarkC(src.getLeaseRemarkC());
		tar.setLeaseRemarkCUser(src.getLeaseRemarkCUser());
		tar.setLinkPhone(src.getLinkPhone());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setUserPhone(src.getUserPhone());
		tar.setCId(src.getCId());
		tar.setCName(src.getCName());
		tar.setAskTime(src.getAskTime());
		tar.setCommitUserId(src.getCommitUserId());
		tar.setCommitUserName(src.getCommitUserName());
		tar.setCommitTime(src.getCommitTime());
		tar.setAuditUserId(src.getAuditUserId());
		tar.setAuditUserName(src.getAuditUserName());
		tar.setAuditTime(src.getAuditTime());
		tar.setAuditReason(src.getAuditReason());
		tar.setCancelReasonUser(src.getCancelReasonUser());
		tar.setCancelReasonC(src.getCancelReasonC());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setMerchantName(src.getMerchantName());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoLeasePO toPO(SoLeaseDTO src) {
		SoLeasePO tar = new SoLeasePO();
		tar.setId(src.getId());
		tar.setLeaseCode(src.getLeaseCode());
		tar.setLeaseStatus(src.getLeaseStatus());
		tar.setLeaseAuditStatus(src.getLeaseAuditStatus());
		tar.setAffirmStatus(src.getAffirmStatus());
		tar.setProductId(src.getProductId());
		tar.setProductName(src.getProductName());
		tar.setRentalPrice(src.getRentalPrice());
		tar.setDepositPrice(src.getDepositPrice());
		tar.setProductPrice(src.getProductPrice());
		tar.setProductNum(src.getProductNum());
		tar.setCode(src.getCode());
		tar.setLeaseStartTime(src.getLeaseStartTime());
		tar.setLeaseEndTime(src.getLeaseEndTime());
		tar.setLeaseRemarkUser(src.getLeaseRemarkUser());
		tar.setLeaseRemarkC(src.getLeaseRemarkC());
		tar.setLeaseRemarkCUser(src.getLeaseRemarkCUser());
		tar.setLinkPhone(src.getLinkPhone());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setUserPhone(src.getUserPhone());
		tar.setCId(src.getCId());
		tar.setCName(src.getCName());
		tar.setAskTime(src.getAskTime());
		tar.setCommitUserId(src.getCommitUserId());
		tar.setCommitUserName(src.getCommitUserName());
		tar.setCommitTime(src.getCommitTime());
		tar.setAuditUserId(src.getAuditUserId());
		tar.setAuditUserName(src.getAuditUserName());
		tar.setAuditTime(src.getAuditTime());
		tar.setAuditReason(src.getAuditReason());
		tar.setCancelReasonUser(src.getCancelReasonUser());
		tar.setCancelReasonC(src.getCancelReasonC());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setMerchantName(src.getMerchantName());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoLeaseDTO> toDTO(List<SoLeasePO> srcs) {
		if (srcs == null)
			return null;
		List<SoLeaseDTO> list = new ArrayList<SoLeaseDTO>();
		for (SoLeasePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoLeasePO> toPO(List<SoLeaseDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoLeasePO> list = new ArrayList<SoLeasePO>();
		for (SoLeaseDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	