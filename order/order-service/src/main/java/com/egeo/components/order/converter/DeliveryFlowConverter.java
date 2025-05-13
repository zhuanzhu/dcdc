package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.DeliveryFlowDTO;
import com.egeo.components.order.po.DeliveryFlowPO;
import com.egeo.components.order.vo.DeliveryFlowVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-02-06 18:48:32
 */
public class DeliveryFlowConverter {

	
	public static DeliveryFlowDTO toDTO(DeliveryFlowVO src) {
		if (src == null)
		return null;	
		DeliveryFlowDTO tar = new DeliveryFlowDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());	
		tar.setOperate(src.getOperate());	
		tar.setDoMail(src.getDoMail());	
		tar.setCreatTime(src.getCreatTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setSoId(src.getSoId());	
		return tar;
	}

	public static DeliveryFlowVO toVO(DeliveryFlowDTO src) {
		if (src == null)
		return null;	
		DeliveryFlowVO tar = new DeliveryFlowVO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());	
		tar.setOperate(src.getOperate());	
		tar.setDoMail(src.getDoMail());	
		tar.setCreatTime(src.getCreatTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setSoId(src.getSoId());	
		return tar;
	}

	public static List<DeliveryFlowDTO> toDTOs(List<DeliveryFlowVO> srcs) {
		if (srcs == null)
			return null;
		List<DeliveryFlowDTO> list = new ArrayList<DeliveryFlowDTO>();
		for (DeliveryFlowVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DeliveryFlowVO> toVO(List<DeliveryFlowDTO> srcs) {
		if (srcs == null)
			return null;
		List<DeliveryFlowVO> list = new ArrayList<DeliveryFlowVO>();
		for (DeliveryFlowDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static DeliveryFlowDTO toDTO(DeliveryFlowPO src) {
		if (src == null)
		return null;	
		DeliveryFlowDTO tar = new DeliveryFlowDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOperate(src.getOperate());
		tar.setDoMail(src.getDoMail());
		tar.setCreatTime(src.getCreatTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoId(src.getSoId());
		return tar;
	}

	public static DeliveryFlowPO toPO(DeliveryFlowDTO src) {
		if (src == null)
		return null;	
		DeliveryFlowPO tar = new DeliveryFlowPO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOperate(src.getOperate());
		tar.setDoMail(src.getDoMail());
		tar.setCreatTime(src.getCreatTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoId(src.getSoId());
		return tar;
	}

	public static List<DeliveryFlowDTO> toDTO(List<DeliveryFlowPO> srcs) {
		if (srcs == null)
			return null;
		List<DeliveryFlowDTO> list = new ArrayList<DeliveryFlowDTO>();
		for (DeliveryFlowPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DeliveryFlowPO> toPO(List<DeliveryFlowDTO> srcs) {
		if (srcs == null)
			return null;
		List<DeliveryFlowPO> list = new ArrayList<DeliveryFlowPO>();
		for (DeliveryFlowDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	