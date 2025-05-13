package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.RefundmentDTO;
import com.egeo.components.order.po.RefundmentPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class RefundmentConverter {
	
	public static RefundmentDTO toDTO(RefundmentPO src) {
		RefundmentDTO tar = new RefundmentDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setReturnCode(src.getReturnCode());
		tar.setRefundmentCode(src.getRefundmentCode());
		tar.setAmount(src.getAmount());
		tar.setChannel(src.getChannel());
		tar.setApplyTime(src.getApplyTime());
		tar.setRefundmentTime(src.getRefundmentTime());
		tar.setVoucher(src.getVoucher());
		tar.setRefundmentStatus(src.getRefundmentStatus());
		tar.setRefundmentType(src.getRefundmentType());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static RefundmentPO toPO(RefundmentDTO src) {
		RefundmentPO tar = new RefundmentPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setReturnCode(src.getReturnCode());
		tar.setRefundmentCode(src.getRefundmentCode());
		tar.setAmount(src.getAmount());
		tar.setChannel(src.getChannel());
		tar.setApplyTime(src.getApplyTime());
		tar.setRefundmentTime(src.getRefundmentTime());
		tar.setVoucher(src.getVoucher());
		tar.setRefundmentStatus(src.getRefundmentStatus());
		tar.setRefundmentType(src.getRefundmentType());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<RefundmentDTO> toDTO(List<RefundmentPO> srcs) {
		if (srcs == null)
			return null;
		List<RefundmentDTO> list = new ArrayList<RefundmentDTO>();
		for (RefundmentPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<RefundmentPO> toPO(List<RefundmentDTO> srcs) {
		if (srcs == null)
			return null;
		List<RefundmentPO> list = new ArrayList<RefundmentPO>();
		for (RefundmentDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	