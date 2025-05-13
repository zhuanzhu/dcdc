package com.egeo.components.pay.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.po.AwaitQueuePO;
import com.egeo.components.pay.vo.AwaitQueueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-02-09 09:49:36
 */
public class AwaitQueueConverter {

	public static AwaitQueueDTO toDTO(AwaitQueueVO src) {
		if (src == null)
		return null;	
		AwaitQueueDTO tar = new AwaitQueueDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setRandomNumber(src.getRandomNumber());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCashPayType(src.getCashPayType());	
		return tar;
	}

	public static AwaitQueueVO toVO(AwaitQueueDTO src) {
		if (src == null)
		return null;	
		AwaitQueueVO tar = new AwaitQueueVO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setRandomNumber(src.getRandomNumber());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCashPayType(src.getCashPayType());	
		return tar;
	}

	public static List<AwaitQueueDTO> toDTOs(List<AwaitQueueVO> srcs) {
		if (srcs == null)
			return null;
		List<AwaitQueueDTO> list = new ArrayList<AwaitQueueDTO>();
		for (AwaitQueueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AwaitQueueVO> toVO(List<AwaitQueueDTO> srcs) {
		if (srcs == null)
			return null;
		List<AwaitQueueVO> list = new ArrayList<AwaitQueueVO>();
		for (AwaitQueueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static AwaitQueueDTO toDTO(AwaitQueuePO src) {
		if (src == null)
		return null;	
		AwaitQueueDTO tar = new AwaitQueueDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setOrderCode(src.getOrderCode());
		tar.setRandomNumber(src.getRandomNumber());
		tar.setCreateTime(src.getCreateTime());
		tar.setCashPayType(src.getCashPayType());
		tar.setIsPayTrue(src.getIsPayTrue());
		return tar;
	}

	public static AwaitQueuePO toPO(AwaitQueueDTO src) {
		if (src == null)
		return null;	
		AwaitQueuePO tar = new AwaitQueuePO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setOrderCode(src.getOrderCode());
		tar.setRandomNumber(src.getRandomNumber());
		tar.setCreateTime(src.getCreateTime());
		tar.setCashPayType(src.getCashPayType());
		tar.setIsPayTrue(src.getIsPayTrue());
		return tar;
	}

	public static List<AwaitQueueDTO> toDTO(List<AwaitQueuePO> srcs) {
		if (srcs == null)
			return null;
		List<AwaitQueueDTO> list = new ArrayList<AwaitQueueDTO>();
		for (AwaitQueuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AwaitQueuePO> toPO(List<AwaitQueueDTO> srcs) {
		if (srcs == null)
			return null;
		List<AwaitQueuePO> list = new ArrayList<AwaitQueuePO>();
		for (AwaitQueueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	