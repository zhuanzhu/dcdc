package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.ReturnPicDTO;
import com.egeo.components.order.po.ReturnPicPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class ReturnPicConverter {
	
	public static ReturnPicDTO toDTO(ReturnPicPO src) {
		ReturnPicDTO tar = new ReturnPicDTO();
		tar.setId(src.getId());
		tar.setReturnId(src.getReturnId());
		tar.setPicUrl(src.getPicUrl());
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static ReturnPicPO toPO(ReturnPicDTO src) {
		ReturnPicPO tar = new ReturnPicPO();
		tar.setId(src.getId());
		tar.setReturnId(src.getReturnId());
		tar.setPicUrl(src.getPicUrl());
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<ReturnPicDTO> toDTO(List<ReturnPicPO> srcs) {
		if (srcs == null)
			return null;
		List<ReturnPicDTO> list = new ArrayList<ReturnPicDTO>();
		for (ReturnPicPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ReturnPicPO> toPO(List<ReturnPicDTO> srcs) {
		if (srcs == null)
			return null;
		List<ReturnPicPO> list = new ArrayList<ReturnPicPO>();
		for (ReturnPicDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	