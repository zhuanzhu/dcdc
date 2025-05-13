package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.components.order.po.SoChildFlowPO;
import com.egeo.components.order.vo.SoOPFlowVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-02-06 18:47:42
 */
public class SoChildFlowConverter {

	public static SoChildFlowDTO toDTO(SoOPFlowVO src) {
		if (src == null)
		return null;	
		SoChildFlowDTO tar = new SoChildFlowDTO();
		tar.setId(src.getId());
		tar.setOperate(src.getOperate());	
		return tar;
	}

	public static SoOPFlowVO toVO(SoChildFlowDTO src) {
		if (src == null)
		return null;	
		SoOPFlowVO tar = new SoOPFlowVO();
		tar.setId(src.getId());
		tar.setOperate(src.getOperate());	
		return tar;
	}

	public static List<SoChildFlowDTO> toDTOs(List<SoOPFlowVO> srcs) {
		if (srcs == null)
			return null;
		List<SoChildFlowDTO> list = new ArrayList<SoChildFlowDTO>();
		for (SoOPFlowVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoOPFlowVO> toVO(List<SoChildFlowDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoOPFlowVO> list = new ArrayList<SoOPFlowVO>();
		for (SoChildFlowDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoChildFlowDTO toDTO(SoChildFlowPO src) {
		if (src == null)
		return null;	
		SoChildFlowDTO tar = new SoChildFlowDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOperate(src.getOperate());
		tar.setDoMail(src.getDoMail());
		tar.setCreatTime(src.getCreatTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoId(src.getSoId());
		tar.setOperatorId(src.getOperatorId());
		return tar;
	}

	public static SoChildFlowPO toPO(SoChildFlowDTO src) {
		if (src == null)
		return null;	
		SoChildFlowPO tar = new SoChildFlowPO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOperate(src.getOperate());
		tar.setDoMail(src.getDoMail());
		tar.setCreatTime(src.getCreatTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoId(src.getSoId());
		tar.setOperatorId(src.getOperatorId());
		return tar;
	}

	public static List<SoChildFlowDTO> toDTO(List<SoChildFlowPO> srcs) {
		if (srcs == null)
			return null;
		List<SoChildFlowDTO> list = new ArrayList<SoChildFlowDTO>();
		for (SoChildFlowPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoChildFlowPO> toPO(List<SoChildFlowDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoChildFlowPO> list = new ArrayList<SoChildFlowPO>();
		for (SoChildFlowDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	