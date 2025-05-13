package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.PayTypeDTO;
import com.egeo.components.user.po.PayTypePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-08-30 17:15:01
 */
public class PayTypeConverter {
	
	public static PayTypeDTO toDTO(PayTypePO src) {
		if (src == null)
		return null;	
		PayTypeDTO tar = new PayTypeDTO();
		tar.setId(src.getId());
		tar.setPayTypeCode(src.getPayTypeCode());
		tar.setPayTypeName(src.getPayTypeName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setLogImageUrl(src.getLogImageUrl());
		tar.setPayTypeRemarks(src.getPayTypeRemarks());
		return tar;
	}

	public static PayTypePO toPO(PayTypeDTO src) {
		if (src == null)
		return null;	
		PayTypePO tar = new PayTypePO();
		tar.setId(src.getId());
		tar.setPayTypeCode(src.getPayTypeCode());
		tar.setPayTypeName(src.getPayTypeName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setLogImageUrl(src.getLogImageUrl());
		tar.setPayTypeRemarks(src.getPayTypeRemarks());
		return tar;
	}

	public static List<PayTypeDTO> toDTO(List<PayTypePO> srcs) {
		if (srcs == null)
			return null;
		List<PayTypeDTO> list = new ArrayList<PayTypeDTO>();
		for (PayTypePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PayTypePO> toPO(List<PayTypeDTO> srcs) {
		if (srcs == null)
			return null;
		List<PayTypePO> list = new ArrayList<PayTypePO>();
		for (PayTypeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	