package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoInstallmentApprovalDTO;
import com.egeo.components.order.po.SoInstallmentApprovalPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoInstallmentApprovalConverter {
	
	public static SoInstallmentApprovalDTO toDTO(SoInstallmentApprovalPO src) {
		SoInstallmentApprovalDTO tar = new SoInstallmentApprovalDTO();
		tar.setId(src.getId());
		tar.setModifyPriceId(src.getModifyPriceId());
		tar.setInstallment_id(src.getInstallment_id());
		tar.setBatchNum(src.getBatchNum());
		tar.setOrderCode(src.getOrderCode());
		tar.setPayPrecondition(src.getPayPrecondition());
		tar.setPayStatus(src.getPayStatus());
		tar.setPayPrice(src.getPayPrice());
		tar.setCId(src.getCId());
		tar.setCName(src.getCName());
		tar.setPayTime(src.getPayTime());
		tar.setPayComment(src.getPayComment());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static SoInstallmentApprovalPO toPO(SoInstallmentApprovalDTO src) {
		SoInstallmentApprovalPO tar = new SoInstallmentApprovalPO();
		tar.setId(src.getId());
		tar.setModifyPriceId(src.getModifyPriceId());
		tar.setInstallment_id(src.getInstallment_id());
		tar.setBatchNum(src.getBatchNum());
		tar.setOrderCode(src.getOrderCode());
		tar.setPayPrecondition(src.getPayPrecondition());
		tar.setPayStatus(src.getPayStatus());
		tar.setPayPrice(src.getPayPrice());
		tar.setCId(src.getCId());
		tar.setCName(src.getCName());
		tar.setPayTime(src.getPayTime());
		tar.setPayComment(src.getPayComment());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<SoInstallmentApprovalDTO> toDTO(List<SoInstallmentApprovalPO> srcs) {
		if (srcs == null)
			return null;
		List<SoInstallmentApprovalDTO> list = new ArrayList<SoInstallmentApprovalDTO>();
		for (SoInstallmentApprovalPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoInstallmentApprovalPO> toPO(List<SoInstallmentApprovalDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoInstallmentApprovalPO> list = new ArrayList<SoInstallmentApprovalPO>();
		for (SoInstallmentApprovalDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	