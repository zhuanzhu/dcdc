package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoReminderDTO;
import com.egeo.components.order.po.SoReminderPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoReminderConverter {
	
	public static SoReminderDTO toDTO(SoReminderPO src) {
		SoReminderDTO tar = new SoReminderDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setReminderDesc(src.getReminderDesc());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantId(src.getMerchantId());
		tar.setUserPhone(src.getUserPhone());
		tar.setPhoneRecord(src.getPhoneRecord());
		tar.setReminderType(src.getReminderType());
		tar.setAskCategoryId(src.getAskCategoryId());
		tar.setAskCategoryDesc(src.getAskCategoryDesc());
		tar.setReminderObject(src.getReminderObject());
		tar.setAskCategoryTwoId(src.getAskCategoryTwoId());
		tar.setStatus(src.getStatus());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoReminderPO toPO(SoReminderDTO src) {
		SoReminderPO tar = new SoReminderPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setReminderDesc(src.getReminderDesc());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantId(src.getMerchantId());
		tar.setUserPhone(src.getUserPhone());
		tar.setPhoneRecord(src.getPhoneRecord());
		tar.setReminderType(src.getReminderType());
		tar.setAskCategoryId(src.getAskCategoryId());
		tar.setAskCategoryDesc(src.getAskCategoryDesc());
		tar.setReminderObject(src.getReminderObject());
		tar.setAskCategoryTwoId(src.getAskCategoryTwoId());
		tar.setStatus(src.getStatus());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoReminderDTO> toDTO(List<SoReminderPO> srcs) {
		if (srcs == null)
			return null;
		List<SoReminderDTO> list = new ArrayList<SoReminderDTO>();
		for (SoReminderPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoReminderPO> toPO(List<SoReminderDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoReminderPO> list = new ArrayList<SoReminderPO>();
		for (SoReminderDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	