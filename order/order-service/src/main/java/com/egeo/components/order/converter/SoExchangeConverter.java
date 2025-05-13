package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoExchangeDTO;
import com.egeo.components.order.po.SoExchangePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:24
 */
public class SoExchangeConverter {
	
	public static SoExchangeDTO toDTO(SoExchangePO src) {
		SoExchangeDTO tar = new SoExchangeDTO();
		tar.setId(src.getId());
		tar.setReturnId(src.getReturnId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setExchangeCode(src.getExchangeCode());
		tar.setProvinceId(src.getProvinceId());
		tar.setProvince(src.getProvince());
		tar.setCityId(src.getCityId());
		tar.setCity(src.getCity());
		tar.setCountyId(src.getCountyId());
		tar.setCounty(src.getCounty());
		tar.setAreaId(src.getAreaId());
		tar.setArea(src.getArea());
		tar.setConsigneeAddress(src.getConsigneeAddress());
		tar.setConsigneeName(src.getConsigneeName());
		tar.setConsigneeMobile(src.getConsigneeMobile());
		tar.setCourierNumber(src.getCourierNumber());
		tar.setLogisticsCompanyId(src.getLogisticsCompanyId());
		tar.setExchangeStatus(src.getExchangeStatus());
		tar.setDeliveryTime(src.getDeliveryTime());
		tar.setDeliveryUserId(src.getDeliveryUserId());
		tar.setConfirmTime(src.getConfirmTime());
		tar.setConfirmUserId(src.getConfirmUserId());
		tar.setReturnCode(src.getReturnCode());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SoExchangePO toPO(SoExchangeDTO src) {
		SoExchangePO tar = new SoExchangePO();
		tar.setId(src.getId());
		tar.setReturnId(src.getReturnId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setExchangeCode(src.getExchangeCode());
		tar.setProvinceId(src.getProvinceId());
		tar.setProvince(src.getProvince());
		tar.setCityId(src.getCityId());
		tar.setCity(src.getCity());
		tar.setCountyId(src.getCountyId());
		tar.setCounty(src.getCounty());
		tar.setAreaId(src.getAreaId());
		tar.setArea(src.getArea());
		tar.setConsigneeAddress(src.getConsigneeAddress());
		tar.setConsigneeName(src.getConsigneeName());
		tar.setConsigneeMobile(src.getConsigneeMobile());
		tar.setCourierNumber(src.getCourierNumber());
		tar.setLogisticsCompanyId(src.getLogisticsCompanyId());
		tar.setExchangeStatus(src.getExchangeStatus());
		tar.setDeliveryTime(src.getDeliveryTime());
		tar.setDeliveryUserId(src.getDeliveryUserId());
		tar.setConfirmTime(src.getConfirmTime());
		tar.setConfirmUserId(src.getConfirmUserId());
		tar.setReturnCode(src.getReturnCode());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoExchangeDTO> toDTO(List<SoExchangePO> srcs) {
		if (srcs == null)
			return null;
		List<SoExchangeDTO> list = new ArrayList<SoExchangeDTO>();
		for (SoExchangePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoExchangePO> toPO(List<SoExchangeDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoExchangePO> list = new ArrayList<SoExchangePO>();
		for (SoExchangeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	