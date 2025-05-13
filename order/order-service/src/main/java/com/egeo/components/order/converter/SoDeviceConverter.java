package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.components.order.po.SoDevicePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class SoDeviceConverter {
	
	public static SoDeviceDTO toDTO(SoDevicePO src) {
		if (src == null)
		return null;	
		SoDeviceDTO tar = new SoDeviceDTO();
		tar.setId(src.getId());
		tar.setOrderId(src.getOrderId());
		tar.setDeviceId(src.getDeviceId());
		tar.setIp(src.getIp());
		tar.setVersionCode(src.getVersionCode());
		tar.setOs(src.getOs());
		tar.setPhoneModel(src.getPhoneModel());
		tar.setInterId(src.getInterId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SoDevicePO toPO(SoDeviceDTO src) {
		if (src == null)
		return null;	
		SoDevicePO tar = new SoDevicePO();
		tar.setId(src.getId());
		tar.setOrderId(src.getOrderId());
		tar.setDeviceId(src.getDeviceId());
		tar.setIp(src.getIp());
		tar.setVersionCode(src.getVersionCode());
		tar.setOs(src.getOs());
		tar.setPhoneModel(src.getPhoneModel());
		tar.setInterId(src.getInterId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoDeviceDTO> toDTO(List<SoDevicePO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeviceDTO> list = new ArrayList<SoDeviceDTO>();
		for (SoDevicePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoDevicePO> toPO(List<SoDeviceDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoDevicePO> list = new ArrayList<SoDevicePO>();
		for (SoDeviceDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	