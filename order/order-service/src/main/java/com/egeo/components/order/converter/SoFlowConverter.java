package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoFlowDTO;
import com.egeo.components.order.po.SoFlowPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-03-12 18:11:12
 */
public class SoFlowConverter {
	
	public static SoFlowDTO toDTO(SoFlowPO src) {
		if (src == null)
		return null;	
		SoFlowDTO tar = new SoFlowDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setOperate(src.getOperate());
		tar.setOperatorId(src.getOperatorId());
		tar.setCreatTime(src.getCreatTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static SoFlowPO toPO(SoFlowDTO src) {
		if (src == null)
		return null;	
		SoFlowPO tar = new SoFlowPO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setOperate(src.getOperate());
		tar.setOperatorId(src.getOperatorId());
		tar.setCreatTime(src.getCreatTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<SoFlowDTO> toDTO(List<SoFlowPO> srcs) {
		if (srcs == null)
			return null;
		List<SoFlowDTO> list = new ArrayList<SoFlowDTO>();
		for (SoFlowPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoFlowPO> toPO(List<SoFlowDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoFlowPO> list = new ArrayList<SoFlowPO>();
		for (SoFlowDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	