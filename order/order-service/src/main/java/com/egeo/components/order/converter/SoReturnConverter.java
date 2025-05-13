package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoReturnDTO;
import com.egeo.components.order.po.SoReturnPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:25
 */
public class SoReturnConverter {
	
	public static SoReturnDTO toDTO(SoReturnPO src) {
		SoReturnDTO tar = new SoReturnDTO();
		tar.setId(src.getId());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setReturnStatus(src.getReturnStatus());
		tar.setRefundStatus(src.getRefundStatus());
		tar.setActualReturnAmount(src.getActualReturnAmount());
		tar.setApplyReturnAmount(src.getApplyReturnAmount());
		tar.setReturnType(src.getReturnType());
		tar.setReturnRemark(src.getReturnRemark());
		tar.setServiceUserId(src.getServiceUserId());
		tar.setServiceDesc(src.getServiceDesc());
		tar.setServiceReturnReason(src.getServiceReturnReason());
		tar.setApplyTime(src.getApplyTime());
		tar.setReturnReason(src.getReturnReason());
		tar.setRefundTime(src.getRefundTime());
		tar.setConsigneeAddress(src.getConsigneeAddress());
		tar.setConsigneeName(src.getConsigneeName());
		tar.setConsigneeMobile(src.getConsigneeMobile());
		tar.setCourierNumber(src.getCourierNumber());
		tar.setLogisticsCompany(src.getLogisticsCompany());
		tar.setRefundConfirmUserId(src.getRefundConfirmUserId());
		tar.setRefundNo(src.getRefundNo());
		tar.setAuditReason(src.getAuditReason());
		tar.setAuditUserId(src.getAuditUserId());
		tar.setAuditUserName(src.getAuditUserName());
		tar.setFreight(src.getFreight());
		tar.setCancelStatus(src.getCancelStatus());
		tar.setAuditTime(src.getAuditTime());
		tar.setType(src.getType());
		tar.setOrderCreateTime(src.getOrderCreateTime());
		tar.setReturnReasonId(src.getReturnReasonId());
		tar.setReturnCode(src.getReturnCode());
		tar.setIsPickUp(src.getIsPickUp());
		tar.setUserCourierNumber(src.getUserCourierNumber());
		tar.setUserLogisticsCompanyId(src.getUserLogisticsCompanyId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SoReturnPO toPO(SoReturnDTO src) {
		SoReturnPO tar = new SoReturnPO();
		tar.setId(src.getId());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setReturnStatus(src.getReturnStatus());
		tar.setRefundStatus(src.getRefundStatus());
		tar.setActualReturnAmount(src.getActualReturnAmount());
		tar.setApplyReturnAmount(src.getApplyReturnAmount());
		tar.setReturnType(src.getReturnType());
		tar.setReturnRemark(src.getReturnRemark());
		tar.setServiceUserId(src.getServiceUserId());
		tar.setServiceDesc(src.getServiceDesc());
		tar.setServiceReturnReason(src.getServiceReturnReason());
		tar.setApplyTime(src.getApplyTime());
		tar.setReturnReason(src.getReturnReason());
		tar.setRefundTime(src.getRefundTime());
		tar.setConsigneeAddress(src.getConsigneeAddress());
		tar.setConsigneeName(src.getConsigneeName());
		tar.setConsigneeMobile(src.getConsigneeMobile());
		tar.setCourierNumber(src.getCourierNumber());
		tar.setLogisticsCompany(src.getLogisticsCompany());
		tar.setRefundConfirmUserId(src.getRefundConfirmUserId());
		tar.setRefundNo(src.getRefundNo());
		tar.setAuditReason(src.getAuditReason());
		tar.setAuditUserId(src.getAuditUserId());
		tar.setAuditUserName(src.getAuditUserName());
		tar.setFreight(src.getFreight());
		tar.setCancelStatus(src.getCancelStatus());
		tar.setAuditTime(src.getAuditTime());
		tar.setType(src.getType());
		tar.setOrderCreateTime(src.getOrderCreateTime());
		tar.setReturnReasonId(src.getReturnReasonId());
		tar.setReturnCode(src.getReturnCode());
		tar.setIsPickUp(src.getIsPickUp());
		tar.setUserCourierNumber(src.getUserCourierNumber());
		tar.setUserLogisticsCompanyId(src.getUserLogisticsCompanyId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoReturnDTO> toDTO(List<SoReturnPO> srcs) {
		if (srcs == null)
			return null;
		List<SoReturnDTO> list = new ArrayList<SoReturnDTO>();
		for (SoReturnPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoReturnPO> toPO(List<SoReturnDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoReturnPO> list = new ArrayList<SoReturnPO>();
		for (SoReturnDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	