package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoInstallmentDTO;
import com.egeo.components.order.po.SoInstallmentPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoInstallmentConverter {
	
	public static SoInstallmentDTO toDTO(SoInstallmentPO src) {
		SoInstallmentDTO tar = new SoInstallmentDTO();
		tar.setId(src.getId());
		tar.setInstallmentCode(src.getInstallmentCode());
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

	public static SoInstallmentPO toPO(SoInstallmentDTO src) {
		SoInstallmentPO tar = new SoInstallmentPO();
		tar.setId(src.getId());
		tar.setInstallmentCode(src.getInstallmentCode());
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

	public static List<SoInstallmentDTO> toDTO(List<SoInstallmentPO> srcs) {
		if (srcs == null)
			return null;
		List<SoInstallmentDTO> list = new ArrayList<SoInstallmentDTO>();
		for (SoInstallmentPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoInstallmentPO> toPO(List<SoInstallmentDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoInstallmentPO> list = new ArrayList<SoInstallmentPO>();
		for (SoInstallmentDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	