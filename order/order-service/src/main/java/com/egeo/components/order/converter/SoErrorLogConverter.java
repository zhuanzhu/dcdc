package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoErrorLogDTO;
import com.egeo.components.order.po.SoErrorLogPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoErrorLogConverter {
	
	public static SoErrorLogDTO toDTO(SoErrorLogPO src) {
		SoErrorLogDTO tar = new SoErrorLogDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setType(src.getType());
		tar.setOperId(src.getOperId());
		tar.setOperName(src.getOperName());
		tar.setDescription(src.getDescription());
		tar.setException(src.getException());
		tar.setErrorCode(src.getErrorCode());
		tar.setErrorMessage(src.getErrorMessage());
		tar.setInputParameter(src.getInputParameter());
		tar.setOutputParameter(src.getOutputParameter());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoErrorLogPO toPO(SoErrorLogDTO src) {
		SoErrorLogPO tar = new SoErrorLogPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setType(src.getType());
		tar.setOperId(src.getOperId());
		tar.setOperName(src.getOperName());
		tar.setDescription(src.getDescription());
		tar.setException(src.getException());
		tar.setErrorCode(src.getErrorCode());
		tar.setErrorMessage(src.getErrorMessage());
		tar.setInputParameter(src.getInputParameter());
		tar.setOutputParameter(src.getOutputParameter());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoErrorLogDTO> toDTO(List<SoErrorLogPO> srcs) {
		if (srcs == null)
			return null;
		List<SoErrorLogDTO> list = new ArrayList<SoErrorLogDTO>();
		for (SoErrorLogPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoErrorLogPO> toPO(List<SoErrorLogDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoErrorLogPO> list = new ArrayList<SoErrorLogPO>();
		for (SoErrorLogDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	