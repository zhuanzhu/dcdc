package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoMessageRelDTO;
import com.egeo.components.order.po.SoMessageRelPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoMessageRelConverter {
	
	public static SoMessageRelDTO toDTO(SoMessageRelPO src) {
		SoMessageRelDTO tar = new SoMessageRelDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setMessageid(src.getMessageid());
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoMessageRelPO toPO(SoMessageRelDTO src) {
		SoMessageRelPO tar = new SoMessageRelPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setMessageid(src.getMessageid());
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoMessageRelDTO> toDTO(List<SoMessageRelPO> srcs) {
		if (srcs == null)
			return null;
		List<SoMessageRelDTO> list = new ArrayList<SoMessageRelDTO>();
		for (SoMessageRelPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoMessageRelPO> toPO(List<SoMessageRelDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoMessageRelPO> list = new ArrayList<SoMessageRelPO>();
		for (SoMessageRelDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	