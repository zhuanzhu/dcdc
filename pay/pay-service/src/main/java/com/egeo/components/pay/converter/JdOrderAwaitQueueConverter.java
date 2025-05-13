package com.egeo.components.pay.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.po.JdOrderAwaitQueuePO;
import com.egeo.components.pay.vo.JdOrderAwaitQueueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2019-04-19 16:40:21
 */
public class JdOrderAwaitQueueConverter {

	
	public static JdOrderAwaitQueueDTO toDTO(JdOrderAwaitQueueVO src) {
		if (src == null)
		return null;	
		JdOrderAwaitQueueDTO tar = new JdOrderAwaitQueueDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());	
		tar.setSoChildId(src.getSoChildId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setRandomNumber(src.getRandomNumber());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setIsPayTrue(src.getIsPayTrue());	
		tar.setJdOrderId(src.getJdOrderId());	
		return tar;
	}

	public static JdOrderAwaitQueueVO toVO(JdOrderAwaitQueueDTO src) {
		if (src == null)
		return null;	
		JdOrderAwaitQueueVO tar = new JdOrderAwaitQueueVO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());	
		tar.setSoChildId(src.getSoChildId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setRandomNumber(src.getRandomNumber());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setIsPayTrue(src.getIsPayTrue());	
		tar.setJdOrderId(src.getJdOrderId());	
		return tar;
	}

	public static List<JdOrderAwaitQueueDTO> toDTOs(List<JdOrderAwaitQueueVO> srcs) {
		if (srcs == null)
			return null;
		List<JdOrderAwaitQueueDTO> list = new ArrayList<JdOrderAwaitQueueDTO>();
		for (JdOrderAwaitQueueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdOrderAwaitQueueVO> toVO(List<JdOrderAwaitQueueDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdOrderAwaitQueueVO> list = new ArrayList<JdOrderAwaitQueueVO>();
		for (JdOrderAwaitQueueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static JdOrderAwaitQueueDTO toDTO(JdOrderAwaitQueuePO src) {
		if (src == null)
		return null;	
		JdOrderAwaitQueueDTO tar = new JdOrderAwaitQueueDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOrderCode(src.getOrderCode());
		tar.setRandomNumber(src.getRandomNumber());
		tar.setCreateTime(src.getCreateTime());
		tar.setIsPayTrue(src.getIsPayTrue());
		tar.setJdOrderId(src.getJdOrderId());
		return tar;
	}

	public static JdOrderAwaitQueuePO toPO(JdOrderAwaitQueueDTO src) {
		if (src == null)
		return null;	
		JdOrderAwaitQueuePO tar = new JdOrderAwaitQueuePO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOrderCode(src.getOrderCode());
		tar.setRandomNumber(src.getRandomNumber());
		tar.setCreateTime(src.getCreateTime());
		tar.setIsPayTrue(src.getIsPayTrue());
		tar.setJdOrderId(src.getJdOrderId());
		return tar;
	}

	public static List<JdOrderAwaitQueueDTO> toDTO(List<JdOrderAwaitQueuePO> srcs) {
		if (srcs == null)
			return null;
		List<JdOrderAwaitQueueDTO> list = new ArrayList<JdOrderAwaitQueueDTO>();
		for (JdOrderAwaitQueuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdOrderAwaitQueuePO> toPO(List<JdOrderAwaitQueueDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdOrderAwaitQueuePO> list = new ArrayList<JdOrderAwaitQueuePO>();
		for (JdOrderAwaitQueueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	